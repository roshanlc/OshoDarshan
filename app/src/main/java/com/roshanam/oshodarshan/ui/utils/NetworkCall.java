package com.roshanam.oshodarshan.ui.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.concurrent.Callable;

/**
 * Class to wrap callable implementation necessary to scrap data from website
 */
public class NetworkCall implements Callable<Result> {

    private String url;
    private Result result;

    public NetworkCall() {
        this.result = new Result();
    }
    public NetworkCall(String url) {
        this.url = url;
        this.result = new Result();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Result call() throws Exception {
        return searchFromOshoWorld(url);
    }

    Result searchFromOshoWorld(String url) {
        try {
          Document  doc = Jsoup.connect(url)
                    .timeout(10000)
                    .get();
            this.result.setDocument(doc);
            System.out.println("Title fetched =="+doc.title());


        } catch (Exception e) {
            //Toast.makeText(getActivity(), "Some error occurred!!", Toast.LENGTH_SHORT).show();

            // e.printStackTrace();
            this.result.setException(e);
        }

        System.out.println("Results found  =="+result.isThereNoResults());

        return this.result;
    }
}



