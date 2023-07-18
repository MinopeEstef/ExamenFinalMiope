package com.example.examenfinalmiope;

public class Holiday {
    private int idHoliday;
    private String date;
    private String name;
    private String countryCode;
    private String localName;

    public Holiday(int idHoliday, String date, String name, String countryCode, String localName) {
       this.idHoliday = idHoliday;
        this.date = date;
        this.name = name;
        this.countryCode = countryCode;
        this.localName = localName;
    }

    public int getIdHoliday() {
        return idHoliday;
    }

    public void setIdHoliday(int idHoliday) {
        this.idHoliday = idHoliday;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLocalName() {
        localName = localName.replace("'", "");
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }
}
