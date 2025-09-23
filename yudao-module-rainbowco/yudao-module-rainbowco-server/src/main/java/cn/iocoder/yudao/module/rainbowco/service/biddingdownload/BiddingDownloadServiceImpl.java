package cn.iocoder.yudao.module.rainbowco.service.biddingdownload;

import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingdownload.vo.BiddingDownloadPageReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingdownload.vo.BiddingDownloadSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.rainbowco.dal.dataobject.biddingdownload.BiddingDownloadDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.rainbowco.dal.mysql.biddingdownload.BiddingDownloadMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.rainbowco.enums.ErrorCodeConstants.BIDDING_DOWNLOAD_NOT_EXISTS;

/**
 * 招标信息下载记录 Service 实现类
 *
 * @author 芋道1
 */
@Service
@Validated
public class BiddingDownloadServiceImpl implements BiddingDownloadService {

    @Resource
    private BiddingDownloadMapper biddingDownloadMapper;

    @Override
    public Long createBiddingDownload(BiddingDownloadSaveReqVO createReqVO) {
        // 插入
        BiddingDownloadDO biddingDownload = BeanUtils.toBean(createReqVO, BiddingDownloadDO.class);
        biddingDownloadMapper.insert(biddingDownload);

        // 返回
        return biddingDownload.getId();
    }

    @Override
    public void updateBiddingDownload(BiddingDownloadSaveReqVO updateReqVO) {
        // 校验存在
        validateBiddingDownloadExists(updateReqVO.getId());
        // 更新
        BiddingDownloadDO updateObj = BeanUtils.toBean(updateReqVO, BiddingDownloadDO.class);
        biddingDownloadMapper.updateById(updateObj);
    }

    @Override
    public void deleteBiddingDownload(Long id) {
        // 校验存在
        validateBiddingDownloadExists(id);
        // 删除
        biddingDownloadMapper.deleteById(id);
    }

    @Override
        public void deleteBiddingDownloadListByIds(List<Long> ids) {
        // 删除
        biddingDownloadMapper.deleteByIds(ids);
        }


    private void validateBiddingDownloadExists(Long id) {
        if (biddingDownloadMapper.selectById(id) == null) {
            throw exception(BIDDING_DOWNLOAD_NOT_EXISTS);
        }
    }

    @Override
    public BiddingDownloadDO getBiddingDownload(Long id) {
        return biddingDownloadMapper.selectById(id);
    }

    @Override
    public PageResult<BiddingDownloadDO> getBiddingDownloadPage(BiddingDownloadPageReqVO pageReqVO) {
        return biddingDownloadMapper.selectPage(pageReqVO);
    }

}