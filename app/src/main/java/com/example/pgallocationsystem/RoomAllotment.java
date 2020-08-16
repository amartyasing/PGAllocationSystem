package com.example.pgallocationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pgallocationsystem.com.dbtask.PgConstant;
import com.example.pgallocationsystem.com.dbtask.PgManager;

import java.util.ArrayList;
import java.util.Calendar;

public class RoomAllotment extends AppCompatActivity  implements View.OnClickListener {
    Button btndate,btnallot;
    EditText txtdate,txtallotid;
    Spinner spinroomid,spinroomno,spincid;
    DatePickerDialog pickerDialog;
    ArrayAdapter<String> roomidarrayAdapter,roomnoarrayadapter,cidarrayadapter;
    ArrayList<String> roomidarrayList,roomnoarraylist,cidarraylist;
    SQLiteDatabase sq;
    PgManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_allotment);
        Calendar calendar=Calendar.getInstance();
        final int tmonth=calendar.get(Calendar.MONTH);
        final int tyear=calendar.get(Calendar.YEAR);
        final int tdate=calendar.get(Calendar.DATE);
        txtallotid=findViewById(R.id.allotid);
        spincid=findViewById(R.id.cidroomallot);
        spinroomid=findViewById(R.id.roomidroomallot);
        spinroomno=findViewById(R.id.roomnoroomallot);
        btndate=findViewById(R.id.btndate);
        txtdate=findViewById(R.id.txtdate);
        btnallot=findViewById(R.id.btnallotroom);
        btnallot.setOnClickListener(this);
        manager=new PgManager(this);
        sq=manager.openDb();
        String []status={"Free"};
        roomidarrayList=new ArrayList<>();
        Cursor cursor=sq.query(PgConstant.SECOND_TABLE_NAME,null,null,null,null,null,null);
        while (cursor!=null && cursor.moveToNext())
        {
            String roomid=cursor.getString(cursor.getColumnIndex(PgConstant.ROOM_ID));
            roomidarrayList.add(roomid);
        }
        roomidarrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,roomidarrayList);
        roomidarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinroomid.setAdapter(roomidarrayAdapter);
        spinroomid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String []itemselected={adapterView.getItemAtPosition(i).toString(),"Free"};
                roomnoarraylist=new ArrayList<>();
                Cursor cursor1=sq.query(PgConstant.THIRD_TABLE_NAME,null,PgConstant.ROOM_ID+"=? and "+PgConstant.STATUS+"=?" ,itemselected,null,null,null);
                while (cursor1!=null && cursor1.moveToNext())
                {
                    String roomno=cursor1.getString(cursor1.getColumnIndex(PgConstant.ROOM_NO));
                    roomnoarraylist.add(roomno);
                }
                roomnoarrayadapter=new ArrayAdapter<>(RoomAllotment.this,android.R.layout.simple_spinner_item,roomnoarraylist);
                roomnoarrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinroomno.setAdapter(roomnoarrayadapter);
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        roomnoarraylist=new ArrayList<>();
        Cursor cursor1=sq.query(PgConstant.THIRD_TABLE_NAME,null,PgConstant.STATUS+"=?",status,null,null,null);
        while (cursor1!=null && cursor1.moveToNext())
        {
            String roomno=cursor1.getString(cursor1.getColumnIndex(PgConstant.ROOM_NO));
            roomnoarraylist.add(roomno);
        }
        roomnoarrayadapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,roomnoarraylist);
        roomnoarrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinroomno.setAdapter(roomnoarrayadapter);


        cidarraylist=new ArrayList<>();
        Cursor cursor2=sq.query(PgConstant.FIFH_TABLE_NAME,null,null,null,null,null,null);
        while (cursor2!=null && cursor2.moveToNext())
        {
            String cid=cursor2.getString(cursor2.getColumnIndex(PgConstant.CANDIDATEDETAILS_CID));
            cidarraylist.add(cid);
        }
        cidarrayadapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,cidarraylist);
        cidarrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spincid.setAdapter(cidarrayadapter);


        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                pickerDialog=new DatePickerDialog(RoomAllotment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String date=year+"/"+(month+1)+"/"+dayOfMonth;
                        txtdate.setText(date);

                    }
                }, tyear, tmonth, tdate);
                pickerDialog.show();

            }
        });
    }

    @Override
    public void onClick(View view) {
        String allotid,cid,roomid,roomno,dateofallotment,type = null,typeid=null,phoneno=null;
        allotid=txtallotid.getText().toString();
        cid=spincid.getSelectedItem().toString();
        roomid=spinroomid.getSelectedItem().toString();
        roomno=spinroomno.getSelectedItem().toString();
        dateofallotment=txtdate.getText().toString();
        if(TextUtils.isEmpty(allotid) || TextUtils.isEmpty(dateofallotment))
        {
            Toast.makeText(RoomAllotment.this, "Fields Can't be Empty", Toast.LENGTH_SHORT).show();
        }
        else if (search(cid)==true)
        {
            Toast.makeText(this, "Room Already Alloted", Toast.LENGTH_SHORT).show();
        }

        else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(PgConstant.ALLOT_ID, allotid);
            contentValues.put(PgConstant.CID, cid);
            contentValues.put(PgConstant.ROOMALLOTMENT_ROOM_ID, roomid);
            contentValues.put(PgConstant.ROOMALLOTMENT_ROOM_NO, roomno);
            contentValues.put(PgConstant.DATEOFALLOTMENT, dateofallotment);
            long row = sq.insert(PgConstant.FOURTH_TABLE_NAME, null, contentValues);
            if (row > 0) {
                Toast.makeText(RoomAllotment.this, "Room Alloted", Toast.LENGTH_SHORT).show();
            }
            Cursor cursor6 = sq.query(PgConstant.FIFH_TABLE_NAME, null, PgConstant.CANDIDATEDETAILS_CID + "=?", new String[]{cid}, null, null, null);
            while (cursor6 != null && cursor6.moveToNext()) {
                phoneno = cursor6.getString(cursor6.getColumnIndex(PgConstant.PHONE_NO));
            }
            String msg = "Congratulations Room have been Alloted to you . \nAllot id= " + allotid + "\n" + "Roomno= " + roomno;
            Intent intent = new Intent(RoomAllotment.this, RoomAllotment.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(RoomAllotment.this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneno, null, msg, pendingIntent, null);
            Toast.makeText(RoomAllotment.this, "Sent", Toast.LENGTH_SHORT).show();

            String[] args = {roomid};
            Cursor cursor3 = sq.query(PgConstant.SECOND_TABLE_NAME, null, PgConstant.ROOM_ID + "=?", args, null, null, null);
            while (cursor3 != null && cursor3.moveToNext()) {
                typeid = cursor3.getString(cursor3.getColumnIndex(PgConstant.ROOMCATEGORY_TYP_ID));
                //Toast.makeText(this, type, Toast.LENGTH_SHORT).show();
            }
            String[] argutypeid = {typeid};
            Cursor cursor5 = sq.query(PgConstant.FIRST_TABLE_NAME, null, PgConstant.TYP_ID + "=?", argutypeid, null, null, null);
            while (cursor5 != null && cursor5.moveToNext()) {
                type = cursor5.getString(cursor5.getColumnIndex(PgConstant.TYP_NAME));

            }

            if (type.equalsIgnoreCase("Single")) {
                String[] arg = {roomno};
                ContentValues contentValue = new ContentValues();
                contentValue.put(PgConstant.STATUS, "Busy");
                sq.update(PgConstant.THIRD_TABLE_NAME, contentValue, PgConstant.ROOM_NO + "=?", arg);

            } else {
                String[] a = {roomno};
                Toast.makeText(this, "else", Toast.LENGTH_SHORT).show();
                Cursor cursor4 = sq.rawQuery("SELECT count(*) from " + PgConstant.FOURTH_TABLE_NAME + " WHERE " + PgConstant.ROOMALLOTMENT_ROOM_NO + "=?", a);
                cursor4.moveToFirst();
                int count = cursor4.getInt(0);
                cursor4.close();
                if (count == 2) {
                    String[] argu = {roomno};
                    ContentValues contentValue = new ContentValues();
                    contentValue.put(PgConstant.STATUS, "Busy");
                    sq.update(PgConstant.THIRD_TABLE_NAME, contentValue, PgConstant.ROOM_NO + "=?", argu);
                }
            }
        }

        }


  public boolean search(String cid)
    {
        Cursor cursor8=sq.query(PgConstant.FOURTH_TABLE_NAME,null,PgConstant.CID+"=?",new String[]{cid},null,null,null);
        if(cursor8.moveToNext())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
