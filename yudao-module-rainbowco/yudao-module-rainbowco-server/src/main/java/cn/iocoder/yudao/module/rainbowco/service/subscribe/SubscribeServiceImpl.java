package cn.iocoder.yudao.module.rainbowco.service.subscribe;

import cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo.SubscribePageReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo.SubscribeSaveReqVO;
import cn.iocoder.yudao.module.rainbowco.controller.admin.subscribe.vo.SubscribeImportExcelVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.rainbowco.dal.dataobject.subscribe.SubscribeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.rainbowco.dal.mysql.subscribe.SubscribeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.rainbowco.enums.ErrorCodeConstants.SUBSCRIBE_NOT_EXISTS;

/**
 * 订阅 Service 实现类
 *
 * @author 润邦集团
 */
@Service
@Validated
public class SubscribeServiceImpl implements SubscribeService {

    @Resource
    private SubscribeMapper subscribeMapper;

    @Override
    public Long createSubscribe(SubscribeSaveReqVO createReqVO) {
        // 插入
        SubscribeDO subscribe = BeanUtils.toBean(createReqVO, SubscribeDO.class);
        subscribeMapper.insert(subscribe);

        // 返回
        return subscribe.getId();
    }

    @Override
    public void updateSubscribe(SubscribeSaveReqVO updateReqVO) {
        // 校验存在
        validateSubscribeExists(updateReqVO.getId());
        // 更新
        SubscribeDO updateObj = BeanUtils.toBean(updateReqVO, SubscribeDO.class);
        subscribeMapper.updateById(updateObj);
    }

    @Override
    public void deleteSubscribe(Long id) {
        // 校验存在
        validateSubscribeExists(id);
        // 删除
        subscribeMapper.deleteById(id);
    }

    @Override
        public void deleteSubscribeListByIds(List<Long> ids) {
        // 删除
        subscribeMapper.deleteByIds(ids);
        }


    private void validateSubscribeExists(Long id) {
        if (subscribeMapper.selectById(id) == null) {
            throw exception(SUBSCRIBE_NOT_EXISTS);
        }
    }

    @Override
    public SubscribeDO getSubscribe(Long id) {
        return subscribeMapper.selectById(id);
    }

    @Override
    public PageResult<SubscribeDO> getSubscribePage(SubscribePageReqVO pageReqVO) {
        return subscribeMapper.selectPage(pageReqVO);
    }

    @Override
    public cn.iocoder.yudao.framework.common.pojo.ImportResult importSubscribeList(List<SubscribeImportExcelVO> importList,Long type) {
        if (importList == null || importList.isEmpty()) {
            return cn.iocoder.yudao.framework.common.pojo.ImportResult.builder()
                    .totalRows(0)
                    .successRows(0)
                    .failedRows(0)
                    .status(cn.iocoder.yudao.framework.common.pojo.ImportStatus.SUCCESS)
                    .errors(new java.util.ArrayList<>())
                    .build();
        }

        java.util.List<cn.iocoder.yudao.framework.common.pojo.ImportErrorDetail> errors = new java.util.ArrayList<>();
        int successRows = 0;
        int totalRows = importList.size();

        for (int i = 0; i < importList.size(); i++) {
            SubscribeImportExcelVO importVO = importList.get(i);
            boolean hasError = false;
            int rowIndex = i + 2; // Excel 第一行是表头，数据从第 2 行开始

            // 校验订阅内容（非空；如包含逗号则需至少两项且每项非空）
            String contents = importVO.getContents();
            if (contents == null || contents.trim().isEmpty()) {
                errors.add(cn.iocoder.yudao.framework.common.pojo.ImportErrorDetail.builder()
                        .rowIndex(rowIndex)
                        .columnIndex(1) // 订阅内容列
                        .reason("订阅内容不能为空")
                        .build());
                hasError = true;
            } else if (contents.contains(",")) {
                String[] parts = contents.split(",");
                long nonEmptyCount = java.util.Arrays.stream(parts).map(String::trim).filter(s -> !s.isEmpty()).count();
                if (nonEmptyCount < 2) {
                    errors.add(cn.iocoder.yudao.framework.common.pojo.ImportErrorDetail.builder()
                            .rowIndex(rowIndex)
                            .columnIndex(1) // 订阅内容列
                            .reason("订阅内容需为逗号分隔的多项内容，且各项不能为空")
                            .build());
                    hasError = true;
                }
            }

            // 校验信息类型（非空；如包含逗号则需至少两项且每项非空）
            String typesStr = importVO.getTypes();
            if (typesStr == null || typesStr.trim().isEmpty()) {
                errors.add(cn.iocoder.yudao.framework.common.pojo.ImportErrorDetail.builder()
                        .rowIndex(rowIndex)
                        .columnIndex(2) // 信息类型列
                        .reason("信息类型不能为空")
                        .build());
                hasError = true;
            } else if (typesStr.contains(",")) {
                String[] parts = typesStr.split(",");
                long nonEmptyCount = java.util.Arrays.stream(parts).map(String::trim).filter(s -> !s.isEmpty()).count();
                if (nonEmptyCount < 2) {
                    errors.add(cn.iocoder.yudao.framework.common.pojo.ImportErrorDetail.builder()
                            .rowIndex(rowIndex)
                            .columnIndex(2) // 信息类型列
                            .reason("信息类型需为逗号分隔的多项内容，且各项不能为空")
                            .build());
                    hasError = true;
                }
            }

            // 若存在错误，则跳过写库
            if (hasError) {
                continue;
            }

            // 转换为DO对象并入库
            SubscribeDO subscribe = BeanUtils.toBean(importVO, SubscribeDO.class);
            subscribe.setType(type);
            subscribe.setUserId(getLoginUserId());
            subscribeMapper.insert(subscribe);

            successRows++;
        }

        int failedRows = totalRows - successRows;
        cn.iocoder.yudao.framework.common.pojo.ImportStatus status;
        if (failedRows == 0) {
            status = cn.iocoder.yudao.framework.common.pojo.ImportStatus.SUCCESS;
        } else if (successRows == 0) {
            status = cn.iocoder.yudao.framework.common.pojo.ImportStatus.FAILED;
        } else {
            status = cn.iocoder.yudao.framework.common.pojo.ImportStatus.PARTIAL_SUCCESS;
        }

        return cn.iocoder.yudao.framework.common.pojo.ImportResult.builder()
                .totalRows(totalRows)
                .successRows(successRows)
                .failedRows(failedRows)
                .status(status)
                .errors(errors)
                .build();
    }

}