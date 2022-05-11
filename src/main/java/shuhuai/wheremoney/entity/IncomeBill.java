package shuhuai.wheremoney.entity;

import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@ToString
public class IncomeBill extends BaseBill {
    private Integer incomeAssetId;
    private Integer billCategoryId;

    public IncomeBill(Integer bookId, Integer incomeAssetId, Integer billCategoryId, BigDecimal amount, Timestamp billTime, String remark) {
        super(bookId, amount, billTime, remark);
        this.incomeAssetId = incomeAssetId;
        this.billCategoryId = billCategoryId;
    }

    public Integer getIncomeAssetId() {
        return incomeAssetId;
    }

    public void setIncomeAssetId(Integer incomeAssetId) {
        this.incomeAssetId = incomeAssetId;
    }

    public Integer getBillCategoryId() {
        return billCategoryId;
    }

    public void setBillCategoryId(Integer billCategoryId) {
        this.billCategoryId = billCategoryId;
    }
}