package com.example.tp1;

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

        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(
                MainActivityViewModel.class
        );

        setContentView(vb.getRoot());

        mv.getConversionMutable().observe(
                this,
                value -> vb.etEuro.setText(String.format(value.toString()))
                );

        vb.btConvert.setOnClickListener(
                view -> {
                    mv.convert();
                }
        );
    }
}