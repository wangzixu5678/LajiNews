package com.example.ftkj.mycook.cache;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.example.ftkj.mycook.App;
import com.example.ftkj.mycook.utils.SDUtils;

import java.io.File;

/****************************************
 * autor:Jerry
 * name:
 * time:2016/11/3
 * desc:
 * step:
 * ****************************************
 */
public class MyCache {
    private String fileName;
    private MyCache() {

    }
    private static class staticClassLazy{
        private static MyCache single=new MyCache();
    }
    public static MyCache getCacheInstance(){
        return staticClassLazy.single;
    }

    //存数据到sd卡
    public void saveData(String response,String fileName) {
        this.fileName = fileName;
        SDUtils.saveFileToSDCardPrivateCacheDir(response.getBytes(), fileName, App.getAppContext());
    }

    //从sd中获取数据
    public String getData(String fileName) {
        this.fileName = fileName;
        String path = SDUtils.getSDCardPrivateCacheDir(App.getAppContext()) + File.separator + fileName;
        String result = null;
        byte[] bytes = SDUtils.loadFileFromSDCard(path);
        if (bytes!=null){
            result = new String(bytes);
        }
            return result;
    }

    //获取文件名字
    public String getFileName() {
        return fileName;
    }
}