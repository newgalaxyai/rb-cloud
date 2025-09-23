package cn.iocoder.yudao.module.rainbowco.controller.admin.biddingdownload;

import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingdownload.vo.BiddingDownloadPageReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingdownload.vo.BiddingDownloadRespVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingdownload.vo.BiddingDownloadSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.rainbowco.dal.dataobject.biddingdownload.BiddingDownloadDO;
import cn.iocoder.yudao.module.rainbowco.service.biddingdownload.BiddingDownloadService;

@Tag(name = "管理后台 - 招标信息下载记录")
@RestController
@RequestMapping("/rb/bidding-download")
@Validated
public class BiddingDownloadController {

    @Resource
    private BiddingDownloadService biddingDownloadService;

    @PostMapping("/create")
    @Operation(summary = "创建招标信息下载记录")
    public CommonResult<Long> createBiddingDownload(@Valid @RequestBody BiddingDownloadSaveReqVO createReqVO) {
        return success(biddingDownloadService.createBiddingDownload(createReqVO));
    }
    @DeleteMapping("/delete")
    @Operation(summary = "删除招标信息下载记录")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteBiddingDownload(@RequestParam("id") Long id) {
        biddingDownloadService.deleteBiddingDownload(id);
        return success(true);
    }

    @PostMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除招标信息下载记录")
    public CommonResult<Boolean> deleteBiddingDownloadList(@RequestBody List<Long> ids) {
        biddingDownloadService.deleteBiddingDownloadListByIds(ids);
        return success(true);
    }

//    @GetMapping("/get")
//    @Operation(summary = "获得招标信息下载记录")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('rb:bidding-download:query')")
//    public CommonResult<BiddingDownloadRespVO> getBiddingDownload(@RequestParam("id") Long id) {
//        BiddingDownloadDO biddingDownload = biddingDownloadService.getBiddingDownload(id);
//        return success(BeanUtils.toBean(biddingDownload, BiddingDownloadRespVO.class));
//    }

    @GetMapping("/page")
    @Operation(summary = "获得招标信息下载记录分页")
    public CommonResult<PageResult<BiddingDownloadRespVO>> getBiddingDownloadPage(@Valid BiddingDownloadPageReqVO pageReqVO) {
        PageResult<BiddingDownloadDO> pageResult = biddingDownloadService.getBiddingDownloadPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BiddingDownloadRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出招标信息下载记录 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBiddingDownloadExcel(@Valid BiddingDownloadPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BiddingDownloadDO> list = biddingDownloadService.getBiddingDownloadPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "招标信息下载记录.xls", "数据", BiddingDownloadRespVO.class,
                        BeanUtils.toBean(list, BiddingDownloadRespVO.class));
    }

}