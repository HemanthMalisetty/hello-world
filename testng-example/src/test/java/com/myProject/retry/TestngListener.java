package com.myProject.retry;

import org.testng.*;

import java.util.Iterator;

/**
 * by:welkin
 * 定义一个TestNGListener，来处理TestCase Retry的状态。
 * 在onTestFailure方法里，未达到最大retry次数的失败Case，
 * 我们把它的状态设置为SKIP，这样TestNG不会把它统计为Failed的test case。
 * */

public class TestngListener extends TestListenerAdapter {
    private int m_count = 0;

    @Override
    public void onTestFailure(ITestResult tr) {

        if(tr.getMethod().getRetryAnalyzer()!=null){
            MyRetryAnalyzer myIRetryAnalyzer=(MyRetryAnalyzer)tr.getMethod().getRetryAnalyzer();

            /**当运行次数小于规定的retry次数的时候*/
            if (myIRetryAnalyzer.getInitReTryNum()<myIRetryAnalyzer.getMaxReTryNum()) {
                tr.setStatus(ITestResult.SKIP);
            }
            // Reporter.setCurrentTestResult(null);
            else{
                tr.setStatus(ITestResult.FAILURE);
                log(tr.getName() + "--Test method failed\n");
            }
            Reporter.setCurrentTestResult(tr);
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log(tr.getName()+ "--Test method skipped\n");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log(tr.getName()+ "--Test method success\n");
       System.out.println(System.nanoTime());
    }

    /**等所有用例运行完之 后，检查用例
    如果fail的用例中存在重复的则在 fail的用例中剔除掉*/
    @Override
    public void onFinish(ITestContext iTestContext) {

        Iterator<ITestResult> skippedTestCases = iTestContext.getFailedTests().getAllResults().iterator();

        while (skippedTestCases.hasNext()) {
            ITestResult skippedTestCase = skippedTestCases.next();
            ITestNGMethod method=skippedTestCase.getMethod();

            if (iTestContext.getSkippedTests().getResults(method).size() > 1) {
                skippedTestCases.remove();
            }
            else {
                if (iTestContext.getPassedTests().getResults(method).size() > 0) {
                    skippedTestCases.remove();
                }
            }
        }
    }


    private void log(String string) {
        System.out.print(string);
        if (++m_count % 40 == 0) {
            System.out.println(" ");
        }
    }

}