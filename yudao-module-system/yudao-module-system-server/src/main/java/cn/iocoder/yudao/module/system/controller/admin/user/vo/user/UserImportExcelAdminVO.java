package cn.iocoder.yudao.module.system.controller.admin.user.vo.user;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户 Excel 导入 VO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class UserImportExcelAdminVO {

    @ExcelProperty("用户名称")
    private String nickname;

    @ExcelProperty("手机号码")
    private String mobile;
    @ExcelProperty("【导入说明】")
    private String importTip;
}
