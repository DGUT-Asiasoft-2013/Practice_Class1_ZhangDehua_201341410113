package com.example.z.helloworld;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.z.helloworld.api.Server;
import com.example.z.helloworld.api.User;
import com.example.z.helloworld.fragments.VersionFragment.SimpleTextInputCellFragment;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Z on 2016/12/4.
 */

public class LoginActivity extends Activity {
    SimpleTextInputCellFragment loginName;
    SimpleTextInputCellFragment loginPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginName=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.frag_login_name);
        loginPwd=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.frag_login_pwd) ;

        //login
        findViewById(R.id.login_btn_signIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        //signUp
        findViewById(R.id.login_btn_signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
        findViewById(R.id.login_textview_forget_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgetPwd();
            }
        });
    }
    //点击注册按钮触发的事件
    private void Register() {
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);

    }
    //点击登录按钮触发的事件
    void Login(){
        OkHttpClient client= Server.getSharedClient();
        MultipartBody requestBody=new MultipartBody.Builder()
                .addFormDataPart("account",loginName.getEditText())
                .addFormDataPart("passwordHash",MD5.getMD5(loginPwd.getEditText()))
                .build();
        Request request=Server.requestBuilderWithApi("login")
                .method("post",null)
                .post(requestBody)
                .build();
        final ProgressDialog dlg=new ProgressDialog(this);
        dlg.setCancelable(false);
        dlg.setCanceledOnTouchOutside(false);
        dlg.setMessage("正在登陆");
        dlg.show();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dlg.dismiss();
                        Toast.makeText(LoginActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseString = response.body().string();
                ObjectMapper mapper = new ObjectMapper();

                final User user = mapper.readValue(responseString, User.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dlg.setMessage("Hello\n"+responseString);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        dlg.setCancelable(true);
                        //finish();
                        Intent intent = new Intent(LoginActivity.this, HelloWorldActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });



    }
    //点击忘记密码触发的事件
    void ForgetPwd(){
        Intent intent=new Intent(this,ForgetPwdActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loginName.setLabelText("用户名：");
        loginName.setEditHint("请输入用户名");
        loginPwd.setLabelText("密  码：");
        loginPwd.setEditHint("请输入密码");
        loginPwd.setEditIsPwd(true);

        loginName.setEdiText("p");
        loginPwd.setEdiText("p");
    }
}
