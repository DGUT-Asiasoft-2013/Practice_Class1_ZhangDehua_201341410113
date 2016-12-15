package com.example.z.helloworld.fragments.VersionFragment.widget;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.z.helloworld.R;

/**
 * Created by Z on 2016/12/14.
 */

public class SwitchBarFragment extends Fragment {
    View view;
    TextView textView1,textView2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_switch_bar,container);
        textView1=(TextView)view.findViewById(R.id.frag_switch_text_1);
        textView2=(TextView)view.findViewById(R.id.frag_switch_text_2);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1Onclick();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2Onclick();
            }
        });
    }
    //创建接口方便外部传送文本的点击事件
    public interface textViewSetOnClick{
        void text1OnClick();
        void text2OnClick();
    }
    textViewSetOnClick tvOnClick;
    //把外部对象传进来
    public void setClickField(textViewSetOnClick tvOnClick){
        this.tvOnClick=tvOnClick;
    }

    private void textView1Onclick() {
        if(tvOnClick!=null){
            tvOnClick.text1OnClick();
        }

    }

    private void textView2Onclick() {
        if(tvOnClick!=null) {
            tvOnClick.text2OnClick();
        }
    }

    public void setTextView1(String s){
        textView1.setText(s);
    }
    public void setTextView2(String s){
        textView2.setText(s);
    }
}
