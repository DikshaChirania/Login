package com.example.hello.fireapp;


import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import static android.R.id.message;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   Button btn;
    TextView txts;
    EditText edn,edp,ede;
    ProgressDialog pro;
    FirebaseAuth fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button2);
        pro=new ProgressDialog(this);
        fa= FirebaseAuth.getInstance();
        txts = (TextView) findViewById(R.id.textView4);
        //edn = (EditText) findViewById(R.id.editText);
        edp = (EditText) findViewById(R.id.editText3);
        ede = (EditText) findViewById(R.id.editText2);
        btn.setOnClickListener(this);
        txts.setOnClickListener(this);
    }

    private void Registeruser() {

       // String username = edn.getText().toString();
        String mail = ede.getText().toString();
        String password = edp.getText().toString();
        if (TextUtils.isEmpty(mail)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        } //else if (TextUtils.isEmpty(username)) {
            //Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
            //return;
         else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        pro.setMessage("Registering user.....");
        pro.show();
        fa.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "successfully registered", Toast.LENGTH_SHORT).show();
                    pro.dismiss();

                } else
                if (!task.isSuccessful()) {
                    FirebaseAuthException e = (FirebaseAuthException) task.getException();
                    Toast.makeText(MainActivity.this, "Failed Registration: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    pro.dismiss();
                }
            }
        });


        }



    public  void onClick(View view)
    {
        if(view.equals(btn))
            Registeruser();
        else
        if(view.equals(txts))
            Registeruser();

    }

}