import com.anke.yingxiang.rest.RestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by qingxiangzhang on 2017/11/17.
 */


@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ResttemplateDemoApplicationTests {

    @Autowired
    private RestService restService;

    @Test
    public void simpleClientHttpRequestFactory() {

//        System.out.println(map);
//        System.out.println(map.get("errcode"));
//        System.out.println(map.get("errmsg"));
    }

}
