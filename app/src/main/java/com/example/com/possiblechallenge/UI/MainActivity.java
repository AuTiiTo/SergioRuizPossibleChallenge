package com.example.com.possiblechallenge.UI;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.com.possiblechallenge.MVP.BookAdapter;
import com.example.com.possiblechallenge.MVP.BooksPresenter;
import com.example.com.possiblechallenge.MVP.BooksView;
import com.example.com.possiblechallenge.POJO.Book;
import com.example.com.possiblechallenge.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BooksView {

    private BookAdapter bookAdapter;
    private RecyclerView rvBooksList;
    private RecyclerView.LayoutManager rvLayouManager;
    private BooksPresenter booksPresenter;
    private SwipeRefreshLayout refreshLayout;
    private LinearLayout emptyState;
    private TextView emptyStateMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyState = findViewById(R.id.booksEmptyState);
        emptyStateMessage = findViewById(R.id.emptyStateMessage);
        refreshLayout = findViewById(R.id.booksLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bookAdapter = new BookAdapter(null);
                booksPresenter.refreshView();
            }
        });

        booksPresenter = new BooksPresenter(this);

        rvLayouManager = new LinearLayoutManager(this);

        rvBooksList = findViewById(R.id.booksRecyclerView);
        rvBooksList.setHasFixedSize(true);
        rvBooksList.setLayoutManager(rvLayouManager);

        booksPresenter.viewReady();
    }

    @Override
    public void showBooks(List<Book> bookList) {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        bookAdapter = new BookAdapter(bookList);
        rvBooksList.setAdapter(bookAdapter);
        bookAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {
        if (message.length() <= 3) {
            emptyStateMessage.setText(getString(R.string.error_message_string, message));
        } else {
            emptyStateMessage.setText(message);
        }
        emptyState.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyState() {
        emptyState.setVisibility(View.VISIBLE);
    }
}
