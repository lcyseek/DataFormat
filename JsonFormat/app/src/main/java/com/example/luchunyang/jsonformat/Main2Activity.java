package com.example.luchunyang.jsonformat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void createJson(View view) {
        //Gson是主类，它暴露出fromJson()和toJson()方法进行转换工作，对于默认实现，可以直接创建对象，
        //也可以使用GsonBuilder类提供的实用选项进行转换，比如整齐打印，字段命名转换，排除字段，日期格式化，等等。

        Gson gson = new Gson();
        Person p = new Person();
        p.setAddress("gd");
        p.setAge(11);
        p.setName("lcy");
        p.setSex("man");//非序列化字段,不计入
        System.out.println("===>"+gson.toJson(p));

        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Person person = new Person();
            person.setName("小李飞刀"+i);
            person.setAge(i);
            person.setAddress("china"+i);
            list.add(person);
        }

        String str = gson.toJson(list);
        System.out.println("json:"+str);
        //上面的代码重点是Gson对象，它提供了toJason()方法将对象转换成Json字符串

        //Gson提供了fromJson()方法来实现从Json相关对象到java实体的方法。
        Person person = getPerson(gson.toJson(p));
        System.out.println("解析出的Person:"+person);

        List<Person> persons = getPersonList(str);
        System.out.println("解析出的所有Person");
        for (int i = 0; i < persons.size(); i++) {
            Person p1 = persons.get(i);
            System.out.println(p1);
        }
    }

    public Person getPerson(String jsonStr){
        Person person = new Gson().fromJson(jsonStr,Person.class);
        return person;
    }

    public List<Person> getPersonList(String jsonStr){
        //可以看到代码使用了TypeToken，它是gson提供的数据类型转换器，可以支持各种数据集合类型转换。
        List<Person> list = new Gson().fromJson(jsonStr,new TypeToken<List<Person>>(){}.getType());
        return list;
    }

    public void createJson1(View view) {
        Gson gson = new Gson();
        int []ids = {11,12,13};
        String[] names = {"小李飞刀","惊鸿仙子","狄仁杰"};

        String idJson = gson.toJson(ids);
        String nameJson = gson.toJson(names);
        System.out.println(idJson);
        System.out.println(nameJson);

        int [] getIds = gson.fromJson(idJson,int[].class);
        String [] getNames = gson.fromJson(nameJson,String[].class);

        for (int i = 0; i < getIds.length; i++) {
            System.out.println(getIds[i]);
        }

        for (int i = 0; i < getNames.length; i++) {
            System.out.println(getNames[i]);
        }
    }

    public void createJson2(View view) {
        Gson gson = new Gson();

        Map<String,String> colours = new HashMap<>();
        colours.put("BLACK", "#000000");
        colours.put("RED", "#FF0000");
        colours.put("GREEN", "#008000");
        colours.put("BLUE", "#0000FF");
        colours.put("YELLOW", "#FFFF00");
        colours.put("WHITE", "#FFFFFF");

        String json = gson.toJson(colours);
        System.out.println(json);

        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> map = gson.fromJson(json, type);
        for (String key : map.keySet()) {
            System.out.println(""+ key + " = " + map.get(key));
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
