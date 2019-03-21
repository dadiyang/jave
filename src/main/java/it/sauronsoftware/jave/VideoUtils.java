package it.sauronsoftware.jave;

import java.io.File;

/**
 * 视频相关工具
 *
 * @author lynk-coder
 * @since 2019/3/21
 */
public class VideoUtils {

    /**
     * 获取视频缩略图
     *
     * @param source      视频来源
     * @param imageTarget 缩略图存放目标文件
     * @param offset      时间（秒）
     */
    public static void thumbnail(File source, File imageTarget, float offset) {
        if (!source.exists()) {
            throw new IllegalArgumentException("source file does not exists: " + source.getAbsoluteFile());
        }
        Encoder encoder = new IgnoreErrorEncoder();
        VideoAttributes video = new VideoAttributes();
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("image2");
        attrs.setOffset(offset);
        attrs.setDuration(1f);
        attrs.setVideoAttributes(video);
        try {
            encoder.encode(source, imageTarget, attrs);
        } catch (Exception e) {
            throw new IllegalStateException("error: ", e);
        }

    }
}