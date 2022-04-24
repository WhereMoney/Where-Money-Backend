package shuhuai.wheremoney.entity;

import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@ToString
public class RefundBill extends BaseBill {
    private Integer payBillId;
    private Integer refundAssetId;

    public RefundBill(Integer id, Integer bookId, Integer payBillId, Integer refundAssetId, BigDecimal amount, Timestamp billTime, String remark) {
        super(id, bookId, amount, billTime, remark);
        this.payBillId = payBillId;
        this.refundAssetId = refundAssetId;
    }

    public Integer getPayBillId() {
        return payBillId;
    }

    public void setPayBillId(Integer payBillId) {
        this.payBillId = payBillId;
    }

    public Integer getRefundAssetId() {
        return refundAssetId;
    }

    public void setRefundAssetId(Integer refundAssetId) {
        this.refundAssetId = refundAssetId;
    }
}