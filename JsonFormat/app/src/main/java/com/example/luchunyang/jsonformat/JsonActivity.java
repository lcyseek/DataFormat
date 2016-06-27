package com.example.luchunyang.jsonformat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
    }

    /*
    {
        "employees": [
        "hello Android"
        { "firstName":"John" , "lastName":"Doe" },
        { "firstName":"Anna" , "lastName":"Smith" },
        { "firstName":"Peter" , "lastName":"Jones" }
        ]
    }
    */
    public void createBasicJson(View view) throws JSONException {
        JSONObject employees = new JSONObject();
        JSONArray array = new JSONArray();
        //数组里面添加个值
        array.put("hello Android");

        JSONObject people = new JSONObject();
        people.put("firstName","John");
        people.put("lastName","Doe");
        //数组里添加对象
        array.put(people);

        people = new JSONObject();
        people.put("firstName","Anna");
        people.put("lastName","Smith");
        array.put(people);

        people = new JSONObject();
        people.put("firstName","Peter");
        people.put("lastName","Jones");
        array.put(people);

        employees.put("employees",array);

        System.out.println(employees.toString());
    }

    //JSONStringer是用来快速生成JSON文本的工具
    //JSONStringer是JSONWriter的子类;
    /*
    {
        "phone":[
                "133602554",
                "0785-12685446"
                ],
        "name":"seek",
        "email":[
                "luchunyang@gmail.com",
                "1352913549@qq.com"
                ]
    }
    */
    public void createBasicJson1(View view) throws JSONException {
        JSONStringer stringer = new JSONStringer();
        stringer.object();//表明开始一个对象，即添加｛
        stringer.key("phone");

        stringer.array();
        stringer.value("133602554").value("0785-12685446");
        stringer.endArray();

        stringer.key("name").value("seek");

        stringer.key("email").array().value("luchunyang@gmail.com").value("1352913549@qq.com").endArray();
        stringer.endObject();


        System.out.println(stringer.toString());
    }

    public void createBasicJson2(View view) throws IOException {
        //从内存中拿出一个对象模型写入流中
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(new FileOutputStream(Environment.getExternalStorageDirectory()+"/abc.txt")));
        writer.beginObject();
        writer.name("phone").value("133665446");
        writer.name("email").beginArray().value("luchunyang@gmail.com").value("1352913549@qq.com").endArray();
        writer.endObject();
        writer.close();
        //从流中读取JSON数据，并且创建一个对象模型在内存中
        JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(Environment.getExternalStorageDirectory()+"/abc.txt")));
        reader.beginObject();

        while (reader.hasNext()){

            String name = reader.nextName();
            if(name.equals("phone"))
                System.out.println(""+name+"="+reader.nextString());
            else{
                reader.beginArray();
                while (reader.hasNext()){
                    System.out.println(name+"="+reader.nextString());
                }
                reader.endArray();
            }
        }
        reader.endObject();

    }

    public void createBasicJson3(View view) throws JSONException {
        HashMap<String,String> map = new HashMap<>();
        map.put("name","luchunyang");
        map.put("phone","13325669");
        map.put("email","luchunyang@gmail.com");

        JSONObject jsonObject = new JSONObject(map);
        jsonObject.put("name","Android");//会替换
        System.out.println(jsonObject);
    }

    public void readJson(View view) throws JSONException {
        JSONObject jsonObject = new JSONObject(JsonString.getJson());
        int id = jsonObject.getInt("id");//100000
        String text = jsonObject.getString("text");//廊坊银行总行
        JSONArray array = jsonObject.getJSONArray("children");
        System.out.println("id="+id+" text="+text+" array="+array);

        for (int i = 0; i < array.length(); i++) {
            System.out.println(array.get(i));
        }
    }

    public void readJson1(View view) throws JSONException {
        JSONTokener tokener = new JSONTokener(JsonString.getJson());

//        System.out.println(tokener.toString());

        JSONObject object = (JSONObject) tokener.nextValue();

        System.out.println(object.toString());
    }


    public void gsonActivity(View view) {
        startActivity(new Intent(this,GsonActivity.class));
    }

}
