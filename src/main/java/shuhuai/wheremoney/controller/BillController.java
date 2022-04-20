package shuhuai.wheremoney.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shuhuai.wheremoney.entity.Asset;
import shuhuai.wheremoney.entity.Bill;
import shuhuai.wheremoney.response.Response;
import shuhuai.wheremoney.response.bill.CategoryStatisticResponse;
import shuhuai.wheremoney.response.bill.GetAllBillResponse;
import shuhuai.wheremoney.response.bill.GetBillResponse;
import shuhuai.wheremoney.service.AssetService;
import shuhuai.wheremoney.service.BillCategoryService;
import shuhuai.wheremoney.service.BillService;
import shuhuai.wheremoney.type.BillType;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bill")
@Api(tags = "账单管理")
@Slf4j
public class BillController extends BaseController {
    @Resource
    private BillService billService;
    @Resource
    private AssetService assetService;
    @Resource
    private BillCategoryService billCategoryService;

    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "token过期"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @RequestMapping(value = "/add-bill", method = RequestMethod.POST)
    @ApiOperation(value = "新建账单")
    public Response<Object> addBill(@RequestParam Integer bookId, Integer inAssetId, Integer outAssetId,
                                    Integer billCategoryId, @RequestParam BillType type, @RequestParam BigDecimal amount,
                                    @RequestParam String time, String remark) {
        Timestamp formatDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            formatDate = Timestamp.valueOf(sdf.format(sdf.parse(time)));
        } catch (ParseException error) {
            error.printStackTrace();
        }
        billService.addBill(bookId, inAssetId, outAssetId, billCategoryId, type, amount, formatDate, remark);
        if (inAssetId != null) {
            Asset inAsset = assetService.getAsset(inAssetId);
            int compare = amount.compareTo(new BigDecimal("0.00"));
            if (compare < 0) {
                amount = new BigDecimal("0.00").subtract(amount);
            }// amount 负转正
            inAsset.setBalance(inAsset.getBalance().add(amount)); //资产中更新
            assetService.updateAsset(inAsset);
        }
        if (outAssetId != null) {
            Asset outAsset = assetService.getAsset(outAssetId);
            int compare = amount.compareTo(new BigDecimal("0.00"));
            if (compare > 0) {
                amount = new BigDecimal("0.00").subtract(amount);
            }// amount 正转负
            outAsset.setBalance(outAsset.getBalance().add(amount)); //资产中更新
            assetService.updateAsset(outAsset);
        }
        Response<Object> response = new Response<>(200, "新建账单成功", null);
        log.info("/api/bill/add-bill：" + response.getMessage());
        return response;
    }

    private String[] idToString(Bill bill) {
        String billCategory = bill.getBillCategoryId() == null ? null : billCategoryService.getBillCategory(bill.getBillCategoryId()).getBillCategoryName();
        String inAsset = bill.getInAssetId() == null ? null : assetService.getAsset(bill.getInAssetId()).getAssetName();
        String outAsset = bill.getOutAssetId() == null ? null : assetService.getAsset(bill.getOutAssetId()).getAssetName();
        return new String[]{billCategory, inAsset, outAsset};
    }

    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "token过期"),
            @ApiResponse(code = 422, message = "参数错误"),
    })
    @RequestMapping(value = "/get-bill", method = RequestMethod.GET)
    @ApiOperation(value = "获得账单")
    public Response<GetBillResponse> getBill(@RequestParam Integer id) {
        Bill bill = billService.getBill(id);
        String[] strings = bill == null ? null : idToString(bill);
        Response<GetBillResponse> response = new Response<>(200, "获得账单成功", bill == null ? null : new GetBillResponse(bill, strings[0], strings[1], strings[2]));
        log.info("/api/bill/get-bill：" + response.getMessage());
        return response;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "token过期"),
            @ApiResponse(code = 422, message = "参数错误"),
    })
    @RequestMapping(value = "/get-all-bill", method = RequestMethod.GET)
    @ApiOperation(value = "获得指定账本的所有账单")
    public Response<GetAllBillResponse> getBillByBook(@RequestParam Integer bookId) {
        List<Bill> billList = billService.getBillByBook(bookId);
        List<GetBillResponse> billResponseList = new ArrayList<>();
        for (Bill bill : billList) {
            String[] strings = idToString(bill);
            billResponseList.add(new GetBillResponse(bill, strings[0], strings[1], strings[2]));
        }
        Response<GetAllBillResponse> response = new Response<>(200, "获得指定账本的所有账单成功", new GetAllBillResponse(billResponseList));
        log.info("/api/bill/get-all-bill：" + response.getMessage());
        return response;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "token过期"),
            @ApiResponse(code = 422, message = "参数错误"),
    })
    @RequestMapping(value = "/get-all-bill-time", method = RequestMethod.GET)
    @ApiOperation(value = "获得指定账本的所有账单时间")
    public Response<GetAllBillResponse> getBillByBookTIme(@RequestParam Integer bookId, @RequestParam Timestamp startTime, @RequestParam Timestamp endTime) {
        List<Bill> billList = billService.getBillByBookTime(bookId, startTime, endTime);
        List<GetBillResponse> getBillResponseList = new ArrayList<>();
        for (Bill bill : billList) {
            String[] strings = idToString(bill);
            getBillResponseList.add(new GetBillResponse(bill, strings[0], strings[1], strings[2]));
        }
        Response<GetAllBillResponse> response = new Response<>(200, "获得指定账本的所有账单时间成功", new GetAllBillResponse(getBillResponseList));
        log.info("/api/bill/get-all-bill-time：" + response.getMessage());
        return response;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "token过期"),
            @ApiResponse(code = 422, message = "参数错误"),
    })
    @RequestMapping(value = "/category-statistic-time", method = RequestMethod.GET)
    @ApiOperation(value = "分类统计指定账本的指定时间段的账单")
    public Response<CategoryStatisticResponse> getCategoryStatisticTime(@RequestParam Integer bookId, @RequestParam Timestamp startTime, @RequestParam Timestamp endTime) {
        List<Map<String, Object>> payStatistic = billService.getCategoryPayStatisticTime(bookId, startTime, endTime);
        List<Map<String, Object>> incomeStatistic = billService.getCategoryIncomeStatisticTime(bookId, startTime, endTime);
        Response<CategoryStatisticResponse> response = new Response<>(200, "分类统计指定账本的指定时间段的账单成功",
                new CategoryStatisticResponse(payStatistic, incomeStatistic));
        log.info("/api/bill/category-statistic-time：" + response.getMessage());
        return response;
    }
}