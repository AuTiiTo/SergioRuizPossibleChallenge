package com.example.com.possiblechallenge.REPOSITORY;

import com.example.com.possiblechallenge.POJO.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/books.json")
    Call<List<Book>> getBooks();
}
