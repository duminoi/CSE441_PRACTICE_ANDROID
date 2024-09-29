package com.example.recylerviewdemo;

public class Country {
    private int flag; // ảnh lá cờ
    private String countryName;
    private String countryCapital;
    private int population;
    private int area;
    private double density;
    private double worldShare;

    public Country(String countryName, String countryCapital, int flag, int population, int area, double density, double worldShare) {
        this.countryName = countryName;
        this.countryCapital = countryCapital;
        this.flag = flag;
        this.population = population;
        this.area = area;
        this.density = density;
        this.worldShare = worldShare;
    }
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }
    // Thêm getter cho các thuộc tính mới
    public int getPopulation() {
        return population;
    }

    public int getArea() {
        return area;
    }

    public double getDensity() {
        return density;
    }

    public double getWorldShare() {
        return worldShare;
    }

    // Các getter khác...
}
