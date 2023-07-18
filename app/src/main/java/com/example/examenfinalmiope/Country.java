package com.example.examenfinalmiope;

public class Country {
    private String name;
    private String initials;

    public Country(String name, String initials) {
        this.name = name;
        this.initials = initials;
    }

    public String getName() {
        return name;
    }

    public String getInitials() {
        return initials;
    }

    @Override
    public String toString() {
        return name + " (" + initials + ")";
    }
}
