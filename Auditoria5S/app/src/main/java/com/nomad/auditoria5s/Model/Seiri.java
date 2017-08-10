package com.nomad.auditoria5s.Model;

import com.github.clans.fab.FloatingActionButton;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by elmar on 9/8/2017.
 */

public class Seiri extends RealmObject {
    private Integer puntajePunto1;
    private Integer puntajePunto2;
    private Integer puntajePunto3;
    private Integer puntajePunto4;
    private Float  puntajePromedio;
    private Float Objetivo;
    private RealmList<Foto> listaFotos;

    public Seiri() {
    }


    public Float getPuntajePromedio() {
        return puntajePromedio;
    }

    public void setPuntajePromedio(Float puntajePromedio) {
        this.puntajePromedio = puntajePromedio;
    }

    public Float getObjetivo() {
        return Objetivo;
    }

    public void setObjetivo(Float objetivo) {
        Objetivo = objetivo;
    }

    public Integer getPuntajePunto1() {
        return puntajePunto1;
    }

    public void setPuntajePunto1(Integer puntajePunto1) {
        this.puntajePunto1 = puntajePunto1;
    }

    public Integer getPuntajePunto2() {
        return puntajePunto2;
    }

    public void setPuntajePunto2(Integer puntajePunto2) {
        this.puntajePunto2 = puntajePunto2;
    }

    public Integer getPuntajePunto3() {
        return puntajePunto3;
    }

    public void setPuntajePunto3(Integer puntajePunto3) {
        this.puntajePunto3 = puntajePunto3;
    }

    public Integer getPuntajePunto4() {
        return puntajePunto4;
    }

    public void setPuntajePunto4(Integer puntajePunto4) {
        this.puntajePunto4 = puntajePunto4;
    }

    public RealmList<Foto> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(RealmList<Foto> listaFotos) {
        this.listaFotos = listaFotos;
    }
}
