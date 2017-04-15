package com.Tirax.RF.Test;

import com.Tirax.RF.SerialPortsHardware.DataProvider;

import main.activity.TestResult;

/**
 * Created by MHSaadatfar on 4/14/2017.
 */
public class TestRun0t100 {
    public void Run(){
        TestResult.setLog("Ready...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (char i=0;i<=20;i++) {
            while (DataProvider.getPedalisActive()==true)
                if (Thread.interrupted()) return;
            char p = (char) (i*5);
            DataProvider.setRegister(DataProvider.RPWR, p);
            TestResult.setLog("Power: " + Integer.toString(p) + "%");
            while (DataProvider.getPedalisActive()==false)
                if (Thread.interrupted()) return;
        }
        TestResult.setLog("Done.");
    }
}
