package com.nomad.auditoria5s.Model;

import io.realm.RealmObject;

/**
 * Created by Martirio on 09/08/2017.
 */

public class Foto extends RealmObject {
    private String nombre;
    private String ruta;
    private String descripcion;

    public Foto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}


