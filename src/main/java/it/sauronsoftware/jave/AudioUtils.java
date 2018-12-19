package it.sauronsoftware.jave;

import java.io.File;

/**
 * 音频转换工具
 *
 * @author dadiyang
 * date 2018/12/14
 */
public class AudioUtils {

    private static final String LIBMP_3_LAME = "libmp3lame";

    /**
     * amr转mp3
     *
     * @param sourcePath 音频来源目录
     * @param targetPath 目标存放地址
     */
    public static void amrToMp3(String sourcePath, String targetPath) {
        File source = new File(sourcePath);
        File target = new File(targetPath);
        amrToMp3(source, target);
    }

    /**
     * amr转mp3
     *
     * @param source 音频来源
     * @param target 目标存放地址
     */
    public static void amrToMp3(File source, File target) {
        convert(source, target, "mp3");
    }

    /**s
     * amr转wav
     *
     * @param source 音频来源
     * @param target 目标存放地址
     */
    public static void amrToWav(File source, File target) {
        convert(source, target, "wav");
    }

    public static void convert(File source, File target, String format) {
        if (!source.exists()) {
            throw new IllegalArgumentException("source file does not exists: " + source.getAbsoluteFile());
        }
        AudioAttributes audio = new AudioAttributes();
        Encoder encoder = new IgnoreErrorEncoder();
        audio.setCodec(LIBMP_3_LAME);
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat(format);
        attrs.setAudioAttributes(audio);
        try {
            encoder.encode(source, target, attrs);
        } catch (Exception e) {
            throw new IllegalStateException("convert amr to " + format + " error: ", e);
        }
    }
}
