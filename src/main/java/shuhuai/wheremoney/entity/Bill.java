package shuhuai.wheremoney.entity;

import shuhuai.wheremoney.type.BillType;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

public class Bill {
    private Integer id;
    private Integer bookId;
    private Integer inAssetId;
    private Integer outAssetId;
    private Integer billCategoryId;
    private BillType type;
    private BigDecimal amount;
    private Date time;
    private String remark;

    public Bill(Integer bookId, Integer inAssetId, Integer outAssetId, Integer billCategoryId, BillType type, BigDecimal amount, Date time, String remark) {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "bookId=" + bookId +
                ", inAssetId=" + inAssetId +
                ", outAssetId=" + outAssetId +
                ", billCategoryId=" + billCategoryId +
                ", type=" + type +
                ", amount=" + amount +
                ", time=" + time +
                ", remark='" + remark + '\'' +
                '}';
    }
}
