package com.example.quiroz.json_parser;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class vista_indiv extends Activity {
    static final String KEY_CLAVE_PRESTAMO = "clave_prestamo";
    static final String KEY_FECHA = "fecha";
    static final String KEY_NOMBRE_SOL = "nombre_sol";
    static final String KEY_AREA_SOL = "area_sol";
    static final String KEY_DESC = "descripcion";
    static final String KEY_REC = "recibido";
    static final String KEY_ENT = "entregado";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_indiv);

        Intent in = getIntent();

        // Get XML values from previous intent
        String clave_prestamo = in.getStringExtra(KEY_CLAVE_PRESTAMO);
        String fecha = in.getStringExtra(KEY_FECHA);
        String nomsol= in.getStringExtra(KEY_NOMBRE_SOL);
        String areasol= in.getStringExtra(KEY_AREA_SOL);
        String descr= in.getStringExtra(KEY_DESC);
        String recib= in.getStringExtra(KEY_REC);
        String entre= in.getStringExtra(KEY_ENT);


        // Displaying all values on the screen
        TextView lbl_clavef = (TextView) findViewById(R.id.id_prestamo);
        TextView lblfecha = (TextView) findViewById(R.id.fecha);
        TextView lblnomsol = (TextView) findViewById(R.id.nombre_sol);
        TextView lblareasol = (TextView) findViewById(R.id.area_sol);
        TextView lbldescr = (TextView) findViewById(R.id.descripcion);
        TextView lblrecib = (TextView) findViewById(R.id.recibido);
        TextView lblentre = (TextView) findViewById(R.id.entregado);

        lbl_clavef.setText(clave_prestamo);
        lblfecha.setText(fecha);
        lblnomsol.setText(nomsol);
        lblareasol.setText(areasol);
        lbldescr.setText(descr);
        lblrecib.setText(recib);
        lblentre.setText(entre);

    }


}


