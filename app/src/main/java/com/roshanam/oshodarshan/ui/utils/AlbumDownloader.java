package com.roshanam.oshodarshan.ui.utils;

import android.os.Build;
import android.util.JsonReader;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class AlbumDownloader {
    private Album album;

    private ArrayList<AlbumEpisodes> albumEpisodes;

    private static final String TAG = "INFO";

    public AlbumDownloader(Album album) {


        this.album = album;
        this.albumEpisodes = new ArrayList<>();
    }


    /**
     * Extract all episodes from the album
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<AlbumEpisodes> extractEpisodes() throws IOException {
        Document doc = Jsoup.connect(this.album.getUrl())
                .timeout(45000)
                .get();

        Elements playbox = doc.selectXpath(Xpath.audioBoxSelector);
        Element audioBox = playbox.get(0);
        String id = audioBox.id().split("-")[1]; // Id is the second portion

        Log.i("INFO", "extractEpisodes: Id for extraction is " + id);

        // Create a neat value object to hold the URL
        URL url = new URL(Xpath.episodesApi + id);

// Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

// Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");

// This line makes the request
        InputStream responseStream = connection.getInputStream();

// Manually converting the response body InputStream to APOD using Jackson
        InputStreamReader responseBodyReader = new InputStreamReader(responseStream, "UTF-8");
        JsonReader jsonReader = new JsonReader(responseBodyReader);

        jsonReader.beginArray(); // Start processing the JSON object

        while (jsonReader.hasNext()) { // Loop through all keys


            jsonReader.beginObject();
            AlbumEpisodes episode = new AlbumEpisodes();

            while (jsonReader.hasNext()) {
                String name = jsonReader.nextName();


                if (name.equals("title")) {
                    episode.setTitle(jsonReader.nextString());
                } else if (name.equals("subtitle")) {
                    episode.setSubtitle(jsonReader.nextString());
                } else if (name.equals("buyUrl")) {
                    episode.setBuyUrl(jsonReader.nextString());
                } else if (name.equals("audio")) {
                    episode.setAudio(jsonReader.nextString());
                } else if (name.equals("downloadUrl")) {
                    episode.setDownloadUrl(jsonReader.nextString());
                } else if (name.equals("downloadFilename")) {
                    episode.setDownloadFilename(jsonReader.nextString());
                } else if (name.equals("cover")) {
                    episode.setCover(jsonReader.nextString());
                } else if (name.equals("lyrics")) {
                    episode.setLyrics(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                }


            }
            jsonReader.endObject();

            albumEpisodes.add(episode);
        }


        jsonReader.endArray();
        connection.disconnect();


        return albumEpisodes;

    }

    class WebViewClientImpl extends WebViewClient {


        @Override
        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
            String func = "function getEpisodeLinks(){" +
                    "let arr = $x(\"//a[contains(@class,'ai-track-btn')]\");" +
                    "return arr.map(item => {return item.href});" +
                    "}";
            view.evaluateJavascript(func, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) {
                    printSomething(s);
                }
            });


        }

        public void printSomething(String data) {
            Log.i("INFO", data);
        }
    }

}


