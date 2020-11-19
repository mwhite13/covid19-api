package com.example.covid19api;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleTasks {


    @Autowired
    private Covid19Dao covid19Dao;

    private Twitter twitter;

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

    @Scheduled(cron = "0 0 * * * *")
    public void tweetCovidData() throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        twitter.postTweet("As of: " + covid19Dao.getCaseId(6).getDate() +
                "/n GA Cases: " + covid19Dao.getCaseId(6).getGa_cases() +
                "/n GA Deaths: " + covid19Dao.getCaseId(6).getGa_deaths());
    }

    @Scheduled(fixedRate = 10000)
    public  void testTweet() throws OAuthExpectationFailedException, OAuthCommunicationException, OAuthMessageSignerException, IOException {
        twitter.postTweet("As of: " + covid19Dao.getCaseId(6).getDate() +
                "/n GA Cases: " + covid19Dao.getCaseId(6).getGa_cases() +
                "/n GA Deaths: " + covid19Dao.getCaseId(6).getGa_deaths());
        System.out.println("Testing.....");


    }

}
