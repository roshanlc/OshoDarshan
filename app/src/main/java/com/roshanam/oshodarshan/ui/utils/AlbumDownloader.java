package com.roshanam.oshodarshan.ui.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        Document doc = Jsoup.connect(this.album.getUrl())
                .timeout(45000)
                .get();
        System.out.println("title for the album page = " + doc.title());
        //Elements all = doc.selectXpath(Xpath.allEpisodeSelector);
        final String epSelector = Xpath.allEpisodeSelector;
        Elements allElem = doc.getAllElements();
        allElem.stream().filter(element -> element.hasAttr("title"))
                .forEach(System.out::println);
              // .forEach(element -> System.out.println(element.attr("download")+" -> "+element.attr("href")));
        System.out.println("Elements ?= " + allElem.size());

//        for (Element elem : allElem) {
//            String url = elem.attr("href");
//            String name = elem.attr("download");
//            System.out.println("Url = " + url + " -> Name=" + name); //TODO: remove later
//            this.episodes.add(new Episode(name, url));
//        }

    }
}
