package com.example.z.helloworld.fragments.VersionFragment.pages;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.Random;

/**
 * Created by Z on 2016/12/7.
 */

public class FeedsListFragment extends Fragment {
    View view;
    ListView listView;
    String[] data;
    Random random = new Random();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_page_feeds, null);
            data = new String[random.nextInt(20)];//生成随机个（0到100之间）数组成员
            Toast.makeText(getActivity(), "rrr", Toast.LENGTH_LONG);
            for (int i = 0; i < data.length; i++) {
                data[i] = "listItem" + random.nextInt(100);
            }
            listView = (ListView) view.findViewById(R.id.list);
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

    //ListView的单击事件
    void OnItemClickListener(int position) {
        String text = (String) listAdapter.getItem(position);
        //String text=data[position];
        Intent intent = new Intent(getActivity(), FeedContentActivity.class);
        intent.putExtra("text", text);
        startActivity(intent);
    }

    //创建ListView的适配器
    BaseAdapter listAdapter = new BaseAdapter() {

        public int getCount() {
            return data == null ? 0 : data.length;
            //return 20;
        }

        public Object getItem(int position) {
            return data[position];
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
            TextView text1 = (TextView) view.findViewById(R.id.list_txt);
            text1.setText(data[position]);
            return view;
        }
    };

}
