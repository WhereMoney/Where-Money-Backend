package shuhuai.wheremoney.entity;

import shuhuai.wheremoney.type.BillType;

import java.text.DecimalFormat;
import java.util.Date;

public class Bill {
    private Integer id;
    private Integer bookId;
    private Integer inAssetId;
    private Integer outAssetId;
    private Integer billCategoryId;
    private BillType type;
    private DecimalFormat amount;
    private Date time;
    private String remark;

    public Bill() {
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

    public DecimalFormat getAmount() {
        return amount;
    }

    public void setAmount(DecimalFormat amount) {
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
}
