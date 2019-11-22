package com.example.bookserverapp.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bookserverapp.view.ItemDetailActivity;
import com.example.bookserverapp.view.ItemDetailFragment;
import com.example.bookserverapp.view.ItemListActivity;
import com.example.bookserverapp.R;
import com.example.bookserverapp.model.pojo.BooksList;

import java.util.List;

public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final ItemListActivity mParentActivity;
    private  List<BooksList> mValues;
    private final boolean mTwoPane;



    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            BooksList item = (BooksList) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.getId().toString());
                ItemDetailFragment fragment = new ItemDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                Log.d("GETID",String.valueOf(item.getId()));
                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, String.valueOf(item.getId()));

                context.startActivity(intent);
            }
        }
    };

    public SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                         List<BooksList> items,
                                         boolean twoPane) {
        mParentActivity = parent;
        mValues = items;
        mTwoPane = twoPane;
    }

    public void addBookList(BooksList bookList) {
        mValues.add(bookList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        BooksList currentBookList = mValues.get(position);
        //holder.mId.setText(String.valueOf(currentBookList.id));
        holder.mTitle.setText("Title: " + String.valueOf(currentBookList.getTitle()));
        holder.mIsbn.setText("ISBN: " + String.valueOf(currentBookList.getIsbn()));
        holder.mPrice.setText("Price: " + String.valueOf(currentBookList.getPrice()) + " " + String.valueOf(currentBookList.getCurrencyCode()));
        //holder.mCurrencyCode.setText(String.valueOf(currentBookList.currencyCode));
        holder.mAuthor.setText("Author: " + String.valueOf(currentBookList.getAuthor()));

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mTitle;
        final TextView mIsbn;
        final TextView mPrice;
        final TextView mAuthor;

        ViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.title_text);
            mIsbn = (TextView) view.findViewById(R.id.isbn);
            mPrice = (TextView) view.findViewById(R.id.price);
            mAuthor = (TextView) view.findViewById(R.id.author);

        }
    }
}

