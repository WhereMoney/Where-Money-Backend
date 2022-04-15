package shuhuai.wheremoney.service.impl;

import org.springframework.stereotype.Service;
import shuhuai.wheremoney.entity.Bill;
import shuhuai.wheremoney.mapper.BillMapper;
import shuhuai.wheremoney.service.BillService;
import shuhuai.wheremoney.service.excep.common.ParamsException;
import shuhuai.wheremoney.service.excep.common.ServerException;
import shuhuai.wheremoney.type.BillType;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Resource
    private BillMapper billMapper;

    @Override
    public void addBill(Integer bookId, Integer inAssetId, Integer outAssetId, Integer billCategoryId, BillType type, BigDecimal amount, Timestamp time, String remark) {
        if (bookId == null || type == null || amount == null) {
            throw new ParamsException("参数错误");
        }
        if (inAssetId == null && outAssetId == null) {
            throw new ParamsException("参数错误");
        }
        Bill bill = new Bill(bookId, inAssetId, outAssetId, billCategoryId, type, amount, time, remark);
        Integer result = billMapper.insertBillSelective(bill);
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }

    @Override
    public List<Bill> getBillByBook(Integer bookId) {
        if (bookId == null) {
            throw new ParamsException("参数错误");
        }
        return billMapper.selectBillByBookId(bookId);
    }
}