package com.gnyapp.booklibrary_room_mvvm.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.gnyapp.booklibrary_room_mvvm.database.BookDatabase;
import com.gnyapp.booklibrary_room_mvvm.model.Book;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BookRepository {


    private BookDatabase bookDatabase;
    private Executor executor = Executors.newSingleThreadExecutor();

    public BookRepository(Context context) {
        bookDatabase = BookDatabase.getBookDatabase(context);
    }

    public void insertBook(Book book) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                bookDatabase.bookDao().insertBook(book);
            }
        });
    }

    public void updateBook(Book book) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                bookDatabase.bookDao().updateBook(book);
            }
        });
    }

    public void deleteBook(Book book) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                bookDatabase.bookDao().deleteBook(book);
            }
        });
    }

    public LiveData<List<Book>> getAllBooks() throws ExecutionException, InterruptedException {
        return bookDatabase.bookDao().getAllBooks();
    }

}
