package shuhuai.wheremoney.response.bill;

import java.util.List;

public class GetAllBillResponse {
    private List<GetBillResponse> billList;

    public GetAllBillResponse(List<GetBillResponse> billList) {
        this.billList = billList;
    }

    public List<GetBillResponse> getBillList() {
        return billList;
    }

    public void setBillList(List<GetBillResponse> billList) {
        this.billList = billList;
    }
}
