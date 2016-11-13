package com.example.xor.note.entities;

import android.text.Editable;

import java.util.UUID;

/**
 * Created by xor on 7.11.16..
 */

public class Note {
    private int id;
    private String title;
    private String content;

    public Note(int id,String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
