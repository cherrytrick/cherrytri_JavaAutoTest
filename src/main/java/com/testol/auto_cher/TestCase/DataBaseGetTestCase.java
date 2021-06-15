package com.testol.auto_cher.TestCase;

import com.testol.auto_cher.Base.TestBase;
import com.testol.auto_cher.Service.JpaTestCaseService;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.testol.auto_cher.Listener.AssertListener.class)
public class DataBaseGetTestCase extends TestBase {
    @Autowired
    JpaTestCaseService jpaWXCaseService;
    @Severity( SeverityLevel.BLOCKER)
    @Description("jpa获取token覆盖测试")
    @Test
    public void gettokenJpaCase()  {
        jpaWXCaseService.getToken();
    }
}
