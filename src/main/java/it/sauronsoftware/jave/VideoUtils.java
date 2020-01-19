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
     * @param source      视频来源
     * @param imageTarget 缩略图存放目标文件
     * @param offset      时间（秒）
     */
    public static void thumbnail(File source, File imageTarget, float offset) {
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

    public static void mp4ToM3u8(String source, String target) {
        mp4ToM3u8(source, target, null, null);
    }

    public static void mp4ToM3u8(File source, File target) {
        mp4ToM3u8(source, target, null, null);
    }

    public static void mp4ToM3u8(String source, String target, VideoSize videoSize) {
        mp4ToM3u8(source, target, videoSize, null);
    }

    public static void mp4ToM3u8(File source, File target, VideoSize videoSize) {
        mp4ToM3u8(source, target, videoSize, null);
    }

    public static void mp4ToM3u8(String source, String target, VideoSize videoSize, EncoderProgressListener listener) {
        mp4ToM3u8(new File(source), new File(target), videoSize, listener);
    }

    /**
     * mp4转m3u8
     * @param source
     */
    public static void mp4ToM3u8(File source, File target, VideoSize videoSize, EncoderProgressListener listener) {
        Encoder encoder = new Encoder();
        VideoAttributes video = new VideoAttributes();
        video.setCodec("libx264");
        video.setSize(videoSize);
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("mp3");
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat(FormatEnum.ssegment.name());
        attrs.setVideoAttributes(video);
        attrs.setAudioAttributes(audio);
        try {
            encoder.encode(source, target, attrs, listener);
        } catch (Exception e) {
            throw new IllegalStateException("error: ", e);
        }
    }



}