package com.roshanam.oshodarshan.ui.utils;

import android.os.Build;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;


import java.io.IOException;
import java.util.ArrayList;

public class AlbumDownloader {
    private Album album;
    private ArrayList<Episode> episodes;


    public AlbumDownloader(Album album) {


        this.album = album;
        this.episodes = new ArrayList<>();
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    public void addEpisode(String name, String url) {
        this.episodes.add(new Episode(name, url));
    }

    /**
     * Extract all episodes from the album
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void extractEpisodes() throws IOException {
        Log.i("INFO", "extractEpisodes: over here");




//         WebClient webClient = new WebClient();
//         webClient.setJavaScriptTimeout(45000);
//       HtmlPage page = webClient.getPage(this.album.getUrl());
//        Log.i("INFO", String.valueOf(page.getElementsByTagName("a")));
//         Log.i("INFO","title for the album page = " + page.getTitleText());
//

//        Document doc = Jsoup.connect(this.album.getUrl())
//                .timeout(45000)
//                .get();
//        //Elements all = doc.selectXpath(Xpath.allEpisodeSelector);
//        final String epSelector = Xpath.allEpisodeSelector;
//        Elements allElem = doc.getAllElements();
//        allElem.stream().filter(element -> element.hasAttr("title"))
//                .forEach(System.out::println);
//              // .forEach(element -> Log.i("INFO",element.attr("download")+" -> "+element.attr("href")));
//        Log.i("INFO","Elements ?= " + allElem.size());



    }

    class WebViewClientImpl extends WebViewClient {


        @Override
        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
            String func = "function getEpisodeLinks(){" +
                    "let arr = $x(\"//a[contains(@class,'ai-track-btn')]\");" +
                    "return arr.map(item => {return item.href});" +
                    "}";
            view.evaluateJavascript(func,  new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) {
                    printSomething(s);
                }
            });


        }
        public void printSomething(String data){
            Log.i("INFO",data);
        }
    }

}


