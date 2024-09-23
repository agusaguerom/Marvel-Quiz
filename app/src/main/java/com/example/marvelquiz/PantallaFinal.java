package com.example.marvelquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PantallaFinal extends AppCompatActivity {
    public TextView puntosObtenidos;
    public Button volver;
    public ImageView imagenvictoria, imagenderrota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_final);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        volver = findViewById(R.id.volverFinal);
        imagenvictoria = findViewById(R.id.imagenvictoria);
        imagenderrota = findViewById(R.id.imagenderrota);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(PantallaFinal.this, MainActivity.class);
                startActivity(volver);
            }
        });

        Intent i = getIntent();
        int puntos = i.getIntExtra("puntos", 0);
        String nombreUsuario = i.getStringExtra("nombreUsuario");
        puntosObtenidos = findViewById(R.id.puntosobtenidos);

        if(puntos > 2){
            puntosObtenidos.setText("Felicidades " + nombreUsuario + " has obtenido "  + puntos + " puntos");
            imagenvictoria.setVisibility(View.VISIBLE);

        }else{
            puntosObtenidos.setText("Lo siento " + nombreUsuario + " unicamente has obtenido "  + puntos + " puntos");
            imagenderrota.setVisibility(View.VISIBLE);

        }


    }
}