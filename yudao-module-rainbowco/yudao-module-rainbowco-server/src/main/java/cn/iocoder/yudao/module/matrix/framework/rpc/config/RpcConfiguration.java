package cn.iocoder.yudao.module.matrix.framework.rpc.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "matrixRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients()
public class RpcConfiguration {
}
