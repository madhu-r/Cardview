package com.example.madhu.cardview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    private ArrayList<SampleObjects> mDataset;
    private static int sBtnClickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mDataset = getDataSet();
        mAdapter = new MyRecyclerViewAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<SampleObjects> getDataSet() {
        ArrayList results = new ArrayList<SampleObjects>();
        for (int index = 0; index < 3; index++) {
            SampleObjects obj = new SampleObjects("My Test Text" + index,
                    "Test Small String " + index);
            results.add(index, obj);
        }
        return results;
    }

    public void addCard(View view) {
        sBtnClickCount++;
        SampleObjects obj = new SampleObjects("Button Click "+sBtnClickCount,"Some String"+sBtnClickCount);
        mDataset.add(obj);
        mAdapter.notifyItemInserted(mDataset.size() -1);
    }

}
