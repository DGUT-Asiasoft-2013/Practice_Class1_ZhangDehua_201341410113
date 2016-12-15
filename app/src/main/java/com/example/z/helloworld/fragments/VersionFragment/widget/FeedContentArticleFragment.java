package com.example.z.helloworld.fragments.VersionFragment.widget;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.z.helloworld.R;
import com.example.z.helloworld.api.Article;

/**
 * Created by Z on 2016/12/15.
 */

public class FeedContentArticleFragment extends Fragment {
    View view;
    Article article;
    TextView txt_content,txt_title,txt_authorName,txt_create_time;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(view==null){
            view=inflater.inflate(R.layout.fragment_feed_content_article,null);
            txt_content=(TextView)view.findViewById(R.id.frag_feed_article_text);
            txt_title=(TextView)view.findViewById(R.id.frag_feed_article_content_title);
            txt_authorName=(TextView)view.findViewById(R.id.frag_feed_article_authorName);
            txt_create_time=(TextView)view.findViewById(R.id.frag_feed_article_create_time);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(article);
        setAuthorName(article);
        setCreatTime(article);
       setContent(article);
    }

    public void setArticle(Article a){
        this.article=a;
    }

    public void setTitle(Article a){
        txt_title.setText(a.getTitle());
    }
    public void setAuthorName(Article a){
        txt_authorName.setText(a.getAuthor().getName());
    }
    public void setCreatTime(Article a){
        String date= (String) android.text.format.DateFormat.format("yyyy-mm-dd hh-mm",a.getCreateDate());
        txt_create_time.setText(date);
    }
    public void setContent(Article a){
        txt_content.setText(a.getText());
    }
}
