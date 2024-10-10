package com.example.inacap;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {
    TextView txvPesoIdeal;
    TextView txvEstadoPeso;
    ImageView imgEstadoPeso;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        vincularElementos();
        obtenerDatos();
        mostrarResultado();
    }

    private void mostrarResultado() {
        int edad = bundle.getInt("p_edad");
        float pesoActual = bundle.getFloat("p_peso");

        int pesoIdeal = edad * 2 + 8;
        txvPesoIdeal.setText("Peso ideal es " + pesoIdeal + " kilos");

        // Calcular la diferencia de peso
        float diferenciaPeso = pesoActual - pesoIdeal;
        String mensajeDiferencia;
        int imagenId;

        if (diferenciaPeso > 0) {
            mensajeDiferencia = "Estás " + diferenciaPeso + " kilos por encima del peso ideal.";
            imagenId = R.drawable.imagen_sobrepeso;
        } else if (diferenciaPeso < 0) {
            mensajeDiferencia = "Estás " + (-diferenciaPeso) + " kilos por debajo del peso ideal.";
            imagenId = R.drawable.imagen_bajo_peso;
        } else {
            mensajeDiferencia = "Estás en el peso ideal.";
            imagenId = R.drawable.imagen_peso_ideal;
        }

        txvEstadoPeso.setText(mensajeDiferencia);
        imgEstadoPeso.setImageResource(imagenId);
    }

    private void obtenerDatos() {
        Intent intento = getIntent();
        bundle = intento.getExtras();
    }

    private void vincularElementos() {
        txvPesoIdeal = findViewById(R.id.txv_pesoideal);
        txvEstadoPeso = findViewById(R.id.txv_estado_peso);
        imgEstadoPeso = findViewById(R.id.img_estado_peso);
    }
}