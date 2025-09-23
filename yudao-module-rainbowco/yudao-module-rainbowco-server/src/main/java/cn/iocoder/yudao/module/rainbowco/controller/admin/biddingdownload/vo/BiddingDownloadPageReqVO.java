package cn.iocoder.yudao.module.rainbowco.controller.admin.biddingdownload.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 招标信息下载记录分页 Request VO")
@Data
public class BiddingDownloadPageReqVO extends PageParam {

    @Schema(description = "招标id", example = "3532")
    private Long biddingId;

    @Schema(description = "用户编号", example = "23562")
    private Long userId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}