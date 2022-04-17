package shuhuai.wheremoney.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.wheremoney.entity.BillCategory;
import shuhuai.wheremoney.entity.Book;

import java.util.List;

@Mapper
public interface BillCategoryMapper {
    Integer insertBillCategorySelective(BillCategory billCategory);

    List<BillCategory> selectBillCategoryByBook(Book book);

    BillCategory selectBillCategoryById(Integer id);
}