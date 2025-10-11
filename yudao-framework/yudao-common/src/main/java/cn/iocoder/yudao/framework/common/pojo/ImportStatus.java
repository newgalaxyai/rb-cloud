package cn.iocoder.yudao.framework.common.pojo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "通用 - 导入状态枚举")
public enum ImportStatus {
    SUCCESS,
    FAILED,
    PARTIAL_SUCCESS
}