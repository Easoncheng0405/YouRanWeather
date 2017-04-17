package com.jilinuniversity.youranweather.db;

import org.litepal.crud.DataSupport;

import java.util.Objects;

/**
 * Created by 程杰 on 2017/4/15.
 */
//市级数据
public class City extends DataSupport {
    private String cityEn;           //县英文名
    private String cityZh;           //县市中文名
    private String countryCode;     //所属国家
    private String countryEn;       //所属国家英文名
    private String countryZh;       //所属国家中文名
    private String provinceZh;      //所属省中文名
    private String provinceEn;      //所属省英文名
    private String leaderEn;        //所属市英文名
    private String leaderZh;        //所属市中文名
    private double lat;             //经度
    private double lon;             //纬度


    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCityZh() {
        return cityZh;
    }

    public void setCityZh(String cityZh) {
        this.cityZh = cityZh;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getCountryZh() {
        return countryZh;
    }

    public void setCountryZh(String countryZh) {
        this.countryZh = countryZh;
    }

    public String getProvinceZh() {
        return provinceZh;
    }

    public void setProvinceZh(String provinceZh) {
        this.provinceZh = provinceZh;
    }

    public String getProvinceEn() {
        return provinceEn;
    }

    public void setProvinceEn(String provinceEn) {
        this.provinceEn = provinceEn;
    }

    public String getLeaderEn() {
        return leaderEn;
    }

    public void setLeaderEn(String leaderEn) {
        this.leaderEn = leaderEn;
    }

    public String getLeaderZh() {
        return leaderZh;
    }

    public void setLeaderZh(String leaderZh) {
        this.leaderZh = leaderZh;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
