package shuhuai.wheremoney.response.book;

import shuhuai.wheremoney.entity.Book;

import java.util.List;

public class GetBookResponse {
    private List<Book> bookList;

    public GetBookResponse(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}