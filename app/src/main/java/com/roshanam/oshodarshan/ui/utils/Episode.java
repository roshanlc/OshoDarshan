package com.roshanam.oshodarshan.ui.utils;

public class Episode {
    private String episodeName;
    private String downloadURL;

    public Episode(){
    }

    public Episode(String episodeName, String downloadURL) {
        this.episodeName = episodeName;
        this.downloadURL = downloadURL;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }
}
