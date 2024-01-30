package com.hr.pereless.commons;

import android.util.Log;

import com.hr.pereless.adapter.schedule.AvailablityAdapter;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.model.setting.TimeZoneModel;
import com.hr.pereless.model.setting.UserModel;
import com.hr.pereless.model.candidate.AppliedJobModel;
import com.hr.pereless.model.candidate.RecruitflowModel;
import com.hr.pereless.model.candidate.ScorecardUserModel;
import com.hr.pereless.model.onboarding.CurrencyModel;
import com.hr.pereless.model.onboarding.RecruitteamModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Commons {
    public static boolean g_isAppRunning=false;
    public static CommonActivity g_commentActivity = null;
    public static String apiKey = "BA2D16C7-6B3A-4447-81D5-497F80E6ADB9";
    public static String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaWQiOjYwMDEsInJvbGUiOjgsImVtYWlsIjoiYnJpYW5AcGVyZWxlc3MuY29tIiwidWlkIjoiNTAzIiwicmVxdWVzdFBlck1pbiI6NTAwMDAsImFjY2VzcyI6WyIqIl0sImlhdCI6MTU4MjEwNDA5OSwiZXhwIjoxNTgyMTkwNDk5LCJhdWQiOiJBVFMgU3lzdGVtIiwiaXNzIjoiTG9jYWwgU3lzdGVtIiwic3ViIjoiQVBJIn0.LHkmul7NX6QgDdk88WVgK6KzKFObq9-4XunPJXJOWklOCZiEiYDQI6rqd-h40KLF0YrO4E3uI-QcOJs4fo8llV8mX_PnUzfjIgrghUePBK1YtPpialbSwPcwCQ7ddl4QuqcUqun-V2QIA1hcnCBJbVXKX1504Bq3aqZ13cHgdin6Pm5B7Ga8HiHHwDO0F9egPcA-umQFe4lniQqVf5O3UPoI_7mRh-3D872ixlthlIZzc-DCAWrpnS4Xkelmuybje6d4Vte5QmSnwKE21FtYrqyo5oukLWbweNoV2eVifcRR0r2fKKno3wHPXiJSzXdtC9pwU1snCbRQjsv6BKBuJrnaQbtlJzvRRgoo7T3TT75M0xqfxJJMwzmeMOE69td8wyZCgWsmUswrejnrB_0rTgZ61NcJwf74mVljOG5E_RWaeygZ19vMr3Ot5Xg7shTlZRrbKQBQjhqTCo_eZ9tWx6KUY_TqL6eljyg_L4QfbdaacQpWpIX44QwtgWiyPKgaw_Ejk8AB5YgMtkkP_FIs2_FGqmvb4YC3sxIIbnTJdyqp43KcrSguEw9Jfae0bb-4YfXlpKAIpMb3qTs5y0HdfjmxWZDKehHLQdLucLC93Pj_inQD2o0gmMxOKbcBjHtkRo2YD9OABZjj0Z8UgJgQTR4f50kKcWKa9bBdDLMLvCk";
    public static UserModel g_user = new UserModel();
    public static ArrayList<AppliedJobModel>appliedJobModelList = new ArrayList<>();
    public static ArrayList<RecruitflowModel> recruitflowModels = new ArrayList<>();
    public static ArrayList<CurrencyModel> currencyModels = new ArrayList<>();
    public static ArrayList<RecruitteamModel> recruitteamModels = new ArrayList<>();
    public static ArrayList<ScorecardUserModel> _scoreUserModels = new ArrayList<>();
    public static ArrayList<TimeZoneModel> timeZoneModels = new ArrayList<>();

    public static int glide_radius = 500;
    public static int glide_magin = 1;
    public static int glide_boder = 2;
    public static String[] Months;
    public static String fileNameWithoutExtFromPath(String path) {

        String fullname = fileNameWithExtFromPath(path);

        if (fullname.lastIndexOf(".") == -1) {
            return fullname;
        } else {
            return fullname.substring(0, fullname.lastIndexOf("."));
        }
    }
    public static String fileNameWithExtFromPath(String path) {

        if (path.lastIndexOf("/") > -1)
            return path.substring(path.lastIndexOf("/") + 1);

        return path;
    }

    public static String listToString(List<String> list, String separator) {
        if(list == null)return "";
        StringBuilder sb = new StringBuilder();
        String mySeparator = "";

        for (String text: list) {
            sb.append(mySeparator);
            sb.append(text);
            mySeparator = separator;
        }

        return sb.toString();
    }

    public static String dateTime(String time){
        DateFormat df = new SimpleDateFormat("MMM, dd yyyy hh:mm:ss Z");
        Date result;
        String date = "";
        try {
            result = df.parse(time);
            SimpleDateFormat sdf = new SimpleDateFormat("dd'th' MMM, yyyy");
            //sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            date  = sdf.format(result);
        }catch (Exception e){
            Log.d("exception_ time", e.toString());
        }
        return date;
    }

    public static String TimelinedateTime(String time){
        DateFormat df = new SimpleDateFormat("MMM, dd yyyy hh:mm:ss Z");
        Date result;
        String date = "";
        try {
            result = df.parse(time);
            SimpleDateFormat sdf = new SimpleDateFormat("dd'th' MMM, yyyy hh:mm a");
            //sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            date  = sdf.format(result);
        }catch (Exception e){
            Log.d("exceptiontime", e.toString());
        }
        return date;
    }
    public static Date DateFromString(String time){
        DateFormat df = new SimpleDateFormat("MMM, dd yyyy hh:mm:ss Z");
        Date result = new Date();

        try {
            result = df.parse(time);


        }catch (Exception e){
            Log.d("exceptiontime", e.toString());
        }
        return result;
    }

    public static String HoursTodateTime(String time){
        DateFormat df = new SimpleDateFormat("MMM, dd yyyy hh:mm:ss Z");
        Date result;
        String date = "";
        try {
            result = df.parse(time);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            //sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            date  = sdf.format(result);
        }catch (Exception e){
            Log.d("exceptiontime", e.toString());
        }
        return date;
    }

    public static String  HoursFromSecond(int second) {
       String str = "";
       int hours = second / 3600;
       String minuturs = String.valueOf(second % 3600 / 60);
       if (minuturs.length() == 1) {
           minuturs = "0" + minuturs;
       }
       if(hours >=12) {

            str = String.valueOf(hours - 12) +":" +  minuturs + " PM";
       }else{
           str = String.valueOf(hours) +":" +  minuturs+ " AM";
       }
       return  str;
    }
}
