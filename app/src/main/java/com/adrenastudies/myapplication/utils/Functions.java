package com.adrenastudies.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.faltenreich.skeletonlayout.Skeleton;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Eder on 20-10-2016.
 */
public class Functions {


    //  <!-- ............... Movements between activities ................. -->

    public static void changeActivity(Context ctx, Class newActivity) {
        Intent mainIntent = new Intent().setClass(
                ctx, newActivity);
        ctx.startActivity(mainIntent);
    }

    public static void closeActivitys(Activity act, Class newClass) {
        Intent intent = new Intent(act.getApplicationContext(), newClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        act.startActivity(intent);
    }

    public static void closeActivitys(Activity act, Class newClass, String extra) {
        Intent intent = new Intent(act.getApplicationContext(), newClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.EXTRA, extra);
        act.startActivity(intent);
    }

    public static void changeActivity(Context ctx, Class newActivity, String extra) {
        Intent mainIntent = new Intent().setClass(
                ctx, newActivity);
        mainIntent.putExtra(Constants.EXTRA, extra);
        ctx.startActivity(mainIntent);
    }

    public static void changeActivity(Context ctx, Class newActivity, String extra, String extra2) {
        Intent mainIntent = new Intent().setClass(
                ctx, newActivity);
        mainIntent.putExtra(Constants.EXTRA, extra);
        mainIntent.putExtra(Constants.EXTRA2, extra2);
        ctx.startActivity(mainIntent);
    }

    public static void changeActivity(Context ctx, Class newActivity, String[] extra) {
        Intent mainIntent = new Intent().setClass(
                ctx, newActivity);
        mainIntent.putExtra(Constants.EXTRA, extra);
        ctx.startActivity(mainIntent);
    }

    public static void changeActivity(Context ctx, Class newActivity, ArrayList<String> extra) {
        Intent mainIntent = new Intent().setClass(
                ctx, newActivity);
        mainIntent.putStringArrayListExtra(Constants.EXTRA, extra);
        ctx.startActivity(mainIntent);
    }

    public static void changeActivity(Context ctx, Class newActivity, Integer extra) {
        Intent mainIntent = new Intent().setClass(
                ctx, newActivity);
        mainIntent.putExtra(Constants.EXTRA, extra);
        ctx.startActivity(mainIntent);
    }

    public static void popupAct(Activity ctx, Class newActivity, String extra, Integer requestCode) {
        Intent mainIntent = new Intent(ctx, newActivity);
        mainIntent.putExtra(Constants.EXTRA, extra);
        ctx.startActivityForResult(mainIntent, requestCode);
    }

    public static void returnPoupParameter(Activity act) {
        Intent returnIntent = new Intent();
        act.setResult(Activity.RESULT_OK, returnIntent);
        act.finish();
    }

    public static String getIntent(Activity act, Integer position) {
        return act.getIntent().getStringArrayExtra(Constants.EXTRA)[position];
    }

    public static String getIntent(Activity act) {
        return act.getIntent().getStringExtra(Constants.EXTRA);
    }

    public static String getIntent(Activity act, String field) {
        return act.getIntent().getStringExtra(field);
    }

    public static String[] getIntentArray(Activity act) {
        return act.getIntent().getStringArrayExtra(Constants.EXTRA) ;
    }

    public static boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        return matcher.matches();
    }

    public static String getRandomUUID(){
        return UUID.randomUUID().toString();
    }

    //  <!-- ............... Desing ................. -->

    public static void setBackgroundColor(Activity act, LinearLayout ly, int color){
        ly.setBackgroundColor(act.getResources().getColor(color));
    }

    //  <!-- ............... Network ................. -->

    public static boolean isOnline(Context ctx){
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo    = cm.getActiveNetworkInfo();
        return netInfo  != null && netInfo.isConnected();
    }

    //  <!-- ............... Utils ................. -->

    public static void showSkeleton(Skeleton skeleton) {
        skeleton.showSkeleton();
    }

    public static void hideSkeleton(Skeleton skeleton) {
        skeleton.showOriginal();
    }

    public static void hideKeyboard(Activity act) {
        InputMethodManager imm = (InputMethodManager) act.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = act.getCurrentFocus();

        if (view == null) {
            view = new View(act);
        }

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(Activity act) {
        InputMethodManager imm = (InputMethodManager) act.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = act.getCurrentFocus();

        if (view == null) {
            view = new View(act);
        }

        imm.showSoftInput(view, 0);
    }

    public static void openUrlInExternalNavigator(Activity act, String path){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(path));
        act.startActivity(i);
    }

    public static boolean isAppInstalled(Activity act, String packcage){
        try {
            act.getPackageManager().getPackageInfo(packcage, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void setImg(Context ctx, ImageView img, String path){
        Glide.with(ctx)
                .load(path)
                .into(img);
    }

    //  <!-- ............... Measures ................. -->

    public static int getMeasuresOfScreen(Activity act, String typeOfMeasue){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        if(typeOfMeasue.equals(Constants.WIDTH)){
            return displayMetrics.widthPixels;
        }

        if (typeOfMeasue.equals(Constants.HEIGHT)){
            return displayMetrics.heightPixels;
        }

        return Constants.INT_NULL;
    }

    public static int getPercentageOfScreen(Activity act, String typeOfMeasure, int percentage){
        return ((Functions.getMeasuresOfScreen(act, typeOfMeasure) * percentage)  /  100);
    }

}
