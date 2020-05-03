package com.example.programadeestudo.model;

import java.util.List;

public class Matter {
    private String nameMatter;
    private String hourAlarm;
    private String summary;

    public Matter(String nameMatter, String hourAlarm, String summary) {
        this.nameMatter = nameMatter;
        this.hourAlarm = hourAlarm;
        this.summary = summary;
    }

    public String getNameMatter() {
        return nameMatter;
    }

    public void setNameMatter(String nameMatter) {
        this.nameMatter = nameMatter;
    }

    public String getHourAlarm() {
        return hourAlarm;
    }

    public void setHourAlarm(String hourAlarm) {
        this.hourAlarm = hourAlarm;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
