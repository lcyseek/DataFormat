package com.example.luchunyang.jsonformat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class GsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
    }

    public void createJson(View view) {
        //Gson是主类，它暴露出fromJson()和toJson()方法进行转换工作，对于默认实现，可以直接创建对象，
        //也可以使用GsonBuilder类提供的实用选项进行转换，比如整齐打印，字段命名转换，排除字段，日期格式化，等等。
        Gson gson = new Gson();

        //添加一个对象
        Person p = new Person();
        p.setAddress("gd");
        p.setAge(11);
        p.setName("lcy");
        p.setSex("man");//非序列化字段,不计入

        //Object-->json
        String json = gson.toJson(p);
        System.out.println(json);

        //json-->Object
        Person object = gson.fromJson(json,Person.class);
        System.out.println("解析出来的Person:"+ object);


        //添加list
        ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setName("小李飞刀"+i);
            person.setAge(i);
            person.setAddress("china"+i);
            list.add(person);
        }
        json = gson.toJson(list);
        System.out.println(json);

        //解析list.可以看到代码使用了TypeToken，它是gson提供的数据类型转换器，可以支持各种数据集合类型转换。
        List<Person> lists = gson.fromJson(json,new TypeToken<List<Person>>(){}.getType());
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }

    }

    public void createJson1(View view) {
        Gson gson = new Gson();
        int []ids = {11,12,13};
        String[] names = {"小李飞刀","惊鸿仙子","狄仁杰"};

        String idJson = gson.toJson(ids);
        String nameJson = gson.toJson(names);
        System.out.println(idJson);
        System.out.println(nameJson);
    }

    public void createJson2(View view) {
        HashMap<String,String> map = new HashMap<>();
        map.put("name","seek");
        map.put("phone","07850-666");
        map.put("email","luchunyang@gmail.com");

        Gson gson = new Gson();
        String json = gson.toJson(map);

        System.out.println(json);

        HashMap<String,String> maps =  gson.fromJson(json,map.getClass());
        Set<String> keys = maps.keySet();
        for (String key:keys){
            System.out.println(""+key+"="+maps.get(key));
        }

    }

    public void createJson3(View view) {
        Gson gson = new Gson();

        Student student = new Student();
        student.id = 1;
        student.nickName = "乔晓松";
        student.age = 22;
        student.email = "965266509@qq.com";
        System.out.println("仅包含基本数据类型的数据结构:"+gson.toJson(student));

        ArrayList<String> books = new ArrayList<String>();
        books.add("数学");
        books.add("语文");
        books.add("英语");
        books.add("物理");
        books.add("化学");
        books.add("生物");
        student.books = books;
        System.out.println("除了基本数据类型还包含了List集合:"+gson.toJson(student));
        

        HashMap<String, String> booksMap = new HashMap<String, String>();
        booksMap.put("1", "数学");
        booksMap.put("2", "语文");
        booksMap.put("3", "英语");
        booksMap.put("4", "物理");
        booksMap.put("5", "化学");
        booksMap.put("6", "生物");
        student.booksMap = booksMap;
        String result = gson.toJson(student);
        System.out.println("除了基本数据类型还包含了List和Map集合:"+result);

        Student studentG = gson.fromJson(result, Student.class);
        System.out.println("解析出的Student:"+studentG);
    }
}
