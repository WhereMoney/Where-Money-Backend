package shuhuai.wheremoney.service;

import shuhuai.wheremoney.entity.Book;

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
}
