package com.example.covid19api;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController

public class Covid19Controller {
    @Autowired
    private Covid19Dao covid19Dao;
    public static WebDriver webDriver;

    public static final String COVID_USA_URL ="https://coronavirus.jhu.edu/region/united-states";
    public static final String COVID_GA_URL = "https://coronavirus.jhu.edu/region/us/georgia";

    @RequestMapping(value = "/addCases", method = RequestMethod.POST)
    public Covidcase addCases() throws Exception{
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
//        System.setProperty("webdriver.chrome.driver", "/Users/juanjuarez/Desktop/chromedriver");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get(COVID_USA_URL);
        String usCases = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div/div/div[2]/div[1]/span")).getText();
        System.out.println("US cases = " + usCases);
        String usDeaths = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div/div/div[2]/div[2]/span")).getText();
        System.out.println("US deaths = " + usDeaths);
        webDriver.get(COVID_GA_URL);
        String gaCases = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[2]/div[1]/span")).getText();
        System.out.println("GA cases = " + gaCases);
        String gaDeaths = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[2]/div[2]/span")).getText();
        System.out.println("GA deaths = " + gaDeaths);

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        Date date = new Date();

        Covidcase newCase = new Covidcase(usCases,usDeaths,gaCases,gaDeaths, formatter.format(date));
        covid19Dao.create(newCase);

        webDriver.close();

        return newCase;

    }

    @RequestMapping(value = "/getCases/{id}" , method = RequestMethod.GET)
    public Covidcase getCases(@PathVariable("id")int id){
        Covidcase c = covid19Dao.getCaseId(id);
        return c;
    }

    @RequestMapping(value="/updateCase", method = RequestMethod.PUT)
    public Covidcase updateCase(@RequestBody Covidcase v) {
        Covidcase temp = covid19Dao.update(v);
        return temp;
    }

    @RequestMapping(value = "/deleteCaseData/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCaseData(@PathVariable("id")int id){
        Covidcase c = covid19Dao.deleteCase(id);
        if(c != null){
            return new ResponseEntity<>("found and deleted", HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getWeekly" , method = RequestMethod.GET)
    public int getWeeklyReport() throws IOException {
        List<String> latest = new ArrayList<>();
        int maxid = covid19Dao.getMaxID();
        for (int i = maxid; i > 0; i--) {
            Covidcase temp = covid19Dao.getCaseId(i);
            if(temp != null) {
                latest.add(temp.getGa_cases());
            }
            if(latest.size() >= 7) {
                break;
            }
        }
        int newCases = (Integer.parseInt(latest.get(0).replaceAll("[,]",""))) - (Integer.parseInt(latest.get(6).replaceAll("[,]","")));
        System.out.println(newCases + " new covid cases in Georgia last week");
        return newCases;
    }
}
