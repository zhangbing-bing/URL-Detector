import com.linkedin.urls.Url;
import com.linkedin.urls.detection.UrlDetector;
import com.linkedin.urls.detection.UrlDetectorOptions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhangbing3
 * @Date 2025/1/20 11:12
 * @Desc
 */
public class OtherTest2 {
    @Test
    public void t0() {
//        String content = "2810  9888，www.pwchk.com 852  + 852";
//        String content = " +，http://localhost";
        String content = " +，http//:localhost.com";
//        String content = "    电话： 2289  8888，传真： 2810  9888， 852  + 852  +，http://localhost";
        UrlDetector urlDetector = new UrlDetector(content, UrlDetectorOptions.ALLOW_SINGLE_LEVEL_DOMAIN);
        List<Url> detect = urlDetector.detect();
        System.out.println("detect = " + detect);
        List<String> collect = detect.stream().map(Url::getOriginalUrl).collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }
}
