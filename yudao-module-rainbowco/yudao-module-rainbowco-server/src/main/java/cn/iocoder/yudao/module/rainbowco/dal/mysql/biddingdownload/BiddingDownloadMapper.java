package cn.iocoder.yudao.module.rainbowco.dal.mysql.biddingdownload;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingdownload.vo.BiddingDownloadPageReqVO;
import cn.iocoder.yudao.module.rainbowco.dal.dataobject.biddingdownload.BiddingDownloadDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 招标信息下载记录 Mapper
 *
 * @author 芋道1
 */
@Mapper
public interface BiddingDownloadMapper extends BaseMapperX<BiddingDownloadDO> {

    default PageResult<BiddingDownloadDO> selectPage(BiddingDownloadPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BiddingDownloadDO>()
                .eqIfPresent(BiddingDownloadDO::getBiddingId, reqVO.getBiddingId())
                .eqIfPresent(BiddingDownloadDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(BiddingDownloadDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BiddingDownloadDO::getId));
    }

}