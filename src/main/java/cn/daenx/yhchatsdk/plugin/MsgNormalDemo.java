package cn.daenx.yhchatsdk.plugin;

import cn.daenx.yhchatsdk.common.constant.ChatTypeConstant;
import cn.daenx.yhchatsdk.common.constant.ContentTypeConstant;
import cn.daenx.yhchatsdk.framework.eventInterface.EventMessageReceiveNormal;
import cn.daenx.yhchatsdk.framework.vo.EventMsgVo;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Order(1)//默认为0
public class MsgNormalDemo implements EventMessageReceiveNormal {
    @Override
    public Integer handle(EventMsgVo eventMsgVo) {
        String s = JSONUtil.toJsonStr(eventMsgVo);
        System.out.println(s);
        String chatType = eventMsgVo.getEvent().getMessage().getChatType();
        String chatId = eventMsgVo.getEvent().getChat().getChatId();
        String senderNickname = eventMsgVo.getEvent().getSender().getSenderNickname();
        String senderId = eventMsgVo.getEvent().getSender().getSenderId();
        String contentType = eventMsgVo.getEvent().getMessage().getContentType();
        String msg = null;
        if (ContentTypeConstant.TEXT.equals(contentType) || ContentTypeConstant.MARKDOWN.equals(contentType)) {
            msg = eventMsgVo.getEvent().getMessage().getContent().getText();
        } else if (ContentTypeConstant.IMAGE.equals(contentType)) {
            msg = eventMsgVo.getEvent().getMessage().getContent().getImageUrl() + " | " + eventMsgVo.getEvent().getMessage().getContent().getImageName() + " | " + eventMsgVo.getEvent().getMessage().getContent().getEtag();
        } else if (ContentTypeConstant.FILE.equals(contentType)) {
            msg = eventMsgVo.getEvent().getMessage().getContent().getFileUrl() + " | " + eventMsgVo.getEvent().getMessage().getContent().getFileName() + " | " + eventMsgVo.getEvent().getMessage().getContent().getFileSize() + " | " + eventMsgVo.getEvent().getMessage().getContent().getEtag();
        }
        if (ChatTypeConstant.GROUP.equals(chatType)) {
            log.info("【群聊消息】群号[{}]，用户[{}]（{}）发的[{}]消息：{}", chatId, senderId, senderNickname, contentType, msg);
        } else if (ChatTypeConstant.BOT.equals(chatType)) {
            log.info("【私聊消息】用户[{}]（{}）发的[{}]消息：{}", senderId, senderNickname, contentType, msg);
        }
        log.info("我是sdk jar里写的案例实现方法-1");
        return 0;
    }
}
