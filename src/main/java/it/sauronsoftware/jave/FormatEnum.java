package it.sauronsoftware.jave;

import java.io.File;

/**
 * @description:
 * @author: zhuyawei
 * @date: 2020-01-08 13:39
 */
public enum FormatEnum {
    def(){
        @Override
        public void handle(FFMPEGExecutor ffmpeg, File target) {
            ffmpeg.addArgument("-y");
            ffmpeg.addArgument(target.getAbsolutePath());
        }
    },
    ssegment(){
        @Override
        public void handle(FFMPEGExecutor ffmpeg, File target) {
            ffmpeg.addArgument("-map");
            ffmpeg.addArgument("0");
            ffmpeg.addArgument("-segment_format");
            ffmpeg.addArgument("mpegts");
            ffmpeg.addArgument("-segment_list");
            if(!target.getParentFile().exists())target.getParentFile().mkdirs();
            ffmpeg.addArgument(target.getAbsolutePath());//拼接存放目录
            ffmpeg.addArgument("-segment_time");
            ffmpeg.addArgument("10");
            ffmpeg.addArgument(target.getParent() + File.separator + "%03d.ts");//拼接存放目录
        }
    };

    FormatEnum() {}

    public abstract void handle(FFMPEGExecutor ffmpeg, File target);

    public static FormatEnum get(String name){
        if(name == null || name.isEmpty()) return def;
        try{
            return FormatEnum.valueOf(name);
        }catch (IllegalArgumentException e){
            return def;
        }
    }

    public static void main(String[] args) {

        System.out.println(FormatEnum.get("ssegment"));
    }

}
