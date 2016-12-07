package com.example.z.helloworld.fragments.VersionFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.z.helloworld.R;

/**
 * Created by Z on 2016/12/6.
 */

public class MainToolBarFragment extends Fragment {
    View btnNew, tabFeeds, tabNotes, tabSearch, tabMe;
    View[] tabs;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab,null);
        btnNew = view.findViewById(R.id.frag_tab_btn);
        tabFeeds = view.findViewById(R.id.frag_tab_feeds);
        tabNotes = view.findViewById(R.id.frag_tab_notes);
        tabSearch = view.findViewById(R.id.frag_tab_search);
        tabMe = view.findViewById(R.id.frag_tab_me);

        tabs = new View[] {
                tabFeeds, tabNotes, tabSearch, tabMe
        };

        for(final View tab : tabs){
            tab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTabClicked(tab);
                }
            });
        }
        return view;
    }
    public static interface OnTabSelectedListener {
        void onTabSelected(int index);
    }

    OnTabSelectedListener onTabSelectedListener;

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }

    public void setSelectedItem(int index){
        if(index>=0 && index<tabs.length){
            onTabClicked(tabs[index]);
        }
    }
    void onTabClicked(View tab){
        int selectedIndex=-1;
        for(int i=0;i<tabs.length;i++){
            //otherTab.setSelected(otherTab == tab);
            if(tabs[i]==tab){
                tabs[i].setSelected(true);
                selectedIndex=i;
                //Toast.makeText(getActivity(),"第"+i+"被点击了",Toast.LENGTH_SHORT).show();
            }else {
                tabs[i].setSelected(false);
            }
        }
        if(onTabSelectedListener!=null && selectedIndex>=0){
            onTabSelectedListener.onTabSelected(selectedIndex);
        }
//
//        for(View otherTab:tabs){
//            otherTab.setSelected(otherTab == tab);
//            Toast.makeText(getActivity(),"第被点击了",Toast.LENGTH_SHORT).show();
//        }
    }
}
