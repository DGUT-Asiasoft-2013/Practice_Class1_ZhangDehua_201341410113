package com.example.z.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.z.helloworld.api.Server;
import com.example.z.helloworld.fragments.VersionFragment.pages.AvatarView;

/**
 * Created by Z on 2016/12/7.
 */

public class FeedContentActivity extends Activity {
    TextView txt_content,txt_title,txt_authorName,txt_create_time;
    int articleID;
    String title,text,authorName,createTime,avatarUrl;
    AvatarView avatarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_content);
        txt_content=(TextView)findViewById(R.id.feed_content_text);
        txt_title=(TextView)findViewById(R.id.feed_content_title);
        txt_authorName=(TextView)findViewById(R.id.feed_content_authorName);
        txt_create_time=(TextView)findViewById(R.id.feed_content_create_time);
        avatarView=(AvatarView)findViewById(R.id.feed_content_avatar);

        articleID=getIntent().getIntExtra("articleID",0);
        title=getIntent().getStringExtra("title");
        text=getIntent().getStringExtra("text");
        authorName=getIntent().getStringExtra("authorName");
        createTime=getIntent().getStringExtra("createTime");
        avatarUrl=getIntent().getStringExtra("avatarUrl");

    }

    @Override
    protected void onResume() {
        super.onResume();
        getArticle();
    }

    private void getArticle() {
       txt_title.setText(title);
        txt_authorName.setText(authorName);
        txt_create_time.setText(createTime);
        txt_content.setText(text);
        avatarView.load(Server.serverAddress+avatarUrl);
    }
}
