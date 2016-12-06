package com.example.z.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.z.helloworld.fragments.VersionFragment.SimpleTextInputCellFragment;


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
        loginName=(SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.frag_login_name);
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

    private void Register() {
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);

    }
    void Login(){
        Intent intent=new Intent(this,HelloWorldActivity.class);
        startActivity(intent);
    }
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
    }
}
