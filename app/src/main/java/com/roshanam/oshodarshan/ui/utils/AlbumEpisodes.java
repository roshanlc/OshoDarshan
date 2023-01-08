package com.roshanam.oshodarshan.ui.utils;

public class AlbumEpisodes {

    /*
   POJO for the following json schema
    {
    "title": "OSHO-Shiksha_Aur_Dharam_01",
    "subtitle": "",
    "audio": "https://oshoworld.com/wp-content/uploads/2020/11/Hindi%20Audio/OSHO-Shiksha_Aur_Dharam_01.mp3",
    "buyUrl": "",
    "downloadUrl": "https://oshoworld.com/wp-content/uploads/2020/11/Hindi%20Audio/OSHO-Shiksha_Aur_Dharam_01.mp3",
    "downloadFilename": "OSHO-Shiksha_Aur_Dharam_01.mp3",
    "cover": "",
    "lyrics": ""
    }
     */

        public String title;
        public String subtitle;
        public String audio;
        public String buyUrl;
        public String downloadUrl;
        public String downloadFilename;
        public String cover;
        public String lyrics;

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getSubtitle() {
                return subtitle;
        }

        public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
        }

        public String getAudio() {
                return audio;
        }

        public void setAudio(String audio) {
                this.audio = audio;
        }

        public String getBuyUrl() {
                return buyUrl;
        }

        public void setBuyUrl(String buyUrl) {
                this.buyUrl = buyUrl;
        }

        public String getDownloadUrl() {
                return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
                this.downloadUrl = downloadUrl;
        }

        public String getDownloadFilename() {
                return downloadFilename;
        }

        public void setDownloadFilename(String downloadFilename) {
                this.downloadFilename = downloadFilename;
        }

        public String getCover() {
                return cover;
        }

        public void setCover(String cover) {
                this.cover = cover;
        }

        public String getLyrics() {
                return lyrics;
        }

        public void setLyrics(String lyrics) {
                this.lyrics = lyrics;
        }
}
