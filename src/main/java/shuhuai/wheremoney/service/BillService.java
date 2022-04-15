package shuhuai.wheremoney.service;

import org.apache.ibatis.jdbc.Null;
import shuhuai.wheremoney.entity.Bill;
import shuhuai.wheremoney.type.BillType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface BillService {

        void addBill(Integer bookId, Integer inAssetId, Integer outAssetId, Integer billCategoryId, BillType type, BigDecimal amount, Date time, String remark);

        List<Bill> getBillByBook(Integer bookId);

}
