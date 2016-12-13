package com.example.z.helloworld;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.z.helloworld.fragments.VersionFragment.MainToolBarFragment;
import com.example.z.helloworld.fragments.VersionFragment.pages.FeedsListFragment;
import com.example.z.helloworld.fragments.VersionFragment.pages.MeListFragment;
import com.example.z.helloworld.fragments.VersionFragment.pages.NoteListFragment;
import com.example.z.helloworld.fragments.VersionFragment.pages.SearchListFragment;


/**
 * Created by Z on 2016/12/6.
 */

public class HelloWorldActivity extends Activity {
    FeedsListFragment contentFeedList = new FeedsListFragment();
    NoteListFragment contentNoteList = new NoteListFragment();
    SearchListFragment contentSearchPage = new SearchListFragment();
    MeListFragment contentMyProfile = new MeListFragment();

    MainToolBarFragment tabbar;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_first_page);
        tabbar = (MainToolBarFragment) getFragmentManager().findFragmentById(R.id.first_page_frag_tab);
        tabbar.setOnTabSelectedListener(new MainToolBarFragment.OnTabSelectedListener() {

            @Override
            public void onTabSelected(int index) {
                changeContentFragment(index);
            }
        });

        //发表
        btn_add=(Button)findViewById(R.id.frag_tab_btn);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendNews();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        tabbar.setSelectedItem(0);
    }


//跳转到发表新心情Activity
    void SendNews(){
        Intent intent=new Intent(getApplicationContext(),SendNewsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slider_in_buttom,R.anim.none);

    }
    void changeContentFragment(int index) {
        Fragment newFrag = null;

        switch (index) {
            case 0:
                newFrag = contentFeedList;
                break;
            case 1:
                newFrag = contentNoteList;
                break;
            case 2:
                newFrag = contentSearchPage;
                break;
            case 3:
                newFrag = contentMyProfile;
                break;

            default:
                break;
        }

        if (newFrag == null) return;

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, newFrag)
                .commit();
    }
}
