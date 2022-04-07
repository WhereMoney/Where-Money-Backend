package shuhuai.wheremoney.service;

import shuhuai.wheremoney.entity.Book;

import java.util.List;

public interface BookService {
    void addBook(String userName, String title, Integer beginDate);
    List<Book> getBook(String userName);
}
