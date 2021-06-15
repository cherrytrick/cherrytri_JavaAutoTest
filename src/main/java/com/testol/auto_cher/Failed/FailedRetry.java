package com.testol.auto_cher.Failed;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailedRetry implements IRetryAnalyzer {

    private int retrycount = 1;
    private static final int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retrycount <= maxRetryCount) {
            result.setAttribute("RETRY", retrycount);
            retrycount++;
            return true;
        }
        return false;
    }
}


