package com.example.bookserverapp.model.api;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface BooksListApi {
    @GET("/books")
    void getBooksList(Callback<String> booksList);

   /*@GET("/book/100")
    void getBooksDesc(Callback<String> bookDesc);*/
    //Callback<Book> getBookDesc(@Path("id") int groupId);*/


    @GET("/book/{id}")
    void getBooksDesc(@Path("id") int id, Callback<String> bookDesc);

}
