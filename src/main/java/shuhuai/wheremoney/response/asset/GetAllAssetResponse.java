package shuhuai.wheremoney.response.asset;

import shuhuai.wheremoney.entity.Asset;

import java.math.BigDecimal;
import java.util.List;

public class GetAllAssetResponse {
    private List<Asset> assetList;
    private BigDecimal netAsset;
    private BigDecimal totalAsset;
    private BigDecimal totalLiabilities;

    public GetAllAssetResponse(List<Asset> assetList) {
        this.assetList = assetList;
        this.netAsset = new BigDecimal(0);
        this.totalLiabilities = new BigDecimal(0);
        for (Asset asset : assetList) {
            this.netAsset = this.netAsset.add(asset.getBalance());
            if (asset.getType().getType().equals("信用卡")) {
                this.totalLiabilities = this.totalLiabilities.add(asset.getBalance());
            }
        }
        this.totalAsset = this.netAsset.add(this.totalLiabilities);
    }

    public List<Asset> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<Asset> assetList) {
        this.assetList = assetList;
    }

    public BigDecimal getNetAsset() {
        return netAsset;
    }

    public void setNetAsset(BigDecimal netAsset) {
        this.netAsset = netAsset;
    }

    public BigDecimal getTotalAsset() {
        return totalAsset;
    }

    public void setTotalAsset(BigDecimal totalAsset) {
        this.totalAsset = totalAsset;
    }

    public BigDecimal getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(BigDecimal totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }
}