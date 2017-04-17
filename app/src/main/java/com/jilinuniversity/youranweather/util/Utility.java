package com.jilinuniversity.youranweather.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jilinuniversity.youranweather.MainActivity;
import com.jilinuniversity.youranweather.db.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ConcurrentModificationException;
import java.util.List;


/**
 * Created by 程杰 on 2017/4/15.
 */
//解析返回的JSON数据
public class Utility {

    public static boolean handleResponse(String response){

        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray jsonArray=new JSONArray(response);
                Gson gson=new Gson();
                List<City> cities=gson.fromJson(response,new TypeToken<List<City>>(){}.getType());
                City[] c=cities.toArray(new City[cities.size()]);
                //System.out.println(c[i].getId()+" "+c[i].getCityEn()+" "+c[i].getCityZh()+" "+c[i].getCountryCode()+" "+c[i].getCountryEn()+" "+c[i].getCountryZh()+" "+c[i].getProvinceEn()+" "+c[i].getProvinceZh()+" "+c[i].getLeaderEn()+" "+c[i].getLeaderZh()+" "+c[i].getLat()+" "+c[i].getLon()+" ");

                for(int i=0;i<c.length;i++)
                   c[i].save();
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

}
