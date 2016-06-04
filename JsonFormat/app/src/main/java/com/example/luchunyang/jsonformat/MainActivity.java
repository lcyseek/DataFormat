package com.example.luchunyang.jsonformat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createJson(View view) throws JSONException {

        //首先最外层是{}，是创建一个对象
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name","陆春阳");
        jsonObject.put("age",12);
        jsonObject.put("isMan",true);

        System.out.println(jsonObject);


        JSONObject person = new JSONObject();
        // 第一个键phone的值是数组，所以需要创建数组对象
        JSONArray phone = new JSONArray();
        phone.put("1234453455").put("1332949775");
        person.put("phone",phone);

        person.put("name","陆春阳");
        person.put("age",22);

        // 键address的值是对象，所以又要创建一个对象
        JSONObject address = new JSONObject();
        address.put("country","china");
        address.put("province","gd");

        person.put("address",address);

        this.json = person.toString();

//        getType可以将要获取的键的值转换为指定的类型，如果无法转换或没有值则抛出JSONException
//        optType也是将要获取的键的值转换为指定的类型，无法转换或没有值时返回用户提供或这默认提供的值
        int age = person.getInt("age");

        System.out.println("json-->"+person+" age:"+age);

    }


    //JSONStringer 还可以使用JSONStringer来构建json文本
    //这个类可以帮助快速和便捷的创建JSONtext。其最大的优点在于可以减少由于格式的错误导致程序异常，引用这个类可以自动严格按照JSON语法规则（syntaxrules）创建JSON text。每个JSONStringer实体只能对应创建一个JSON text。
    public void createJson1(View view) throws JSONException {
        // 首先是{，对象开始。object和endObject必须配对使用
        JSONStringer stringer = new JSONStringer();
        stringer.object();
        stringer.key("phone");
        

        // 键phone的值是数组。array和endArray必须配对使用
        stringer.array();
        stringer.value("1334958604").value("13684990561");
        stringer.endArray();

        stringer.key("name");
        stringer.value("陆春阳");
        stringer.key("age").value(13);

        stringer.key("address");
        stringer.object();
        stringer.key("country");
        stringer.value("US");
        stringer.key("province");
        stringer.value("GD");
        stringer.endObject();

        stringer.endObject();

        System.out.println("json1-->"+stringer.toString());
    }

    private String json = null;
    public void readJson(View view) throws JSONException {
        JSONTokener tokener = new JSONTokener(json);

//        Returns the next {@code length} characters of the input.
//        System.out.println("next 8:"+tokener.next(8));

//        System.out.println("next one char:"+tokener.next());//{

        JSONObject object = (JSONObject) tokener.nextValue();
        JSONArray phone = object.getJSONArray("phone");
        for (int i = 0; i < phone.length(); i++) {
            System.out.println("phone:"+phone.get(i));
        }

        String name = object.getString("name");
        int age = object.getInt("age");
        System.out.println("name:"+name + " age:"+age);

        JSONObject address = object.getJSONObject("address");
        String country = address.getString("country");
        String province = address.getString("province");
        System.out.println("country:" + country + " province:"+province);

    }

    public void jsonTools(View view) {
        startActivity(new Intent(this,Main2Activity.class));
    }
}
