package shuhuai.wheremoney.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.wheremoney.entity.Book;
import shuhuai.wheremoney.entity.User;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface BookMapper {
    Integer insertBookSelective(Book book);

    Book selectBookById(Integer id);

    List<Book> selectBookByUser(User user);

    Book selectBookByUserTitle(User user, String title);

    BigDecimal selectPayMonthByBookId(Integer bookId);

    BigDecimal selectPayMonthByBookIdTime(Integer bookId, Timestamp startTime, Timestamp endTime);

    BigDecimal selectIncomeMonthByBookId(Integer bookId);

    BigDecimal selectIncomeMonthByBookIdTime(Integer bookId, Timestamp startTime, Timestamp endTime);

    BigDecimal selectBalanceMonthByBookId(Integer bookId);

    BigDecimal selectBalanceMonthByBookIdTime(Integer bookId,Timestamp startTime,Timestamp endTime);

    BigDecimal selectRefundMonthByBookId(Integer bookId);
}