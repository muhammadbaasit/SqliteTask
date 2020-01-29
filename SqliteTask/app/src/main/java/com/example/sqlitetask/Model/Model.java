package com.example.sqlitetask.Model;

public class Model {

    String fName;
    String lName;
    int id;

    public Model() {
    }

    public Model(int id,String fName, String lName) {
        this.id=id;
        this.fName = fName;
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
