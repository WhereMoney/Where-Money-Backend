package shuhuai.wheremoney.entity;

import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@ToString
public class TransferBill extends BaseBill {
    private Integer inAssetId;
    private Integer outAssetId;
    private BigDecimal tranferFee;

    public TransferBill(Integer id, Integer bookId, Integer inAssetId, Integer outAssetId, BigDecimal amount, BigDecimal tranferFee, Timestamp billTime, String remark) {
        super(id, bookId, amount, billTime, remark);
        this.inAssetId = inAssetId;
        this.outAssetId = outAssetId;
        this.tranferFee = tranferFee;
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

    public BigDecimal getTranferFee() {
        return tranferFee;
    }

    public void setTranferFee(BigDecimal tranferFee) {
        this.tranferFee = tranferFee;
    }
}
