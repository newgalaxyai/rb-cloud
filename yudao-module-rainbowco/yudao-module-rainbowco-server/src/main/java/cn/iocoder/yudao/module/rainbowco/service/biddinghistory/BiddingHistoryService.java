package cn.iocoder.yudao.module.rainbowco.service.biddinghistory;

import java.util.*;

import cn.iocoder.yudao.module.rainbowco.controller.admin.biddinghistory.vo.BiddingHistoryPageReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddinghistory.vo.BiddingHistorySaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.rainbowco.dal.dataobject.biddinghistory.BiddingHistoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 招标信息历史浏览记录 Service 接口
 *
 * @author 芋道1
 */
public interface BiddingHistoryService {

    /**
     * 创建招标信息历史浏览记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBiddingHistory(@Valid BiddingHistorySaveReqVO createReqVO);

    /**
     * 更新招标信息历史浏览记录
     *
     * @param updateReqVO 更新信息
     */
    void updateBiddingHistory(@Valid BiddingHistorySaveReqVO updateReqVO);

    /**
     * 删除招标信息历史浏览记录
     *
     * @param id 编号
     */
    void deleteBiddingHistory(Long id);

    /**
    * 批量删除招标信息历史浏览记录
    *
    * @param ids 编号
    */
    void deleteBiddingHistoryListByIds(List<Long> ids);

    /**
     * 获得招标信息历史浏览记录
     *
     * @param id 编号
     * @return 招标信息历史浏览记录
     */
    BiddingHistoryDO getBiddingHistory(Long id);

    /**
     * 获得招标信息历史浏览记录分页
     *
     * @param pageReqVO 分页查询
     * @return 招标信息历史浏览记录分页
     */
    PageResult<BiddingHistoryDO> getBiddingHistoryPage(BiddingHistoryPageReqVO pageReqVO);

}