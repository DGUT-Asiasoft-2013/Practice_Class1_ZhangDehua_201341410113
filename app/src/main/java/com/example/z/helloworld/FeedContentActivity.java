package com.example.z.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Z on 2016/12/7.
 */

public class FeedContentActivity extends Activity {
    TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_content);
        txtView=(TextView)findViewById(R.id.feed_content_text);
        String txt=getIntent().getStringExtra("text");
        txtView.setText(txt);
    }
}
