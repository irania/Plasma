package com.Tirax.Plasma.Errors;


public abstract class Error {
    public static int number;
    public static String message;

    public abstract void beforeError();
    public abstract void afterError();
    public abstract boolean isError();

}
