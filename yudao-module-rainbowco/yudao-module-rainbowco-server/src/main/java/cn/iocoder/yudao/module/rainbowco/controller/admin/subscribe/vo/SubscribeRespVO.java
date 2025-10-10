package cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 订阅 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SubscribeRespVO {

    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18117")
    @ExcelProperty("主键id")
    private Long id;

    @Schema(description = "订阅内容（逗号拼接）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("订阅内容（逗号拼接）")
    private String contents;

    @Schema(description = "信息类型（逗号拼接）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("信息类型（逗号拼接）")
    private String types;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "9434")
    @ExcelProperty("用户编号")
    private Long userId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
    @Schema(description = "类型（1.产品2.企业）", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long type;
}