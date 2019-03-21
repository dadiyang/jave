package it.sauronsoftware.jave;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * 测试视频工具
 *
 * @author huangxuyang
 * @date 2019/3/21
 */
public class VideoUtilsTest {

    @Test
    public void thumbnail() {
        File source = new File("target/test-classes/material/testVideo.mov");
        File target = new File("testVideoThumbnail.png");
        VideoUtils.thumbnail(source, target, 0.5F);
        assertTrue("视频缩略图应该被生成", target.exists());
        assertTrue("视频缩略图大小应该大于0", target.length() > 0);
    }
}