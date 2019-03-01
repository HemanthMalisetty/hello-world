package com.myProject.testcase;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.testng.annotations.*;

/**
 * by:welkin
 *1.xml中配置testOrder1与testOrder2的执行shunxu进行测试
 *2. 将DataProvider与testcase放在同一个文件中
 *3.Before,After 生命周期重传机制
 */
public class testOrder1 {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite----------------------------------------------------------------------------------");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite----------------------------------------------------------------------------------");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest-------------------------------------------------------------------");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("afterTest-------------------------------------------------------------------");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("testOrder1.beforeClass---------------------------------");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("testOrder1.afterClass---------------------------------"+"\n");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("testOrder1.beforeMethod-------------------");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("testOrder1.afterMethod-------------------");
    }

    @Test
    public  void printOrder1(){
        System.out.println("Order1's start time:"+System.nanoTime()+"\n");
    }

    @Test(priority = 1)
    public void printlning(){
        System.out.println("这是一条不打印的信息");
    }

    /**
     * 以下为：
     * @DataProvider机制实现的一种方式,This test will run 5 times since we have 5 parameters defined,
     *
     *  注意：DataProvider 与retry 同时使用的问题：以下有5组数据，若发生重传，则有5*3次重传。
     */
    @DataProvider(name = "test1")
    public static Object[][] primeNumbers() {
        return new Object[][] { { 1, true ,"hi"}, { 2, false ,"test"}, { 3, true ,"good"},
                { 4, false ,"aha"}, { 5, true ,"loo"} };
    }

    // This test will run 5 times since we have 5 parameters defined
    @Test(dataProvider = "test1")
    public void testDataprovider_1(Integer inputNumber,
                                       Boolean expectedResult, String test) {
        System.out.println(inputNumber + " " + expectedResult+" "+test);

    }
}
