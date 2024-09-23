package com.example.marvelquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Cuestionario extends AppCompatActivity {
    public TextView pregunta;
    public Button siguiente;
    public RadioButton opcion1;
    public RadioButton opcion2;
    public RadioButton opcion3;
    public RadioButton opcion4;
    public Button volver;
    public TextView puntosMomentaneos;
    public int numerodepregunta = 0;
    public int puntos = 0;
    public String nombreUsuario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cuestionario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });





        Intent i =  getIntent();
        nombreUsuario = i.getStringExtra("nombre");

        Contenido contenido = new Contenido();
        pregunta = findViewById(R.id.pregunta);
        opcion1 = findViewById(R.id.opcion1);
        opcion2 = findViewById(R.id.opcion2);
        opcion3 = findViewById(R.id.opcion3);
        opcion4 = findViewById(R.id.opcion4);
        siguiente = findViewById(R.id.siguiente);
        volver = findViewById(R.id.volver);
        puntosMomentaneos = findViewById(R.id.puntos);

        pregunta.setText(contenido.preguntas[numerodepregunta]);
        opcion1.setText(contenido.opciones[numerodepregunta][0]);
        opcion2.setText(contenido.opciones[numerodepregunta][1]);
        opcion3.setText(contenido.opciones[numerodepregunta][2]);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(Cuestionario.this, MainActivity.class);
                startActivity(volver);
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(contenido);
            }
        });


        mostrarPreguntas(contenido);
    }

    public void mostrarPreguntas(Contenido contenido){
        if(numerodepregunta < contenido.preguntas.length){
            pregunta.setText(contenido.preguntas[numerodepregunta]);
            opcion1.setText(contenido.opciones[numerodepregunta][0]);
            opcion2.setText(contenido.opciones[numerodepregunta][1]);
            opcion3.setText(contenido.opciones[numerodepregunta][2]);
            opcion4.setText(contenido.opciones[numerodepregunta][3]);


        }else{
            Intent i = new Intent(Cuestionario.this, PantallaFinal.class);
            i.putExtra("puntos", puntos);
            i.putExtra("nombreUsuario", nombreUsuario);
            startActivity(i);
        }
    }

    public void verificarRespuesta(Contenido contenido) {
        String respuesta = "";

        if (opcion1.isChecked()) {
            respuesta = opcion1.getText().toString();
        } else if (opcion2.isChecked()) {
            respuesta = opcion2.getText().toString();
        } else if (opcion3.isChecked()) {
            respuesta = opcion3.getText().toString();
        } else if (opcion4.isChecked()) {
            respuesta = opcion4.getText().toString();
        }

        if (!respuesta.isEmpty()) {
            if (respuesta.equals(contenido.respuestas[numerodepregunta])) {
                puntos++;
            }
            puntosMomentaneos.setText(nombreUsuario + " llevas " + String.valueOf(puntos) + " puntos obtenidos");

            numerodepregunta++;
            mostrarPreguntas(contenido);
        } else {
            Toast mensajeError = Toast.makeText(this, "Por favor seleccione una opcion", Toast.LENGTH_LONG);
            mensajeError.show();
        }
 }



}