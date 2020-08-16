package com.example.pgallocationsystem.com.dbtask;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class PgHelper extends SQLiteOpenHelper {
    Context context;
    public PgHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PgConstant.TBL_QUERY_ROOMTYPE);
        sqLiteDatabase.execSQL(PgConstant.TBL_QUERY_ROOMCATEGORY);
        sqLiteDatabase.execSQL(PgConstant.TBL_QUERY_ROOMDETAILS);
        sqLiteDatabase.execSQL(PgConstant.TBL_QUERY_ROOMALLOTMENT);
        sqLiteDatabase.execSQL(PgConstant.TBL_QUERY_CANDIDATEDETAILS);
        Toast.makeText(context, "Tables Created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+PgConstant.FIRST_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+PgConstant.SECOND_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+PgConstant.THIRD_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+PgConstant.FOURTH_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+PgConstant.FIFH_TABLE_NAME);
        onCreate(db);

    }
}
