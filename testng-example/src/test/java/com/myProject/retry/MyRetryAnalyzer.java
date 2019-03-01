package com.myProject.retry;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


/**
 * by:welkin
 对retry方法进行重写
 */
public class MyRetryAnalyzer implements IRetryAnalyzer {

    private int initReTryNum=0;
    private int maxReTryNum=3;

    @Override
    public boolean retry(ITestResult iTestResult) {

        if(initReTryNum<maxReTryNum){

            initReTryNum=initReTryNum+1;
            return true;
        }

    //    System.out.println("false");
        return false;
    }

    public int getInitReTryNum(){
        return this.initReTryNum;
    }

    public int getMaxReTryNum(){
        return this.maxReTryNum;
    }

}

