package com.example.android.jotitdown;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Newnote extends AppCompatActivity implements View.OnClickListener{

    Button buttonSave;
    EditText newNoteTitle, newNoteDate, newNotes;
    DBase JotItDownDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note);

        newNoteTitle = (EditText) findViewById(R.id.newNoteTitle);
        newNoteDate = (EditText) findViewById(R.id.newNoteDate);
        newNotes = (EditText) findViewById(R.id.newNotes);
        buttonSave = (Button) findViewById(R.id.buttonSave);

        JotItDownDB = new DBase(this);
        buttonSave.setOnClickListener(this);

        AddData();

    }

    public void AddData(){
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = newNoteTitle.getText().toString();
                String Date = newNoteDate.getText().toString();
                String Notes = newNotes.getText().toString();
                boolean insertData = JotItDownDB.addData(Title, Date, Notes);
                if(insertData==true){
                    Toast.makeText(Newnote.this, "New note saved!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(Newnote.this, Homepage.class));
                }else {
                    Toast.makeText(Newnote.this, "Opps, fill all the fields!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buttonSave:

              //  startActivity(new Intent(this, Homepage.class));

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




