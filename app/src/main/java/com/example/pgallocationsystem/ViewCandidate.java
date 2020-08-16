package com.example.pgallocationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pgallocationsystem.com.bean.CallMessage;
import com.example.pgallocationsystem.com.bean.Candidate;
import com.example.pgallocationsystem.com.dbtask.PgConstant;
import com.example.pgallocationsystem.com.dbtask.PgManager;

import java.util.ArrayList;

public class ViewCandidate extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    EditText txtcid;
    PgManager manager;
    Button btnview;
    SQLiteDatabase sq;
    ArrayList<Candidate> arrayList;
    Candidate candidate;
    ArrayAdapter<Candidate> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_candidate);
        listView=findViewById(R.id.listcandidate);
        txtcid=findViewById(R.id.textviewcid);
        btnview=findViewById(R.id.btnviewcandidate);
        btnview.setOnClickListener(this);
        manager=new PgManager(this);
        sq=manager.openDb();
        arrayList=new ArrayList<>();

    }
    public void populatelist(final String cid)
    {
        String [] args={cid};
        Cursor cursor=sq.query(PgConstant.FIFH_TABLE_NAME,null,PgConstant.CANDIDATEDETAILS_CID+"=?",args,null,null,null);
        while (cursor!=null && cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(PgConstant.NAME));
            String email = cursor.getString(cursor.getColumnIndex(PgConstant.EMAIL));
            String phone = cursor.getString(cursor.getColumnIndex(PgConstant.PHONE_NO));
            String address = cursor.getString(cursor.getColumnIndex(PgConstant.ADDRESS));
            String gender = cursor.getString(cursor.getColumnIndex(PgConstant.GENDER));
            String age = cursor.getString(cursor.getColumnIndex(PgConstant.AGE));
            String profession = cursor.getString(cursor.getColumnIndex(PgConstant.PROFESSION));
            String collegename = cursor.getString(cursor.getColumnIndex(PgConstant.COLLEGE_NAME));
            String companyname = cursor.getString(cursor.getColumnIndex(PgConstant.COMPANY_NAME));
            String altetrnatephoneno = cursor.getString(cursor.getColumnIndex(PgConstant.ALTERNATE_PHONE_NO));
            candidate = new Candidate(name, email, phone, address, gender, age, profession, collegename, companyname, altetrnatephoneno);
            arrayList.add(candidate);
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(arrayAdapter);
        }

    }



    @Override
    public void onClick(View view) {
        String cid=txtcid.getText().toString();
        if(search(cid)==true)
        {
            populatelist(cid);
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
