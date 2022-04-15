package shuhuai.wheremoney.service;

import shuhuai.wheremoney.entity.Asset;
import shuhuai.wheremoney.type.AssetType;

import java.math.BigDecimal;
import java.util.List;

public interface AssetService {
    void addAsset(String userName, String assetName, BigDecimal balance, AssetType type, Integer billDate, Integer repayDate, BigDecimal quota, Boolean inTotal);

    List<Asset> getAllAsset(String userName);

    Asset getAsset(Integer id);

    void updateAsset(Asset asset);
}
