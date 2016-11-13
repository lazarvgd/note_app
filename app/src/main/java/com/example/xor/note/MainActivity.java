package com.example.xor.note;

import android.app.ActionBar;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.xor.note.adapters.RecyclerViewAdapter;
import com.example.xor.note.adapters.ViewPagerAdatper;
import com.example.xor.note.entities.Note;
import com.example.xor.note.fragments.AllNotesFragment;
import com.example.xor.note.fragments.NewNoteFragment;
import com.example.xor.note.helpers.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    public static List<Note> masterNoteList;
    private ViewPager mViewPager;
    private ViewPagerAdatper mAdapter;
    private SQLiteDatabase noteDB = null;
    private static final String tableName="notes";
    private String data ="";
    private DatabaseHelper db;
    private NewNoteFragment newNoteFragment;
    private AllNotesFragment allNotesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);

        masterNoteList = new ArrayList<>();
        mAdapter = new ViewPagerAdatper(this.getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tab = (TabLayout) findViewById(R.id.tabs);
        tab.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mAdapter);
        newNoteFragment= (NewNoteFragment) mAdapter.getItem(0);
        allNotesFragment= (AllNotesFragment) mAdapter.getItem(1);



    }

    public void updateAllNotesFragmentData(){
        allNotesFragment.updateData();
    }


}
