package com.example.bookserverapp.view;

import android.app.Activity;
import android.os.Bundle;

import com.example.bookserverapp.R;
import com.example.bookserverapp.controller.Controller;
import com.example.bookserverapp.model.pojo.Book;
import com.example.bookserverapp.model.pojo.BooksList;
import com.example.bookserverapp.model.utilities.BookDescEvent;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment implements Controller.BookListCallbackListener {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static Book BOOK_Desc_ITEMS ;
    public Book bookDescItem;

    private Controller mController;
    /**
     * The dummy content this fragment is presenting.
     */
   // private BooksList mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BOOK_Desc_ITEMS = new Book();
        mController= new Controller(ItemDetailFragment.this);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            Log.d("ItemDetailFragment","INSIDE");
           // mItem = ItemListActivity.ITEMS.get(getArguments().getInt(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle("BookServerApp");
            }
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        //if (mItem != null) {

            Bundle bundle = this.getArguments();
            String book_id = bundle.getString(ItemDetailFragment.ARG_ITEM_ID);
            Log.d("", (bundle.toString()));
            int id = Integer.parseInt(book_id);

            //Api call
            mController.startFetchingBooksDesc(id);
            mController.getBookDescription();

            Log.d("BOOKDESC", BOOK_Desc_ITEMS.getAuthor());

            //Log.d("BOOKDESC", mController.getBookDescription().getAuthor());
            //mItem = bundle.get(ItemDetailFragment.ARG_ITEM_ID);

        //Display elements in fragment

            //((TextView) rootView.findViewById(R.id.item_detail)).setText(bookDescription.getAuthor());
       // }

        return rootView;
    }

    @Override
    public void onFetchStart() {

    }

    @Override
    public void onFetchProgress(BooksList bookList) {

    }

    @Override
    public void onFetchProgressBookDesc(Book bookDesc) {
        BOOK_Desc_ITEMS = bookDesc;
    }

    @Override
    public void onFetchProgress(List<BooksList> flowerList) {

    }

    @Override
    public void onFetchComplete() {

    }

    @Override
    public void onFetchFailed() {

    }
}
