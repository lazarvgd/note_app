package com.example.xor.note.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.xor.note.fragments.AllNotesFragment;
import com.example.xor.note.fragments.NewNoteFragment;

/**
 * Created by xor on 7.11.16..
 */
public class ViewPagerAdatper extends FragmentStatePagerAdapter {
    private RecyclerViewAdapter recAdapter;
    private NewNoteFragment newNoteFragment;
    private AllNotesFragment allNotesFragment;


    public ViewPagerAdatper(FragmentManager fm) {
        super(fm);
    }

    public AllNotesFragment allNotesFragment(){
        return allNotesFragment;
    }


    @Override
    public Fragment getItem(int position) {
       if(position == 0 ){

            newNoteFragment = new NewNoteFragment();
           newNoteFragment.setViewPagerAdapter(this);
            return newNoteFragment;
        }else{

           allNotesFragment =new AllNotesFragment();

            return allNotesFragment;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position){
        if(position==0){
            return "New Note";
        }
        else{
            return "My Notes";
        }
    }

}
