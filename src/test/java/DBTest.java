import com.anke.yingxiang.domain.UserInfo;
import com.anke.yingxiang.service.userinfo.UserInfoRepository;
import com.anke.yingxiang.service.userinfo.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DBTest {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void test(){
        String email = "zhangqingxiang@ankenj.com";
        UserInfo userInfo = userInfoRepository.findByEmail(email);
        System.out.println(userInfo.toString());
    }

}
