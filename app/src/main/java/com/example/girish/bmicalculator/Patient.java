package com.example.girish.bmicalculator;
import java.util.Calendar;
import java.util.Date;

public class Patient {
    private double bmi;
    private Date c = Calendar.getInstance().getTime();
    public Patient() {
    }

    public Patient(double bmi, Date c)
    {
        this.bmi=bmi;
        this.c=c;
    }

    @Override
    public String toString() {
        return "Date: "+c +"\nBmi: "+bmi;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public Date getC() {
        return c;
    }

    public void setC(Date c) {
        this.c = c;
    }
}
