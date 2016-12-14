package com.example.z.helloworld.fragments.VersionFragment.pages;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.z.helloworld.FeedContentActivity;
import com.example.z.helloworld.R;
import com.example.z.helloworld.api.Article;
import com.example.z.helloworld.api.Page;
import com.example.z.helloworld.api.Server;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Z on 2016/12/7.
 */

public class FeedsListFragment extends Fragment {
    View view;
    ListView listView;
    //String[] data;
    Random random = new Random();
    Article art = new Article();

    List<Article> data;
    int page = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_page_feeds, null);
            //data = new String[random.nextInt(20)];//生成随机个（0到100之间）数组成员
            Toast.makeText(getActivity(), "rrr", Toast.LENGTH_LONG);

            listView = (ListView) view.findViewById(R.id.list);
            //listView.addFooterView();
            listView.setAdapter(listAdapter);
            //设置ListView里的单击事件
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    OnItemClickListener(position);
                }
            });
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        reload();
    }

    void reload() {
        Request request = Server.requestBuilderWithApi("feeds")
                .get()
                .build();

        Server.getSharedClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        new AlertDialog.Builder(getActivity())
                                .setMessage(e.getMessage())
                                .show();
                    }
                });
            }

            @Override
            public void onResponse(Call arg0, Response arg1) throws IOException {
                try {
                    Page<Article> data = new ObjectMapper()
                            .readValue(arg1.body().string(),
                                    new TypeReference<Page<Article>>() {
                                    });
                    FeedsListFragment.this.page = data.getNumber();
                    FeedsListFragment.this.data = data.getContent();
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
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

    //ListView的单击事件
    void OnItemClickListener(int position) {
        Intent intent = new Intent(getActivity(), FeedContentActivity.class);
        Article article=data.get(position);

        int articleID=article.getId();
        String title=article.getTitle();
        String text=article.getText();
        String createTime=DateFormat.format("yyyy-MM-dd hh:mm", article.getCreateDate()).toString();
        String authorName=article.getAuthorName();
        String avatarUrl=article.getAuthorAvatar();

        intent.putExtra("articleID",articleID);
        intent.putExtra("title",title);
        intent.putExtra("text",text);
        intent.putExtra("createTime",createTime);
        intent.putExtra("authorName",authorName);
        intent.putExtra("avatarUrl",avatarUrl);

        startActivity(intent);
    }

    //创建ListView的适配器
    BaseAdapter listAdapter = new BaseAdapter() {

        public int getCount() {
            //return data == null ? 0 : data.length;
            return 10;
        }

        public Object getItem(int position) {
            return data.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                view = inflater.inflate(R.layout.list_view_item, null);
            } else {
                view = convertView;
            }
            //取出list布局文件里的控件
            TextView title = (TextView) view.findViewById(R.id.list_title);
            TextView text = (TextView) view.findViewById(R.id.list_txt);
            TextView author = (TextView) view.findViewById(R.id.list_author);
            TextView createTime = (TextView) view.findViewById(R.id.list_cteate_time);
            AvatarView avatar = (AvatarView) view.findViewById(R.id.list_avatar);

            //设置相应控件的值
            Article article = data.get(position);
            text.setText(article.getText());
            title.setText(article.getTitle());
            author.setText(article.getAuthorName());
            avatar.load(Server.serverAddress + article.getAuthorAvatar());
            String datastr= DateFormat.format("yyyy-MM-dd hh:mm", article.getCreateDate()).toString();
            createTime.setText(datastr);
            return view;
        }
    };

}

