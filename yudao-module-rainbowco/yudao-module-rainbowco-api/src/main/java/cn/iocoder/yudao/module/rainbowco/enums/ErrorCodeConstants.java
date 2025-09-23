package cn.iocoder.yudao.module.rainbowco.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Infra 错误码枚举类
 *
 * infra 系统，使用 1-001-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 参数配置 1-001-000-000 ==========
    ErrorCode CONFIG_NOT_EXISTS = new ErrorCode(1_001_000_001, "参数配置不存在");
    ErrorCode BIDDING_DOWNLOAD_NOT_EXISTS = new ErrorCode(1_001_000_002, "招标信息下载记录不存在");
    ErrorCode BIDDING_FAVORITE_NOT_EXISTS = new ErrorCode(1_001_000_003, "招标信息收藏不存在");
    ErrorCode BIDDING_HISTORY_NOT_EXISTS = new ErrorCode(1_001_000_004, "招标信息历史浏览记录不存在");

}
