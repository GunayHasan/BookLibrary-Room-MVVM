package com.gnyapp.booklibrary_room_mvvm.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class Book implements Parcelable {

    public Book() {
    }

    @PrimaryKey(autoGenerate = true)
    public int pId;

    public String title;
    public String author;
    public int pageNumber;

    public Book(Parcel in) {
        pId = in.readInt();
        title = in.readString();
        author = in.readString();
        pageNumber = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pId);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeInt(pageNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
