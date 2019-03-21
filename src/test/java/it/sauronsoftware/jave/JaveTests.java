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
    private File mp3Source = new File("target/test-classes/material/testAudio.mp3");

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

    @Test
    public void getDuration() {
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo amrInfo = encoder.getInfo(source);
            System.out.println("amr duration:" + amrInfo.getDuration());
            assertTrue("Duration of the amr source file must greater than 0", amrInfo.getDuration() > 0);
            MultimediaInfo mp3Info = encoder.getInfo(mp3Source);
            System.out.println("mp3 duration: " + mp3Info.getDuration());
            assertTrue("Duration of the mp3 source file must greater than 0", mp3Info.getDuration() > 0);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }
}
