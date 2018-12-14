# 音频转码工具

本工具主要用于将微信语音 amr 格式转换为 mp3 格式以便在 html5 的 audio 标签中进行播放。

支持 Linux/Windows/Mac 平台

# 原理

1. 初始化时判断当前运行环境，将bin目录中对应的 ffmpeg 可执行文件拷贝到临时目录中
2. 通过 Runtime.getRuntime().exec(cmd) 执行 ffmpeg 对应的转码命令

# jave 项目的问题

ffmpeg 是依赖运行环境的，jave项目封装了ffmpeg，它通过上述的原理使 java 可以调用ffmpeg而且支持跨平台。

1. 项目老旧没再维护。官网最近版本是2009年发布的，其依赖的ffmpeg早已过时，很多情况下用不了。
2. 转码一直报异常 EncoderException: Stream mapping 
3. 没有发布maven仓库，而且jave本身也不是一个maven项目
4. 不支持mac

# 本项目特点

本项目为解决上述问题而生。

* 这是一个maven项目，可以发布到maven仓库。
* 项目依赖的 ffmpeg 可执行文件经过验证可以使用（单元测试中提供一个简单可选的检验方法）
* 解决了amr转mp3出现的 EncoderException （其实导致这个异常的原因并没有处理，只是直接忽略异常而已，而且忽略那些异常转换也是成功的）
* 支持mac

# 使用示例（只需三行代码）
```java
public void amrToMp3()  {
    File source = new File("target/test-classes/material/testAudio.amr");
    File target = new File("testAudio.mp3");
    AudioUtils.amrToMp3(source, target);
}
```

# 扩展

如果程序无法通过拷贝资源文件的方式获取到 ffmpeg 的可执行文件，你可以通过 System.setProperty("ffmpeg.home", "ffmpeg可执行文件所在的目录") 的方式指定 ffmpeg 文件目录
 
# 参考

本工具使用 [jave](http://www.sauronsoftware.it/projects/jave/download.php) 源码改造而来

借鉴 [xiaoymin](https://github.com/xiaoymin/jave) 的代码

参考 [解決linux AMR轉MP3出現轉碼成功卻無法播放的問題](https://hk.saowen.com/a/2ec2a73ec73091967c3ebdb5697832006cb255a7183377b6e8fae1c13f5e54bc)