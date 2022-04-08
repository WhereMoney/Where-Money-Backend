package shuhuai.wheremoney.entity;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class BillCategory implements Serializable {
    private Integer id;
    private Integer bookId;
    private String name;
    private String svg;

    public BillCategory(Integer bookId, String name, String svg) {
        this.bookId = bookId;
        this.name = name;
        this.svg = svg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }
}
