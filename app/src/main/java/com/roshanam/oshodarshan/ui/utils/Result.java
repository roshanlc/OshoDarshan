package com.roshanam.oshodarshan.ui.utils;

import org.jsoup.nodes.Document;



/**
 * Wrapper Class to hold result and exception
 */
public class Result {
    public Document result;
    public Exception e;

    public Result(Document result, Exception e) {
        this.result = result;
        this.e = e;
    }
}
