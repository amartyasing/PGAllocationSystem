package com.example.pgallocationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pgallocationsystem.com.dbtask.PgConstant;
import com.example.pgallocationsystem.com.dbtask.PgManager;

import java.util.ArrayList;

public class UpdateCandidate extends AppCompatActivity {
    Spinner spincid;
    EditText txtemail,txtphoneno,txtaddress,txtage,txtcollegename,txtcompanyname,txtalternatephoneno;
    RadioGroup rdprofession;
    Button btnupdate;
    SQLiteDatabase sq;
    PgManager manager;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_candidate);
        spincid=findViewById(R.id.updatecid);
        txtemail=findViewById(R.id.emailupdate);
        txtphoneno=findViewById(R.id.phoneupdate);
        txtaddress=findViewById(R.id.addressupdate);
        txtage=findViewById(R.id.ageupdate);
        rdprofession=findViewById(R.id.rdprofessionupdate);
        txtcollegename=findViewById(R.id.collegeupdate);
        txtcompanyname=findViewById(R.id.companyupdate);
        txtalternatephoneno=findViewById(R.id.alternateupdate);
        btnupdate=findViewById(R.id.updatebtncd);
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
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rb1=rdprofession.findViewById(rdprofession.getCheckedRadioButtonId());
                String profession= rb1.getText().toString();
                String cid=spincid.getSelectedItem().toString();
                String[] args={cid};
                String email=txtemail.getText().toString();
                String phoneno=txtphoneno.getText().toString();
                String address=txtaddress.getText().toString();
                String age=txtage.getText().toString();
                String collegename=txtcollegename.getText().toString();
                String companyname=txtcompanyname.getText().toString();
                String alternatephoneno=txtalternatephoneno.getText().toString();
                if ( TextUtils.isEmpty(email)||TextUtils.isEmpty(phoneno)||TextUtils.isEmpty(address)||TextUtils.isEmpty(age)||TextUtils.isEmpty(alternatephoneno))
                {
                    Toast.makeText(UpdateCandidate.this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ContentValues contentValues=new ContentValues();
                    contentValues.put(PgConstant.EMAIL,email);
                    contentValues.put(PgConstant.PHONE_NO,phoneno);
                    contentValues.put(PgConstant.ADDRESS,address);
                    contentValues.put(PgConstant.AGE,age);
                    contentValues.put(PgConstant.PROFESSION,profession);
                    contentValues.put(PgConstant.COLLEGE_NAME,collegename);
                    contentValues.put(PgConstant.COMPANY_NAME,companyname);
                    contentValues.put(PgConstant.ALTERNATE_PHONE_NO,alternatephoneno);
                    long row=sq.update(PgConstant.FIFH_TABLE_NAME,contentValues,PgConstant.CANDIDATEDETAILS_CID+"=?",args);
                    if (row>0)
                    {
                        Toast.makeText(UpdateCandidate.this, "data updated", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(manager!=null)
        {
            manager.closeDb();
        }
    }
}
