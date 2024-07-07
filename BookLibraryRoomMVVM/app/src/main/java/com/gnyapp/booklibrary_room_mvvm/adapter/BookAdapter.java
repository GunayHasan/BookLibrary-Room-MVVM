package com.gnyapp.booklibrary_room_mvvm.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gnyapp.booklibrary_room_mvvm.OnClickItemInterface;
import com.gnyapp.booklibrary_room_mvvm.R;
import com.gnyapp.booklibrary_room_mvvm.databinding.BookItemContainerBinding;
import com.gnyapp.booklibrary_room_mvvm.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    List<Book> bookList;
    private OnClickItemInterface onClickItemInterface;

    public BookAdapter(OnClickItemInterface onClickItemInterface) {
        this.onClickItemInterface = onClickItemInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookItemContainerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.book_item_container, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(bookList != null) {
            Book book = bookList.get(position);
            holder.binding.setBookModel(book);
            holder.binding.setListener(onClickItemInterface);
        }
    }

    @Override
    public int getItemCount() {
        if(bookList != null) {
            return bookList.size();
        }else{
            return 0;
        }

    }

    public void setBooks(List<Book> list) {
        bookList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        BookItemContainerBinding binding;
        public ViewHolder(BookItemContainerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
