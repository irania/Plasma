package com.Tirax.plasma.Test;

import com.Tirax.plasma.SerialPortsHardware.DataProvider;

import main.activity.Test.TestResult;

/**
 * Created by MHSaadatfar on 4/14/2017.
 */
public class TestShower {
    public void Run() {
        TestResult.setLog("Ready...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestResult.setLog("Running...");

        DataProvider.setRegister(DataProvider.RPWR, (char) 50);
        DataProvider.setRegister(DataProvider.MTTI, (char) 10);

        while(true) {

            DataProvider.setRegister(DataProvider.MTB1, (char) 1);
            try {
                Thread.sleep(11000);
            } catch (InterruptedException e) {
                return;
            }
            if (Thread.interrupted()) return;
        }
        //TestResult.setLog("Done.");
    }
}
