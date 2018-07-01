package com.example.android.jotitdown;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Welcome extends AppCompatActivity implements View.OnClickListener {
    Button welcomeLogin,welcomeCreate;
    TextView logoText, WelcomeText, WelcomeText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);


        welcomeCreate = (Button) findViewById(R.id.welcomeCreate);
        welcomeLogin = (Button) findViewById(R.id.welcomeLogin);
        logoText = (TextView) findViewById(R.id.logoText);
        WelcomeText = (TextView) findViewById(R.id.WelcomeText);
        WelcomeText2 = (TextView) findViewById(R.id.WelcomeText2);


        Typeface myCustomfont = Typeface.createFromAsset(getAssets(), "fonts/Ubuntu-Title.ttf");
        logoText.setTypeface(myCustomfont);
        Typeface myCustomfont2 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        WelcomeText.setTypeface(myCustomfont2);
        Typeface myCustomfont3 = Typeface.createFromAsset(getAssets(), "fonts/Angelique-Rose-font-FFP.ttf");
        WelcomeText2.setTypeface(myCustomfont3);

        welcomeLogin.setOnClickListener(this);
        welcomeCreate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.welcomeCreate:

                startActivity(new Intent(this, Registration.class));

                break;
            case R.id.welcomeLogin:

                startActivity(new Intent(this, Login.class));

                break;
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
