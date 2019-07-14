package com.example.rohan.f7;

public class Choices {

    public String Elective1;
    public String Elective2;
    public String Elective3;
    public String Elective4;

    public Choices(String elective1, String elective2, String elective3, String elective4) {
        Elective1 = elective1;
        Elective2 = elective2;
        Elective3 = elective3;
        Elective4 = elective4;
    }
    public Choices(){}

    public String getElective1() {
        return Elective1;
    }

    public String getElective4() {
        return Elective4;
    }

    public void setElective4(String elective4) {
        Elective4 = elective4;
    }

    public void setElective1(String elective1) {
        Elective1 = elective1;
    }

    public String getElective2() {
        return Elective2;
    }

    public void setElective2(String elective2) {
        Elective2 = elective2;
    }

    public String getElective3() {
        return Elective3;
    }

    public void setElective3(String elective3) {
        Elective3 = elective3;
    }
}
