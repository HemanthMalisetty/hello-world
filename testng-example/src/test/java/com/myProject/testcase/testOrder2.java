package com.myProject.testcase;


import com.myProject.retry.MyRetryAnalyzer;
import com.myProject.retry.TestngListener;
import org.testng.annotations.*;

import javax.swing.plaf.PanelUI;

/**
 * by:welkin
 * 1.除了在xml中配置listener，可单独为某个@Test配置@Listeners，进行重传,例如如下注释部分，但是最好不用，
 * 以免xml 中配置listern，和为单独某个method配置进行重复。
 *
 * 2.将DataProvider与testcase放在不同的文件夹中，注意重传机制与dataprovider结合使用，会产生多余的run，
 * 例如3条数据在dataprovider中，则会在报告中显示run了三次
 *
 * 3.TestNg生命周期的例子。
 * *
 */


/***
 * 根据不指定顺序，按照首字母排序原则， 先执行a,后执行printOrder2，最后执行public void testDataprovider_2。
 */


//@Listeners({TestngListener.class})
public class testOrder2  {

    @BeforeClass
    public void beforeClass(){
        System.out.println("testOrder2.beforeClass---------------------------------");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("testOrder2.afterClass---------------------------------"+"\n");
    }

    @BeforeMethod
    public void beforeMthod(){
        System.out.println("testOrder2.beforeMethod-------------------");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("testOrder2.afterMethod-------------------");
    }
//    @Test(retryAnalyzer = MyRetryAnalyzer.class)
    @Test
    public  void printOrder2(){
        System.out.println("Order2's start time:"+System.nanoTime()+"\n");
    }

    //重传失败用例
    @Test
    public void a(){
        System.out.println("a"+2/0);
    }

    //此为dataprovider在不同类中的测试用例
    @Test(dataProvider = "datamethodwithname",dataProviderClass = TestDataProvider.class)
    public void testDataprovider_2(String a){
        System.out.println("this is:"+a);
    }


}