package com.example.programadeestudo.model;

import java.io.Serializable;
import java.util.List;

public class Matter implements Serializable {
    private String nameMatter;
    private String summary;
    private String hourAlarm;

    public Matter() {
    }

    public Matter(String nameMatter, String summary, String hourAlarm) {
        this.nameMatter = nameMatter;
        this.summary = summary;
        this.hourAlarm = hourAlarm;
    }

    public String getHourAlarm() {
        return hourAlarm;
    }

    public void setHourAlarm(String hourAlarm) {
        this.hourAlarm = hourAlarm;
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
