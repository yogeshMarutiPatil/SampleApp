package com.example.bookserverapp.model.api;

import com.example.bookserverapp.dummy.DummyContent;
import com.example.bookserverapp.model.utilities.Constants;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class BooksListApiManager {
    private BooksListApi mBookListApi;

    public BooksListApi getBookListApi() {

        if(mBookListApi == null) {
            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(DummyContent.DummyItem.class, new StringDeserializer());

            mBookListApi = new RestAdapter.Builder()
                    .setEndpoint(Constants.BASE_URL)
                    .setConverter(new GsonConverter(gson.create()))
                    .build()
                    .create(BooksListApi.class);
        }
        return mBookListApi;
    }
}
