package com.testol.auto_cher.Failed;


import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class FailedRetryListener implements IAnnotationTransformer {

    @Override
//    自动化测试重复失败的用例
//    使用反射的方法反射constructor属性和method。
//    此类可以重复使用，格式不变
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer retryAnalyzer =  annotation.getRetryAnalyzer();
        if (retryAnalyzer == null) {
            annotation.setRetryAnalyzer(FailedRetry.class);
        }

    }
}


