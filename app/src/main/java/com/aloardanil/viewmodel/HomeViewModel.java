package com.aloardanil.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> mData;

    public HomeViewModel() {
        mData = new MutableLiveData<>();
        ArrayList<String> data = new ArrayList<>();
        data.add("https://images.unsplash.com/photo-1503066211613-c17ebc9daef0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=800&q=80");
        data.add("https://images.pexels.com/photos/247502/pexels-photo-247502.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        data.add("https://kids.nationalgeographic.com/content/dam/kidsea/kids-core-objects/backgrounds/youareleaving_kids.adapt.768.1.jpg");
        data.add("https://images.unsplash.com/photo-1504208434309-cb69f4fe52b0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=800&q=80");
        data.add("https://www.iata.org/contentassets/d7c512eb9a704ba2a8056e3186a31921/cargo_live_animals_parrot.jpg?w=330&h=200&mode=crop&scale=both&v=20191213012337");

        mData.setValue(data);
    }

    public LiveData<ArrayList<String>> getData() {
        return mData;
    }
}