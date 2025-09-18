package cn.iocoder.yudao.module.system.controller.admin.auth.vo;

import cn.iocoder.yudao.framework.common.validation.Mobile;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Schema(description = "管理后台 - 短信重置账号密码 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResetPasswordReqVO {

    @Schema(description = "旧密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    @NotEmpty(message = "旧密码不能为空")
    @Length(min = 4, max = 16, message = "旧密码长度为 4-16 位")
    private String oldPassword;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "buzhidao")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED, example = "15601691300")
    @NotEmpty(message = "手机号不能为空")
    @Mobile
    private String mobile;

    // @Schema(description = "短信验证码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    // @NotEmpty(message = "验证码不能为空")
    // private String code;

}