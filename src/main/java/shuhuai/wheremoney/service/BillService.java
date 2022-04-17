package shuhuai.wheremoney.service;

import shuhuai.wheremoney.entity.Bill;
import shuhuai.wheremoney.type.BillType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface BillService {
    void addBill(Integer bookId, Integer inAssetId, Integer outAssetId, Integer billCategoryId, BillType type, BigDecimal amount, Timestamp time, String remark);

    List<Bill> getBillByBook(Integer bookId);

    Bill getBill(Integer id);
}