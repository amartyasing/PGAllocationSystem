package com.example.pgallocationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   TextInputLayout txtid,txtpass;
    Button btnlogin;
    FirebaseAuth firebaseAuth;
    String userpass,userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtid=findViewById(R.id.userid);
        txtpass=findViewById(R.id.userpass);
        btnlogin=findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();

    }
    public boolean validateemail()
    {
        userid=txtid.getEditText().getText().toString().trim();
        if (userid.isEmpty())
        {
            txtid.setError("User Id Can't Be Empty");
            return false;
        }
        else
        {
            txtid.setError(null);
            return true;
        }

    }
    public boolean validatepass()
    {
       userpass=txtpass.getEditText().getText().toString().trim();
        if(userpass.isEmpty())
        {
            txtpass.setError("Password can't be empty");
            return false;
        }
        else {
            txtpass.setError(null);
            return true;
        }

    }

    @Override
    public void onClick(View view) {
        if (!validateemail() | !validatepass())
        {
            return;
        }
        else
        {
            firebaseAuth.signInWithEmailAndPassword(userid,userpass).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Intent intent = new Intent(MainActivity.this, OptionActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Invalid Userid/Password", Toast.LENGTH_SHORT).show();
                    }



                }
            });

        }

    }
}
