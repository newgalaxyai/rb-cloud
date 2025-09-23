package cn.iocoder.yudao.module.rainbowco.service.biddingdownload;

import java.util.*;

import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingdownload.vo.BiddingDownloadPageReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingdownload.vo.BiddingDownloadSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.rainbowco.dal.dataobject.biddingdownload.BiddingDownloadDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 招标信息下载记录 Service 接口
 *
 * @author 芋道1
 */
public interface BiddingDownloadService {

    /**
     * 创建招标信息下载记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBiddingDownload(@Valid BiddingDownloadSaveReqVO createReqVO);

    /**
     * 更新招标信息下载记录
     *
     * @param updateReqVO 更新信息
     */
    void updateBiddingDownload(@Valid BiddingDownloadSaveReqVO updateReqVO);

    /**
     * 删除招标信息下载记录
     *
     * @param id 编号
     */
    void deleteBiddingDownload(Long id);

    /**
    * 批量删除招标信息下载记录
    *
    * @param ids 编号
    */
    void deleteBiddingDownloadListByIds(List<Long> ids);

    /**
     * 获得招标信息下载记录
     *
     * @param id 编号
     * @return 招标信息下载记录
     */
    BiddingDownloadDO getBiddingDownload(Long id);

    /**
     * 获得招标信息下载记录分页
     *
     * @param pageReqVO 分页查询
     * @return 招标信息下载记录分页
     */
    PageResult<BiddingDownloadDO> getBiddingDownloadPage(BiddingDownloadPageReqVO pageReqVO);

}