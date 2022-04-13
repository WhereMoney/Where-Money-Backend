package shuhuai.wheremoney.entity;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class BillCategory implements Serializable {
    private Integer id;
    private Integer bookId;
    private String billCategoryName;
    private String svg;

    public BillCategory(Integer bookId, String billCategoryName, String svg) {
        this.bookId = bookId;
        this.billCategoryName = billCategoryName;
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

    public String getBillCategoryName() {
        return billCategoryName;
    }

    public void setBillCategoryName(String billCategoryName) {
        this.billCategoryName = billCategoryName;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }
}
