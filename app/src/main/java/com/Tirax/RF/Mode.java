package com.Tirax.RF;

import com.Tirax.RF.Storage.Values;

/**
 * Mother of type of diffrent options in program
 */
public class Mode {

    public double powerMultiplyer =0;
    public int power =0;
    public String autoMode="";
    public Mode(){
        powerMultiplyer = 1;
        autoMode = "";
        power = Values.power;

    }



}
