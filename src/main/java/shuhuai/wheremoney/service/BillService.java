package shuhuai.wheremoney.service;

import shuhuai.wheremoney.entity.Bill;
import shuhuai.wheremoney.type.BillType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface BillService {
    void addBill(Integer bookId, Integer inAssetId, Integer outAssetId, Integer billCategoryId, BillType type, BigDecimal amount, Timestamp time, String remark);

    List<Bill> getBillByBook(Integer bookId);

    List<Bill> getBillByBookTime(Integer bookId, Timestamp startTime, Timestamp endTime);

    Bill getBill(Integer id);

    List<Map<String,Object>> getCategoryPayStatisticTime(Integer bookId, Timestamp startTime, Timestamp endTime);

    List<Map<String, Object>> getCategoryIncomeStatisticTime(Integer bookId, Timestamp startTime, Timestamp endTime);
}