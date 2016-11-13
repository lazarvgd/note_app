package com.example.xor.note.fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xor.note.MainActivity;
import com.example.xor.note.R;
import com.example.xor.note.adapters.RecyclerViewAdapter;
import com.example.xor.note.adapters.ViewPagerAdatper;
import com.example.xor.note.entities.Note;
import com.example.xor.note.helpers.DatabaseHelper;

import static android.content.Context.MODE_PRIVATE;
import static com.example.xor.note.MainActivity.masterNoteList;

/**
 * Created by xor on 7.11.16..
 */
public class NewNoteFragment extends Fragment {

    private EditText mTitle;
    private EditText mContent;
    private Button mSubmitBtn;
    private Context mContext;
    private ViewPagerAdatper adapter;
    private String message;
    private Toast toast;

    private DatabaseHelper db;
    private String tableName = "notes";
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.new_note_fragment, container, false);
        mTitle = (EditText) rootView.findViewById(R.id.note_title);
        mContent = (EditText) rootView.findViewById(R.id.note_content);
        mSubmitBtn = (Button) rootView.findViewById(R.id.btn_submit);
        mContext = getActivity();
        db = new DatabaseHelper(mContext);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("".equals(mTitle.getText().toString()) && "".equals(mContent.getText().toString())){
                    message="Must enter at least one field...";
                    int duration = Toast.LENGTH_LONG;

                    toast = Toast.makeText(mContext,message,duration);
                    toast.show();

                }else {

                    db.inserData(mTitle.getText().toString(), mContent.getText().toString());
                    adapter.notifyDataSetChanged();
                    message = "Note Saved";
                    int duration = Toast.LENGTH_LONG;
                    toast = Toast.makeText(mContext,message,duration);
                    toast.show();
                    adapter.allNotesFragment().updateData();

                }
                mTitle.setText("");
                mContent.setText("");
            }
        });
        return rootView;
    }

    public void setViewPagerAdapter(ViewPagerAdatper adapter) {
        this.adapter = adapter;
    }
}
