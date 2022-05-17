package shuhuai.wheremoney.response.budget;

import shuhuai.wheremoney.entity.Budget;

import java.math.BigDecimal;

public class GetBudgetResponse {
    Budget budget;

    public GetBudgetResponse(Budget budget) {
        this.budget = budget;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
