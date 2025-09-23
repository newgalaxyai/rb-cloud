package cn.iocoder.yudao.module.rainbowco.controller.admin.biddinghistory;

import cn.iocoder.yudao.module.rainbowco.controller.admin.biddinghistory.vo.BiddingHistoryPageReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddinghistory.vo.BiddingHistoryRespVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddinghistory.vo.BiddingHistorySaveReqVO;
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

import cn.iocoder.yudao.module.rainbowco.dal.dataobject.biddinghistory.BiddingHistoryDO;
import cn.iocoder.yudao.module.rainbowco.service.biddinghistory.BiddingHistoryService;

@Tag(name = "管理后台 - 招标信息历史浏览记录")
@RestController
@RequestMapping("/rb/bidding-history")
@Validated
public class BiddingHistoryController {

    @Resource
    private BiddingHistoryService biddingHistoryService;

    @PostMapping("/create")
    @Operation(summary = "创建招标信息历史浏览记录")
    public CommonResult<Long> createBiddingHistory(@Valid @RequestBody BiddingHistorySaveReqVO createReqVO) {
        return success(biddingHistoryService.createBiddingHistory(createReqVO));
    }

//    @PutMapping("/update")
//    @Operation(summary = "更新招标信息历史浏览记录")
//    @PreAuthorize("@ss.hasPermission('rb:bidding-history:update')")
//    public CommonResult<Boolean> updateBiddingHistory(@Valid @RequestBody BiddingHistorySaveReqVO updateReqVO) {
//        biddingHistoryService.updateBiddingHistory(updateReqVO);
//        return success(true);
//    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除招标信息历史浏览记录")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteBiddingHistory(@RequestParam("id") Long id) {
        biddingHistoryService.deleteBiddingHistory(id);
        return success(true);
    }

    @PostMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除招标信息历史浏览记录")
    public CommonResult<Boolean> deleteBiddingHistoryList(@RequestParam("ids") List<Long> ids) {
        biddingHistoryService.deleteBiddingHistoryListByIds(ids);
        return success(true);
    }

//    @GetMapping("/get")
//    @Operation(summary = "获得招标信息历史浏览记录")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('rb:bidding-history:query')")
//    public CommonResult<BiddingHistoryRespVO> getBiddingHistory(@RequestParam("id") Long id) {
//        BiddingHistoryDO biddingHistory = biddingHistoryService.getBiddingHistory(id);
//        return success(BeanUtils.toBean(biddingHistory, BiddingHistoryRespVO.class));
//    }

    @GetMapping("/page")
    @Operation(summary = "获得招标信息历史浏览记录分页")
    public CommonResult<PageResult<BiddingHistoryRespVO>> getBiddingHistoryPage(@Valid BiddingHistoryPageReqVO pageReqVO) {
        PageResult<BiddingHistoryDO> pageResult = biddingHistoryService.getBiddingHistoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BiddingHistoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出招标信息历史浏览记录 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBiddingHistoryExcel(@Valid BiddingHistoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BiddingHistoryDO> list = biddingHistoryService.getBiddingHistoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "招标信息历史浏览记录.xls", "数据", BiddingHistoryRespVO.class,
                        BeanUtils.toBean(list, BiddingHistoryRespVO.class));
    }

}