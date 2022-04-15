package shuhuai.wheremoney.entity;

import shuhuai.wheremoney.type.BillType;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Bill {
    private Integer id;
    private Integer bookId;
    private Integer inAssetId;
    private Integer outAssetId;
    private Integer billCategoryId;
    private BillType type;
    private BigDecimal amount;
    private Timestamp time;
    private String remark;

    public Bill(Integer bookId, Integer inAssetId, Integer outAssetId, Integer billCategoryId, BillType type, BigDecimal amount, Timestamp time, String remark) {
        this.bookId = bookId;
        this.inAssetId = inAssetId;
        this.outAssetId = outAssetId;
        this.billCategoryId = billCategoryId;
        this.type = type;
        this.amount = amount;
        this.time = time;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getInAssetId() {
        return inAssetId;
    }

    public void setInAssetId(Integer inAssetId) {
        this.inAssetId = inAssetId;
    }

    public Integer getOutAssetId() {
        return outAssetId;
    }

    public void setOutAssetId(Integer outAssetId) {
        this.outAssetId = outAssetId;
    }

    public Integer getBillCategoryId() {
        return billCategoryId;
    }

    public void setBillCategoryId(Integer billCategoryId) {
        this.billCategoryId = billCategoryId;
    }

    public BillType getType() {
        return type;
    }

    public void setType(BillType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
