package com.example.z.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Z on 2016/12/7.
 */

public class SendNewsActivity extends Activity {
    Button btn_cancel;
    Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_news);
        btn_cancel=(Button)findViewById(R.id.activity_send_btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancel();
            }
        });
        btn_send=(Button)findViewById(R.id.activity_send_btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Send();
            }
        });
    }
    //取消操作
    void Cancel(){
        finish();
        //overridePendingTransition();
    }
    //发送按钮操作
    void Send(){

    }
}
