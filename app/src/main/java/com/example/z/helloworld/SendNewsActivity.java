package com.example.z.helloworld;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.z.helloworld.Useful.Dialog1;
import com.example.z.helloworld.api.Server;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Z on 2016/12/7.
 */

public class SendNewsActivity extends Activity {
    Button btn_cancel;
    Button btn_send;
    EditText edi_title;
    EditText edi_body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_news);
        btn_cancel=(Button)findViewById(R.id.news_btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancel();
            }
        });
        btn_send=(Button)findViewById(R.id.news_btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Send();
            }
        });
        edi_title=(EditText)findViewById(R.id.news_edi_title);
        edi_body=(EditText)findViewById(R.id.news_edi_body);
    }
    //取消操作
    void Cancel(){
        finish();
        //overridePendingTransition();
    }
    //发送按钮操作
    void Send(){
        String title=edi_title.getText().toString();
        String body=edi_body.getText().toString();
        if (title!=null && body!=null){
            final ProgressDialog dlg= new ProgressDialog(this);
            //dlg.setCancelable(false);
            dlg.setCanceledOnTouchOutside(false);
            dlg.setMessage("正在发送");
            dlg.show();
            OkHttpClient client= Server.getSharedClient();
            MultipartBody requestBody=new MultipartBody.Builder()
                    .addFormDataPart("title",title)
                    .addFormDataPart("text",body)
                    .build();
            Request request=Server.requestBuilderWithApi("article")
                    .method("post",null)
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    SendNewsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dlg.dismiss();
                            Toast.makeText(SendNewsActivity.this,"失败",Toast.LENGTH_SHORT);
                        }
                    });

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    SendNewsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dlg.dismiss();
                            Toast.makeText(SendNewsActivity.this,"成功",Toast.LENGTH_SHORT);
                            finish();
                        }
                    });

                }
            });
        }else {
            Dialog1.alertDialog("","不能为空",this);
        }


    }
}
