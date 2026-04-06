package com.example.tp1.ui;

import android.os.Bundle;

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

        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        setContentView(vb.getRoot());

        mv.getConversionMutable().observe(this, result -> {
            String formattedResult = String.format("%.2f", result);

            if (vb.rbChangeToEuro.isChecked()) {
                vb.etEuro.setText(formattedResult);
            } else if (vb.rbChangeToDolar.isChecked()) {
                vb.etDolar.setText(formattedResult);
            }
        });

        vb.btConvert.setOnClickListener(view -> {
            String strDolars = vb.etDolar.getText().toString();
            String strEuros = vb.etEuro.getText().toString();
            String strRate = vb.etConversionValueInput.getText().toString();

            boolean toEuros = vb.rbChangeToEuro.isChecked();

            mv.convert(strDolars, strEuros, strRate, toEuros);
        });
    }
}