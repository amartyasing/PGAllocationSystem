package com.example.pgallocationsystem.com.dbtask;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PgManager {
    PgHelper pgHelper;
    Context context;

    public PgManager(Context context) {
        pgHelper=new PgHelper(context,PgConstant.DB_NAME,null,PgConstant.DB_VERSION);
        this.context = context;
    }
    public SQLiteDatabase openDb()
    {
        SQLiteDatabase sqLiteDatabase=pgHelper.getWritableDatabase();
        return sqLiteDatabase;
    }
    public void closeDb()
    {
        if (pgHelper!=null)
        {
            pgHelper.close();
        }
    }
}
