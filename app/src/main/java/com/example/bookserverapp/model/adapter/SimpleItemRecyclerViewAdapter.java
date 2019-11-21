package com.example.bookserverapp.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bookserverapp.ItemDetailActivity;
import com.example.bookserverapp.ItemDetailFragment;
import com.example.bookserverapp.ItemListActivity;
import com.example.bookserverapp.R;
import com.example.bookserverapp.model.pojo.BooksList;

import java.util.List;

public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final ItemListActivity mParentActivity;
    private final List<BooksList> mValues;
    private final boolean mTwoPane;



    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            BooksList item = (BooksList) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.title);
                ItemDetailFragment fragment = new ItemDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.title);

                context.startActivity(intent);
            }
        }
    };

    public SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                         List<BooksList> items,
                                         boolean twoPane) {
        mValues = items;
        mParentActivity = parent;
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
        holder.mTitle.setText(currentBookList.title);
        holder.mIsbn.setText(currentBookList.isbn);
        holder.mPrice.setText(currentBookList.price);
        holder.mCurrencyCode.setText(currentBookList.currencyCode);
        holder.mAuthor.setText(currentBookList.author);

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mId;
        final TextView mTitle;
        final TextView mIsbn;
        final TextView mPrice;
        final TextView mCurrencyCode;
        final TextView mAuthor;

        ViewHolder(View view) {
            super(view);
            mId = (TextView) view.findViewById(R.id.id_text);
            mTitle = (TextView) view.findViewById(R.id.title_text);
            mIsbn = (TextView) view.findViewById(R.id.isbn);
            mPrice = (TextView) view.findViewById(R.id.price);
            mCurrencyCode = (TextView) view.findViewById(R.id.currencyCode);
            mAuthor = (TextView) view.findViewById(R.id.author);

        }
    }
}

