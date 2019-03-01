package com.myProject.testcase;

import org.testng.annotations.DataProvider;

/**
 * by：welkin
 * DataProvider放在一个单独的类中
 */

public class TestDataProvider {

    @DataProvider
    public static Object[][] DataMethodNome(){
     return new Object[][]{
             {"data with no name 1"},
             {"data with no name 2"},
             {"data with no name 3"}
     };
    }


    @DataProvider(name ="datamethodwithname")
    public static Object[][] DataMethodWithName(){
        return new Object[][]{
                {"data with name 1"},
                {"data with name 2"},
                {"data with name 3"}
        };
    }
}
