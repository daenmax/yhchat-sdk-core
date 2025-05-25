package cn.daenx.yhchatsdk.framework.vo.v1.req;

import cn.daenx.yhchatsdk.common.constant.ContentTypeConstant;
import lombok.Data;


/**
 * 上传接口请求体
 *
 * @author DaenMax
 */
@Data
public class ApiUploadReqV1 {
    // image/video/file
    private String type;
    private String filePath;

    //快捷构造方法

    /**
     * 上传图片
     *
     * @param filePath 本地文件路径
     * @return
     */
    public ApiUploadReqV1 Image(String filePath) {
        this.type = ContentTypeConstant.IMAGE;
        this.filePath = filePath;
        return this;
    }
    /**
     * 上传视频
     *
     * @param filePath 本地文件路径
     * @return
     */
    public ApiUploadReqV1 Video(String filePath) {
        this.type = ContentTypeConstant.VIDEO;
        this.filePath = filePath;
        return this;
    }
    /**
     * 上传文件
     *
     * @param filePath 本地文件路径
     * @return
     */
    public ApiUploadReqV1 File(String filePath) {
        this.type = ContentTypeConstant.FILE;
        this.filePath = filePath;
        return this;
    }

}
