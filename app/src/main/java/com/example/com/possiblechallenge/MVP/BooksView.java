package com.example.com.possiblechallenge.MVP;

import com.example.com.possiblechallenge.POJO.Book;

import java.util.List;

public interface BooksView {
    void showBooks(List<Book> bookList);

    void showErrorMessage(String message);

    void showEmptyState();
}
