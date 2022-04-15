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
import shuhuai.wheremoney.response.asset.GetAllAssetResponse;
import shuhuai.wheremoney.response.bill.GetBillResponse;
import shuhuai.wheremoney.service.AssetService;
import shuhuai.wheremoney.service.BillService;
import shuhuai.wheremoney.type.AssetType;
import shuhuai.wheremoney.type.BillType;
import shuhuai.wheremoney.utils.TokenValidator;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/bill")
@Api(tags = "账单管理")
@Slf4j
public class BillController extends BaseController{
    @Resource
    private BillService billService;

    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "token过期"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @RequestMapping(value = "/add-bill", method = RequestMethod.POST)
    @ApiOperation(value = "新建账单")
    public Response<Object> addBill(@RequestParam Integer bookId, Integer inAssetId, Integer outAssetId,
                                    @RequestParam Integer billCategoryId, @RequestParam BillType type,@RequestParam BigDecimal amount,
                                    @RequestParam String billDate, String remark){
        // billDate: yyyy-mm-dd
        Date formateDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            formateDate = sdf.parse(billDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        billService.addBill(bookId,inAssetId,outAssetId,billCategoryId,type,amount,formateDate,remark);
        Response<Object> response = new Response<>(200, "新建账单成功", null);
        log.info("/api/user/add-bill：" + response.getMessage());
        return response;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "token过期"),
            @ApiResponse(code = 422, message = "参数错误"),
    })
    @RequestMapping(value = "/get-bills-by-book", method = RequestMethod.GET)
    @ApiOperation(value = "获得指定账本的所有账单")
    public Response<GetBillResponse> getBillByBook(@RequestParam Integer bookId) {
        List<Bill> billList = billService.getBillByBook(bookId);
        Response<GetBillResponse> response = new Response<>(200, "获得账单成功", new GetBillResponse(billList));
        log.info("/api/user/get-bill-by-book：" + response.getMessage());
        return response;
    }
}
