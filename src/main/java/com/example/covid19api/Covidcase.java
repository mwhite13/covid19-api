package com.example.covid19api;

import javax.persistence.*;

@Entity
@Table(name = "covidcases")
public class Covidcase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int us_cases;
    private int us_deaths;
    private int ga_cases;
    private int ga_deaths;
    private String date;

    public Covidcase() {}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getUs_cases() { return us_cases; }

    public void setUs_cases(int us_cases) { this.us_cases = us_cases; }

    public int getUs_deaths() { return us_deaths; }

    public void setUs_deaths(int us_deaths) { this.us_deaths = us_deaths; }

    public int getGa_cases() { return ga_cases; }

    public void setGa_cases(int ga_cases) { this.ga_cases = ga_cases; }

    public int getGa_deaths() { return ga_deaths; }

    public void setGa_deaths(int ga_deaths) { this.ga_deaths = ga_deaths; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }
}