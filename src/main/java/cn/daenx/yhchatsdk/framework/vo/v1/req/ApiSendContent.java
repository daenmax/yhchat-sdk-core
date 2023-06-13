package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ApiSendContent {

    /**
     * 当消息类型为text、markdown时
     */
    private String text;

    /**
     * 当消息类型为image时
     */
    private String imageUrl;

    /**
     * 当消息类型为file时，有值
     */
    private String fileName;
    private String fileUrl;

    /**
     * 消息中包括button
     * 非必填
     */
    private List<ApiSendButton> buttons;

}
