package com.example.covid19api;

import javax.persistence.*;

@Entity
@Table(name = "covidcases")
public class Covidcase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String us_cases;
    private String us_deaths;
    private String ga_cases;
    private String ga_deaths;
    private String date;

    public Covidcase() {}

    public Covidcase(String usCases, String usDeaths, String gaCases, String gaDeaths, String date) {
        this.us_cases = usCases;
        this.us_deaths = usDeaths;
        this.ga_cases = gaCases;
        this.ga_deaths = gaDeaths;
        this.date = date;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUs_cases() { return us_cases; }

    public void setUs_cases(String us_cases) { this.us_cases = us_cases; }

    public String getUs_deaths() { return us_deaths; }

    public void setUs_deaths(String us_deaths) { this.us_deaths = us_deaths; }

    public String getGa_cases() { return ga_cases; }

    public void setGa_cases(String ga_cases) { this.ga_cases = ga_cases; }

    public String getGa_deaths() { return ga_deaths; }

    public void setGa_deaths(String ga_deaths) { this.ga_deaths = ga_deaths; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }
}