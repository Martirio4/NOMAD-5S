package com.nomad.auditoria5s.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nomad.auditoria5s.Fragment.FragmentSeiri;
import com.nomad.auditoria5s.R;

public class ActivityFormulario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        FragmentSeiri fragmentFormulario = new FragmentSeiri();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.formularioContenedor, fragmentFormulario);
        fragmentTransaction.commit();
    }
}
