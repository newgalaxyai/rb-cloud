package cn.iocoder.yudao.module.rainbowco.service.biddingfavorite;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingfavorite.vo.BiddingFavoritePageReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingfavorite.vo.BiddingFavoriteSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.rainbowco.dal.dataobject.biddingfavorite.BiddingFavoriteDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.rainbowco.dal.mysql.biddingfavorite.BiddingFavoriteMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.rainbowco.enums.ErrorCodeConstants.BIDDING_FAVORITE_NOT_EXISTS;

/**
 * 招标信息收藏 Service 实现类
 *
 * @author 芋道1
 */
@Service
@Validated
public class BiddingFavoriteServiceImpl implements BiddingFavoriteService {

    @Resource
    private BiddingFavoriteMapper biddingFavoriteMapper;

    @Override
    public Long createBiddingFavorite(BiddingFavoriteSaveReqVO createReqVO) {
        // 插入
        BiddingFavoriteDO biddingFavorite = BeanUtils.toBean(createReqVO, BiddingFavoriteDO.class);
        biddingFavoriteMapper.insert(biddingFavorite);

        // 返回
        return biddingFavorite.getId();
    }

    @Override
    public void updateBiddingFavorite(BiddingFavoriteSaveReqVO updateReqVO) {
        // 校验存在
        validateBiddingFavoriteExists(updateReqVO.getId());
        // 更新
        BiddingFavoriteDO updateObj = BeanUtils.toBean(updateReqVO, BiddingFavoriteDO.class);
        biddingFavoriteMapper.updateById(updateObj);
    }

    @Override
    public void deleteBiddingFavorite(Long id) {
        // 校验存在
        validateBiddingFavoriteExists(id);
        // 删除
        biddingFavoriteMapper.deleteById(id);
    }

    @Override
        public void deleteBiddingFavoriteListByIds(List<Long> ids) {
        // 删除
        biddingFavoriteMapper.deleteByIds(ids);
        }


    private void validateBiddingFavoriteExists(Long id) {
        if (biddingFavoriteMapper.selectById(id) == null) {
            throw exception(BIDDING_FAVORITE_NOT_EXISTS);
        }
    }

    @Override
    public BiddingFavoriteDO getBiddingFavorite(Long id) {
        return biddingFavoriteMapper.selectById(id);
    }

    @Override
    public PageResult<BiddingFavoriteDO> getBiddingFavoritePage(BiddingFavoritePageReqVO pageReqVO) {
        return biddingFavoriteMapper.selectPage(pageReqVO);
    }

    @Override
    public boolean toggleBiddingFavorite(BiddingFavoriteSaveReqVO createReqVO) {
        // 查询是否已存在收藏记录
        BiddingFavoriteDO existingFavorite = biddingFavoriteMapper.selectOne(
            new LambdaQueryWrapperX<BiddingFavoriteDO>()
                .eq(BiddingFavoriteDO::getBiddingId, createReqVO.getBiddingId())
                .eq(BiddingFavoriteDO::getUserId, createReqVO.getUserId())
        );
        
        if (existingFavorite != null) {
            // 已收藏，执行取消收藏
            biddingFavoriteMapper.deleteById(existingFavorite.getId());
            return false; // 返回false表示已取消收藏
        } else {
            // 未收藏，执行收藏
            BiddingFavoriteDO biddingFavorite = BeanUtils.toBean(createReqVO, BiddingFavoriteDO.class);
            biddingFavoriteMapper.insert(biddingFavorite);
            return true; // 返回true表示已收藏
        }
    }
}