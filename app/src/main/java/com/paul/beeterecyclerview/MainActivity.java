package com.paul.beeterecyclerview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_ID = "com.paul.beeterecyclerview.ID";

    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //populate DB in RoomDB onOpen

        RecyclerView myRecyclerView = findViewById(R.id.recyclerview); //Handler
        BeeteListAdapter myBeeteListAdapter = new BeeteListAdapter(this);// ab hier läuft der adapter
        myRecyclerView.setAdapter(myBeeteListAdapter); //set adapter for recycler view layout element

        myRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //layout manager

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class); //Provider erstellt und erinnert ViewModel
        // jeweils bei Activity create
        mMainViewModel.getAllBeete().observe(this, new Observer<List<Beet>>() { //getAll - Observer für alle Beete
            @Override
            public void onChanged(@Nullable final List<Beet> beete) {
                myBeeteListAdapter.setBeeteArray(beete);
            }
        });

/*        ItemTouchHelper helper = new ItemTouchHelper( //ITEMtouch helper statt setOnSwipeListener im adapter
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Beet currentBeet = myBeeteListAdapter.getBeetAtPosition(position);
                        Toast.makeText(MainActivity.this, "Deleting " +
                                currentBeet.getDesc(), Toast.LENGTH_SHORT).show();
                        mMainViewModel.deleteBeet(currentBeet);
                    }
                });

        helper.attachToRecyclerView(myRecyclerView);*/

    }

}
