package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.*;

/**
 * 接口响应体
 */

public class ApiSendReqV1 {
  /**
   * 接收消息对象ID
   * 用户: userId
   * 群: groupId
   */
  private String recvId;
  /**
   * 接收对象类型
   * 用户: user
   * 群: group
   */
  private String recvType;
  /**
   * 消息类型，取值如下
   * text\image\file\markdown
   */
  private String contentType;
  private String content;

  @Data
  public static class Content {
    /**
     * 当消息类型为text、markdown时，有值
     */
    private String text;

    /**
     * 当消息类型为image时，有值
     */
    private String imageUrl;
    private String imageName;

    /**
     * 当消息类型为file时，有值
     */
    private String fileName;
    private String fileUrl;
    /**
     * 文件大小，单位Byte，字节
     */
    private Long fileSize;

    /**
     * 当消息类型为image、file时，有值
     */
    private String etag;
  }

}
