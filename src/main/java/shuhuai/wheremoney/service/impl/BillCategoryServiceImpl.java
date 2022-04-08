package shuhuai.wheremoney.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import shuhuai.wheremoney.entity.BillCategory;
import shuhuai.wheremoney.mapper.BillCategoryMapper;
import shuhuai.wheremoney.service.BillCategoryService;
import shuhuai.wheremoney.service.excep.common.ServerException;
import shuhuai.wheremoney.utils.JsonOperator;

import javax.annotation.Resource;

@Service
public class BillCategoryServiceImpl implements BillCategoryService {
    @Resource
    private BillCategoryMapper billCategoryMapper;

    @Override
    public void addDefaultBillCategory(Integer bookId) {
        JSONArray jsonArray = JsonOperator.getMapFromJson("DefaultBillCategory");
        for (Object item : jsonArray) {
            JSONObject obj = JSON.parseObject(item + "");
            Integer result = billCategoryMapper.insertBillCategorySelective(new BillCategory(bookId, obj.get("name").toString(), obj.get("svg").toString()));
            if (result != 1) {
                throw new ServerException("服务器错误");
            }
        }
    }
}
