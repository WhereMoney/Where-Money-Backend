package shuhuai.wheremoney.service;

import shuhuai.wheremoney.entity.BillCategory;
import shuhuai.wheremoney.entity.Book;
import shuhuai.wheremoney.type.BillType;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    void addBook(String userName, String title, Integer beginDate);

    List<Book> getBook(String userName);

    Book getBook(Integer id);

    BigDecimal getPayMonth(Integer bookId);

    BigDecimal getIncomeMonth(Integer bookId);

    BigDecimal getBalanceMonth(Integer bookId);

    BigDecimal getRefundMonth(Integer bookId);

    List<BillCategory> getAllBillCategory(Integer bookId, BillType type);
}
