package com.roshanam.oshodarshan.ui.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.concurrent.Callable;

/**
 * Class to wrap callable implementation necessary to scrap data from website
 */
public class NetworkCall implements Callable<Result> {

    private String url;

    public NetworkCall() {

    }
    public NetworkCall(String url) {
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Result call() throws Exception {
        return searchFromOshoWorld(url);
    }

    Result searchFromOshoWorld(String url) {
        Result r = new Result(null,null);
        Document doc = new Document(url);
        try {
            doc = Jsoup.connect(url)
                    .timeout(10000)
                    .get();
            r.result = doc;
            System.out.println("Title fetched =="+doc.title());

        } catch (Exception e) {
            //Toast.makeText(getActivity(), "Some error occurred!!", Toast.LENGTH_SHORT).show();

            e.printStackTrace();
            r.e = e;
        }

        return r;
    }
}



