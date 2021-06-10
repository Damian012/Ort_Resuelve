package com.example.ejemplo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> DatosLista;
    ArrayList<String> DatosLista2;
    ArrayAdapter<String> Adaptador;
    ArrayAdapter<String> Adaptador2;
    String textoSeleccionado;
    String textoSeleccionado2;
    EditText aula;
    Button botonContinuar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    DatabaseReference mirootReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mirootReference = FirebaseDatabase.getInstance().getReference();

        botonContinuar = findViewById(R.id.boton1);
        aula = findViewById(R.id.aula);

        DatosLista=new ArrayList<>();
        DatosLista.add("1 Yatay");
        DatosLista.add("2 Rio");
        DatosLista.add("4 Yatay Nuevo");

        Adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DatosLista);
        Adaptador.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));

        Spinner spnOpciones = (Spinner) findViewById(R.id.spinner1);
        spnOpciones.setAdapter(Adaptador);

        int Posicion;
        Posicion=spnOpciones.getSelectedItemPosition();


        textoSeleccionado=spnOpciones.getItemAtPosition(Posicion).toString();

        DatosLista2=new ArrayList<>();
        DatosLista2.add("PB");
        DatosLista2.add("1");
        DatosLista2.add("2");
        DatosLista2.add("3");
        DatosLista2.add("4");

        Adaptador2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DatosLista2);
        Adaptador2.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(Adaptador2);

        int posicion2;
        posicion2=spinner2.getSelectedItemPosition();


        textoSeleccionado2=spinner2.getItemAtPosition(posicion2).toString();

        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edificio;
                String aulaIngresada;
                String piso;
                aulaIngresada=aula.getText().toString();
                edificio=textoSeleccionado;
                piso=textoSeleccionado2;

                Map<String, Object> registroUbicacion = new HashMap<>();
                registroUbicacion.put("edificio", edificio);
                registroUbicacion.put("piso", piso);
                registroUbicacion.put("aula", aulaIngresada);

                mirootReference.child("Ubicacion").push().setValue(registroUbicacion);

            }
        });

    }

    public void guardarUbicacion(View v){
        String edificio;
        String aulaIngresada;
        String piso;
        aulaIngresada=aula.getText().toString();
        edificio=textoSeleccionado;
        piso=textoSeleccionado2;

        Ubicacion ub = new Ubicacion();
        ub.setEdificio(edificio);
        ub.setPiso(piso);
        ub.setAula(aulaIngresada);
    }
}
