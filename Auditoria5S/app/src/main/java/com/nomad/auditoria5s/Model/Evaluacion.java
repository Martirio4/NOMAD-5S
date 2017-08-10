package com.nomad.auditoria5s.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Martirio on 09/08/2017.
 */

public class Evaluacion extends RealmObject {
    @PrimaryKey
    private String idAuditoria;
    private String auditor;
    private String sectorAuditado;
    private Seiri seiri;
    private Seiton seiton;
    private Seiso seiso;
}
