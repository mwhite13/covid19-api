package com.example.covid19api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTasks {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private Covid19Dao covid19Dao;

    @Scheduled(cron = "0 22 * * 0 *") // runs every sunday at 10pm est
    public int getWeeklyReport() throws IOException {
        List<String> latest = new ArrayList<>();
        int maxid = covid19Dao.getMaxID();
        for (int i = maxid; i > 0; i--) {
            Covidcase temp = covid19Dao.getCaseId(i);
            if (temp != null) {
                latest.add(temp.getGa_cases());
            }
            if (latest.size() >= 7) {
                break;
            }
        }
        int newCases = (Integer.parseInt(latest.get(0).replaceAll("[,]", ""))) - (Integer.parseInt(latest.get(6).replaceAll("[,]", "")));
        System.out.println(newCases + " new covid cases in Georgia last week");
        return newCases;
    }

}
