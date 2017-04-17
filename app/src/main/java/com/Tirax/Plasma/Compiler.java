package com.Tirax.Plasma;

import android.util.Log;

import com.Tirax.Plasma.SerialPortsHardware.DataProvider;

/**
 * Created by a.irani on 11/1/2016.
 */
public class Compiler {

    public static void setRegisters(Mode mode){



        DataProvider.setRegister(DataProvider.RPWR, (char) (mode.power*mode.powerMultiplyer));


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