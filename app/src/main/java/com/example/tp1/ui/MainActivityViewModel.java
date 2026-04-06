package com.example.tp1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private Double conversionValue = 1.16;
    private MutableLiveData<Double> conversionMutable;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Double> getConversionMutable() {
        if (conversionMutable == null) {
            conversionMutable = new MutableLiveData<>();
        }

        return conversionMutable;
    }

    public void convert(String amountDolars, String amountEuros, String inputRate, boolean toEuros) {
        if (inputRate.isEmpty()) return;

        double changeRate = Double.parseDouble(inputRate);
        double result = 0.0;

        if (toEuros) {
            if (amountDolars.isEmpty()) return;
            double dolars = Double.parseDouble(amountDolars);
            result = convertToEuros(dolars, changeRate);
        } else {
            if (amountEuros.isEmpty()) return;
            double euros = Double.parseDouble(amountEuros);
            result = convertToDolars(euros, changeRate);
        }

        conversionMutable.setValue(result);
    }

    public double convertToEuros(double dolars, double changeRate) {
        return dolars * changeRate;
    }

    public double convertToDolars(double euros, double changeRate) {
        return euros / changeRate;
    }
}
