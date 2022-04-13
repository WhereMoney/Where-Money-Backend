package shuhuai.wheremoney.entity;

import lombok.AllArgsConstructor;
import shuhuai.wheremoney.type.AssetType;

import java.math.BigDecimal;

@AllArgsConstructor
public class Asset {
    private Integer id;
    private Integer userId;
    private AssetType type;
    private BigDecimal balance;
    private String assetName;
    private Integer billDate;
    private Integer repayDate;
    private BigDecimal quota;
    private Boolean inTotal;


    public Asset(Integer userId, AssetType type, BigDecimal balance, String assetName, Integer billDate, Integer repayDate, BigDecimal quota, Boolean inTotal) {
        this.userId = userId;
        this.type = type;
        this.balance = balance;
        this.assetName = assetName;
        this.billDate = billDate;
        this.repayDate = repayDate;
        this.quota = quota;
        this.inTotal = inTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public AssetType getType() {
        return type;
    }

    public void setType(AssetType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Integer getBillDate() {
        return billDate;
    }

    public void setBillDate(Integer billDate) {
        this.billDate = billDate;
    }

    public Integer getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Integer repayDate) {
        this.repayDate = repayDate;
    }

    public BigDecimal getQuota() {
        return quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    public Boolean getInTotal() {
        return inTotal;
    }

    public void setInTotal(Boolean inTotal) {
        this.inTotal = inTotal;
    }
}
