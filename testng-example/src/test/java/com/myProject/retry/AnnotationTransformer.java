package com.myProject.retry;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/**
 * by:welkin
 * 对@Test 注释进行重写
 */
public class AnnotationTransformer implements IAnnotationTransformer {

/**
IAnnotationTransformer 只能用来修改 @Test 注释，
如果需要修改其他 TestNG 的注释（比如，@DataProvider, @Factory 以及 @Configuration），
需要使用 IAnnotationTransformer2 监听器。
 */

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {

        IRetryAnalyzer retry=annotation.getRetryAnalyzer();

        if(retry==null) {
            annotation.setRetryAnalyzer(MyRetryAnalyzer.class);
        }

    }

}