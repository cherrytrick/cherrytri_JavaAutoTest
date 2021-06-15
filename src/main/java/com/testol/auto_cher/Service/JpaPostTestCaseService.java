package com.testol.auto_cher.Service;


import com.testol.auto_cher.Dao.InterfaceMsgDao;
import com.testol.auto_cher.Dao.TestCaseDao;
import com.testol.auto_cher.Dao.VariableSubstitutionDao;
import com.testol.auto_cher.Enity.InterfaceMsg;
import com.testol.auto_cher.Enity.TestCase;
import com.testol.auto_cher.Enity.VariableSubstitution;
import com.testol.auto_cher.Util.AssertionOverrideUtil;
import com.testol.auto_cher.Util.JsonUtil;
import com.testol.auto_cher.Client.RestTemplateClient;
import com.testol.auto_cher.Util.VariableUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
public class JpaPostTestCaseService {
    @Autowired
    private InterfaceMsgDao interfaceMsgDao;
    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private VariableSubstitutionDao variableSubstitutionDao;

    public void postToken() {
        List<InterfaceMsg> interfaceMsgList = interfaceMsgDao.findAll();
        List<TestCase> testCaseList = testCaseDao.findAll();
        List<VariableSubstitution> variableSubstitutionList = variableSubstitutionDao.findAll();
        List<String> responseList = new ArrayList<>();
        for (InterfaceMsg interfaceMsg : interfaceMsgList) {
            if (interfaceMsg.getId() != null) {
                for (TestCase testCase : testCaseList) {
                    String parames = testCase.getBodyParames();
                    if (testCase.getInterfacemsgId().equals(interfaceMsg.getId())) {
                        if (testCase.getInterfacemsgId().equals(interfaceMsg.getId())) {
                            //调用工具类把body的变量名替换为变量值并写入数据库中
                            parames = VariableUtil.replaceVariables(parames, variableSubstitutionList, variableSubstitutionDao);
                            //请求参数写入到allure报告中
                            Allure.addAttachment("Request", parames);
//                            Map<String, Object> paramesVariableValueMap = JsonUtil.json2map(parames);
                            Object postparames = JsonUtil.jsonToObject(String.class,testCase.getBodyParames());
                            String url = interfaceMsg.getUrlAddress();
                            String response = RestTemplateClient.post(url, postparames,String.class).getBody();
                            System.out.println(response);
                            //预期结果断言
                            Map<String, Object> assertExpectMap = JsonUtil.json2map(response);
                            int expected = (int) assertExpectMap.get("errcode");
                            if (expected == 0) {
                                Allure.step("测试通过!---这是预期的成功", Status.PASSED);
                                //响应结果写入allure报告中
                                Allure.addAttachment("Response", response);
                            } else {
                                Allure.step("测试通过！---这是预期的失败", Status.FAILED);
                                //响应结果写入allure报告中
                                Allure.addAttachment("Response", response);
                            }
                            AssertionOverrideUtil.verifyEquals(expected, 0);
                            //区分测试类型0是正向，1是反向
                            if (testCase.getCaseStatus() == 0) {
                                response = "正向测试响应结果----- : " + response;
                            } else {
                                response = "反向测试响应结果----- : " + response;
                            }
                            //获取响应结果更新数据库
                            testCaseDao.updateActual(response, testCase.getId());
                        }
                    }
                }
            }
        }
    }
}
