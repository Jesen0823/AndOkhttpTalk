package com.jesen.andokhttptalk.buildpatern;

// 用户
public class UserClient {

    public static void main(String[] args) {
        Designer designer = new Designer();

        designer.addHeight(2);
        designer.addWidth(100);
        designer.addColor("白色");

        designer.addHeight(3);
        designer.addWidth(120);
        designer.addColor("蓝色");

        House house = designer.build();
        System.out.println(house);

        // 链式调用
        House house1 = new Designer().addColor("黄色").addHeight(3).addWidth(80).build();
    }
}
