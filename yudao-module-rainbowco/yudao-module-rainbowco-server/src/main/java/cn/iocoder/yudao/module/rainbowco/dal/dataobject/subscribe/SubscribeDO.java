package cn.iocoder.yudao.module.rainbowco.dal.dataobject.subscribe;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订阅 DO
 *
 * @author 润邦集团
 */
@TableName("rb_subscribe")
@KeySequence("rb_subscribe_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeDO extends BaseDO {

    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 订阅内容（逗号拼接）
     */
    private String contents;
    /**
     * 信息类型（逗号拼接）
     */
    private String types;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 类型（1.产品2.企业）
     */
    private Long type;

}