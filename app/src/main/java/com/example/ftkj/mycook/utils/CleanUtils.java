package com.example.ftkj.mycook.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/10/31.
 */
public class CleanUtils {


    //清除本应用里内部缓存（/data/data/com.xxx.xxx/cache）
    public static void cleanItnternalCache(Context context){

        deleteFileByDirectory(context.getCacheDir());
    }

    //清除本应用所有数据库（/data/data/com.xxx.xxx/databases）

    public static void cleanDatabases(Context context){

        deleteFileByDirectory(new File("data/data"+context.getPackageName()+"/databases"));
    }
    /**
     *清除本应用SharedPreference（data/data/com.xxx.xxx/shared_prefs）
     *
     */
    public static void cleanShareprefence(Context context){

        deleteFileByDirectory(new File("data/data"+context.getPackageName()+"/shared_prefs"));
    }

    /**
     * 按名字清除本应用数据库**
     */
    public static  void cleanDatabaseByName(Context context, String dbname){

        context.deleteDatabase(dbname);
    }

    //清除/data/data/com.xxx.xxx/files下的内容
    public static void cleanFiles(Context context){

        deleteFileByDirectory(context.getFilesDir());
    }

    /**
     * 清除外部cache下的内容（/mnt/sdcard/android/data/com.xxx.xxx/cache）
     *
     */
    public static void cleanExternalCache(Context context){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteFileByDirectory(context.getExternalCacheDir());
        }

    }

    //清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的问价删除
    public static void cleanCustomCache(String filePath){
        deleteFileByDirectory(new File(filePath));

    }

    /**
     * 清除本应用所有数据
     *
     */
    public static  void cleanApplicationData(Context context, String... filepath){
        cleanItnternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanShareprefence(context);
        cleanFiles(context);
        for (String filePath:filepath){
            cleanCustomCache(filePath);
        }
    }
    //删除方法。只删除某个文件夹下的文件，如果传入的是directory是个文件，将不做处理
        private static void deleteFileByDirectory(File directory){

            if (directory!=null&& directory.exists()&&directory.isDirectory()) {
                for(File item:directory.listFiles()){
                    item.delete();
                }
            }
        }



}
