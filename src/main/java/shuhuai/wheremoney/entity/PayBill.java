package shuhuai.wheremoney.entity;

import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@ToString
public class PayBill extends BaseBill {
    private Integer payAssetId;
    private Integer billCategoryId;

    public PayBill(Integer id, Integer bookId, Integer payAssetId, Integer billCategoryId, BigDecimal amount, Timestamp billTime, String remark) {
        super(id, bookId, amount, billTime, remark);
        this.payAssetId = payAssetId;
        this.billCategoryId = billCategoryId;
    }

    public Integer getPayAssetId() {
        return payAssetId;
    }

    public void setPayAssetId(Integer payAssetId) {
        this.payAssetId = payAssetId;
    }

    public Integer getBillCategoryId() {
        return billCategoryId;
    }

    public void setBillCategoryId(Integer billCategoryId) {
        this.billCategoryId = billCategoryId;
    }
}