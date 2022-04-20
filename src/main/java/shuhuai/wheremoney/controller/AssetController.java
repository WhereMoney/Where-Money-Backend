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
import shuhuai.wheremoney.response.Response;
import shuhuai.wheremoney.response.asset.GetAllAssetResponse;
import shuhuai.wheremoney.response.asset.GetAssetResponse;
import shuhuai.wheremoney.service.AssetService;
import shuhuai.wheremoney.type.AssetType;
import shuhuai.wheremoney.utils.RequestGetter;
import shuhuai.wheremoney.utils.TokenValidator;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/asset")
@Api(tags = "资产管理")
@Slf4j
public class AssetController extends BaseController {
    @Resource
    private AssetService assetService;

    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "token过期"),
            @ApiResponse(code = 422, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @RequestMapping(value = "/add-asset", method = RequestMethod.POST)
    @ApiOperation(value = "新建资产")
    public Response<Object> addAsset(@RequestParam String assetName, @RequestParam BigDecimal balance, @RequestParam AssetType type,
                                     Integer billDate, Integer repayDate, BigDecimal quota, @RequestParam Boolean inTotal) {
        String userName = TokenValidator.getUser().get("userName");
        assetService.addAsset(userName, assetName, balance, type, billDate, repayDate, quota, inTotal);
        Response<Object> response = new Response<>(200, "新建资产成功", null);
        log.info(RequestGetter.getRequestUrl() + "：" + response.getMessage());
        return response;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "token过期"),
            @ApiResponse(code = 422, message = "参数错误"),
    })
    @RequestMapping(value = "/get-all-asset", method = RequestMethod.GET)
    @ApiOperation(value = "获得所有资产")
    public Response<GetAllAssetResponse> getAllAsset() {
        String userName = TokenValidator.getUser().get("userName");
        List<Asset> assetList = assetService.getAllAsset(userName);
        Response<GetAllAssetResponse> response = new Response<>(200, "获得资产成功", new GetAllAssetResponse(assetList));
        log.info(RequestGetter.getRequestUrl() + "：" + response.getMessage());
        return response;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "token过期"),
            @ApiResponse(code = 422, message = "参数错误"),
    })
    @RequestMapping(value = "/get-asset", method = RequestMethod.GET)
    @ApiOperation(value = "获得资产")
    public Response<GetAssetResponse> getAsset(@RequestParam Integer id) {
        Asset asset = assetService.getAsset(id);
        Response<GetAssetResponse> response = new Response<>(200, "获得资产成功", new GetAssetResponse(asset));
        log.info(RequestGetter.getRequestUrl() + "：" + response.getMessage());
        return response;
    }
}