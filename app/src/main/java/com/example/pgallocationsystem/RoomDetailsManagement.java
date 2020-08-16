package com.example.pgallocationsystem;

import android.content.ContentValues;
import android.database.Cursor;
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

import java.util.ArrayList;

public class RoomDetailsManagement extends Fragment {
    SQLiteDatabase sq;
    PgManager manager;
    View v;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.roomdetailsmanage,container,false);
        final EditText txtroomno,txtnoofcandidates;
        final Spinner spinstatus,spinroomid;
        Button btnadd;
        txtroomno=v.findViewById(R.id.roomnoroomdetail);
        spinroomid=v.findViewById(R.id.roomidroomdetail);
        spinstatus=v.findViewById(R.id.spinstatus);
        txtnoofcandidates=v.findViewById(R.id.noofcandidate);
        btnadd=v.findViewById(R.id.addroomdtails);
        arrayList=new ArrayList<>();
        Cursor cursor=sq.query(PgConstant.SECOND_TABLE_NAME,null,null,null,null,null,null);
        while (cursor!=null && cursor.moveToNext())
        {
            String roomid=cursor.getString(cursor.getColumnIndex(PgConstant.ROOM_ID));
            arrayList.add(roomid);
        }
        arrayAdapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinroomid.setAdapter(arrayAdapter);

        ArrayAdapter<CharSequence> statusarrayAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.status,android.R.layout.simple_spinner_item);
        statusarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinstatus.setAdapter(statusarrayAdapter);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roomno,roomid,status,noofcandidtaes;
                roomno=txtroomno.getText().toString();
                roomid=spinroomid.getSelectedItem().toString();
                status=spinstatus.getSelectedItem().toString();
                noofcandidtaes=txtnoofcandidates.getText().toString();
                if(TextUtils.isEmpty(roomno) ||  TextUtils.isEmpty(noofcandidtaes))
                {
                    Toast.makeText(getActivity(), "Fields Can't be Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ContentValues contentValues=new ContentValues();
                    contentValues.put(PgConstant.ROOM_NO,roomno);
                    contentValues.put(PgConstant.ROOMDETAILS_ROOM_ID,roomid);
                    contentValues.put(PgConstant.STATUS,status);
                    contentValues.put(PgConstant.NOOFCANDIDATE,noofcandidtaes);
                    long row=sq.insert(PgConstant.THIRD_TABLE_NAME,null,contentValues);
                    if(row>0)
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
