package com.example.covid19api;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestGetCovidData {

    public static WebDriver webDriver;
    public static final String COVID_URL ="https://coronavirus.jhu.edu/region/united-states";
    public static final String COVID_GA_URL = "https://coronavirus.jhu.edu/region/us/georgia";

    @Test
    public void testGetCovidNumbers() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get(COVID_URL);
       // webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String usCases = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div/div/div[2]/div[1]/span")).getText();
        System.out.println("US cases = " + usCases);
        String usDeaths = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[1]/div/div/div[2]/div[2]/span")).getText();
        System.out.println("US deaths = " + usDeaths);
        webDriver.get(COVID_GA_URL);
        String gaCases = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[2]/div[1]/span")).getText();
        System.out.println("GA cases = " + gaCases);
        String gaDeaths = webDriver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div[2]/div[2]/span")).getText();
        System.out.println("GA deaths = " + gaDeaths);

    }
}
