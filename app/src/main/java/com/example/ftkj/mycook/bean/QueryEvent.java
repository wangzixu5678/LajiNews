package com.example.ftkj.mycook.bean;

/**
 * Created by FTKJ on 2017/5/23.
 */

public class QueryEvent {
    private int id;
    private int page;
    private int rows;
    public QueryEvent(int id,int page){
        this.id =id;
        this.page =page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public QueryEvent(int id, int page, int rows){
        this.id =id;
        this.page =page;
        this.rows = rows;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
