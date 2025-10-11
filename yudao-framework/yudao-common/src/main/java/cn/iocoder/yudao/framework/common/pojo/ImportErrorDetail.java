package cn.iocoder.yudao.framework.common.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "通用 - 导入错误明细")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImportErrorDetail {

    @Schema(description = "错误所在的行号（从 1 开始）", requiredMode = Schema.RequiredMode.REQUIRED)
    private int rowIndex;

    @Schema(description = "错误所在的列号（从 1 开始）", requiredMode = Schema.RequiredMode.REQUIRED)
    private int columnIndex;

    @Schema(description = "错误原因描述", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reason;
}