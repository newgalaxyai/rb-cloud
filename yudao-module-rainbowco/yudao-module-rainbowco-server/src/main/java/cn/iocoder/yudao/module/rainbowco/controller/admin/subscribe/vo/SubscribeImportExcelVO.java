package cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import cn.idev.excel.annotation.*;
import lombok.experimental.Accessors;

@Schema(description = "管理后台 - 订阅导入 Excel VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class SubscribeImportExcelVO {

    @Schema(description = "订阅内容（逗号拼接）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty(value = "订阅内容")
    private String contents;

    @Schema(description = "信息类型（逗号拼接）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty(value = "信息类型")
    private String types;

    @Schema(description = "导入提示")
    @ExcelProperty(value = "导入提示")
    private String importTip;
}