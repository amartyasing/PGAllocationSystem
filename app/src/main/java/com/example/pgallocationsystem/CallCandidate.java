package com.example.pgallocationsystem;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pgallocationsystem.com.bean.CallMessage;
import com.example.pgallocationsystem.com.dbtask.PgConstant;
import com.example.pgallocationsystem.com.dbtask.PgManager;

import java.util.ArrayList;

public class CallCandidate extends Fragment {
    ListView listView;
    PgManager manager;
    SQLiteDatabase sq;
    ArrayList<CallMessage> arrayList;
    CallMessage callMessage;
    ArrayAdapter<CallMessage> arrayAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.contact,container,false);
        listView=v.findViewById(R.id.contactlist);
        arrayList=new ArrayList<>();
        populatelist();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              CallMessage call=arrayList.get(i);
              String phone=call.getPhoneno();
                Intent intent = new Intent(Intent.ACTION_CALL);//implicit Intent
                Uri number = Uri.parse("tel:" + phone); //Uniform resource identifier is used identify number
                intent.setData(number);

                startActivity(intent);

            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager=new PgManager(getActivity());
        sq=manager.openDb();
    }
    public void populatelist()
    {
        Cursor cursor=sq.query(PgConstant.FIFH_TABLE_NAME,null,null,null,null,null,null);
        while (cursor!=null && cursor.moveToNext()) {



            String name =cursor.getString(cursor.getColumnIndex(PgConstant.NAME));
            String phone = cursor.getString(cursor.getColumnIndex(PgConstant.PHONE_NO));
            callMessage=new CallMessage(name,phone);
            arrayList.add(callMessage);


        }

        arrayAdapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
    }
}
