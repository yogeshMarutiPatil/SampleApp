package com.example.bookserverapp.model.api;

import retrofit.Callback;
import retrofit.http.GET;

public interface BooksListApi {
    @GET("/books")
    void getBooksList(Callback<String> booksList);

    @GET("/books/{id}")
    void getBookDesc(Callback<String> bookDesc);
    //Callback<Book> getBookDesc(@Path("id") int groupId);
}
