package com.gnyapp.booklibrary_room_mvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gnyapp.booklibrary_room_mvvm.OnClickItemInterface;
import com.gnyapp.booklibrary_room_mvvm.R;
import com.gnyapp.booklibrary_room_mvvm.adapter.BookAdapter;
import com.gnyapp.booklibrary_room_mvvm.databinding.ActivityMainBinding;
import com.gnyapp.booklibrary_room_mvvm.model.Book;
import com.gnyapp.booklibrary_room_mvvm.viewmodel.BookViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements OnClickItemInterface {

    private ActivityMainBinding activityMainBinding;
    private BookViewModel bookViewModel;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        init();
        activityMainBinding.addBookButton.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), AddBookActivity.class)));

    }

    private void init() {
        activityMainBinding.recylerView.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BookAdapter(this);
        activityMainBinding.recylerView.setAdapter(bookAdapter);

        bookViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(BookViewModel.class);
        try {
            bookViewModel.getAllBooks().observe(MainActivity.this, books -> {
                if(books != null) {
                    activityMainBinding.textEmptyLibrary.setVisibility(View.GONE);
                    bookAdapter.setBooks(books);
                }
                if(books.size() == 0){
                    activityMainBinding.textEmptyLibrary.setVisibility(View.VISIBLE);
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onclickItem(Book book, boolean isEdit) {
        if(isEdit) {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            intent.putExtra("model", book);
            startActivity(intent);
        }else{
            bookViewModel.deleteBook(book);
        }
    }
}