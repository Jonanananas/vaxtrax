package com.example.vaxtrax;

public class Rokote {

    private String maa;
    private String v1;
    private String v2;
    private String v3;
    private String v4;
    private String v5;
    private String v6;
    private String v7;

    public String getMaa() {
        return maa;
    }

    public String getV1() {
        return v1;
    }

    public String getV2() {
        return v2;
    }

    public String getV3() {
        return v3;
    }

    public String getV4() {
        return v4;
    }

    public String getV5() {
        return v5;
    }

    public String getV6() {
        return v6;
    }

    public String getV7() {
        return v7;
    }

    public Rokote(String maa, String v1, String v2, String v3, String v4, String v5, String v6, String v7) {
        this.maa = maa;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.v5 = v5;
        this.v6 = v6;
        this.v7 = v7;
    }
    public String toString(){
        return this.maa;
    }



}

