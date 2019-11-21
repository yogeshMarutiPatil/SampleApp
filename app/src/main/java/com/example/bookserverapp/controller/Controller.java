package com.example.bookserverapp.controller;

import android.util.Log;

import com.example.bookserverapp.ItemListActivity;
import com.example.bookserverapp.dummy.DummyContent;
import com.example.bookserverapp.model.api.BooksListApi;
import com.example.bookserverapp.model.api.BooksListApiManager;
import com.example.bookserverapp.model.pojo.BooksList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Controller {

    private static final String TAG = Controller.class.getSimpleName();
    private BookListCallbackListener mListener;
    private BooksListApiManager mApiManager;

    public Controller(BookListCallbackListener listener) {
        mListener = listener;
        mApiManager = new BooksListApiManager();
    }



    public void startFetching() {
        mApiManager.getBookListApi().getBooksList(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "JSON :: " + s);

                try {
                    JSONArray array = new JSONArray(s);

                    for(int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        DummyContent.DummyItem booksList = new DummyContent.DummyItem();
                        booksList.setTitle(object.getString("title"));
                        booksList.setAuthor(object.getString("author"));
                        booksList.setIsbn(object.getString("isbn"));
                        booksList.setPrice(object.getInt("price"));
                        booksList.setCurrencyCode(object.getString("currencyCode"));

                        mListener.onFetchProgress(booksList);

                    }

                } catch (JSONException e) {
                    mListener.onFetchFailed();
                }


                mListener.onFetchComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                mListener.onFetchComplete();
            }
        });
    }


    public interface BookListCallbackListener {

        void onFetchStart();
        void onFetchProgress(DummyContent.DummyItem flower);
        void onFetchProgress(List<DummyContent.DummyItem> flowerList);
        void onFetchComplete();
        void onFetchFailed();
    }
}
