package com.example.tp1.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp1.models.ConversionModel;

public class MainActivityViewModel extends AndroidViewModel {
    private Double conversionValue = 1.16;
    private MutableLiveData<Double> conversionMutable;
    private ConversionModel conversionModel;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        conversionModel = new ConversionModel();
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
            result = conversionModel.convertToEuros(dolars, changeRate);
        } else {
            if (amountEuros.isEmpty()) return;
            double euros = Double.parseDouble(amountEuros);
            result = conversionModel.convertToDolars(euros, changeRate);
        }

        conversionMutable.setValue(result);
    }
}
