package com.nomad.auditoria5s.Fragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.nomad.auditoria5s.Activity.MainActivity;
import com.nomad.auditoria5s.Activity.RadarChartActivitry;
import com.nomad.auditoria5s.Adapter.AdapterFotos;
import com.nomad.auditoria5s.Model.Foto;
import com.nomad.auditoria5s.Model.Seiri;
import com.nomad.auditoria5s.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSeiri extends Fragment {

    private TextView titulo110;
    private TextView titulo120;
    private TextView titulo130;
    private TextView titulo140;

    private RadioGroup radioGroup110;
    private RadioGroup radioGroup120;
    private RadioGroup radioGroup130;
    private RadioGroup radioGroup140;

    private TextView titulo;

    private ScrollView mainScrollView;
    private RecyclerView recyclerEvidencias;
    private AdapterFotos adapterFotos;
    private LinearLayoutManager recyclerLayout;
    private FloatingActionButton fabEvidencias;
    private FloatingActionButton fabGraficos;
    private FloatingActionMenu fabMenu;
    private FloatingActionButton fabSalir;

    private RealmList<Foto> listaFotos;


    public FragmentSeiri() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_formulario, container, false);


        titulo110=(TextView)vista.findViewById(R.id.item110);
        titulo120=(TextView)vista.findViewById(R.id.item120);
        titulo130=(TextView)vista.findViewById(R.id.item130);
        titulo140=(TextView)vista.findViewById(R.id.item140);

        radioGroup110=(RadioGroup)vista.findViewById(R.id.rGItem11);
        radioGroup120=(RadioGroup)vista.findViewById(R.id.rGItem120);
        radioGroup130=(RadioGroup)vista.findViewById(R.id.rGItem130);
        radioGroup140=(RadioGroup)vista.findViewById(R.id.rGItem140);

        titulo= (TextView)vista.findViewById(R.id.tituloFormulario);
        mainScrollView=(ScrollView)vista.findViewById(R.id.scrollFormulario);


        //Setear Recicler evidencias
        recyclerEvidencias=(RecyclerView)vista.findViewById(R.id.recyclerFotosFormulario);
        recyclerLayout= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        adapterFotos= new AdapterFotos();
        adapterFotos.setContext(getContext());
        listaFotos=new RealmList<>();
        adapterFotos.setListaFotosOriginales(listaFotos);
        recyclerEvidencias.setAdapter(adapterFotos);
        recyclerEvidencias.setLayoutManager(recyclerLayout);

        fabMenu=(FloatingActionMenu)vista.findViewById(R.id.fab_menu);


        fabEvidencias = new FloatingActionButton(getActivity());
        fabEvidencias.setButtonSize(FloatingActionButton.SIZE_MINI);
        fabEvidencias.setLabelText(getString(R.string.tagSacarFoto));
        fabEvidencias.setImageResource(R.drawable.ic_camera_alt_black_24dp);
        fabMenu.addMenuButton(fabEvidencias);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fabEvidencias.setLabelColors(ContextCompat.getColor(getActivity(), R.color.rojoOscuro),
                    ContextCompat.getColor(getActivity(), R.color.light_grey),
                    ContextCompat.getColor(getActivity(), R.color.white_transparent));
            fabEvidencias.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
        else {
            fabEvidencias.setLabelColors(getResources().getColor(R.color.rojoOscuro),
                    getResources().getColor(R.color.light_grey),
                    getResources().getColor(R.color.white_transparent));
            fabEvidencias.setLabelTextColor(getResources().getColor( R.color.black));
        }


        fabEvidencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabMenu.close(true);
                EasyImage.openCamera(FragmentSeiri.this, 1);
            }
        });


        fabGraficos = new FloatingActionButton(getActivity());
        fabGraficos.setButtonSize(FloatingActionButton.SIZE_MINI);
        fabGraficos.setLabelText(getString(R.string.tagVerGrafico));
        fabGraficos.setImageResource(R.drawable.ic_show_chart_black_24dp);
        fabMenu.addMenuButton(fabGraficos);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fabGraficos.setLabelColors(ContextCompat.getColor(getActivity(), R.color.rojoOscuro),
                    ContextCompat.getColor(getActivity(), R.color.light_grey),
                    ContextCompat.getColor(getActivity(), R.color.white_transparent));
            fabGraficos.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));
        }
        else {
            fabGraficos.setLabelColors(getResources().getColor(R.color.rojoOscuro),
                    getResources().getColor(R.color.light_grey),
                    getResources().getColor(R.color.white_transparent));
            fabGraficos.setLabelTextColor(getResources().getColor( R.color.black));
        }


        fabGraficos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabMenu.close(true);
                // Obtain a Realm instance
                try {

                    Seiri otroSeiri=chequearRadioButtons();
                    String unString =otroSeiri.getPuntajePunto1().toString();
                    Intent unIntent=new Intent(getContext(), RadarChartActivitry.class);
                    Bundle unBundle=new Bundle();
                    unBundle.putInt(RadarChartActivitry.PUNJTAJE1, otroSeiri.getPuntajePunto1());
                    unBundle.putInt(RadarChartActivitry.PUNJTAJE2, otroSeiri.getPuntajePunto2());
                    unBundle.putInt(RadarChartActivitry.PUNJTAJE3, otroSeiri.getPuntajePunto3());
                    unBundle.putInt(RadarChartActivitry.PUNJTAJE4, otroSeiri.getPuntajePunto4());
                    unBundle.putFloat(RadarChartActivitry.PROMEDIO, otroSeiri.getPuntajePromedio());
                    unIntent.putExtras(unBundle);
                    startActivity(unIntent);
                }
                catch (Exception e) {

                }
                //chequear los radio buttons
                //cargar los datos en la base de datos
                //ir al grafico
            }
        });


        fabSalir = new FloatingActionButton(getActivity());
        fabSalir.setButtonSize(FloatingActionButton.SIZE_MINI);
        fabSalir.setLabelText(getString(R.string.salirDeSistema));
        fabSalir.setImageResource(R.drawable.ic_cancel_black_24dp);

        fabMenu.addMenuButton(fabSalir);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fabSalir.setLabelColors(ContextCompat.getColor(getActivity(), R.color.accent),
                    ContextCompat.getColor(getActivity(), R.color.light_grey),
                    ContextCompat.getColor(getActivity(), R.color.white_transparent));
            fabSalir.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));
            fabSalir.setColorNormal(ContextCompat.getColor(getActivity(), R.color.accent));
        }
        else {
            fabSalir.setLabelColors(getResources().getColor(R.color.accent),
                    getResources().getColor(R.color.light_grey),
                    getResources().getColor(R.color.white_transparent));
            fabSalir.setLabelTextColor(getResources().getColor( R.color.black));
            fabSalir.setColorNormal(ContextCompat.getColor(getActivity(), R.color.accent));
        }


        fabSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabMenu.close(true);
                Intent unIntent = new Intent(getContext(), MainActivity.class);
                startActivity(unIntent);
                getActivity().finish();
            }
        });

        titulo110.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup110.getVisibility()==View.GONE){
                    esconderTodosRadioGroup();
                    radioGroup110.setVisibility(View.VISIBLE);
                }
                else{
                    radioGroup110.setVisibility(View.GONE);
                }
                mainScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
        titulo120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup120.getVisibility()==View.GONE){
                    esconderTodosRadioGroup();
                    radioGroup120.setVisibility(View.VISIBLE);
                }
                else{
                    radioGroup120.setVisibility(View.GONE);
                }
                mainScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
        titulo130.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup130.getVisibility()==View.GONE){
                    esconderTodosRadioGroup();
                    radioGroup130.setVisibility(View.VISIBLE);
                }
                else{
                    radioGroup130.setVisibility(View.GONE);
                }
                mainScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
        titulo140.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup140.getVisibility()==View.GONE){
                    esconderTodosRadioGroup();
                    radioGroup140.setVisibility(View.VISIBLE);
                }
                else{
                    radioGroup140.setVisibility(View.GONE);
                }
                mainScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        return vista;
    }

    public void esconderTodosRadioGroup(){
        radioGroup110.setVisibility(View.GONE);
        radioGroup120.setVisibility(View.GONE);
        radioGroup130.setVisibility(View.GONE);
        radioGroup140.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult( int  requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {

                if (type==1){
                    Foto unaFoto=new Foto();
                    unaFoto.setRuta(imageFile.getAbsolutePath());
                    listaFotos.add(unaFoto);
                    adapterFotos.notifyDataSetChanged();
                }
                if (type==2){

                    Toast.makeText(getContext(), "metodo no implementado", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                super.onCanceled(source, type);
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(getActivity());
                    if (photoFile != null) photoFile.delete();
                }
            }
        });
    }

    public Seiri chequearRadioButtons() {
        Seiri seiri= new Seiri();
        List<String> unaLista = new ArrayList<>();
        if (radioGroup110.getCheckedRadioButtonId() == -1) {
            unaLista.add("1.1");
        }
        if (radioGroup120.getCheckedRadioButtonId() == -1) {
            unaLista.add("1.2");
        }
        if (radioGroup130.getCheckedRadioButtonId() == -1) {
            unaLista.add("1.3");
        }
        if (radioGroup140.getCheckedRadioButtonId() == -1) {
            unaLista.add("1.4");
        }
        if (unaLista.size()>=1){
            Toast.makeText(getContext(), "Debe completar los puntos; "+unaLista.toString(), Toast.LENGTH_SHORT).show();
            return seiri;
        }
        else {
            seiri.setPuntajePunto1(radioGroup110.indexOfChild(getActivity().findViewById(radioGroup110.getCheckedRadioButtonId()))+1);
            seiri.setPuntajePunto2(radioGroup120.indexOfChild(getActivity().findViewById(radioGroup120.getCheckedRadioButtonId()))+1);
            seiri.setPuntajePunto3(radioGroup130.indexOfChild(getActivity().findViewById(radioGroup130.getCheckedRadioButtonId()))+1);
            seiri.setPuntajePunto4(radioGroup140.indexOfChild(getActivity().findViewById(radioGroup140.getCheckedRadioButtonId()))+1);
            Integer sumita= seiri.getPuntajePunto1()+seiri.getPuntajePunto2()+seiri.getPuntajePunto3()+seiri.getPuntajePunto4();
            seiri.setPuntajePromedio((float)(sumita/4));
            return seiri;
        }
    }

}
