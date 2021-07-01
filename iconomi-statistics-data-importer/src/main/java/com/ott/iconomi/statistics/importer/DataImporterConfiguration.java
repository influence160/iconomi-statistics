package com.ott.iconomi.statistics.importer;

import net.iconomi.api.client.IconomiApiBuilder;
import net.iconomi.api.client.IconomiRestApi;

public class DataImporterConfiguration {

    private static final String API_KEY = "33e224af6dae6a1317b9722aaeebe4f1a2c07b4c0b541bb5198ab11c3145ca73";
    private static final String API_SECRET = "254288f3d8e22aed1323081452e9cb761d1c179c786a0983acc399a93a01f795";

    /*private static final String API_KEY = "4a5bbaaa421d2bcbcfe857d33bfc31da66a0242e83c5121da72fa9cfd214d2c5";
    private static final String API_SECRET = "ee20ba079cd78b66ac08d73f0a73df593b26f26dd0759d6e18dfaad516d508ce";*/
    public IconomiRestApi restApi() {
        return new IconomiApiBuilder()
                //.setApiKey("api-key", "secret")
                .setApiKey(API_KEY, API_SECRET)
                //.setBaseApiUrl("https://api-durkadur.iconomi.com")
                .buildRestApi();
    }

}
