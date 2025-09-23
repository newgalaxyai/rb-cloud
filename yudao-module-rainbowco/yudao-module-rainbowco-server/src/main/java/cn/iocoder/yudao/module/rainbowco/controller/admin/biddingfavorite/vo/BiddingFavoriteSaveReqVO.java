package cn.iocoder.yudao.module.rainbowco.controller.admin.biddingfavorite.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 招标信息收藏新增/修改 Request VO")
@Data
public class BiddingFavoriteSaveReqVO {

    @Schema(description = "日志主键", example = "6721")
    private Long id;

    @Schema(description = "招标id", requiredMode = Schema.RequiredMode.REQUIRED, example = "7378")
    @NotNull(message = "招标id不能为空")
    private Long biddingId;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "23629")
    @NotNull(message = "用户编号不能为空")
    private Long userId;

}