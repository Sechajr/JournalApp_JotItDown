package com.example.android.jotitdown;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button  buttonLogin;
    SignInButton buttonGoogle;
    TextView TextForget;
    EditText editText2, editText6;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editText2 = (EditText) findViewById(R.id.editText2);
        editText6 = (EditText) findViewById(R.id.editText6);
        TextForget = (TextView) findViewById(R.id.TextForget);
        buttonGoogle = (SignInButton) findViewById(R.id.buttonGoogle);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user =firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);

        if (user != null){
            finish();
            startActivity(new Intent(Login.this, Homepage.class));
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateLogin()) {
                    validate(editText6.getText().toString(), editText2.getText().toString());
                }
            }
        });
        TextForget.setOnClickListener(this);
        buttonGoogle.setOnClickListener(this);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(Login.this, gso);
    }
//If user is already logged in, skip login activity
    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }
//updated UI if already signed in
    public void updateUI(GoogleSignInAccount account) {
        if (account!=null){
        startActivity(new Intent(Login.this,Homepage.class));}

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.TextForget:
                startActivity(new Intent(Login.this, ForgetPassword.class));
                break;

            case R.id.buttonGoogle:
                 Intent sigInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(sigInIntent, RC_SIGN_IN);


                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            startActivity(new Intent(Login.this,Homepage.class));
        }
    }

    private void validate (String userName, String userPassword){

        progressDialog.setMessage("Verifying");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, "Successful, Welcome", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, Homepage.class));

                }else
                    Toast.makeText(Login.this, "Something is wrong, Login has failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean validateLogin(){
        Boolean result = false;

        String email = editText2.getText().toString();
        String password = editText6.getText().toString();

        if (password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please enter valid details", Toast.LENGTH_SHORT).show();
        }else {
            result = true;
        }
        return result;
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
