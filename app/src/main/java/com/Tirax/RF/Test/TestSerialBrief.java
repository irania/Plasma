package com.Tirax.RF.Test;

import com.Tirax.RF.SerialPortsHardware.ReadWriteSerialPort;
import com.Tirax.RF.SerialPortsHardware.SerialComHelper;

/**
 * Created by MHSaadatfar on 4/14/2017.
 */
public class TestSerialBrief {
    public String Run(){
        ReadWriteSerialPort.Run=0;
        SerialComHelper.sendRegister((char)11,(char) 2);
        char ack=SerialComHelper.calcAckCode((char)11,(char) 2);
        int d1=TestHelper.WaitForSerial(SerialComHelper.ACK_STARTBIT,1000);
        if (d1==1000){
            ReadWriteSerialPort.Run=1;
            return "Failed.";
        }
        int d2=TestHelper.WaitForSerial(ack,1000);
        SerialComHelper.sendRegister((char)11,(char) 0);
        if (d2==1000){
            ReadWriteSerialPort.Run=1;
            return "Failed.";
        }
        ReadWriteSerialPort.Run=1;
        return "Passed. Delays:" + Integer.toString(d1) + "," + Integer.toString(d2);
    }
}
