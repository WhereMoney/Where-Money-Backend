package shuhuai.wheremoney.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import shuhuai.wheremoney.entity.*;
import shuhuai.wheremoney.mapper.AssetMapper;
import shuhuai.wheremoney.mapper.BookMapper;
import shuhuai.wheremoney.mapper.UserMapper;
import shuhuai.wheremoney.service.AssetService;
import shuhuai.wheremoney.service.BillService;
import shuhuai.wheremoney.service.excep.common.ParamsException;
import shuhuai.wheremoney.service.excep.common.ServerException;
import shuhuai.wheremoney.type.AssetType;
import shuhuai.wheremoney.utils.RedisConnector;
import shuhuai.wheremoney.utils.TimeComputer;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class AssetServiceImpl implements AssetService {
    @Value("${redis.billCategory.expire}")
    private Long assetExpire;
    @Resource
    private AssetMapper assetMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private BillService billService;

    @Resource
    private RedisConnector redisConnector;

    @Override
    public void addAsset(String userName, String assetName, BigDecimal balance, AssetType type, Integer billDate,
                         Integer repayDate, BigDecimal quota, Boolean inTotal, String svg) {
        if (userName == null || assetName == null || balance == null || type == null || inTotal == null) {
            throw new ParamsException("参数错误");
        }
        Integer userId = userMapper.selectUserByUserName(userName).getId();
        Asset asset = new Asset(userId, type, balance, assetName, billDate, repayDate, quota, inTotal, svg);
        Integer result = assetMapper.insertAssetSelective(asset);
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
        redisConnector.writeObject("asset:" + asset.getId(), asset, TimeComputer.dayToSecond(assetExpire));
    }

    @Override
    public List<Asset> getAllAsset(String userName) {
        if (userName == null) {
            throw new ParamsException("参数错误");
        }
        return assetMapper.selectAssetByUserId(userMapper.selectUserByUserName(userName).getId());
    }

    @Override
    public Asset getAsset(Integer id) {
        if (id == null) {
            throw new ParamsException("参数错误");
        }
        if (redisConnector.existObject("asset:" + id)) {
            redisConnector.setExpire("asset:" + id, TimeComputer.dayToSecond(assetExpire));
            return (Asset) redisConnector.readObject("asset:" + id);
        }
        Asset result = assetMapper.selectAssetById(id);
        if (result != null) {
            redisConnector.writeObject("asset:" + id, result, TimeComputer.dayToSecond(assetExpire));
        }
        return result;
    }

    @Override
    public void updateAsset(Asset asset) {
        Integer result = assetMapper.updateAssetSelectiveById(asset);
        if (result != 1) {
            throw new ServerException("服务器错误");
        }
        redisConnector.writeObject("asset:" + asset.getId(), asset, TimeComputer.dayToSecond(assetExpire));
    }

    @Override
    public List<Map<String, Object>> getDayStatisticTime(String userName, Timestamp startTime, Timestamp endTime) {
        if (userName == null || startTime == null || endTime == null) {
            throw new ParamsException("参数错误");
        }
        User user = userMapper.selectUserByUserName(userName);
        if (user == null) {
            throw new ParamsException("参数错误");
        }
        Integer userId = user.getId();
        BigDecimal curTotal = assetMapper.selectTotalAssetByUserId(userId);
        List<Book> bookList = bookMapper.selectBookByUser(user);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Timestamp curTime = TimeComputer.getDay(TimeComputer.getNow()); !curTime.before(TimeComputer.getDay(startTime)); curTime = TimeComputer.prevDay(curTime)) {
            BigDecimal dayTotal = new BigDecimal(0);
            for (Book book : bookList) {
                for (BaseBill bill : billService.getBillByBookTime(book.getId(), curTime, TimeComputer.nextDay(curTime))) {
                    if (bill instanceof IncomeBill || bill instanceof RefundBill) {
                        dayTotal = dayTotal.add(bill.getAmount());
                    } else if (bill instanceof PayBill) {
                        dayTotal = dayTotal.subtract(bill.getAmount());
                    }
                }
            }
            Timestamp finalTime = curTime;
            curTotal = curTotal.subtract(dayTotal);
            BigDecimal finalDayTotal = curTotal;
            if (!curTime.after(TimeComputer.getDay(endTime))) {
                result.add(new HashMap<>(2) {{
                    put("time", finalTime);
                    put("total", finalDayTotal);
                }});
            }
        }
        return result;
    }
}