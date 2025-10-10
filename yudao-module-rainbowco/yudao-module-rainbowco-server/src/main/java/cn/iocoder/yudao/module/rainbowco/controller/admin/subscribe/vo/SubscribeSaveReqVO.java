package cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 订阅新增/修改 Request VO")
@Data
public class SubscribeSaveReqVO {

    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18117")
    private Long id;

    @Schema(description = "订阅内容（逗号拼接）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "订阅内容（逗号拼接）不能为空")
    private String contents;

    @Schema(description = "信息类型（逗号拼接）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "信息类型（逗号拼接）不能为空")
    private String types;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "9434")
    @NotNull(message = "用户编号不能为空")
    private Long userId;
    @Schema(description = "类型（1.产品2.企业）", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long type;
}