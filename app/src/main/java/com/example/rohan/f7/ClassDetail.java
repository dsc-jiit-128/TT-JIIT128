package com.example.rohan.f7;

public class ClassDetail {
    public String type, subject,timing,faculty,room;

    public ClassDetail(String type, String subject,String timing,String faculty, String room){
        this.type=type;
        this.subject=subject;
        this.timing=timing;
        this.faculty=faculty;
        this.room=room;
    }
    public ClassDetail(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
