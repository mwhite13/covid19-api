package com.example.covid19api;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController

public class Covid19Controller {
    @Autowired
    private Covid19Dao covid19Dao;

    public static WebDriver webDriver;
    public static final String COVID_URL ="https://coronavirus.jhu.edu/map.html";
    public static final String COVID19_NATIONALCASES = "//*[@id=\"ember1051\"]/div/div/h5/span[1]/strong";
    public static final String COVID19_NATIONALDEATH =" //*[@id=\"ember1435\"]/div/div/p[1]/span";
    public static final String COVID19_STATEDATA = "";
    public static final String COVID19_STATEDEATH ="";

    public static void setup(){
        System.setProperty("webDriver.chrome.driver","chromedriver.exe");

    }

    @RequestMapping(value = "/addCases", method = RequestMethod.PUT)
    public Covidcase addCases(@RequestBody Covidcase c) throws Exception{
        covid19Dao.create(c);
        return c;
    }

    @RequestMapping(value = "/getCases/{id}" , method = RequestMethod.GET)
    public Covidcase getCases(@PathVariable("id")int id){
        return null;
    }

    @RequestMapping(value = "/deleteCaseData/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCaseData(@PathVariable("id")int id){
        return null;
    }
}
