package com.example.pgallocationsystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pgallocationsystem.com.bean.CallMessage;
import com.example.pgallocationsystem.com.dbtask.PgConstant;
import com.example.pgallocationsystem.com.dbtask.PgManager;

import java.util.ArrayList;

public class CallUser extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    PgManager manager;
    SQLiteDatabase sq;
    ArrayList<CallMessage> arrayList;
    CallMessage callMessage;
    ArrayAdapter<CallMessage> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_user);
        listView = findViewById(R.id.contactlist);
        listView.setOnItemClickListener(this);
        manager = new PgManager(this);
        sq = manager.openDb();
        arrayList = new ArrayList<>();
        populatelist();
    }

    public void populatelist() {
        Cursor cursor = sq.query(PgConstant.FIFH_TABLE_NAME, null, null, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {


            String name = cursor.getString(cursor.getColumnIndex(PgConstant.NAME));
            String phone = cursor.getString(cursor.getColumnIndex(PgConstant.PHONE_NO));
            callMessage = new CallMessage(name, phone);
            arrayList.add(callMessage);


        }

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CallMessage call = arrayList.get(i);
        String phone = call.getPhoneno();
        Toast.makeText(this, phone, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_CALL);//implicit Intent
        Uri number = Uri.parse("tel:" + phone); //Uniform resource identifier is used identify number
        intent.setData(number);

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }
}
