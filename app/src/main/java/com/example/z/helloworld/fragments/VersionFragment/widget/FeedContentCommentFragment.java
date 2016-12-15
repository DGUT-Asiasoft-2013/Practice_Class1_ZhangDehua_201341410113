package com.example.z.helloworld.fragments.VersionFragment.widget;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z.helloworld.R;
import com.example.z.helloworld.api.Article;
import com.example.z.helloworld.api.Comment;
import com.example.z.helloworld.api.Page;
import com.example.z.helloworld.api.Server;
import com.example.z.helloworld.fragments.VersionFragment.pages.AvatarView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Z on 2016/12/15.
 */

public class FeedContentCommentFragment extends Fragment {
    View view;
    Article article;
    Button btn_com;
    EditText edi_com;
    TextView textView;
    ListView listView;
    List<Comment> data;
    Handler handler;
    int page=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null){
            handler=new Handler();
            view=inflater.inflate(R.layout.fragment_feed_content_comment,null);
            edi_com=(EditText)view.findViewById(R.id.feed_edi_comment);
            btn_com=(Button) view.findViewById(R.id.feed_btn_comment);
            listView=(ListView)view.findViewById(R.id.list_view_comment);
            listView.setAdapter(listAdapter);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        reload();
        btn_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendComment();
            }
        });
    }

    public void reload() {
        //获取数据
        Request request=Server.requestBuilderWithApi("article/"+article.getId()+"/comments")
                .get()
                .build();
        Server.getSharedClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    final Page<Comment> data = new ObjectMapper()
                            .readValue(response.body().string(),
                                    new TypeReference<Page<Comment>>() {
                                    });

                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            FeedContentCommentFragment.this.page = data.getNumber();
                            FeedContentCommentFragment.this.data = data.getContent();
                            listAdapter.notifyDataSetInvalidated();
                        }
                    });
                } catch (final Exception e) {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            new AlertDialog.Builder(getActivity())
                                    .setMessage(e.getMessage())
                                    .show();
                        }
                    });
                }
            }
        });
    }

    public  void setArticle(Article a){
        this.article=a;
    }
    private void sendComment() {


        if(edi_com.getText().toString().contentEquals("")){
            Toast.makeText(getActivity(),"内容不能为空",Toast.LENGTH_SHORT).show();
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
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),"发送成功",Toast.LENGTH_SHORT).show();
                            edi_com.setText("");
                            reload();
                        }
                    });
                }
            });
        }
    }
    BaseAdapter listAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                view = inflater.inflate(R.layout.list_view_item, null);
            } else {
                view = convertView;
            }
            //取出list布局文件里的控件
            TextView txt_comment = (TextView) view.findViewById(R.id.list_title);
            TextView text = (TextView) view.findViewById(R.id.list_txt);
            TextView author = (TextView) view.findViewById(R.id.list_author);
            TextView createTime = (TextView) view.findViewById(R.id.list_cteate_time);
            AvatarView avatar = (AvatarView) view.findViewById(R.id.list_avatar);

            try {
                text.setText("");
                Comment comment = data.get(position);
                txt_comment.setText(comment.getText());
                author.setText(comment.getAuthor().getName());
                createTime.setText(comment.getStringCreateDate());
                avatar.load(comment.getAuthor());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //
            return view;
        }
    };
}
