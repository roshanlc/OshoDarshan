package com.roshanam.oshodarshan.ui.utils;

public final class Xpath {
    // Selector for error msg
    public static final String errorMsgSelector = "//h3[@class='page_subtitle']";
    public static final String errorMsgSelectorCSS = "h3.page_subtitle";

    // Selector for albums listed on result page
    public static final String allAlbumSelector = "//div[contains(@class,'masonry_item')]/article/div/h4/a";
    // Selector for all episodes in an album page
    public static final String allEpisodeSelector = "ai-track-btn";
    public static final String audioBoxSelector = "//div[contains(@class,'audioigniter-root')]";
    public static final String episodesApi = "https://oshoworld.com/?audioigniter_playlist_id=";
            //"//a[contains(@class,'ai-track-btn') and (@aria-label='Download this track')]";
}
