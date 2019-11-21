package com.example.bookserverapp.model.api;

import com.example.bookserverapp.dummy.DummyContent;

import retrofit.Callback;
import retrofit.http.GET;

public interface BooksListApi {
    @GET("/books")
    void getBooksList(Callback<String> booksList);
}
