package cn.iocoder.yudao.module.rainbowco.controller.admin.biddingfavorite;

import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingfavorite.vo.BiddingFavoritePageReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingfavorite.vo.BiddingFavoriteRespVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.biddingfavorite.vo.BiddingFavoriteSaveReqVO;
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

import cn.iocoder.yudao.module.rainbowco.dal.dataobject.biddingfavorite.BiddingFavoriteDO;
import cn.iocoder.yudao.module.rainbowco.service.biddingfavorite.BiddingFavoriteService;

@Tag(name = "管理后台 - 招标信息收藏")
@RestController
@RequestMapping("/rb/bidding-favorite")
@Validated
public class BiddingFavoriteController {

    @Resource
    private BiddingFavoriteService biddingFavoriteService;

    @PostMapping("/create")
    @Operation(summary = "创建招标信息收藏")
    @PreAuthorize("@ss.hasPermission('rb:bidding-favorite:create')")
    public CommonResult<Long> createBiddingFavorite(@Valid @RequestBody BiddingFavoriteSaveReqVO createReqVO) {
        return success(biddingFavoriteService.createBiddingFavorite(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新招标信息收藏")
    @PreAuthorize("@ss.hasPermission('rb:bidding-favorite:update')")
    public CommonResult<Boolean> updateBiddingFavorite(@Valid @RequestBody BiddingFavoriteSaveReqVO updateReqVO) {
        biddingFavoriteService.updateBiddingFavorite(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除招标信息收藏")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('rb:bidding-favorite:delete')")
    public CommonResult<Boolean> deleteBiddingFavorite(@RequestParam("id") Long id) {
        biddingFavoriteService.deleteBiddingFavorite(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除招标信息收藏")
                @PreAuthorize("@ss.hasPermission('rb:bidding-favorite:delete')")
    public CommonResult<Boolean> deleteBiddingFavoriteList(@RequestParam("ids") List<Long> ids) {
        biddingFavoriteService.deleteBiddingFavoriteListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得招标信息收藏")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('rb:bidding-favorite:query')")
    public CommonResult<BiddingFavoriteRespVO> getBiddingFavorite(@RequestParam("id") Long id) {
        BiddingFavoriteDO biddingFavorite = biddingFavoriteService.getBiddingFavorite(id);
        return success(BeanUtils.toBean(biddingFavorite, BiddingFavoriteRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得招标信息收藏分页")
    @PreAuthorize("@ss.hasPermission('rb:bidding-favorite:query')")
    public CommonResult<PageResult<BiddingFavoriteRespVO>> getBiddingFavoritePage(@Valid BiddingFavoritePageReqVO pageReqVO) {
        PageResult<BiddingFavoriteDO> pageResult = biddingFavoriteService.getBiddingFavoritePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BiddingFavoriteRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出招标信息收藏 Excel")
    @PreAuthorize("@ss.hasPermission('rb:bidding-favorite:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBiddingFavoriteExcel(@Valid BiddingFavoritePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BiddingFavoriteDO> list = biddingFavoriteService.getBiddingFavoritePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "招标信息收藏.xls", "数据", BiddingFavoriteRespVO.class,
                        BeanUtils.toBean(list, BiddingFavoriteRespVO.class));
    }

}