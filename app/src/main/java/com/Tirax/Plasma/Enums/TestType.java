package com.Tirax.Plasma.Enums;

/**
 * Created by a.irani on 11/1/2016.
 */
public enum TestType {
   SERIAL_BRIEF(0),POWER0T100(1);
   private int value;
   private TestType(int value) {
      this.value = value;
   }
   public int getValue() {
      return value;
   }
}