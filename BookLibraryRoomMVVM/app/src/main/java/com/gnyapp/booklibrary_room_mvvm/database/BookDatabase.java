package com.gnyapp.booklibrary_room_mvvm.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.gnyapp.booklibrary_room_mvvm.dao.BookDao;
import com.gnyapp.booklibrary_room_mvvm.model.Book;

@Database(entities = Book.class, version = 1, exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {
    private static BookDatabase bookDatabase;

    public static synchronized BookDatabase getBookDatabase(Context context) {
        if(bookDatabase == null) {
            bookDatabase = Room.databaseBuilder(
                    context,
                    BookDatabase.class,
                    "book_db"
            ).build();
        }
        return bookDatabase;
    }

    public abstract BookDao bookDao();

}

