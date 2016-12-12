package com.example.z.helloworld.fragments.VersionFragment.pages;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.z.helloworld.R;
import com.example.z.helloworld.api.User;

/**
 * Created by Z on 2016/12/7.
 */

public class MeListFragment extends Fragment{
    View view;
    AvatarView avatarView;
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.fragment_page_me,null);
            avatarView=(AvatarView)view.findViewById(R.id.avatar);

        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        avatarView.load("avatar");
    }
}
