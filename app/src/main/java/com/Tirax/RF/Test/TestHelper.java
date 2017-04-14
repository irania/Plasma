package com.Tirax.RF.Test;

import android.os.SystemClock;

import com.Tirax.RF.SerialPortsHardware.SerialPort;

/**
 * Created by MHSaadatfar on 4/14/2017.
 */
public class TestHelper {
    public static int WaitForSerial(char data,int max){
        int d=0;
        for (int i=1;i<=max;i++) {
            SystemClock.sleep(1);
            d++;
            char c= SerialPort.read();
            if (c==data){
                return d;
            }
        }
        return max;
    }
}
