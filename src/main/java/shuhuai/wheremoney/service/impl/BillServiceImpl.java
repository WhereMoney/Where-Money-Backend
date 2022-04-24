package shuhuai.wheremoney.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.wheremoney.entity.*;
import shuhuai.wheremoney.mapper.*;
import shuhuai.wheremoney.service.BillService;
import shuhuai.wheremoney.service.excep.common.ParamsException;
import shuhuai.wheremoney.service.excep.common.ServerException;
import shuhuai.wheremoney.type.BillType;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BillServiceImpl implements BillService {
    @Resource
    private BillMapper billMapper;
    @Resource
    private BillCategoryMapper billCategoryMapper;
    @Resource
    private PayBillMapper payBillMapper;
    @Resource
    private IncomeBillMapper incomeBillMapper;
    @Resource
    private TransferBillMapper transferBillMapper;
    @Resource
    private RefundBillMapper refundBillMapper;

    @Override
    public void addBill(Integer bookId, Integer inAssetId, Integer outAssetId, Integer billCategoryId, BillType type, BigDecimal amount, Timestamp time, String remark) {
        if (bookId == null || type == null || amount == null) {
            throw new ParamsException("参数错误");
        }
        if (inAssetId == null && outAssetId == null) {
            throw new ParamsException("参数错误");
        }
        if (type == BillType.支出 && inAssetId != null) {
            throw new ParamsException("参数错误");
        }
        if (type == BillType.收入 && outAssetId != null) {
            throw new ParamsException("参数错误");
        }
        if (type == BillType.转账 && (inAssetId == null || outAssetId == null)) {
            throw new ParamsException("参数错误");
        }
        Bill bill = new Bill(bookId, inAssetId, outAssetId, billCategoryId, type, amount, time, remark);
        Integer result = billMapper.insertBillSelective(bill);
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    private void sortByBillTime(List<BaseBill> bills) {
        for (int i = 0; i < bills.size() - 1; i++) {
            for (int j = i + 1; j < bills.size(); j++) {
                if (bills.get(i).getBillTime().before(bills.get(j).getBillTime())) {
                    BaseBill temp = bills.get(i);
                    bills.set(i, bills.get(j));
                    bills.set(j, temp);
                }
            }
        }
    }

    @Override
    public List<BaseBill> getBillByBook(Integer bookId) {
        if (bookId == null) {
            throw new ParamsException("参数错误");
        }
        List<BaseBill> bills = new ArrayList<>();
        bills.addAll(payBillMapper.selectPayBillByBookId(bookId));
        bills.addAll(incomeBillMapper.selectIncomeBillByBookId(bookId));
        bills.addAll(transferBillMapper.selectTransferBillByBookId(bookId));
        bills.addAll(refundBillMapper.selectRefundBillByBookId(bookId));
        sortByBillTime(bills);
        return bills;
    }

    @Override
    public List<BaseBill> getBillByBookTime(Integer bookId, Timestamp startTime, Timestamp endTime) {
        if (bookId == null || startTime == null || endTime == null) {
            throw new ParamsException("参数错误");
        }
        List<BaseBill> bills = new ArrayList<>();
        bills.addAll(payBillMapper.selectPayBillByBookIdTime(bookId, startTime, endTime));
        bills.addAll(incomeBillMapper.selectIncomeBillByBookIdTime(bookId, startTime, endTime));
        bills.addAll(transferBillMapper.selectTransferBillByBookIdTime(bookId, startTime, endTime));
        bills.addAll(refundBillMapper.selectRefundBillByBookIdTime(bookId, startTime, endTime));
        sortByBillTime(bills);
        return bills;
    }

    @Override
    public BaseBill getBill(Integer id, BillType type) {
        if (id == null || type == null) {
            throw new ParamsException("参数错误");
        }
        return switch (type) {
            case 支出 -> payBillMapper.selectPayBillById(id);
            case 收入 -> incomeBillMapper.selectIncomeBillById(id);
            case 转账 -> transferBillMapper.selectTransferBillById(id);
            case 退款 -> refundBillMapper.selectRefundBillById(id);
        };
    }

    private List<Map<String, Object>> categoryStatistic(List<Bill> bills) {
        Map<Integer, BigDecimal> temp = new java.util.HashMap<>();
        BigDecimal total = BigDecimal.ZERO;
        for (Bill bill : bills) {
            if (temp.containsKey(bill.getBillCategoryId())) {
                temp.replace(bill.getBillCategoryId(), temp.get(bill.getBillCategoryId()).add(bill.getAmount()));
            } else {
                temp.put(bill.getBillCategoryId(), bill.getAmount());
            }
            total = total.add(bill.getAmount());
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<Integer, BigDecimal> entry : temp.entrySet()) {
            result.add(Map.of("category", billCategoryMapper.selectBillCategoryById(entry.getKey()).getBillCategoryName(),
                    "amount", entry.getValue(),
                    "percent", entry.getValue().divide(total, 4, RoundingMode.HALF_UP).movePointRight(2) + "%"));
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getCategoryPayStatisticTime(Integer bookId, Timestamp startTime, Timestamp endTime) {
        if (bookId == null || startTime == null || endTime == null) {
            throw new ParamsException("参数错误");
        }
        return categoryStatistic(billMapper.selectPayBillByBookIdTime(bookId, startTime, endTime));
    }

    @Override
    public List<Map<String, Object>> getCategoryIncomeStatisticTime(Integer bookId, Timestamp startTime, Timestamp endTime) {
        if (bookId == null || startTime == null || endTime == null) {
            throw new ParamsException("参数错误");
        }
        return categoryStatistic(billMapper.selectIncomeBillByBookIdTime(bookId, startTime, endTime));
    }
}