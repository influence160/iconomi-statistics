package com.ott.iconomi.statistics.importer;

import net.iconomi.api.client.IconomiApiBuilder;
import net.iconomi.api.client.IconomiRestApi;
import net.iconomi.api.client.model.Strategy;

import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        String API_KEY = "PUT_THE_API_KEY_HERE";
        String API_SECRET = "PUT_THE_API_SECRET_HERE";

        IconomiRestApi restApi = new IconomiApiBuilder()
                //.setApiKey("api-key", "secret")
                .setApiKey(API_KEY, API_SECRET)
//                .setBaseApiUrl("https://api-durkadur.iconomi.com")
                .buildRestApi();
        List<Strategy> strategies = restApi.getStrategiesList();
        System.out.println(strategies);
    }
}
