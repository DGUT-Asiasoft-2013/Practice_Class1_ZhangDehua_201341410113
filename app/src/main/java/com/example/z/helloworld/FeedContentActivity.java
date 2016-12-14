package com.example.z.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z.helloworld.api.Article;
import com.example.z.helloworld.api.Server;
import com.example.z.helloworld.fragments.VersionFragment.pages.AvatarView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Z on 2016/12/7.
 */

public class FeedContentActivity extends Activity {
    Button btn_opt,btn_go_com,btn_com;
    TextView txt_content,txt_title,txt_authorName,txt_create_time;
    EditText edi_com;
    LinearLayout linearLayout;
    Article article;

    AvatarView avatarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_content);

        //找出需要显示的部件
        txt_content=(TextView)findViewById(R.id.feed_content_text);
        txt_title=(TextView)findViewById(R.id.feed_content_title);
        txt_authorName=(TextView)findViewById(R.id.feed_content_authorName);
        txt_create_time=(TextView)findViewById(R.id.feed_content_create_time);
        avatarView=(AvatarView)findViewById(R.id.feed_content_avatar);

        //获取前一个Activity传过来的内容
        article= (Article) getIntent().getSerializableExtra("article");

        //动作按钮
        btn_go_com= (Button) findViewById(R.id.feed_btn_goto_comment);
        btn_com=(Button) findViewById(R.id.feed_btn_comment);
        btn_opt=(Button) findViewById(R.id.feed_btn_opt);


        edi_com=(EditText)findViewById(R.id.feed_edi_comment);
        linearLayout=(LinearLayout)findViewById(R.id.feed_content_lineLayout);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getArticle();
        edi_com.clearFocus();
        btn_go_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoComment();
            }
        });
        btn_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendComment();
            }
        });
    }

    private void sendComment() {


        if(edi_com.getText().toString().contentEquals("")){
            Toast.makeText(getApplicationContext(),"内容不能为空",Toast.LENGTH_SHORT).show();
        }else {
            MultipartBody requestBody=new MultipartBody.Builder()
                    .addFormDataPart("text",edi_com.getText().toString())
                    .build();
            Request request=Server.requestBuilderWithApi("article/"+article.getId()+"/comments")
                    .post(requestBody)
                    .build();
            Server.getSharedClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                        }
                    });

                }
            });
        }
    }

    private void gotoComment() {

        linearLayout.setVisibility(View.VISIBLE);
        edi_com.requestFocusFromTouch();

    }


    //设置部件内容
    private void getArticle() {
       txt_title.setText(article.getTitle());
        txt_authorName.setText("作者: "+article.getAuthor().getName());
        String date= (String) android.text.format.DateFormat.format("yyyy-mm-dd hh-mm",article.getCreateDate());
        txt_create_time.setText(date);
        txt_content.setText(article.getText());
        avatarView.load(Server.serverAddress+article.getAuthor().getAvatar());
    }
}
