package com.example.pgallocationsystem.com.bean;

public class CallMessage {
    String name,phoneno;

    @Override
    public String toString() {
        return name+" "+phoneno;
    }

    public CallMessage(String name, String phoneno) {
        this.name = name;
        this.phoneno = phoneno;
    }

    public CallMessage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
