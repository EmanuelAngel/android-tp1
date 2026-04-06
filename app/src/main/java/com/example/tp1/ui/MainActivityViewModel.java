package com.example.tp1.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp1.models.ConversionModel;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Double> rate;
    private MutableLiveData<Double> conversionResult;
    private final ConversionModel conversionModel;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        conversionModel = new ConversionModel();
    }

    public LiveData<Double> getRate() {
        if (rate == null) {
            rate = new MutableLiveData<>(1.16);
        }

        return rate;
    }

    public LiveData<Double> getConversionResult() {
        if (conversionResult == null) {
            conversionResult = new MutableLiveData<>();
        }

        return conversionResult;
    }

    public void updateRate(String inputRate) {
        if (inputRate != null && !inputRate.isEmpty()) {
            try {
                double newRate = Double.parseDouble(inputRate);
                if (rate == null) {
                    rate = new MutableLiveData<>();
                }
                rate.setValue(newRate);
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public void convert(String amountDolars, String amountEuros, boolean toEuros) {
        Double currentRate = getRate().getValue();
        if (currentRate == null) return;

        double result = 0.0;

        try {
            if (toEuros) {
                if (amountDolars.isEmpty()) return;

                double dolars = Double.parseDouble(amountDolars);

                result = conversionModel.convertToEuros(dolars, currentRate);
            } else {
                if (amountEuros.isEmpty()) return;

                double euros = Double.parseDouble(amountEuros);

                result = conversionModel.convertToDolars(euros, currentRate);
            }

            getConversionResult();

            conversionResult.setValue(result);
        } catch (NumberFormatException ignored) {
        }
    }
}
