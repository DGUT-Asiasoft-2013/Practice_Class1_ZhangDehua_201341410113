package com.example.z.helloworld.fragments.VersionFragment.pages;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.z.helloworld.R;
import com.example.z.helloworld.api.Server;
import com.example.z.helloworld.api.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Z on 2016/12/7.
 */

public class MeListFragment extends Fragment{
    View view;
    AvatarView avatarView;
    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(R.layout.fragment_page_me,null);
            avatarView=(AvatarView)view.findViewById(R.id.avatar);
            text=(TextView)view.findViewById(R.id.txet_me);

        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        OkHttpClient client=Server.getSharedClient();
        Request request=Server.requestBuilderWithApi("me")
                .method("get",null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                //Dialog1.alertDialog("","fa",MeListFragment.this.getActivity());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MeListFragment.this.onFailuer(call,e);
                    }
                });
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final  User user=new ObjectMapper().readValue(response.body().bytes(),User.class);
                //Dialog1.alertDialog("","",MeListFragment.this.getActivity());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MeListFragment.this.onResponse(call,user);
                    }
                });
            }
        });


    }

    private void onResponse(Call call ,User user) {

        text.setText("Hello! "+user.getName());
        avatarView.load(user);
    }

    private void onFailuer(Call call,Exception e) {
        text.setText(e.getLocalizedMessage().toString());
    }
}
