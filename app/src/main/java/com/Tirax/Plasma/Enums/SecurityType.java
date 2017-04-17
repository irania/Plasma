package com.Tirax.Plasma.Enums;

/**
 * Created by Emertat on 3/10/2017.
 */
public enum SecurityType {
    SERIAL(0), REQUEST_CODE(1), TIME(2), VALID_TIME(3), VALID_DATE(4);
    private int value;
    private SecurityType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}