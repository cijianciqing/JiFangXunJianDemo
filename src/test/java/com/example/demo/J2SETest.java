package com.example.demo;

import com.example.demo.beans.dingding.rizhi.MyImage;

public class J2SETest {


    public static void main(String[] args) {

        MyImage image1 = new MyImage();
        image1.setUrl("abc");

        MyImage image2 = new MyImage();
        image2.setUrl("abc");

        System.out.println(image1);
        System.out.println(image2);
//        synchronized (image1 == image2);
//        String a ="123";
//        String b="123";
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(a==b);
//        String str = "\"[3.28-6.47]\"";
//        System.out.println(str);
////        str = str.replaceAll("\\[", "");
////        str = str.replaceAll("\\]", "");
////去掉多余的双引号
//        str = str.replaceAll("[\"", "");
//        System.out.println(str);
    }
}
