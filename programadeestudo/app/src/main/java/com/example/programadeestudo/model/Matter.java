package com.example.programadeestudo.model;

import java.io.Serializable;
import java.util.List;

public class Matter implements Serializable {
    private String nameMatter;
    private String summary;
    private int hourAlarm;
    private int minuteAlarm;

    public Matter() {
    }

    public Matter(String nameMatter, String summary, int hourAlarm, int minuteAlarm) {
        this.nameMatter = nameMatter;
        this.summary = summary;
        this.hourAlarm = hourAlarm;
        this.minuteAlarm = minuteAlarm;
    }

    public int getHourAlarm() {
        return hourAlarm;
    }

    public void setHourAlarm(int hourAlarm) {
        this.hourAlarm = hourAlarm;
    }

    public int getMinuteAlarm() {
        return minuteAlarm;
    }

    public void setMinuteAlarm(int minuteAlarm) {
        this.minuteAlarm = minuteAlarm;
    }

    public String getNameMatter() {
        return nameMatter;
    }

    public void setNameMatter(String nameMatter) {
        this.nameMatter = nameMatter;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
