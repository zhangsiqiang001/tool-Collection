package com.itgod.restController.newJava8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


/**
 * @author zhangsq
 * @date 2021/6/11 12:02
 */
public class lambdatest {

    public static void main(String[] args) {
        String test = "aaa";
        List<String> featuree = Arrays.asList("Lambdas", "default Method");
        for (String o : featuree) {
            System.out.println(o);
        }
        featuree.forEach(n -> System.out.println(n));
        featuree.forEach(n -> dofilter(n.startsWith("L")));
        System.out.println("Languages which starts with J:");
       // filter(featuree, (str) -> str.startsWith("J"));
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.sort((t1, t2) -> t2 - t1);
        list.forEach(System.out::println);
    }

    public static void dofilter(boolean a) {
        if (a) {
            System.out.println(a);
        }
    }




}
