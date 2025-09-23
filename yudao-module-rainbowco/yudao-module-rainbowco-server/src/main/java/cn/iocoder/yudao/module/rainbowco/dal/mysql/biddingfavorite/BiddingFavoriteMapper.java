package cn.iocoder.yudao.module.rainbowco.dal.mysql.biddingfavorite;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingfavorite.vo.BiddingFavoritePageReqVO;
import cn.iocoder.yudao.module.rainbowco.dal.dataobject.biddingfavorite.BiddingFavoriteDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 招标信息收藏 Mapper
 *
 * @author 芋道1
 */
@Mapper
public interface BiddingFavoriteMapper extends BaseMapperX<BiddingFavoriteDO> {

    default PageResult<BiddingFavoriteDO> selectPage(BiddingFavoritePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BiddingFavoriteDO>()
                .eqIfPresent(BiddingFavoriteDO::getBiddingId, reqVO.getBiddingId())
                .eqIfPresent(BiddingFavoriteDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(BiddingFavoriteDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BiddingFavoriteDO::getId));
    }

}