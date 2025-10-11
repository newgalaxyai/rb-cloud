package cn.iocoder.yudao.framework.common.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Schema(description = "通用 - 导入结果对象")
@Data
@Builder
public class ImportResult {

    @Schema(description = "导入总行数", requiredMode = Schema.RequiredMode.REQUIRED)
    private int totalRows;

    @Schema(description = "导入成功行数", requiredMode = Schema.RequiredMode.REQUIRED)
    private int successRows;

    @Schema(description = "导入失败行数", requiredMode = Schema.RequiredMode.REQUIRED)
    private int failedRows;

    @Schema(description = "导入状态（SUCCESS/FAILED/PARTIAL_SUCCESS）", requiredMode = Schema.RequiredMode.REQUIRED)
    private ImportStatus status;

    @Schema(description = "错误明细列表", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<ImportErrorDetail> errors;
}