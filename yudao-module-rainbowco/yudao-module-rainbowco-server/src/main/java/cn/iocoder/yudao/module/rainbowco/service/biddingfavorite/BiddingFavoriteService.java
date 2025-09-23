package cn.iocoder.yudao.module.rainbowco.service.biddingfavorite;

import java.util.*;

import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingfavorite.vo.BiddingFavoritePageReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingfavorite.vo.BiddingFavoriteSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.rainbowco.dal.dataobject.biddingfavorite.BiddingFavoriteDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 招标信息收藏 Service 接口
 *
 * @author 芋道1
 */
public interface BiddingFavoriteService {

    /**
     * 创建招标信息收藏
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBiddingFavorite(@Valid BiddingFavoriteSaveReqVO createReqVO);

    /**
     * 更新招标信息收藏
     *
     * @param updateReqVO 更新信息
     */
    void updateBiddingFavorite(@Valid BiddingFavoriteSaveReqVO updateReqVO);

    /**
     * 删除招标信息收藏
     *
     * @param id 编号
     */
    void deleteBiddingFavorite(Long id);

    /**
    * 批量删除招标信息收藏
    *
    * @param ids 编号
    */
    void deleteBiddingFavoriteListByIds(List<Long> ids);

    /**
     * 获得招标信息收藏
     *
     * @param id 编号
     * @return 招标信息收藏
     */
    BiddingFavoriteDO getBiddingFavorite(Long id);

    /**
     * 获得招标信息收藏分页
     *
     * @param pageReqVO 分页查询
     * @return 招标信息收藏分页
     */
    PageResult<BiddingFavoriteDO> getBiddingFavoritePage(BiddingFavoritePageReqVO pageReqVO);

    /**
     * 切换招标信息收藏状态
     *
     * @param createReqVO 收藏信息
     * @return 是否已收藏（true=已收藏，false=已取消收藏）
     */
    boolean toggleBiddingFavorite(@Valid BiddingFavoriteSaveReqVO createReqVO);
}