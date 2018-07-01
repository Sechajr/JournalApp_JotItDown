package com.example.android.jotitdown;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    Button buttonRegister;
    EditText editText2, editText6, nameUser, nameUserSurname;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);


        firebaseAuth = FirebaseAuth.getInstance();
        editText2 = (EditText) findViewById(R.id.editText2);
        editText6 = (EditText) findViewById(R.id.editText6);
        nameUser = (EditText) findViewById(R.id.nameUser);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        nameUserSurname = (EditText) findViewById(R.id.nameUserSurname);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()){
                    //Upload entered data
                    String user_email = editText6.getText().toString().trim();
                    String user_password = editText2.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Registration.this,"Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registration.this, Login.class));
                            
                            }else {
                                Toast.makeText(Registration.this, "Not Successfull", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private Boolean validate(){
        Boolean result = false;
        
        String name = nameUser.getText().toString();
        String surname = nameUserSurname.getText().toString();
        String email = editText6.getText().toString();
        String password = editText2.getText().toString();
        
        if (name.isEmpty() || surname.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please enter valid details", Toast.LENGTH_SHORT).show();
    }else {
            result = true;
    }
       return result;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
