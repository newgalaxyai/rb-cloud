package cn.iocoder.yudao.module.rainbowco.service.subscribe;

import java.util.*;

import cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo.SubscribePageReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo.SubscribeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.rainbowco.dal.dataobject.subscribe.SubscribeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 订阅 Service 接口
 *
 * @author 润邦集团
 */
public interface SubscribeService {

    /**
     * 创建订阅
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSubscribe(@Valid SubscribeSaveReqVO createReqVO);

    /**
     * 更新订阅
     *
     * @param updateReqVO 更新信息
     */
    void updateSubscribe(@Valid SubscribeSaveReqVO updateReqVO);

    /**
     * 删除订阅
     *
     * @param id 编号
     */
    void deleteSubscribe(Long id);

    /**
    * 批量删除订阅
    *
    * @param ids 编号
    */
    void deleteSubscribeListByIds(List<Long> ids);

    /**
     * 获得订阅
     *
     * @param id 编号
     * @return 订阅
     */
    SubscribeDO getSubscribe(Long id);

    /**
     * 获得订阅分页
     *
     * @param pageReqVO 分页查询
     * @return 订阅分页
     */
    PageResult<SubscribeDO> getSubscribePage(SubscribePageReqVO pageReqVO);

}