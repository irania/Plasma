package com.Tirax.plasma.Test;

import com.Tirax.plasma.SerialPortsHardware.DataProvider;

import main.activity.TestResult;

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
        for (int i = 0; i < 3600; i++){
            DataProvider.setRegister(DataProvider.RPWR, (char) 100);
            DataProvider.setRegister(DataProvider.MTB1, (char) 1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
            if (Thread.interrupted()) return;
        }
        TestResult.setLog("Done.");
    }
}
