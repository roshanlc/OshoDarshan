package com.roshanam.oshodarshan.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public static final String ABOUT_OSHO = "Rajneesh (born Chandra Mohan Jain; 11 December 1931 – 19 January 1990), also known as " +
            "Osho, was an Indian mystic, and founder of the Rajneesh movement." +
            "\n\nHe rejected institutional religions, insisting that spiritual experience could not be organized into any one system of religious dogma." +
            "\n\nAs a guru, he taught a form of meditation called dynamic meditation and advocated that his followers live fully but without attachment" +
            " , a rejection of traditional ascetic practices.\n\nAuthor: Roshan Lamichhane";
    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(ABOUT_OSHO);
    }

    public LiveData<String> getText() {
        return mText;
    }
}