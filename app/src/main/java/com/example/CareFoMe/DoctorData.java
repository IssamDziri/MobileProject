package com.example.CareFoMe;

import java.io.Serializable;

public class DoctorData implements Serializable {

    static int id=1 ;
    String email;
    String name;

    public DoctorData(){
        this.id=id++;

    }
}
