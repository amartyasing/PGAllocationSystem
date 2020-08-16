package com.example.pgallocationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pgallocationsystem.com.bean.CallMessage;
import com.example.pgallocationsystem.com.bean.FreeRooms;
import com.example.pgallocationsystem.com.dbtask.PgConstant;
import com.example.pgallocationsystem.com.dbtask.PgManager;

import java.util.ArrayList;

public class ViewRooms extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    PgManager manager;
    SQLiteDatabase sq;
    ArrayList<FreeRooms> arrayList;
    FreeRooms freeRooms;
    ArrayAdapter<FreeRooms> arrayAdapter;
    Dialog dialog;
    TextView txtequipped,txtmonthly,txtfacilities,txttype;
    ImageView closepopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rooms);
        listView=findViewById(R.id.listfreerooms);
        listView.setOnItemClickListener(this);
        dialog=new Dialog(this);

        arrayList=new ArrayList<>();
        manager=new PgManager(this);
        sq=manager.openDb();
        populatelist();

    }
    public void populatelist()
    {
        String[]args={"Free"};
        String[]coloumn={PgConstant.STATUS};
        Cursor cursor=sq.query(PgConstant.THIRD_TABLE_NAME,null,PgConstant.STATUS+"=?",args,null,null,null);
        while (cursor!=null && cursor.moveToNext()) {



            String roomno =cursor.getString(cursor.getColumnIndex(PgConstant.ROOM_NO));
            String roomid = cursor.getString(cursor.getColumnIndex(PgConstant.ROOMDETAILS_ROOM_ID));
            freeRooms=new FreeRooms(roomno,roomid);
            arrayList.add(freeRooms);


        }

        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String typeid=null,type=null,equippedwith=null,facilities=null,monthlycharge=null;
        dialog.setContentView(R.layout.popuproomdetails);
        closepopup=dialog.findViewById(R.id.closepopup);
        txtequipped=dialog.findViewById(R.id.txtviewequippedwith);
        txtfacilities=dialog.findViewById(R.id.txtviewfacilities);
        txtmonthly=dialog.findViewById(R.id.txtviewmonthlycharge);
        txttype=dialog.findViewById(R.id.txtviewroomtype);
        FreeRooms freeRoom=arrayList.get(i);
        String roomid=freeRoom.getRoomid();
        String []args={roomid};
        Cursor cursor3=sq.query(PgConstant.SECOND_TABLE_NAME,null,PgConstant.ROOM_ID+"=?",args,null,null,null);
        while (cursor3!=null && cursor3.moveToNext())
        {
            typeid=cursor3.getString(cursor3.getColumnIndex(PgConstant.ROOMCATEGORY_TYP_ID));
            equippedwith=cursor3.getString(cursor3.getColumnIndex(PgConstant.EQUIPPED_WITH));
            facilities=cursor3.getString(cursor3.getColumnIndex(PgConstant.FACILITIES));
            monthlycharge=cursor3.getString(cursor3.getColumnIndex(PgConstant.MONTHLY_CHARGE));
            //Toast.makeText(this, type, Toast.LENGTH_SHORT).show();

        }
        String []argutypeid={typeid};
        Cursor cursor5=sq.query(PgConstant.FIRST_TABLE_NAME,null,PgConstant.TYP_ID+"=?",argutypeid,null,null,null);
        while (cursor5!=null && cursor5.moveToNext())
        {
            type=cursor5.getString(cursor5.getColumnIndex(PgConstant.TYP_NAME));

        }

        txtequipped.setText("Equippedwith- "+equippedwith);
        txtmonthly.setText("Monthlycharge- "+monthlycharge);
        txtfacilities.setText("facilities-"+facilities);
        txttype.setText("Type-"+type);
        closepopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
}
