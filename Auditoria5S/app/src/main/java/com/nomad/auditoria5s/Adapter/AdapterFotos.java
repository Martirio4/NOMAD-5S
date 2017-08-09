package com.nomad.auditoria5s.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nomad.auditoria5s.Model.Foto;

import com.nomad.auditoria5s.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;

import io.realm.RealmList;

/**
 * Created by elmar on 18/5/2017.
 */

public class AdapterFotos extends RecyclerView.Adapter implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    private RealmList<Foto> listaFotosOriginales;
    private RealmList<Foto> listaFotosFavoritos;
    private View.OnClickListener listener;
    private AdapterView.OnLongClickListener listenerLong;
    private Favoritable favoritable;
    private TextView elTextView;

    public void setLongListener(View.OnLongClickListener unLongListener) {
        this.listenerLong = unLongListener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setListaFotosOriginales(RealmList<Foto> listaFotosOriginales) {
        this.listaFotosOriginales = listaFotosOriginales;
    }

    public void addListaFotosOriginales(RealmList<Foto> listaFotosOriginales) {
        this.listaFotosOriginales.addAll(listaFotosOriginales);
    }


    public RealmList<Foto> getListaFotosOriginales() {
        return listaFotosOriginales;
    }

    //crear vista y viewholder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewCelda;
        FragmentActivity unaActivity = (FragmentActivity) context;
        FragmentManager fragmentManager = (FragmentManager) unaActivity.getSupportFragmentManager();
        viewCelda = layoutInflater.inflate(R.layout.detalle_celda_recycler_cargar_producto, parent, false);

        final FotoViewHolder peliculasViewHolder = new FotoViewHolder(viewCelda);
        viewCelda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (peliculasViewHolder.editText.getVisibility()==View.VISIBLE) {
                    if(peliculasViewHolder.textView.getText()==null||peliculasViewHolder.editText.getText().toString().isEmpty()){
                        peliculasViewHolder.editText.setVisibility(View.GONE);
                    }
                    else{
                        peliculasViewHolder.textView.setText(peliculasViewHolder.editText.getText().toString());
                        peliculasViewHolder.editText.setText("");
                        imm.hideSoftInputFromWindow(peliculasViewHolder.editText.getWindowToken(), 0);
                        peliculasViewHolder.editText.setVisibility(View.GONE);
                    }
                }
                else{
                    peliculasViewHolder.editText.setVisibility(View.VISIBLE);
                    peliculasViewHolder.editText.requestFocus();
                    imm.showSoftInput(peliculasViewHolder.editText, InputMethodManager.SHOW_IMPLICIT);
                }
                peliculasViewHolder.textView.setVisibility(View.VISIBLE);

            }
        });

        return peliculasViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Foto unFoto = listaFotosOriginales.get(position);
        FotoViewHolder FotoViewHolder = (FotoViewHolder) holder;
        FotoViewHolder.cargarFoto(unFoto);


    }

    @Override
    public int getItemCount() {
        return listaFotosOriginales.size();
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }

    @Override
    public boolean onLongClick(View v) {
        listenerLong.onLongClick(v);
        return true;
    }

    //creo el viewholder que mantiene las referencias
    //de los elementos de la celda

    private static class FotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private EditText editText;
        //private TextView textViewTitulo;


        public FotoViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imagenCamara);
            textView= (TextView)itemView.findViewById(R.id.textoObseracion);
            editText=(EditText)itemView.findViewById(R.id.editRecycler);

        }

        public void cargarFoto(Foto unFoto) {
           textView.setText(R.string.clicParaObservacion);


            File f =new File(unFoto.getRuta());

            Picasso.with(imageView.getContext())
                    .load(f)
                    .placeholder(R.drawable.placeholder)
                    .into(imageView);
        }


    }

    public interface Favoritable {
        public void recibirFotoFavorito(Foto unFoto);
    }

}
