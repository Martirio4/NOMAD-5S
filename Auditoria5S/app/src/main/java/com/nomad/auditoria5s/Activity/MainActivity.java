package com.nomad.auditoria5s.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nomad.auditoria5s.R;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_main);
        Intent unIntent= new Intent(this, ActivityFormulario.class);
        startActivity(unIntent);
    }
}
