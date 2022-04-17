package shuhuai.wheremoney.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.wheremoney.entity.Bill;

import java.util.List;

@Mapper
public interface BillMapper {
    Integer insertBillSelective(Bill bill);

    List<Bill> selectBillByBookId(Integer bookId);

    Bill selectBillById(Integer id);
}