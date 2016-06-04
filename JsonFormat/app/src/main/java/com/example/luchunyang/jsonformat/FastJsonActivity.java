package com.example.luchunyang.jsonformat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 阿里巴巴FastJson是一个Json处理工具包，包括“序列化”和“反序列化”两部分，它具备如下特征：
 * 速度最快，测试表明，fastjson具有极快的性能，超越任其他的Java Json parser
 * 功能强大，完全支持Java Bean、集合、Map、日期、Enum，支持范型，支持自省；无依赖
 */

public class FastJsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_json);
    }

    public void createJson(View view) {
        int []ids = {11,14,13,35};
        String[] names = {"小李飞刀","惊鸿仙子","狄仁杰","Android"};

        JSONArray array = (JSONArray) JSON.toJSON(ids); //将JavaBean转换为JSONObject或者JSONArray。
        String nameJson = JSON.toJSONString(names);     //将JavaBean序列化为JSON文本

        System.out.println(array);
        System.out.println(nameJson);

        //{"name":"陆春阳","phone":"0987-2566644","email":["luchunyang@gmail.con","1352913549@qq.com"]}
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("name","陆春阳");
        jsonObject.put("phone","0987-2566644");

        JSONArray jsonArray = new JSONArray();
        jsonArray.add("luchunyang@gmail.con");
        jsonArray.add("1352913549@qq.com");
        jsonObject.put("email",jsonArray);

        System.out.println(jsonObject);


        String s = "[{\"id\":\"0375\",\"city\":\"平顶山\"},{\"id\":\"0377\",\"city\":\"南阳\"}]";
        JSONArray array1 = JSON.parseArray(s);
        System.out.println("json数组"+array1);

        JSONObject object = (JSONObject) array1.get(0);
        System.out.println("json对象"+object);
    }

    public void createJson1(View view) {

        //添加一个对象
        Person p = new Person();
        //p.setAddress("gd");
        p.setAge(11);
        p.setName("lcy");
        p.setSex("man");//非序列化字段,不计入

        String json = JSON.toJSONString(p,SerializerFeature.WriteMapNullValue);
        System.out.println(json);


        Person person = JSON.parseObject(json,Person.class);
        System.out.println("解析出来的:"+person);


        ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Person person1 = new Person();
            person1.setName("小李飞刀"+i);
            person1.setAge(i);
            person1.setAddress("china"+i);
            list.add(person1);
        }

        json = JSON.toJSONString(list);
        System.out.println(json);
        List<Person> persons = JSON.parseObject(json,new TypeReference<List<Person>>(){});
        for (int i = 0; i < persons.size(); i++) {
            System.out.println(persons.get(i));
        }

    }

    public void createJson2(View view) {
        //{"email":"luchunyang@gmail.com","phone":"07850-666","name":"seek"}
        HashMap<String,String> map = new HashMap<>();
        map.put("name","seek");
        map.put("phone","07850-666");
        map.put("email","luchunyang@gmail.com");


        String json = JSON.toJSONString(map);
        System.out.println(json);

        HashMap<String,String> m = new HashMap<>();
//        m = JSON.parseObject(json,m.getClass());
        m = JSON.parseObject(json,new TypeReference<HashMap<String, String>>(){});
        Set<String> keys = m.keySet();
        for (String key:keys){
            System.out.println(""+key+"="+m.get(key));
        }
    }

    public void createJson3(View view) {

        Student student = new Student();
        student.id = 1;
        student.nickName = "乔晓松";
        student.age = 22;
        student.email = "965266509@qq.com";
        System.out.println("仅包含基本数据类型的数据结构:"+JSON.toJSON(student));

        ArrayList<String> books = new ArrayList<String>();
        books.add("数学");
        books.add("语文");
        books.add("英语");
        books.add("物理");
        books.add("化学");
        books.add("生物");
        student.books = books;
        System.out.println("除了基本数据类型还包含了List集合:"+JSON.toJSONString(student));


        HashMap<String, String> booksMap = new HashMap<String, String>();
        booksMap.put("1", "数学");
        booksMap.put("2", "语文");
        booksMap.put("3", "英语");
        booksMap.put("4", "物理");
        booksMap.put("5", "化学");
        booksMap.put("6", "生物");
        student.booksMap = booksMap;
        String result = JSON.toJSONString(student);
        System.out.println("除了基本数据类型还包含了List和Map集合:"+result);

        Student studentG = JSON.parseObject(result,new TypeReference<Student>(){});
        System.out.println("解析出的Student:"+studentG);
    }

    /*Fastjson序列化时，可以指定序列化的特性，以满足不同的序列化需求:SerializerFeature
        QuoteFieldNames———-输出key时是否使用双引号,默认为true
        WriteMapNullValue——–是否输出值为null的字段,默认为false
        WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
        WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
        WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
        WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
    */

    public void dateJson(View view) {
        Date date = new Date();

        //输出1465024071179 毫秒值
        System.out.println(JSON.toJSONString(date));

        //默认格式为yyyy-MM-dd HH:mm:ss
        System.out.println(JSON.toJSONString(date, SerializerFeature.WriteDateUseDateFormat));

        //根据自定义格式输出日期
        System.out.println(JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat));
    }
}
