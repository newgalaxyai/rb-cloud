package cn.iocoder.yudao.module.rainbowco.controller.admin.biddinghistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 招标信息历史浏览记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BiddingHistoryRespVO {

    @Schema(description = "日志主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "9933")
    @ExcelProperty("日志主键")
    private Long id;

    @Schema(description = "招标id", requiredMode = Schema.RequiredMode.REQUIRED, example = "21479")
    @ExcelProperty("招标id")
    private Long biddingId;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "4403")
    @ExcelProperty("用户编号")
    private Long userId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}