package cn.daenx.yhchatsdk.framework.vo.v1.req;

import lombok.*;

/**
 * 请求体
 */
@Data
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
  private ApiSendContent content;

}
