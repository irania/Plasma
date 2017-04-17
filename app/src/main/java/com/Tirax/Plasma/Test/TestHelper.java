package com.Tirax.Plasma.Test;

import com.Tirax.Plasma.SerialPortsHardware.ReadWriteSerialPort;
import com.Tirax.Plasma.SerialPortsHardware.SerialPort;

/**
 * Created by MHSaadatfar on 4/14/2017.
 */
public class TestHelper {
    public static int WaitForSerial(char data,int max){
        int d=0;
        for (int i=1;i<=max;i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            d++;
            if(SerialPort.isData()) {
                char c = SerialPort.read();
                if (c==data){
                    return d;
                }
            }
        }
        return max;
    }
    public static void StopSerialService(){
        ReadWriteSerialPort.Run=0;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void StartSerialService(){
        ReadWriteSerialPort.Run=1;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
