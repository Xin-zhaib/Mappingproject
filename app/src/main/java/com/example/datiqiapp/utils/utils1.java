package com.example.datiqiapp.utils;

import com.example.datiqiapp.bean.Question;

import java.util.ArrayList;
import java.util.List;
//工具类

//这些全局变量可以在应用程序的任何地方进行访问和修改
// 因此可以方便地在不同的 Activity 或 Fragment 之间共享数据。
// 例如，在一个 Activity 中获取用户的名字，可以直接使用 utils1.NAME；在另一个 Activity 中获取题目数据，可以使用 utils1.listdata
public class utils1 {
    //全局变量字符串,字符串类型，用于存储用户的名字；
    public static String NAME="";
   //全局List集合,Question 类型的 List 集合，用于存储题目数据。
    public static List<Question>  listdata =new ArrayList<>();
}
