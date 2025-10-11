package cn.iocoder.yudao.module.rainbowco.service.subscribe;

import cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo.SubscribePageReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo.SubscribeSaveReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo.SubscribeImportExcelVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.rainbowco.dal.dataobject.subscribe.SubscribeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.rainbowco.dal.mysql.subscribe.SubscribeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.rainbowco.enums.ErrorCodeConstants.SUBSCRIBE_NOT_EXISTS;

/**
 * 订阅 Service 实现类
 *
 * @author 润邦集团
 */
@Service
@Validated
public class SubscribeServiceImpl implements SubscribeService {

    @Resource
    private SubscribeMapper subscribeMapper;

    @Override
    public Long createSubscribe(SubscribeSaveReqVO createReqVO) {
        // 插入
        SubscribeDO subscribe = BeanUtils.toBean(createReqVO, SubscribeDO.class);
        subscribeMapper.insert(subscribe);

        // 返回
        return subscribe.getId();
    }

    @Override
    public void updateSubscribe(SubscribeSaveReqVO updateReqVO) {
        // 校验存在
        validateSubscribeExists(updateReqVO.getId());
        // 更新
        SubscribeDO updateObj = BeanUtils.toBean(updateReqVO, SubscribeDO.class);
        subscribeMapper.updateById(updateObj);
    }

    @Override
    public void deleteSubscribe(Long id) {
        // 校验存在
        validateSubscribeExists(id);
        // 删除
        subscribeMapper.deleteById(id);
    }

    @Override
        public void deleteSubscribeListByIds(List<Long> ids) {
        // 删除
        subscribeMapper.deleteByIds(ids);
        }


    private void validateSubscribeExists(Long id) {
        if (subscribeMapper.selectById(id) == null) {
            throw exception(SUBSCRIBE_NOT_EXISTS);
        }
    }

    @Override
    public SubscribeDO getSubscribe(Long id) {
        return subscribeMapper.selectById(id);
    }

    @Override
    public PageResult<SubscribeDO> getSubscribePage(SubscribePageReqVO pageReqVO) {
        return subscribeMapper.selectPage(pageReqVO);
    }

    @Override
    public void importSubscribeList(List<SubscribeImportExcelVO> importList,Long type) {
        if (importList == null || importList.isEmpty()) {
            return;
        }
        
        for (SubscribeImportExcelVO importVO : importList) {
            // 转换为DO对象
            SubscribeDO subscribe = BeanUtils.toBean(importVO, SubscribeDO.class);
            subscribe.setType(type);
            subscribe.setUserId(getLoginUserId());
            // 插入数据
            subscribeMapper.insert(subscribe);
        }
    }

}