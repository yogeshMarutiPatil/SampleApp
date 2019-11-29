package com.example.bookserverapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.example.bookserverapp.R;
import com.example.bookserverapp.controller.Controller;
import com.example.bookserverapp.model.adapter.SimpleItemRecyclerViewAdapter;
import com.example.bookserverapp.model.pojo.Book;
import com.example.bookserverapp.model.pojo.BooksList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity implements Controller.BookListCallbackListener{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private Controller mController;
    private SimpleItemRecyclerViewAdapter mBookListAdapter;
    public static final List<BooksList> ITEMS = new ArrayList<BooksList>();
    public static final Map<String, BooksList> ITEM_MAP = new HashMap<String, BooksList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mController = new Controller(ItemListActivity.this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        mController.startFetching();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ItemListActivity.this));
        mBookListAdapter = new SimpleItemRecyclerViewAdapter(this, ITEMS, mTwoPane);
        recyclerView.setAdapter(mBookListAdapter);
    }



    @Override
    public void onFetchStart() {

    }

    @Override
    public void onFetchProgress(BooksList book) {
        mBookListAdapter.addBookList(book);

    }

    @Override
    public void onFetchProgressBookDesc(Book bookDesc) {

    }

    @Override
    public void onFetchProgress(List<BooksList> bookList) {

    }


    @Override
    public void onFetchComplete() {

    }

    @Override
    public void onFetchFailed() {

    }

}
