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
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.nomad.auditoria5s.Adapter.AdapterFotos;
import com.nomad.auditoria5s.Model.Foto;
import com.nomad.auditoria5s.R;

import java.io.File;

import io.realm.RealmList;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSeiton extends Fragment {

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
    private FloatingActionMenu fabMenu;

    private RealmList<Foto> listaFotos;


    public FragmentSeiton() {
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
                EasyImage.openCamera(FragmentSeiton.this, 1);
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


}
