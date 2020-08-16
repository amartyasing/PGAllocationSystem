package com.example.pgallocationsystem;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pgallocationsystem.com.dbtask.PgConstant;
import com.example.pgallocationsystem.com.dbtask.PgManager;

public class RoomTypeManagement extends Fragment {
    View v;
    SQLiteDatabase sq;
    PgManager manager;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.roomtypemanagement,container,false);
        final EditText txtypeid;
        final Spinner txttypename;
        Button btnadd;
        txtypeid=v.findViewById(R.id.typeidroomtyp);
        txttypename=v.findViewById(R.id.typename);
        btnadd=v.findViewById(R.id.addroomtype);
        ArrayAdapter<CharSequence> arrayAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.typename,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txttypename.setAdapter(arrayAdapter);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String typeid=txtypeid.getText().toString();
                String typename=txttypename.getSelectedItem().toString();
                if(TextUtils.isEmpty(typeid) || TextUtils.isEmpty(typename))
                {
                    Toast.makeText(getActivity(), "Fields Can't be Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ContentValues contentValues=new ContentValues();
                    contentValues.put(PgConstant.TYP_ID,typeid);
                    contentValues.put(PgConstant.TYP_NAME,typename);
                    long row=sq.insert(PgConstant.FIRST_TABLE_NAME,null,contentValues);
                    if (row>0)
                    {
                        Toast.makeText(getActivity(), "Data Added "+row, Toast.LENGTH_SHORT).show();
                    }
                }
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
}
