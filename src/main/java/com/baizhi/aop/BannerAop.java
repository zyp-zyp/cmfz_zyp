package com.baizhi.aop;

import com.baizhi.service.BannerService;
import io.goeasy.GoEasy;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

@Component
@Aspect
public class BannerAop {
    @Resource
    private BannerService bannerService;
    //定一切入点
    @Pointcut(value = "execution(void com.baizhi.service.BannerService.insert(..))")
    public void expression(){
    }

    //后置通知
    @After("expression()")
    public void after(){
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-caac2a998f3044c1a91cb1ce88f67093");
        int[] ints = bannerService.selectDay7();
        goEasy.publish("zypDay7",Arrays.toString(ints));
        int[] ints1 = bannerService.selectMonth12();
        goEasy.publish("zypMonth12",Arrays.toString(ints1));
    }
}
