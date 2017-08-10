package com.nomad.auditoria5s.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.nomad.auditoria5s.R;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private Button botonInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_main);
        botonInicio=(Button)findViewById(R.id.botonInicio);
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unIntent= new Intent(v.getContext(), ActivityFormulario.class);
                startActivity(unIntent);
            }
        });


    }
}
