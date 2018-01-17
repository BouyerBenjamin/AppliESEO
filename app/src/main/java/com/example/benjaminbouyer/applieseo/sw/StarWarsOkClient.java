package com.example.benjaminbouyer.applieseo.sw;

import android.os.Build;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit.client.OkClient;
import retrofit.client.Request;

/**
 * Created by Oleur on 23/12/2014.
 * Star Wars OkClient
 */
public class StarWarsOkClient extends OkClient {

    public StarWarsOkClient() {
        super();
    }

    @Override
    protected HttpURLConnection openConnection(Request request) throws IOException {
        HttpURLConnection connection = super.openConnection(request);
        connection.setRequestProperty("User-Agent", "swapi-android-" + Build.VERSION.RELEASE);
        return connection;
    }
}