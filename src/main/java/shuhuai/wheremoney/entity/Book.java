package shuhuai.wheremoney.entity;
import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    private Integer id;
    private  Integer userId;
    private String title;
    private Date createTime;

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
