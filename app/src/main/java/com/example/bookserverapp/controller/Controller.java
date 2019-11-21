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

                        BooksList booksList = new BooksList.Builder()
                                .setId(object.getInt("id"))
                                .setTitle(object.getString("title"))
                                .setIsbn(object.getString("isbn"))
                                .setPrice(object.getInt("price"))
                                .setCurrencyCode(object.getString("currencyCode"))
                                .build();

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
        void onFetchProgress(BooksList bookList);
        void onFetchProgress(List<BooksList> flowerList);
        void onFetchComplete();
        void onFetchFailed();
    }
}
