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
import cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo.SubscribeImportExcelVO;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;

@Tag(name = "管理后台 - 订阅")
@RestController
@RequestMapping("/rb/subscribe")
@Validated
public class SubscribeController {

    @Resource
    private SubscribeService subscribeService;

    @PostMapping("/create")
    @Operation(summary = "创建订阅")
    public CommonResult<Long> createSubscribe(@Valid @RequestBody SubscribeSaveReqVO createReqVO) {
        return success(subscribeService.createSubscribe(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新订阅")
    public CommonResult<Boolean> updateSubscribe(@Valid @RequestBody SubscribeSaveReqVO updateReqVO) {
        subscribeService.updateSubscribe(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "取消订阅")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteSubscribe(@RequestParam("id") Long id) {
        subscribeService.deleteSubscribe(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除订阅")
    public CommonResult<Boolean> deleteSubscribeList(@RequestParam("ids") List<Long> ids) {
        subscribeService.deleteSubscribeListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订阅")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<SubscribeRespVO> getSubscribe(@RequestParam("id") Long id) {
        SubscribeDO subscribe = subscribeService.getSubscribe(id);
        return success(BeanUtils.toBean(subscribe, SubscribeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得订阅分页")
    public CommonResult<PageResult<SubscribeRespVO>> getSubscribePage(@Valid SubscribePageReqVO pageReqVO) {
        PageResult<SubscribeDO> pageResult = subscribeService.getSubscribePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SubscribeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出订阅 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSubscribeExcel(@Valid SubscribePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SubscribeDO> list = subscribeService.getSubscribePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "订阅.xls", "数据", SubscribeRespVO.class,
                        BeanUtils.toBean(list, SubscribeRespVO.class));
    }

    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入订阅模板")
    public void getImportTemplate(HttpServletResponse response) throws IOException {
        // 手动创建导入模板示例数据
        List<SubscribeImportExcelVO> list = Arrays.asList(
                SubscribeImportExcelVO.builder()
                        .contents("产品A,产品B")
                        .types("中标结果,采购意向")
                        .importTip("1.订阅内容、信息类型不能为空\n2.多个内容、类型用逗号分隔")
                        .build(),
                SubscribeImportExcelVO.builder()
                        .contents("企业A,企业B")
                        .types("变更信息,招标公告")
                        .importTip("导入时注意删除本列，有问题及时联系管理员")
                        .build()
        );
        ExcelUtils.write(response, "订阅导入模板.xls", "订阅列表", SubscribeImportExcelVO.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入订阅")
    @Parameter(name = "file", description = "Excel 文件", required = true)
    @Parameter(name = "type", description = "类型", required = true)
    public CommonResult<cn.iocoder.yudao.framework.common.pojo.ImportResult> importSubscribeExcel(@RequestParam("file") MultipartFile file,@RequestParam("type")Long type) throws Exception {
        List<SubscribeImportExcelVO> list = ExcelUtils.read(file, SubscribeImportExcelVO.class);
        cn.iocoder.yudao.framework.common.pojo.ImportResult result = subscribeService.importSubscribeList(list,type);
        return success(result);
    }

}