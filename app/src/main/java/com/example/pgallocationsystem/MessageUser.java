package com.example.pgallocationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pgallocationsystem.com.dbtask.PgConstant;
import com.example.pgallocationsystem.com.dbtask.PgManager;

public class MessageUser extends AppCompatActivity implements View.OnClickListener {
    EditText txtcid,txtmessage;
    Button btnmessage;
    PgManager manager;
    SQLiteDatabase sq;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_user);
        txtcid=findViewById(R.id.messagecid);
        txtmessage=findViewById(R.id.messagemessage);
        btnmessage=findViewById(R.id.btnmessage);
        btnmessage.setOnClickListener(this);
        manager=new PgManager(this);
        sq=manager.openDb();
    }

    @Override
    public void onClick(View view) {
        String cid=txtcid.getText().toString();
        if(search(cid)==true)
        {
            String[]args={cid};
            Cursor cursor=sq.query(PgConstant.FIFH_TABLE_NAME,null,PgConstant.CANDIDATEDETAILS_CID+"=?",args,null,null,null) ;
            while(cursor!=null && cursor.moveToNext()) {
                phone = cursor.getString(cursor.getColumnIndex(PgConstant.PHONE_NO));
                Toast.makeText(this, phone, Toast.LENGTH_SHORT).show();
                String message = txtmessage.getText().toString();
                Intent intent = new Intent(MessageUser.this, MessageUser.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MessageUser.this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone, null, message, pendingIntent, null);
                Toast.makeText(MessageUser.this, "Sent", Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(this, "no such cid exist", Toast.LENGTH_SHORT).show();
        }

    }
    public boolean search(String cid)
    {
        String[]coloumns={PgConstant.CANDIDATEDETAILS_CID};
        String[]args={cid};// for vlaue which is to be selected
        Cursor cursor=sq.query(PgConstant.FIFH_TABLE_NAME,coloumns,PgConstant.CANDIDATEDETAILS_CID+"=?",args,null,null,null) ;
        if(cursor.moveToNext())
        {

            return true;
        }
        else
        {
            return false;
        }
    }
}
