package com.roshanam.oshodarshan.ui.utils;

import android.util.Log;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Objects;


/**
 * Wrapper Class to hold result and exception
 */
public class Result {
    private final String errorMsg = "We're sorry, but your search";
    private boolean hasResults = true;
    private Document document;
    private Exception exception;

    public Result(){
        this.document = new Document("");
        this.exception = null;
    }

    public Result(Document document, Exception exception) {
        this.document = document;
        this.exception = exception;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    // Check if there is an exception
    public boolean isThereException()  {
        return this.exception != null;
    }

    public boolean isHasResults() {
        return hasResults;
    }

    public void setHasResults(boolean hasResults) {
        this.hasResults = hasResults;
    }

    // Check if no results exist for the searched item
    public boolean isThereNoResults(){
        Element errorElem = this.document.selectFirst(Xpath.errorMsgSelectorCSS);
        if (errorElem != null && errorElem.hasText()){
            this.hasResults = !errorElem.text().contains(this.errorMsg);
        }
        return this.hasResults;
    }

}
