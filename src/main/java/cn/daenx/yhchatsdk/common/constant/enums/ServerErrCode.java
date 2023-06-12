package cn.daenx.yhchatsdk.common.constant.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务端错误代码
 */
public enum ServerErrCode {

    SUCCESS("1", "正常"),
    INVALID_PARAMETER("1002", "参数缺失或者有误"),
    UNAUTHORIZED("1003", "未授权"),
    BOT_BANNED("1004", "机器人被封禁"),
    CHAT_BANNED("1005", "会话被封禁"),
    FREQUENCY_LIMIT("1007", "触发频次限制");

    private String code;
    private String desc;

    private static final Map<String, String> map;
    private static final List<String> list;

    static {
        map = new HashMap<>();
        list = new ArrayList<>();
        for (ServerErrCode value : ServerErrCode.values()) {
            map.put(value.getCode(), value.getDesc());
            list.add(value.getCode());
        }
    }

    public static String getDesc(String code) {
        String desc = map.get(code);
        if (desc == null) {
            return "";
        }
        return desc;
    }

    public static boolean hasCode(String code) {
        return list.contains(code);
    }

    ServerErrCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    ServerErrCode() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
