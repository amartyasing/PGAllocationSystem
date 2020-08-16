package com.example.pgallocationsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pgallocationsystem.com.dbtask.PgConstant;
import com.example.pgallocationsystem.com.dbtask.PgManager;

import java.util.ArrayList;

public class DeleteCandidate extends AppCompatActivity implements View.OnClickListener {
    Button btndelete;
    Spinner spincid;
    SQLiteDatabase sq;
    PgManager manager;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_candidate);
        btndelete=findViewById(R.id.deletebtncd);
        spincid=findViewById(R.id.deletecid);
        manager=new PgManager(this);
        sq=manager.openDb();
        arrayList=new ArrayList<>();
        Cursor cursor=sq.query(PgConstant.FIFH_TABLE_NAME,null,null,null,null,null,null);
        while (cursor!=null && cursor.moveToNext())
        {
            String cid=cursor.getString(cursor.getColumnIndex(PgConstant.CANDIDATEDETAILS_CID));
            arrayList.add(cid);
        }
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spincid.setAdapter(arrayAdapter);
        btndelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       final String deletecid=spincid.getSelectedItem().toString();
       String roomno = null;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Record Deletion");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String[]args={deletecid};
                int rw= sq.delete(PgConstant.FIFH_TABLE_NAME,PgConstant.CANDIDATEDETAILS_CID+"=?",args);
                if(rw>0)
                {
                    Toast.makeText(DeleteCandidate.this, "Row Deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("No",null);
        builder.setMessage("Are you sure you want delete");
        AlertDialog dialog=builder.create();
        dialog.show();
        String[]arg={deletecid};
        Cursor cursor=sq.query(PgConstant.FOURTH_TABLE_NAME,null,PgConstant.CID+"=?",arg,null,null,null);
        while (cursor!=null && cursor.moveToNext())
        {
            roomno=cursor.getString(cursor.getColumnIndex(PgConstant.ROOMALLOTMENT_ROOM_NO));
        }
        String [] argu={roomno};
        ContentValues contentValue=new ContentValues();
        contentValue.put(PgConstant.STATUS,"Free");
        sq.update(PgConstant.THIRD_TABLE_NAME,contentValue,PgConstant.ROOM_NO+"=?",argu);
        sq.delete(PgConstant.FOURTH_TABLE_NAME,PgConstant.CID+"=?",arg);




    }
}
