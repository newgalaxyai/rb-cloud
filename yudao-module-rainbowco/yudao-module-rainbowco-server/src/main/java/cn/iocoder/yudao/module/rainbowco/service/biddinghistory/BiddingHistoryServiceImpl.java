package cn.iocoder.yudao.module.rainbowco.service.biddinghistory;

import cn.iocoder.yudao.module.rainbowco.controller.admin.biddinghistory.vo.BiddingHistoryPageReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddinghistory.vo.BiddingHistorySaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.rainbowco.dal.dataobject.biddinghistory.BiddingHistoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.rainbowco.dal.mysql.biddinghistory.BiddingHistoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.rainbowco.enums.ErrorCodeConstants.BIDDING_HISTORY_NOT_EXISTS;

/**
 * 招标信息历史浏览记录 Service 实现类
 *
 * @author 芋道1
 */
@Service
@Validated
public class BiddingHistoryServiceImpl implements BiddingHistoryService {

    @Resource
    private BiddingHistoryMapper biddingHistoryMapper;

    @Override
    public Long createBiddingHistory(BiddingHistorySaveReqVO createReqVO) {
        // 插入
        BiddingHistoryDO biddingHistory = BeanUtils.toBean(createReqVO, BiddingHistoryDO.class);
        biddingHistoryMapper.insert(biddingHistory);

        // 返回
        return biddingHistory.getId();
    }

    @Override
    public void updateBiddingHistory(BiddingHistorySaveReqVO updateReqVO) {
        // 校验存在
        validateBiddingHistoryExists(updateReqVO.getId());
        // 更新
        BiddingHistoryDO updateObj = BeanUtils.toBean(updateReqVO, BiddingHistoryDO.class);
        biddingHistoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteBiddingHistory(Long id) {
        // 校验存在
        validateBiddingHistoryExists(id);
        // 删除
        biddingHistoryMapper.deleteById(id);
    }

    @Override
        public void deleteBiddingHistoryListByIds(List<Long> ids) {
        // 删除
        biddingHistoryMapper.deleteByIds(ids);
        }


    private void validateBiddingHistoryExists(Long id) {
        if (biddingHistoryMapper.selectById(id) == null) {
            throw exception(BIDDING_HISTORY_NOT_EXISTS);
        }
    }

    @Override
    public BiddingHistoryDO getBiddingHistory(Long id) {
        return biddingHistoryMapper.selectById(id);
    }

    @Override
    public PageResult<BiddingHistoryDO> getBiddingHistoryPage(BiddingHistoryPageReqVO pageReqVO) {
        return biddingHistoryMapper.selectPage(pageReqVO);
    }

}