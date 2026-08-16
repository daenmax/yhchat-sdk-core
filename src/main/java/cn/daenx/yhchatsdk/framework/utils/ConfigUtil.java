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
class ConfigUtil {
    @Getter
    private static String token;
    @Getter
    private static String url;

    @Value("${yhchat.token}")
    public void setToken(String token) {
        ConfigUtil.token = token;
    }

    @Value("${yhchat.url}")
    public void setUrl(String url) {
        ConfigUtil.url = url;
    }


}
