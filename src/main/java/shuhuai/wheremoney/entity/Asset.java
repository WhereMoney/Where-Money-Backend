package shuhuai.wheremoney.entity;

public class Asset {
    private Integer id;
    private Integer userId;
    private String type;
    private String name;
    private Integer billDate;
    private Integer repayDate;

    public Asset() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBillDate() {
        return billDate;
    }

    public void setBillDate(Integer billDate) {
        this.billDate = billDate;
    }

    public Integer getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Integer repayDate) {
        this.repayDate = repayDate;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", userId=" + userId +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", billDate=" + billDate +
                ", repayDate=" + repayDate +
                '}';
    }
}
