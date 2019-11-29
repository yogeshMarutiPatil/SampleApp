package com.example.bookserverapp.controller;

import android.util.Log;

import com.example.bookserverapp.model.api.BooksListApiManager;
import com.example.bookserverapp.model.pojo.Book;
import com.example.bookserverapp.model.pojo.BooksList;
import com.example.bookserverapp.model.utilities.BookDescEvent;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
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
    private static Book bookDesc;

    public Controller(BookListCallbackListener listener) {
        mListener = listener;
        mApiManager = new BooksListApiManager();
    }



    public void startFetching() {
        mApiManager.getBookListApi().getBooksList(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
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


    public void startFetchingBooksDesc(int id) {
        mApiManager.getBookListApi().getBooksDesc(id,new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d("STARTFETCH", "JSON :: " + s);

                Gson gson = new Gson();
                bookDesc = gson.fromJson(s, Book.class);

                Log.d("AUTHOR1",bookDesc.getAuthor());

                mListener.onFetchProgressBookDesc(bookDesc);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                mListener.onFetchComplete();
            }
        });

        if(bookDesc!= null){
            Log.d("AUTHOR2",bookDesc.getAuthor());
        }

    }

    public Book getBookDescription(){
        return this.bookDesc;
    }



    public interface BookListCallbackListener {

        void onFetchStart();
        void onFetchProgress(BooksList bookList);
        void onFetchProgressBookDesc(Book bookDesc);
        void onFetchProgress(List<BooksList> flowerList);
        void onFetchComplete();
        void onFetchFailed();
    }
}
