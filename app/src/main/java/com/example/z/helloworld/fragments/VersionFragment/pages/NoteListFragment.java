package com.example.z.helloworld.fragments.VersionFragment.pages;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.z.helloworld.R;

/**
 * Created by Z on 2016/12/7.
 */

public class NoteListFragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.fragment_page_notes,null);
        }
        return view;
    }
}
