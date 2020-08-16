package com.example.pgallocationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.pgallocationsystem.com.dbtask.PgConstant;
import com.example.pgallocationsystem.com.dbtask.PgManager;

public class AddCandidate extends AppCompatActivity {
    EditText txtcid,txtname,txtemail,txtphoneno,txtaddress,txtage,txtcollegename,txtcompanyname,txtalternatephoneno;
    RadioGroup rdgender,rdprofession;
    Button btnadd;
    SQLiteDatabase sq;
    PgManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidate);
        txtcid=findViewById(R.id.cidadd);
        txtname=findViewById(R.id.nameadd);
        txtemail=findViewById(R.id.emailadd);
        txtphoneno=findViewById(R.id.phoneadd);
        txtaddress=findViewById(R.id.addressadd);
        rdgender=findViewById(R.id.rdgender);
        txtage=findViewById(R.id.ageadd);
        rdprofession=findViewById(R.id.rdprofession);
        txtcollegename=findViewById(R.id.collegeadd);
        txtcompanyname=findViewById(R.id.companyadd);
        txtalternatephoneno=findViewById(R.id.alternateadd);
        btnadd=findViewById(R.id.btnsubmit);
        manager=new PgManager(this);
        sq=manager.openDb();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rb=rdgender.findViewById(rdgender.getCheckedRadioButtonId());
                String gendertype= rb.getText().toString();
                RadioButton rb1=rdprofession.findViewById(rdprofession.getCheckedRadioButtonId());
                String profession= rb1.getText().toString();
                String cid=txtcid.getText().toString();
                String name=txtname.getText().toString();
                String email=txtemail.getText().toString();
                String phoneno=txtphoneno.getText().toString();
                String address=txtaddress.getText().toString();
                String age=txtage.getText().toString();
                String collegename=txtcollegename.getText().toString();
                String companyname=txtcompanyname.getText().toString();
                String alternatephoneno=txtalternatephoneno.getText().toString();
                if (TextUtils.isEmpty(cid) || TextUtils.isEmpty(name)|| TextUtils.isEmpty(email)||TextUtils.isEmpty(phoneno)||TextUtils.isEmpty(address)||TextUtils.isEmpty(age)||TextUtils.isEmpty(alternatephoneno))
                {
                    Toast.makeText(AddCandidate.this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ContentValues contentValues=new ContentValues();
                    contentValues.put(PgConstant.CANDIDATEDETAILS_CID,cid);
                    contentValues.put(PgConstant.NAME,name);
                    contentValues.put(PgConstant.EMAIL,email);
                    contentValues.put(PgConstant.PHONE_NO,phoneno);
                    contentValues.put(PgConstant.ADDRESS,address);
                    contentValues.put(PgConstant.GENDER,gendertype);
                    contentValues.put(PgConstant.AGE,age);
                    contentValues.put(PgConstant.PROFESSION,profession);
                    contentValues.put(PgConstant.COLLEGE_NAME,collegename);
                    contentValues.put(PgConstant.COMPANY_NAME,companyname);
                    contentValues.put(PgConstant.ALTERNATE_PHONE_NO,alternatephoneno);
                    long row=sq.insert(PgConstant.FIFH_TABLE_NAME,null,contentValues);
                    if (row>0)
                    {
                        Toast.makeText(AddCandidate.this, "data added "+row, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
