package shuhuai.wheremoney.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.wheremoney.entity.Asset;
import shuhuai.wheremoney.mapper.AssetMapper;
import shuhuai.wheremoney.mapper.UserMapper;
import shuhuai.wheremoney.service.AssetService;
import shuhuai.wheremoney.service.excep.common.ParamsException;
import shuhuai.wheremoney.service.excep.common.ServerException;
import shuhuai.wheremoney.type.AssetType;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service

public class AssetServiceImpl implements AssetService {
    @Resource
    private AssetMapper assetMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public void addAsset(String userName, String assetName, BigDecimal balance, AssetType type, Integer billDate, Integer repayDate, BigDecimal quota, Boolean inTotal) {
        if (userName == null || assetName == null || balance == null || type == null || inTotal == null) {
            throw new ParamsException("参数错误");
        }
        Integer userId = userMapper.selectUserByUserName(userName).getId();
        Asset asset = new Asset(userId, type, balance, assetName, billDate, repayDate, quota, inTotal);
        Integer result = assetMapper.insertAssetSelective(asset);
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public List<Asset> getAllAsset(String userName) {
        if (userName == null) {
            throw new ParamsException("参数错误");
        }
        return assetMapper.selectAssetByUserId(userMapper.selectUserByUserName(userName).getId());
    }

    @Override
    public Asset getAsset(Integer id) {
        if (id == null) {
            throw new ParamsException("参数错误");
        }
        return assetMapper.selectAssetById(id);
    }

    @Override
    public void updateAsset(Asset asset) {
        Integer result = assetMapper.updateAssetSelectiveById(asset);
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }
}