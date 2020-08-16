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

public class RoomCategoryManagement extends Fragment {
    SQLiteDatabase sq;
    PgManager manager;
    View v;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.roomcategorymanagement,container,false);
        final EditText txtroomid,txtnoofrooms,txtequippedwith,txtmonthlycharge,txtfacilities;
        final Spinner spintypeid;
        Button btnadd;
        txtroomid=v.findViewById(R.id.roomidroomcategory);
        spintypeid=v.findViewById(R.id.typeidroomcategory);
        txtnoofrooms=v.findViewById(R.id.noofroom);
        txtequippedwith=v.findViewById(R.id.equippedwith);
        txtmonthlycharge=v.findViewById(R.id.monthlycharge);
        txtfacilities=v.findViewById(R.id.facilities);
        btnadd=v.findViewById(R.id.addroomcategory);
        arrayList=new ArrayList<>();
        Cursor cursor=sq.query(PgConstant.FIRST_TABLE_NAME,null,null,null,null,null,null);
        while (cursor!=null && cursor.moveToNext())
        {
            String typeid=cursor.getString(cursor.getColumnIndex(PgConstant.TYP_ID));
            arrayList.add(typeid);
        }
        arrayAdapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spintypeid.setAdapter(arrayAdapter);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roomid,typeid,noofrooms,equippedwith,monthlycharge,facilities;
                roomid=txtroomid.getText().toString();
                typeid=spintypeid.getSelectedItem().toString();
                noofrooms=txtnoofrooms.getText().toString();
                equippedwith=txtequippedwith.getText().toString();
                monthlycharge=txtmonthlycharge.getText().toString();
                facilities=txtfacilities.getText().toString();
                if(TextUtils.isEmpty(roomid) || TextUtils.isEmpty(noofrooms)||TextUtils.isEmpty(equippedwith)||TextUtils.isEmpty(monthlycharge)||TextUtils.isEmpty(facilities))
                {
                    Toast.makeText(getActivity(), "Fields Can't be Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ContentValues contentValues=new ContentValues();
                    contentValues.put(PgConstant.ROOM_ID,roomid);
                    contentValues.put(PgConstant.ROOMCATEGORY_TYP_ID,typeid);
                    contentValues.put(PgConstant.NOOFROOMS,noofrooms);
                    contentValues.put(PgConstant.EQUIPPED_WITH,equippedwith);
                    contentValues.put(PgConstant.MONTHLY_CHARGE,monthlycharge);
                    contentValues.put(PgConstant.FACILITIES,facilities);
                    long row=sq.insert(PgConstant.SECOND_TABLE_NAME,null,contentValues);
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
