package com.jilinuniversity.youranweather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.jilinuniversity.youranweather.util.HttpUtil;
import com.jilinuniversity.youranweather.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherNow extends AppCompatActivity {
    private AMapLocationClient mLocationClient = null;
    private AMapLocationClientOption mLocationOption = null;
    private TextView textView;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int result:grantResults){
                        if(result!=PackageManager.PERMISSION_GRANTED)
                            Toast.makeText(this,"权限请求失败",Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }
                mLocationClient.startLocation();
                break;

            default:
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_now);
        textView=(TextView)findViewById(R.id.weather_now) ;
        List<String> permissionList=new ArrayList<>();
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation loc) {
                if (null != loc) {
                    String address="https://free-api.heweather.com/v5/now?city="+String.valueOf(loc.getLongitude())+","+String.valueOf(loc.getLatitude())+"&key=bfd5f3591edc41508ed46939a56767fd";
                    HttpUtil.sendOkHttpRequest(address, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String responseText=response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(responseText);
                                }
                            });
                        }
                    });
                } else {
                    textView.setText("定位失败，loc is null");
                }
            }
        });
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setNeedAddress(true);
        mLocationClient.setLocationOption(mLocationOption);
        //检查权限
        if(ContextCompat.checkSelfPermission(WeatherNow.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED)
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        if(ContextCompat.checkSelfPermission(WeatherNow.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        if(ContextCompat.checkSelfPermission(WeatherNow.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //启动定位
        if(permissionList.isEmpty()) {
            mLocationClient.startLocation();

        }
        else{
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(WeatherNow.this,permissions,1);
        }
    }
}
