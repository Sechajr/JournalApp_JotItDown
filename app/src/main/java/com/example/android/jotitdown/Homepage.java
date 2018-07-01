package com.example.android.jotitdown;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity  {

    Button buttonNewNote;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient mGoogleSignInClient;
    DBase JotItDownDB;
    ListView rv_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        buttonNewNote = (Button) findViewById(R.id.buttonNewNote);
        firebaseAuth =  FirebaseAuth.getInstance();
        JotItDownDB = new DBase(this);
        rv_notes = (ListView) findViewById(R.id.rv_notes);

        buttonNewNote.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), Newnote.class));
            }
        });
    //new for Google sign out
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(Homepage.this, gso);

        populateListView();
    }

    private void populateListView(){
        Cursor data = JotItDownDB.getTitles();
        ArrayList<String> listTitles = new ArrayList<>();
        while (data.moveToNext()){
            listTitles.add(data.getString(1));
        }

        ListAdapter adapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTitles);
        rv_notes.setAdapter(adapter);

        rv_notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String Title = parent.getItemAtPosition(i).toString();
                String Notes = "Hello";
                Cursor data = JotItDownDB.getTitleId(Title);
                int TitleID = -1;
                while (data.moveToNext()){
                    TitleID = data.getInt(0);
                    Notes = data.getString(3);
                }
                if(TitleID > -1){
                    Intent editNote = new Intent(Homepage.this, Editnote.class);
                    editNote.putExtra("id", TitleID);
                    editNote.putExtra("Title", Title);
                    editNote.putExtra("Notes", Notes);
                    startActivity(editNote);
                }else
                {
                    Toast.makeText(Homepage.this, "Opps, there is no such note!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout: {
                firebaseAuth.signOut();
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(Homepage.this,Login.class));
                            }
                        });
                finish();
                startActivity(new Intent(Homepage.this, Login.class));
            }
        }
        return super.onOptionsItemSelected(item);
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
