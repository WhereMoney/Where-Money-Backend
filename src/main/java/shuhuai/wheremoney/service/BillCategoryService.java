package shuhuai.wheremoney.service;

import shuhuai.wheremoney.entity.BillCategory;

public interface BillCategoryService {
    void addDefaultBillCategory(Integer bookId);

    BillCategory getBillCategory(Integer id);
}