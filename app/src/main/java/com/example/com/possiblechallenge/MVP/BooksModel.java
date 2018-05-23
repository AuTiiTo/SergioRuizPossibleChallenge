package com.example.com.possiblechallenge.MVP;

import com.example.com.possiblechallenge.POJO.Book;
import com.example.com.possiblechallenge.REPOSITORY.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksModel {
    private ApiService apiService;
    private BooksPresenter bookPresenter;

    public BooksModel(BooksPresenter booksPresenter) {
        bookPresenter = booksPresenter;
    }

    public void requestBooks() {
        setUpRetrofitService();
        callApi();
    }

    private void setUpRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://de-coding-test.s3.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    private void callApi() {
        apiService.getBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        bookPresenter.booksLoaded(response.body());
                    } else {
                        bookPresenter.errorResponse(response.code());
                    }
                } else {
                    bookPresenter.bodyNull();
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                bookPresenter.internetError(t.getMessage());
            }
        });
    }
}
