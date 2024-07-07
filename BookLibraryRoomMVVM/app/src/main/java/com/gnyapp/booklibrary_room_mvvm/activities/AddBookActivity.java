package com.gnyapp.booklibrary_room_mvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.gnyapp.booklibrary_room_mvvm.R;
import com.gnyapp.booklibrary_room_mvvm.databinding.ActivityAddBookBinding;
import com.gnyapp.booklibrary_room_mvvm.model.Book;
import com.gnyapp.booklibrary_room_mvvm.viewmodel.BookViewModel;

public class AddBookActivity extends AppCompatActivity {

    private ActivityAddBookBinding activityAddBookBinding;
    private String title, author;
    private int pageNumber;
    private BookViewModel bookViewModel;
    private Book book;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddBookBinding = ActivityAddBookBinding.inflate(getLayoutInflater());
        setContentView(activityAddBookBinding.getRoot());
        init();
        activityAddBookBinding.addButton.setOnClickListener(view -> {
            if(isEdit) {
                if(isValidBookDetails()){
                    updateBook();
                }
            }else {
                if(isValidBookDetails()){
                    addBook();
                }
            }
        });

    }

    private void init() {
        bookViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(BookViewModel.class);
        if(getIntent().hasExtra("model")) {
            book = getIntent().getParcelableExtra("model");
            activityAddBookBinding.editTitle.setText(book.title);
            activityAddBookBinding.editPageNumber.setText(String.valueOf(book.pageNumber));
            activityAddBookBinding.editAuthor.setText(book.author);
            isEdit = true;
        }
        changeAddToUpdate();
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Boolean isValidBookDetails(){
        if(activityAddBookBinding.editTitle.getText().toString().trim().isEmpty()){
            showToast("Enter book title");
            return false;
        }
        else if(activityAddBookBinding.editAuthor.getText().toString().trim().isEmpty()){
            showToast("Enter author name");
            return false;
        }
        else if(activityAddBookBinding.editPageNumber.getText().toString().trim().isEmpty()){
            showToast("Enter page number");
            return false;
        }
        else{
            return true;
        }
    }

    private void changeAddToUpdate(){
        if(isEdit) {
            activityAddBookBinding.addButton.setText(R.string.update);
            activityAddBookBinding.textHeader.setText(R.string.update_book);
        }else{
            activityAddBookBinding.addButton.setText(R.string.add);
            activityAddBookBinding.textHeader.setText(R.string.add_new_book);
        }
    }

    private void addBook(){
        title = activityAddBookBinding.editTitle.getText().toString().trim();
        author = activityAddBookBinding.editAuthor.getText().toString().trim();
        pageNumber = Integer.parseInt(activityAddBookBinding.editPageNumber.getText().toString().trim());

        book = new Book();

        book.author = author;
        book.title = title;
        book.pageNumber = pageNumber;

        bookViewModel.insertBook(book);

        Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void updateBook() {
        title = activityAddBookBinding.editTitle.getText().toString().trim();
        author = activityAddBookBinding.editAuthor.getText().toString().trim();
        pageNumber = Integer.parseInt(activityAddBookBinding.editPageNumber.getText().toString().trim());

        book.author = author;
        book.title = title;
        book.pageNumber = pageNumber;

        bookViewModel.updateBook(book);

        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        finish();
    }
}