package cn.iocoder.yudao.module.rainbowco.dal.dataobject.biddinghistory;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 招标信息历史浏览记录 DO
 *
 * @author 芋道1
 */
@TableName("rb_bidding_history")
@KeySequence("rb_bidding_history_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BiddingHistoryDO extends BaseDO {

    /**
     * 日志主键
     */
    @TableId
    private Long id;
    /**
     * 招标id
     */
    private Long biddingId;
    /**
     * 用户编号
     */
    private Long userId;


}