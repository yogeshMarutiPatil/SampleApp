package com.example.bookserverapp.controller;

import android.util.Log;

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

                        BooksList booksList = new BooksList();

                        booksList.setId(object.getInt("id"));
                        booksList.setTitle(object.getString("title"));
                        booksList.setIsbn(object.getString("isbn"));
                        booksList.setPrice(object.getInt("price"));
                        booksList.setCurrencyCode(object.getString("currencyCode"));
                        booksList.setAuthor(object.getString("author"));

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


    /*public void startFetchingBooksDesc() {
        mApiManager.getBookListApi().getBookDesc(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "JSON :: " + s);

                try {
                    JSONArray array = new JSONArray(s);

                    for(int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        Book bookDesc = new Book();

                        bookDesc.setId(object.getInt("id"));
                        bookDesc.setTitle(object.getString("title"));
                        bookDesc.setDescription(object.getString("description"));
                        bookDesc.setIsbn(object.getString("isbn"));
                        bookDesc.setPrice(object.getInt("price"));
                        bookDesc.setCurrencyCode(object.getString("currencyCode"));
                        bookDesc.setAuthor(object.getString("author"));


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

*/

    public interface BookListCallbackListener {

        void onFetchStart();
        void onFetchProgress(BooksList bookList);
        void onFetchProgress(List<BooksList> flowerList);
        void onFetchComplete();
        void onFetchFailed();
    }
}
