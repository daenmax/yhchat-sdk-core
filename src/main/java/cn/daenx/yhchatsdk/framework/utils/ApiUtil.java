package cn.daenx.yhchatsdk.framework.utils;

import cn.daenx.yhchatsdk.framework.vo.v1.ret.ApiSendRetV1;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiUtil {
    private static String token;
    private static String url = "https://chat-go.jwzhd.com/open-apis/v1/bot";

    @Value("${yhchat.token}")
    public void setToken(String token) {
        ApiUtil.token = token;
    }

    public static String getToken() {
        return token;
    }

    public static ApiSendRetV1 sendMsg() {
        String urlPost = url + "/send?token=" + token;
        String content = "{\n" +
                "    \"recvId\": \"4137637\",\n" +
                "    \"recvType\": \"user\",\n" +
                "    \"contentType\": \"text\",\n" +
                "    \"content\": {\n" +
                "        \"text\": \"测试\"\n" +
                "    }\n" +
                "}";
        String body = HttpRequest.post(urlPost).header("Content-Type", "application/json; charset=utf-8").body(content).execute().body();
        System.out.println(body);
        ApiSendRetV1 apiSendRetV1 = JSONUtil.toBean(body, ApiSendRetV1.class);
        return apiSendRetV1;
    }
}
