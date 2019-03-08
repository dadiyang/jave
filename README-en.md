[中文](./README.md)

# Jave, An Audio Transcoding Tool

This tool is mainly used to convert the AMR to MP3 format for playing in the audio tag of HTML5.

Linux, Windows and Mac is all supported.

Based on a well known project, named JAVE which relies on [ffmpeg](http://ffmpeg.org/), this tool can be used to all ffmpeg supported format conversion. See [JAVE official documentation](http://www.sauronsoftware.it/projects/jave/manual.php) for details.

# USE EXAMPLE

## Inclue Maven Depandency

```xml
 <dependency>
    <groupId>com.github.dadiyang</groupId>
    <artifactId>jave</artifactId>
    <version>1.0.4</version>
 </dependency>
```

## Invoke `AudioUtils.amrToMp3` Method

Just 3 lines, JAVE make it extraordinary easy.

```java
public void amrToMp3()  {
    File source = new File("target/test-classes/material/testAudio.amr");
    File target = new File("testAudio.mp3");
    AudioUtils.amrToMp3(source, target);
}
```

# HOW IT WORKS

1. Determine the current running environment when initializing and copy the corresponding ffmpeg executable file in classpath:bin directory to a temporary directory
2. Invoke Runtime.getRuntime().exec(cmd) to execute ffmpeg's transcode command according to the format and configuration. 

# PROBLEMS OF THE OLD JAVE PROJECT

Ffmpeg is dependent on runtime platform, and JAVE project encapsulation the ffmpeg, it make ffmpeg has cross-platform features by the above mechanism. However, the old JAVE project has the following problems:

1. It's too old. The latest time they release it was 2009. The ffmpeg it involved was outdated and didn't work in many conditions.
2. EncoderException: Stream mapping somethings occur.
3. It didn't publish to Maven Central Repository. Besides, JAVE itself is not a maven project.
4. Mac was not supported.

Therefore, I created this project to solve the above problems.

# FEATURE OF THIS NEW JAVE

1. This is a maven project and has published to [Maven Central Repository](https://mvnrepository.com/artifact/com.github.dadiyang/jave).
2. The ffmpeg was updated and verified. (The project provided an unit test to easily verified)
3. Avoid the EncoderException: Stream mapping.
4. Linux, Windows and Mac is all supported.

# EXTENSION

Set `System.setProperty("ffmpeg.home", "your ffmpeg executable file's path")` to specify your own ffmpeg executable when the provided one doesn't match your runtime.

eg. `System.setProperty("ffmpeg.home", "/usr/local/bin/")`

# REFERENCES

* [JAVE](http://www.sauronsoftware.it/projects/jave/download.php) 
* [xiaoymin's jave](https://github.com/xiaoymin/jave)
* [解決linux AMR轉MP3出現轉碼成功卻無法播放的問題](https://hk.saowen.com/a/2ec2a73ec73091967c3ebdb5697832006cb255a7183377b6e8fae1c13f5e54bc)

# LICENSE

JAVE project is open source following GPL License, and this new JAVE project is modified and enhanced on the basis of JAVE, hence GPL License is adopted.