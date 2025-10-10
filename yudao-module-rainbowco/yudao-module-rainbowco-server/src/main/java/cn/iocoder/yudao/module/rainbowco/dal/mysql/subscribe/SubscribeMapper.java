package cn.iocoder.yudao.module.rainbowco.dal.mysql.subscribe;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo.SubscribePageReqVO;
import cn.iocoder.yudao.module.rainbowco.dal.dataobject.subscribe.SubscribeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订阅 Mapper
 *
 * @author 润邦集团
 */
@Mapper
public interface SubscribeMapper extends BaseMapperX<SubscribeDO> {

    default PageResult<SubscribeDO> selectPage(SubscribePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SubscribeDO>()
                .eqIfPresent(SubscribeDO::getContents, reqVO.getContents())
                .eqIfPresent(SubscribeDO::getTypes, reqVO.getTypes())
                .eqIfPresent(SubscribeDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(SubscribeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SubscribeDO::getId));
    }

}