package com.Tirax.RF;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.Tirax.RF.Enums.SecurityType;

import java.math.BigInteger;
import java.security.SecureRandom;

import main.activity.MainActivity;


/**
 * Created by Emertat on 3/8/2017.
 */
public class License {



    public void getimeiHashed(){
        TelephonyManager telephonyManager = (TelephonyManager) MainActivity.nowActivity.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();

    }


    public static boolean licenceIsOk(){
        try {
            int usedTime = tryParse(SecurityFile.load(SecurityType.TIME));
            int validTime =  tryParse(SecurityFile.load(SecurityType.VALID_TIME));

            //TODO set a margin for it
            if(usedTime>validTime)
                return false;
            return true;
        } catch (Exception e) {
            //TODO get a big error
            e.printStackTrace();
            return false;
        }
    }

    private static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }


    public static void generateReqCode() {
        try {
            //TODO serial==null?
            String serial = SecurityFile.load(SecurityType.SERIAL);

            int workedTime = tryParse(SecurityFile.load(SecurityType.TIME))/10;

            String requestCode = serial + Integer.toHexString(workedTime);

            SecurityFile.save(SecurityType.SERIAL,requestCode);

            Log.e("TIRAX6","Request code: "+requestCode+" time: "+workedTime);
        } catch (Exception e) {
            //TODO exception for it
            e.printStackTrace();
        }
    }
}
