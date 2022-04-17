package shuhuai.wheremoney.response.bill;

import shuhuai.wheremoney.entity.Bill;
import shuhuai.wheremoney.type.BillType;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class GetBillResponse {
    private Integer id;
    private String billCategory;
    private String inAsset;
    private String outAsset;
    private BillType type;
    private BigDecimal amount;
    private String remark;
    private Timestamp time;

    public GetBillResponse(Integer id, String billCategory, String inAsset, String outAsset, BillType type, BigDecimal amount, String remark, Timestamp time) {
        this.id = id;
        this.billCategory = billCategory;
        this.inAsset = inAsset;
        this.outAsset = outAsset;
        this.type = type;
        this.amount = amount;
        this.remark = remark;
        this.time = time;
    }

    public GetBillResponse(Bill bill, String billCategory, String inAsset, String outAsset) {
        this.id = bill.getId();
        this.billCategory = billCategory;
        this.inAsset = inAsset;
        this.outAsset = outAsset;
        this.type = bill.getType();
        this.amount = bill.getAmount();
        this.remark = bill.getRemark();
        this.time = bill.getTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillCategory() {
        return billCategory;
    }

    public void setBillCategory(String billCategory) {
        this.billCategory = billCategory;
    }

    public String getInAsset() {
        return inAsset;
    }

    public void setInAsset(String inAsset) {
        this.inAsset = inAsset;
    }

    public String getOutAsset() {
        return outAsset;
    }

    public void setOutAsset(String outAsset) {
        this.outAsset = outAsset;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}