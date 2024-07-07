package com.gnyapp.booklibrary_room_mvvm.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gnyapp.booklibrary_room_mvvm.model.Book;
import com.gnyapp.booklibrary_room_mvvm.repository.BookRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BookViewModel extends AndroidViewModel {

    private BookRepository bookRepository;

    public BookViewModel(Application application) {
        super(application);
        bookRepository = new BookRepository(application);
    }

    public void insertBook(Book book) {
        bookRepository.insertBook(book);
    }

    public void updateBook(Book book) {
        bookRepository.updateBook(book);
    }

    public void deleteBook(Book book) {
        bookRepository.deleteBook(book);
    }

    public LiveData<List<Book>> getAllBooks() throws ExecutionException, InterruptedException {
        return bookRepository.getAllBooks();
    }

}
