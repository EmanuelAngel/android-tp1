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
    private MutableLiveData<String> errorMessage;
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

    public LiveData<String> getErrorMessage() {
        if (errorMessage == null) {
            errorMessage = new MutableLiveData<>();
        }
        return errorMessage;
    }

    public void updateRate(String inputRate) {
        if (inputRate == null || inputRate.trim().isEmpty()) {
            errorMessage.setValue("El tipo de cambio no puede estar vacío");
            return;
        }
        try {
            double newRate = Double.parseDouble(inputRate);
            rate.setValue(newRate);
        } catch (NumberFormatException e) {
            errorMessage.setValue("Formato de número inválido para el tipo de cambio");
        }
    }

    public void convert(String amountDolars, String amountEuros, boolean toEuros) {
        Double currentRate = getRate().getValue();
        if (currentRate == null) {
            errorMessage.setValue("Tipo de cambio no definido");
            return;
        }

        double result;
        try {
            if (toEuros) {
                if (amountDolars == null || amountDolars.trim().isEmpty()) {
                    errorMessage.setValue("Ingrese monto en dólares");
                    return;
                }
                double dolars = Double.parseDouble(amountDolars);
                result = conversionModel.convertToEuros(dolars, currentRate);
            } else {
                if (amountEuros == null || amountEuros.trim().isEmpty()) {
                    errorMessage.setValue("Ingrese monto en euros");
                    return;
                }
                double euros = Double.parseDouble(amountEuros);
                result = conversionModel.convertToDolars(euros, currentRate);
            }
            conversionResult.setValue(result);
        } catch (NumberFormatException e) {
            errorMessage.setValue("El valor ingresado no es un número válido");
        }
    }
}
