package shuhuai.wheremoney.mapper;

import org.apache.ibatis.annotations.Mapper;
import shuhuai.wheremoney.entity.Asset;
import shuhuai.wheremoney.entity.User;

import java.util.List;

@Mapper
public interface AssetMapper {
    Integer insertAssetSelective(Asset asset);

    List<Asset> selectAssetByUserId(Integer userId);
}