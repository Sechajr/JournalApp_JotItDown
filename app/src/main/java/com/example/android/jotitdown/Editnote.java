package com.example.android.jotitdown;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editnote extends AppCompatActivity {

    Button buttonSaveChanges, buttonDelete;
    EditText editNote;
    DBase JotItDownDB;
    String selectedNote, selectedTitle;
    int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editnote);


        buttonSaveChanges = (Button) findViewById(R.id.buttonSaveChanges);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        editNote = (EditText) findViewById(R.id.editNote);
        JotItDownDB = new DBase(this);

        Intent receivedIntent = getIntent();

        selectedID = receivedIntent.getIntExtra("id",-1);
        selectedNote = receivedIntent.getStringExtra("Notes");
        selectedTitle = receivedIntent.getStringExtra("Title");

        editNote.setText(selectedNote);

        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editedNote = editNote.getText().toString();

                JotItDownDB.editNote(editedNote, selectedID );
                startActivity(new Intent(Editnote.this, Homepage.class));

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JotItDownDB.deleteNote(selectedID,selectedTitle,selectedNote);
                startActivity(new Intent(Editnote.this, Homepage.class));
            }
        });

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
