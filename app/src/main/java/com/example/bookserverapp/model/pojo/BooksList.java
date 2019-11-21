
package com.example.bookserverapp.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BooksList {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("isbn")
    @Expose
    public String isbn;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("currencyCode")
    @Expose
    public String currencyCode;
    @SerializedName("author")
    @Expose
    public String author;

    private BooksList(Builder builder){
        id = builder.id;
        title = builder.title;
        isbn = builder.isbn;
        price = builder.price;
        currencyCode = builder.currencyCode;
        author = builder.author;
    }


    public static class Builder {
        private Integer id;
        private String title;
        private String isbn;
        private Integer price;
        private String currencyCode;
        private String author;

        public Builder setId(Integer id) {
            id = id;
            return Builder.this;
        }

        public Builder setTitle(String title) {
            title = title;
            return Builder.this;
        }

        public Builder setIsbn(String isbn) {
            isbn = isbn;
            return Builder.this;
        }

        public Builder setPrice(Integer price) {
            price = price;
            return Builder.this;
        }

        public Builder setCurrencyCode(String currencyCode) {
            currencyCode = currencyCode;
            return Builder.this;
        }

        public BooksList build() {
            return new BooksList(Builder.this);
        }

    }

    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }*/

}
