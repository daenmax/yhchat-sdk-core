package cn.daenx.yhchatsdk.framework.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * API配置工具类
 *
 * @author DaenMax
 */
@Component
class CommonUtil {
    @Getter
    private static String token;
    @Getter
    private static String mode;
    @Getter
    private static String httpUrl ="https://chat-go.jwzhd.com/open-apis/v1";
    @Getter
    private static String webSocketUrl="wss://ws.jwzhd.com/subscribe";

    @Value("${yhchat.token}")
    public void setToken(String token) {
        CommonUtil.token = token;
    }
    @Value("${yhchat.mode}")
    public void setMode(String mode) {
        CommonUtil.mode = mode;
    }



}
