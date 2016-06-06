package com.sudasell.model;

public class ModelNotificationCard {

    private String line1;
    private String line2;
    private int line3;
    private String line4;

    public ModelNotificationCard(String line1, String line2, int line3, String line4) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
    }


    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public int getLine3() {
        return line3;
    }

    public String getLine4() {
        return line4;
    }
}
