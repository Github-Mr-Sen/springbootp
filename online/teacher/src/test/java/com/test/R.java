package com.test;

import java.util.ArrayList;
import java.util.List;

public class R {
    public static void main(String[] args) {
        List<O> kk = new ArrayList<>();
        O o = new O();
        o.age = 10;
        kk.add(o);
        System.out.println(kk.get(0));
        o.love = "许珂";
        o.name = "zaisen";
        System.out.println(kk.get(0));



    }
}
