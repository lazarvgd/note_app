package com.example.xor.note.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.xor.note.MainActivity;
import com.example.xor.note.R;
import com.example.xor.note.adapters.RecyclerViewAdapter;
import com.example.xor.note.entities.Note;
import com.example.xor.note.helpers.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.xor.note.MainActivity.masterNoteList;

/**
 * Created by xor on 7.11.16..
 */
public class AllNotesFragment extends Fragment {


    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private Context mContext;
    private Cursor cursor;
    private DatabaseHelper db;
    private List<Note> notes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.all_notes_fragment, container, false);
        mContext = getActivity();
        db = new DatabaseHelper(mContext);

        notes = new ArrayList<>();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);

        notes = getAllNotes();


        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(mContext,notes,db);

        recyclerView.setAdapter(recyclerViewAdapter);
       /* recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setTitle(notes.get(recyclerViewAdapter.getItemCount()).getTitle());
                alertDialog.setMessage(notes.get(recyclerViewAdapter.getItemCount()).getContent());
                alertDialog.show();
                System.out.println("CLICK");

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });*/


        return rootView;
    }

    private List<Note> getAllNotes() {

        cursor = db.getCursor();
        List list = new ArrayList<Note>();
        if(cursor.getCount()==0){
            int duration = Toast.LENGTH_LONG;
            String message = "No data to display!";
            Toast toast = Toast.makeText(mContext,message,duration);
            toast.show();
            return new ArrayList<>();
        }


        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()){
            Note n = new Note(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2));
            list.add(0,n);

        }
        return list;
    }
    public void updateData(){

        recyclerViewAdapter.swap(getAllNotes());
    }

}
