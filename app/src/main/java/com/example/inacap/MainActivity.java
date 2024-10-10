package com.example.inacap;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtEdad;
    EditText edtPeso;
    Button btnCalcular;
    TextView txvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vincularElementos();
        habilitarListener();
    }

    private void habilitarListener() {
        btnCalcular.setOnClickListener(this);
    }

    private void vincularElementos() {
        edtEdad = findViewById(R.id.edt_edad);
        edtPeso = findViewById(R.id.edt_peso);
        btnCalcular = findViewById(R.id.btn_calcular);
        txvResultado = findViewById(R.id.txv_resultado);
    }

    private boolean validarDatos() {
        String edadStr = edtEdad.getText().toString();
        String pesoStr = edtPeso.getText().toString();

        if (edadStr.isEmpty() || pesoStr.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            int edad = Integer.parseInt(edadStr);
            float peso = Float.parseFloat(pesoStr);

            if (edad < 1 || edad > 10) {
                Toast.makeText(this, "La edad debe estar entre 1 y 10 años.", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (peso < 1 || peso > 200) {
                Toast.makeText(this, "El peso debe estar entre 1 y 200 kilos.", Toast.LENGTH_SHORT).show();
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, ingrese valores válidos.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        if (validarDatos()) {
            abrirResultado();
        }
    }

    private void abrirResultado() {
        int edad = Integer.parseInt(edtEdad.getText().toString());
        float peso = Float.parseFloat(edtPeso.getText().toString());

        Intent intento = new Intent(MainActivity.this, ResultadoActivity.class);
        intento.putExtra("p_edad", edad);
        intento.putExtra("p_peso", peso);
        startActivity(intento);
    }
}

