package com.example.ftkj.mycook.bean;

/**
 * Created by FTKJ on 2017/6/5.
 */

public class ImageItemBean {
    private String classifyName;
    private int ImageRes;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ImageItemBean(String classifyName, int imageRes, int id) {

        this.classifyName = classifyName;
        ImageRes = imageRes;
        this.id = id;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public int getImageRes() {
        return ImageRes;
    }

    public void setImageRes(int imageRes) {
        ImageRes = imageRes;
    }
}
