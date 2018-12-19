package it.sauronsoftware.jave;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * jave 格式转换测试
 *
 * @author huangxuyang
 */
public class JaveTests {
    private File source = new File("target/test-classes/material/testAudio.amr");

    @Test
    public void amrToMp3() {
        File mp3Target = new File("testAudio.mp3");
        AudioUtils.amrToMp3(source, mp3Target);
        assertTrue(mp3Target.getAbsolutePath() + " must exists.", mp3Target.exists());
    }

    @Test
    public void amrToWav() {
        File wavTarget = new File("testAudio.wav");
        AudioUtils.amrToWav(source, wavTarget);
        assertTrue(wavTarget.getAbsolutePath() + " must exists.", wavTarget.exists());
    }
}
