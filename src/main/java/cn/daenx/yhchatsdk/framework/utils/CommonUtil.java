package cn.daenx.yhchatsdk.framework.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置工具类
 *
 * @author DaenMax
 */
@Component
public class CommonUtil {
    @Getter
    private static String token;
    @Getter
    private static String mode;
    @Getter
    private static String httpUrl;
    @Getter
    private static String webSocketUrl;

    @Value("${yhchat.token}")
    public void setToken(String token) {
        CommonUtil.token = token;
    }
    @Value("${yhchat.mode}")
    public void setMode(String mode) {
        CommonUtil.mode = mode;
    }

    @Value("${yhchat-config.httpUrl}")
    public void setHttpUrl(String httpUrl) {
        CommonUtil.httpUrl = httpUrl;
    }

    @Value("${yhchat-config.webSocketUrl}")
    public void setWebSocketUrl(String webSocketUrl) {
        CommonUtil.webSocketUrl = webSocketUrl;
    }
}
