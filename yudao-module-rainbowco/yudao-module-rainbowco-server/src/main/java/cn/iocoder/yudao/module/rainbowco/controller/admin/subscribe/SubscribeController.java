package cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe;

import cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo.SubscribePageReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo.SubscribeRespVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo.SubscribeSaveReqVO;
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

import cn.iocoder.yudao.module.rainbowco.dal.dataobject.subscribe.SubscribeDO;
import cn.iocoder.yudao.module.rainbowco.service.subscribe.SubscribeService;

@Tag(name = "管理后台 - 订阅")
@RestController
@RequestMapping("/rb/subscribe")
@Validated
public class SubscribeController {

    @Resource
    private SubscribeService subscribeService;

    @PostMapping("/create")
    @Operation(summary = "创建订阅")
    @PreAuthorize("@ss.hasPermission('rb:subscribe:create')")
    public CommonResult<Long> createSubscribe(@Valid @RequestBody SubscribeSaveReqVO createReqVO) {
        return success(subscribeService.createSubscribe(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新订阅")
    @PreAuthorize("@ss.hasPermission('rb:subscribe:update')")
    public CommonResult<Boolean> updateSubscribe(@Valid @RequestBody SubscribeSaveReqVO updateReqVO) {
        subscribeService.updateSubscribe(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "取消订阅")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('rb:subscribe:delete')")
    public CommonResult<Boolean> deleteSubscribe(@RequestParam("id") Long id) {
        subscribeService.deleteSubscribe(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除订阅")
                @PreAuthorize("@ss.hasPermission('rb:subscribe:delete')")
    public CommonResult<Boolean> deleteSubscribeList(@RequestParam("ids") List<Long> ids) {
        subscribeService.deleteSubscribeListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订阅")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('rb:subscribe:query')")
    public CommonResult<SubscribeRespVO> getSubscribe(@RequestParam("id") Long id) {
        SubscribeDO subscribe = subscribeService.getSubscribe(id);
        return success(BeanUtils.toBean(subscribe, SubscribeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得订阅分页")
    @PreAuthorize("@ss.hasPermission('rb:subscribe:query')")
    public CommonResult<PageResult<SubscribeRespVO>> getSubscribePage(@Valid SubscribePageReqVO pageReqVO) {
        PageResult<SubscribeDO> pageResult = subscribeService.getSubscribePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SubscribeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出订阅 Excel")
    @PreAuthorize("@ss.hasPermission('rb:subscribe:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSubscribeExcel(@Valid SubscribePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SubscribeDO> list = subscribeService.getSubscribePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "订阅.xls", "数据", SubscribeRespVO.class,
                        BeanUtils.toBean(list, SubscribeRespVO.class));
    }

}