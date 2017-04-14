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
        for (char i=0;i<=40;i++) {
            if (Thread.interrupted()) return;
            int p;
            if (i<=20)
                p=i*5;
            else
                p=(40-i)*5;
            DataProvider.setRegister(DataProvider.RPWR, (char) p);
            TestResult.setLog("Power: " + Integer.toString(p) + "%");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
        TestResult.setLog("Done.");
    }
}
