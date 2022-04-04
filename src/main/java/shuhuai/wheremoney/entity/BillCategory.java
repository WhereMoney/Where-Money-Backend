package shuhuai.wheremoney.entity;

import java.io.Serializable;

public class BillCategory implements Serializable {
    private Integer id;
    private Integer book_id;
    private String name;

    public BillCategory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BillCategory{" +
                "id=" + id +
                ", book_id=" + book_id +
                ", name='" + name + '\'' +
                '}';
    }
}
