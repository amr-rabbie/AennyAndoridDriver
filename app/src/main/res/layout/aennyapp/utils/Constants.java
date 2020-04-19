package design.swira.aennyapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import design.swira.aennyapp.pojo.aenny.DisabilitiesTypesidData;

import static android.content.Context.MODE_PRIVATE;

public class Constants {
    public static final String MyPREFERENCES = "PREF2" ;

    public  static  void saveClientData(int id , String un, String pw , String mob , String email ,Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putInt("id", id);
        editor.putString("mob", mob);
        editor.putString("un", un);
        editor.putString("pw", pw);
        editor.putString("email", email);
        editor.apply();
    }

    public  static  int getClientId( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getInt("id", 0);
    }

    public  static  String getClientMobile( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("mob", "");
    }

    public  static  String getClientName( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("un", "");
    }

    public  static  String getClientPassword( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("pw", "");
    }

    public  static  String getClientEmail( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("email", "");
    }

    public  static  void saveClientsDisabilities(int disid1 , int disid2, int disid3 , int disid4 ,int disid5 ,Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putInt("disid1", disid1);
        editor.putInt("disid2", disid2);
        editor.putInt("disid3", disid3);
        editor.putInt("disid4", disid4);
        editor.putInt("disid5", disid5);
        editor.apply();
    }

    public  static  int getdisid1( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getInt("disid1", 0);
    }

    public  static  int getdisid2( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getInt("disid2", 0);
    }

    public  static  int getdisid3( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getInt("disid3", 0);
    }

    public  static  int getdisid4( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getInt("disid4", 0);
    }

    public  static  int getdisid5( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getInt("disid5", 0);
    }




    public  static  void saveTripData(String latitude , String longitude, String ori , String latitude2 , String longitude2,String  des ,Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putString("latitude", latitude);
        editor.putString("longitude", longitude);
        editor.putString("ori", ori);
        editor.putString("latitude2", latitude2);
        editor.putString("longitude2", longitude2);
        editor.putString("des", des);
        editor.apply();
    }

    public  static  String getlatitude( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("latitude", "");
    }

    public  static  String getlongitude( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("longitude", "");
    }

    public  static  String getori( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("ori", "");
    }

    public  static  String getlatitude2( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("latitude2", "");
    }

    public  static  String getlongitude2( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("longitude2", "");
    }

    public  static  String getdes( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("des", "");
    }


    public  static  void saveTripData2(int healthy , int handcap, int paidtype , String paid , String distance,String  cost ,Context context )
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putInt("healthy", healthy);
        editor.putInt("handcap", handcap);
        editor.putInt("paidtype", paidtype);
        editor.putString("paid", paid);
        editor.putString("distance", distance);
        editor.putString("cost", cost);
        editor.apply();
    }


    public  static  int gethealthy( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getInt("healthy", 0);
    }

    public  static  int gethandcap( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getInt("handcap", 0);
    }

    public  static  int getpaidtype( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getInt("paidtype", 0);
    }

    public  static  String getpaid( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("paid", "");
    }

    public  static  String getdistance( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("distance", "");
    }

    public  static  String getcost( Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        return prefs.getString("cost", "");
    }









}
