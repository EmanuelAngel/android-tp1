package com.example.tp1.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding vb;
    private MainActivityViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vb = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());

        // Instancia del ViewModel de forma estándar (no con Factory)
        mv = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Observa los errores que manda el ViewModel
        mv.getErrorMessage().observe(this, errorMsg -> {
            if (errorMsg != null && !errorMsg.isEmpty()) {
                Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
            }
        });

        // Observar el resultado de la conversión
        mv.getConversionResult().observe(this, result -> {
            String formattedResult = String.format("%.2f", result);

            if (vb.rbChangeToEuro.isChecked()) {
                vb.etEuro.setText(formattedResult);
            } else if (vb.rbChangeToDolar.isChecked()) {
                vb.etDolar.setText(formattedResult);
            }
        });

        // Observar el tipo de cambio (para mostrar el valor inicial y actualizaciones)
        mv.getRate().observe(this, rate -> {
            vb.etConversionValueInput.setText(String.valueOf(rate));
        });

        // Botón para actualizar el tipo de cambio
        vb.btChangeConversionValue.setOnClickListener(view -> {
            String strRate = vb.etConversionValueInput.getText().toString();
            if (strRate.isEmpty()) {
                vb.etConversionValueInput.setError("Ingrese un valor");
            } else {
                mv.updateRate(strRate);
                Toast.makeText(this, "Tipo de cambio actualizado", Toast.LENGTH_SHORT).show();
            }
        });

        // Botón para realizar la conversión
        vb.btConvert.setOnClickListener(view -> {
            String strDolars = vb.etDolar.getText().toString();
            String strEuros = vb.etEuro.getText().toString();
            boolean toEuros = vb.rbChangeToEuro.isChecked();

            // Validaciones básicas de campos vacíos según el modo seleccionado (aunque ya se valida)
            if (toEuros && strDolars.isEmpty()) {
                vb.etDolar.setError("Ingrese monto en dólares");
                return;
            }

            if (!toEuros && strEuros.isEmpty()) {
                vb.etEuro.setError("Ingrese monto en euros");
                return;
            }

            if (!vb.rbChangeToEuro.isChecked() && !vb.rbChangeToDolar.isChecked()) {
                Toast.makeText(this, "Seleccione un tipo de conversión", Toast.LENGTH_SHORT).show();
                return;
            }

            mv.convert(strDolars, strEuros, toEuros);
        });
    }
}
