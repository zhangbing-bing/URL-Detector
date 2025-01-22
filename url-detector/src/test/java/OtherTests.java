import com.linkedin.urls.Url;
import com.linkedin.urls.detection.UrlDetector;
import com.linkedin.urls.detection.UrlDetectorOptions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhangbing3
 * @Date 2025/1/22 10:03
 * @Desc
 */
public class OtherTests {

    @Test
    public void t1() {
        char ch = 192;
        System.out.println("ch = " + ch);
    }

    @Test
    public void t0() {
        // 性能优化：正则比较复杂用正则提取一万个文本要三十秒，有业务方反馈说一分钟后还没收到检测结果，然后我调研了一下这个技术，性能比正则提升了三倍左右；
        // 一开始是需要人工复验后才放出的，后面人工来不及看，优化成先下发人工后检验逻辑，并且我们动态维护了一个非法外链库，可以减轻人工看的压力；
        // 问题修复：有一些问题，对中文不支持，首先我先把正文替换了，然后改了源码，在判断分隔的地方加了中文标点判断。
        // 说一下它的原理
        // 把URL分为几个部分，协议://用户名:密码@域名:端口/相对路径?查询条件#片段
        // 针对这几个部分定义了一个状态机，每个部分是一个状态，以及状态之间的转移关系
        // 比如：协议的下一个状态可以是用户名和域名，域名的下一个状态可以是端口和相对路径、相对路径的下一个状态可以是查询条件和对应片段
        // 有状态可以是结尾，比如域名、端口、相对路径、查询条件和片段
        // 如何识别状态：用分隔符，读到:就会去尝试读取协议或用户名密码，读到.就会去尝试读取域名，
        // 每个读取器都有读取和检测逻辑，比如最重要的域名，会去尝试向右侧读取域名的最远结束的地方，这里就是判断结束符，比如空格和各种英文标点，我在这里加了中文标点的判断；
        // 读取完了后会检测域名是不是合法，用的是很多结尾判断比如.com、.us、.net等结尾。

        // 收获：1、熟练编写正则表达式，这个在很多场景都能用到；2、状态机设计模式的学习


        String text = "a.ca";
        UrlDetector urlDetector = new UrlDetector(text, UrlDetectorOptions.Default);
        List<String> collect = urlDetector.detect().stream().map(Url::getOriginalUrl).collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }
}
