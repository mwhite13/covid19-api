package com.example.covid19api;

import com.sun.deploy.net.URLEncoder;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

public class Twitter {
    private static final String API_KEY ="W8Et3JOtOx0TxSjZmWk6O2g1t";
    private static final String API_SECRET_KEY="vPIrxiK3Z3wYfZOJK8qbtqgkeouAbOih0KkF5wq5nYUpuNkbfc";
    private static final String ACCESS_TOKEN ="1230970154747662338-nQTVj80mXAPPzlBQqRTIJR3oqr5vGV";
    private static final String ACCESS_SECRET_TOKEN = "HC1NmhlGNKAZe6cNPL4U2vFoCuk06hJBuxkhmPhnOQMIy";

    /*
API Key:
W8Et3JOtOx0TxSjZmWk6O2g1t

API Secret Key:
vPIrxiK3Z3wYfZOJK8qbtqgkeouAbOih0KkF5wq5nYUpuNkbfc

Access Token:
1230970154747662338-nQTVj80mXAPPzlBQqRTIJR3oqr5vGV

Access Secret Token:
HC1NmhlGNKAZe6cNPL4U2vFoCuk06hJBuxkhmPhnOQMIy

     */

    public static void postTweet(String msg) throws IOException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthMessageSignerException {
        OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(API_KEY, API_SECRET_KEY);
        oAuthConsumer.setTokenWithSecret(ACCESS_TOKEN, ACCESS_SECRET_TOKEN);
        String encoded = URLEncoder.encode(msg,"UTF-8");
        HttpPost httpPost = new HttpPost("https://api.twitter.com/1.1/statuses/update.json?status=" + encoded);
        oAuthConsumer.sign(httpPost);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpPost);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':' + httpResponse.getStatusLine().getReasonPhrase());
        System.out.println(IOUtils.toString(httpResponse.getEntity().getContent()));
    }
}
