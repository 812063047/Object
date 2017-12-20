package com.xzq.bos.test;

import java.util.Random;

import org.junit.Test;

public class NumTest {
	@Test
	public  void test(){
        // 返回一个0~(指定数-1)之间的随机值
        //Random random = new Random();
        double ran = (9+Math.random())*0.1;
        System.out.println(ran);
    }
}
