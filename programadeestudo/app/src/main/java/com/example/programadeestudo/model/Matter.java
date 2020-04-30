package com.example.programadeestudo.model;

import java.util.List;

public class Matter {
    private String name;
    private List<String> abstracts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(List<String> abstracts) {
        this.abstracts = abstracts;
    }
}
