package cn.iocoder.yudao.module.rainbowco.dal.mysql.biddinghistory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddinghistory.vo.BiddingHistoryPageReqVO;
import cn.iocoder.yudao.module.rainbowco.dal.dataobject.biddinghistory.BiddingHistoryDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 招标信息历史浏览记录 Mapper
 *
 * @author 芋道1
 */
@Mapper
public interface BiddingHistoryMapper extends BaseMapperX<BiddingHistoryDO> {

    default PageResult<BiddingHistoryDO> selectPage(BiddingHistoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BiddingHistoryDO>()
                .eqIfPresent(BiddingHistoryDO::getBiddingId, reqVO.getBiddingId())
                .eqIfPresent(BiddingHistoryDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(BiddingHistoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BiddingHistoryDO::getId));
    }

}