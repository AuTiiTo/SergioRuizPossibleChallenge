package com.example.com.possiblechallenge.MVP;

import com.example.com.possiblechallenge.POJO.Book;
import com.example.com.possiblechallenge.R;

import java.util.List;

public class BooksPresenter {
    private BooksView bookView;
    private BooksModel bookModel;

    public BooksPresenter(BooksView view) {
        bookView = view;
        bookModel = new BooksModel(this);
    }

    public void viewReady() {
        bookModel.requestBooks();
    }

    public void booksLoaded(List<Book> bookList) {
        bookView.showBooks(bookList);
    }

    public void internetError(String message) {
        bookView.showErrorMessage(message);
    }

    public void errorResponse(int code) {
        bookView.showErrorMessage(String.valueOf(code));
    }

    public void bodyNull() {
        bookView.showEmptyState();
    }

    public void refreshView() {
        bookModel.requestBooks();
    }
}
