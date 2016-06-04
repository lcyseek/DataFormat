package com.example.luchunyang.jsonformat;

/**
 * Created by luchunyang on 16/6/3.
 */
public class JsonString {
    public static String getJson(){
        return "{  \n" +
                "  id: '100000',  \n" +
                "  text: '廊坊银行总行',  \n" +
                "  children: " +
                "   [  \n" +
                "    {  \n" +
                "      id: '110000',  \n" +
                "      text: '廊坊分行',  \n" +
                "      children: [  \n" +
                "        {  \n" +
                "          id: '113000',  \n" +
                "          text: '廊坊银行开发区支行',  \n" +
                "          leaf: true  \n" +
                "        },  \n" +
                "        {  \n" +
                "          id: '112000',  \n" +
                "          text: '廊坊银行解放道支行',  \n" +
                "          children: [  \n" +
                "            {  \n" +
                "              id: '112200',  \n" +
                "              text: '廊坊银行三大街支行',  \n" +
                "              leaf: true  \n" +
                "            },  \n" +
                "            {  \n" +
                "              id: '112100',  \n" +
                "              text: '廊坊银行广阳道支行',  \n" +
                "              leaf: true  \n" +
                "            }  \n" +
                "          ]  \n" +
                "        },  \n" +
                "        {  \n" +
                "          id: '111000',  \n" +
                "          text: '廊坊银行金光道支行',  \n" +
                "          leaf: true  \n" +
                "        }  \n" +
                "      ]  \n" +
                "    }  \n" +
                "  ]  \n" +
                "}  \n";
    }
}
