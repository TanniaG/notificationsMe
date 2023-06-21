package com.example.notificationsme;


import com.google.firebase.Timestamp;

public class UserDatabase {

String topics;
String amounts;
String dates;
String times;
String reminders;

String notez;
Timestamp timestamp;

    public UserDatabase() {

    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getAmounts() {
        return amounts;
    }

    public void setAmounts(String amounts) {
        this.amounts = amounts;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getNotes(){return notez;}

    public void setNotes(String notes){this.notez = notes;}

   // public String getReminders() {return reminders;}

   // public void setReminders(String reminders) {this.reminders = reminders;}

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}


