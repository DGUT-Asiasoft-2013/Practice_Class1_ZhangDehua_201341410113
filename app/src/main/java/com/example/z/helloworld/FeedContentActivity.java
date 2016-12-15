package com.example.z.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.z.helloworld.api.Article;
import com.example.z.helloworld.api.Server;
import com.example.z.helloworld.fragments.VersionFragment.pages.AvatarView;
import com.example.z.helloworld.fragments.VersionFragment.widget.FeedContentArticleFragment;
import com.example.z.helloworld.fragments.VersionFragment.widget.FeedContentCommentFragment;
import com.example.z.helloworld.fragments.VersionFragment.widget.SwitchBarFragment;

/**
 * Created by Z on 2016/12/7.
 */

public class FeedContentActivity extends Activity {
    Button btn_opt,btn_watch_com,btn_com;


    LinearLayout linearLayout;
    Article article;

    AvatarView avatarView;
    SwitchBarFragment switchBarFragment;
    FeedContentArticleFragment feedContentArticleFragment =new FeedContentArticleFragment();
    FeedContentCommentFragment feedContentCommentFragment=new FeedContentCommentFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_content);

        switchBarFragment=(SwitchBarFragment)getFragmentManager().findFragmentById(R.id.feed_content_switch_bar);

        //找出需要显示的部件
        avatarView=(AvatarView)findViewById(R.id.feed_content_avatar);

        article= (Article) getIntent().getSerializableExtra("article");//获取前一个Activity传过来的内容
        feedContentArticleFragment.setArticle(article);//把article传给对应的Fragment
        feedContentCommentFragment.setArticle(article);

        //动作按钮
        btn_watch_com= (Button) findViewById(R.id.feed_btn_watch_comment);

        btn_opt=(Button) findViewById(R.id.feed_btn_opt);


        linearLayout=(LinearLayout)findViewById(R.id.feed_content_lineLayout);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setDefaultFragment();
        setSwitchBar();
        getArticle();

    }

    private void setDefaultFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.feed_content_contain,feedContentArticleFragment)
                .commit();
    }

    private void setSwitchBar() {
        switchBarFragment.setTextView1("文章详情");
        switchBarFragment.setTextView2("评论详情");
        SwitchBarFragment.textViewSetOnClick click= new SwitchBarFragment.textViewSetOnClick() {
            @Override
            public void text1OnClick() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getApplicationContext(),"文章详情",Toast.LENGTH_SHORT).show();
                        setDefaultFragment();
                    }
                });
            }

            @Override
            public void text2OnClick() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getApplicationContext(),"评论详情",Toast.LENGTH_SHORT).show();
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.feed_content_contain,feedContentCommentFragment)
                                .commit();

                    }
                });
            }
        };
        switchBarFragment.setClickField(click);

    }



    private void watchComment() {


    }


    //设置部件内容
    private void getArticle() {

        avatarView.load(Server.serverAddress+article.getAuthor().getAvatar());
    }
}
