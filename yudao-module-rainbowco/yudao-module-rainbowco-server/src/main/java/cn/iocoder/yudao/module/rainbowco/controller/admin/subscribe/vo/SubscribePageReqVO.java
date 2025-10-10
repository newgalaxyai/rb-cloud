package cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 订阅分页 Request VO")
@Data
public class SubscribePageReqVO extends PageParam {

    @Schema(description = "订阅内容（逗号拼接）")
    private String contents;

    @Schema(description = "信息类型（逗号拼接）")
    private String types;

    @Schema(description = "用户编号", example = "9434")
    private Long userId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "类型（1.产品2.企业）", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long type;
}