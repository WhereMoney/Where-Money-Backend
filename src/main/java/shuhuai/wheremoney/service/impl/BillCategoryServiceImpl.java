package shuhuai.wheremoney.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import shuhuai.wheremoney.entity.BillCategory;
import shuhuai.wheremoney.mapper.BillCategoryMapper;
import shuhuai.wheremoney.service.BillCategoryService;
import shuhuai.wheremoney.service.excep.common.ParamsException;
import shuhuai.wheremoney.service.excep.common.ServerException;
import shuhuai.wheremoney.type.BillType;
import shuhuai.wheremoney.utils.JsonOperator;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BillCategoryServiceImpl implements BillCategoryService {
    @Resource
    private BillCategoryMapper billCategoryMapper;

    @Override
    public void addDefaultBillCategory(Integer bookId) {
        JSONArray jsonArray = JsonOperator.getMapFromJson("DefaultBillCategory");
        for (Object item : jsonArray) {
            JSONObject obj = JSON.parseObject(item + "");
            Integer result = billCategoryMapper.insertBillCategorySelective(new BillCategory(bookId, obj.get("billCategoryName").toString(), obj.get("svg").toString(),
                    BillType.valueOf(obj.get("type").toString())));
            if (result != 1) {
                throw new ServerException("服务器错误");
            }
        }
    }

    @Override
    public BillCategory getBillCategory(Integer id) {
        if (id == null) {
            throw new ParamsException("参数错误");
        }
        return billCategoryMapper.selectBillCategoryById(id);
    }

    @Override
    public List<BillCategory> getBillCategoriesByBook(Integer bookId) {
        return billCategoryMapper.selectBillCategoryByBook(bookId);
    }

    @Override
    public List<BillCategory> getBillCategoriesByBookType(Integer bookId, BillType type) {
        return billCategoryMapper.selectBillCategoryByBookType(bookId, type);
    }

    @Override
    public void addBillCategory(Integer bookId, String name, String svg, BillType type) {
        Integer result = billCategoryMapper.insertBillCategorySelective(new BillCategory(bookId, name, svg, type));
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
    }
}
