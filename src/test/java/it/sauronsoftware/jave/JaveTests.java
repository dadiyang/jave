package it.sauronsoftware.jave;

import org.junit.Test;

import java.io.File;

/**
 * jave 格式转换测试
 * @author huangxuyang
 */
public class JaveTests {
    @Test
    public void amrToMp3()  {
        File source = new File("target/test-classes/material/testAudio.amr");
        File target = new File("testAudio.mp3");
        AudioUtils.amrToMp3(source, target);
    }
}
