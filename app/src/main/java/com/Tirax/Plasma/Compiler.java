package com.Tirax.plasma;

import android.util.Log;

import com.Tirax.plasma.SerialPortsHardware.DataProvider;

/**
 * Created by a.irani on 11/1/2016.
 */
public class Compiler {

    public static void setRegisters(Mode mode){
        DataProvider.setRegister(DataProvider.RPWR, (char) (mode.power*mode.powerMultiplyer+mode.powerAdder));

        if (LogCatEnabler.compilerSetRegLog) {
            Log.e("TIRAXREG", "POWER" + mode.powerMultiplyer);
        }
    }

    private  static char getType0(char bit) {
        return  (char)(1 << bit);
    }


    public static void setRTYPRegister(Mode op) {
    }
}
