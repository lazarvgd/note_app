package com.example.xor.note.adapters;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xor.note.MainActivity;
import com.example.xor.note.R;
import com.example.xor.note.entities.Note;
import com.example.xor.note.helpers.DatabaseHelper;

import java.util.List;

import static android.widget.Toast.LENGTH_LONG;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private Context mContext;
    private DatabaseHelper db;

    public RecyclerViewAdapter(Context context, List<Note> notes, DatabaseHelper db){
        this.noteList = notes;
        this.db = db;
        this.mContext=context;
    }

    public void swap(List<Note> list){
        noteList.clear();
        noteList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_row, parent, false);

        return new NoteViewHolder(itemView);    }


    @Override
    public void onBindViewHolder(final NoteViewHolder holder, final int position) {
        Note note = noteList.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());
        final String title = note.getTitle();
        final String content = note.getContent();

        holder.title.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setTitle((CharSequence) holder.title.getText().toString());
                alertDialog.setMessage(holder.content.getText().toString());
                alertDialog.show();
                System.out.println("CLICK");

            }
        });

        holder.content.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setTitle((CharSequence) holder.title.getText().toString());
                alertDialog.setMessage(holder.content.getText().toString());
                alertDialog.show();
                System.out.println("CLICK");

            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note item = noteList.get(position);
                db.deleteItem((int) item.getId());
                noteList.remove(item);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,noteList.size());

            }
        });

    }

    @Override
    public int getItemCount() {


        if (noteList == null) {
            return 0;
        }
        return noteList.size();
    }



    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        Button button;

        public NoteViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.one_row_title);
            content = (TextView) view.findViewById(R.id.one_row_content);
            button = (Button) view.findViewById(R.id.btn_delete);
        }
    }


}
