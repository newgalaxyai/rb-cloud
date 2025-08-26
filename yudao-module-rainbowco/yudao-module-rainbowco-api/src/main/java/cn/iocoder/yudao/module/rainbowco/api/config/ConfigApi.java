package cn.iocoder.yudao.module.rainbowco.api.config;

import cn.iocoder.yudao.module.rainbowco.enums.ApiConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = ApiConstants.NAME) // TODO 芋艿：fallbackFactory =
@Tag(name = "RPC 服务 - 参数配置")
public interface ConfigApi {

    String PREFIX = ApiConstants.PREFIX + "/config";

}
