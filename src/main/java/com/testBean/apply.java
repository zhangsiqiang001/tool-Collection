package com.testBean;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author zhangsq
 * @date 2021/6/3 15:42
 */
@Data
public class apply implements InitializingBean, Runnable{
    private String aaa;

    public String getadd() {
        System.out.println("我被加载了");
        return null;
    }

    @Override
    public void run() {
        System.out.println("我调用run方法了");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我调用afterPropertiesSet");
    }
}
