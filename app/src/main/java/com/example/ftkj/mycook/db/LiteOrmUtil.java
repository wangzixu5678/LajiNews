package com.example.ftkj.mycook.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;

/**
 * Created by FTKJ on 2017/5/25.
 */

public class LiteOrmUtil {

    private static LiteOrm liteOrm;

    public static LiteOrm getLiteOrm(Context context){
    if (liteOrm == null) {
        liteOrm = LiteOrm.newSingleInstance(context, "liteorm.db");
    }
        liteOrm.setDebugged(true);

        return liteOrm;
    }

}
