package shuhuai.wheremoney.response.bill;
import shuhuai.wheremoney.entity.Bill;

import java.util.List;

public class GetBillResponse {
    private List<Bill> billList;

    public GetBillResponse(List<Bill> billList){this.billList = billList;}

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }
}
