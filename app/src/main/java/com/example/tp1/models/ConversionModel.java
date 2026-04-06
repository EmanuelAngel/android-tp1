package com.example.tp1.models;

public class ConvertionModel {
    public double convertToEuros(double dolars, double changeRate) {
        return dolars * changeRate;
    }

    public double convertToDolars(double euros, double changeRate) {
        return euros / changeRate;
    }
}
