package com.example.z.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.z.helloworld.Useful.Dialog1;
import com.example.z.helloworld.fragments.VersionFragment.SimpleTextInputCellFragment;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Z on 2016/12/5.
 */

public class RegisterActivity extends Activity {
    SimpleTextInputCellFragment simpleTextInputCellFragmentAccount;
    SimpleTextInputCellFragment simpleTextInputCellFragmentPwd;
    SimpleTextInputCellFragment simpleTextInputCellFragmentPwdAgain;
    SimpleTextInputCellFragment simpleTextInputCellFragmentEmail;

    String account;
    String pwd;
    String pwdAgain;
    String email;

    Button btn_sumit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        simpleTextInputCellFragmentAccount = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_account);
        simpleTextInputCellFragmentPwd = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_pwd);
        simpleTextInputCellFragmentPwdAgain = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_pwdagain);
        simpleTextInputCellFragmentEmail = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_email);

    }


    @Override
    protected void onResume() {
        super.onResume();
        simpleTextInputCellFragmentAccount.setLabelText("用户名：");
        simpleTextInputCellFragmentAccount.setEditHint("请输入登录用户名");

        simpleTextInputCellFragmentPwd.setLabelText("密  码：");
        simpleTextInputCellFragmentPwd.setEditHint("请输入密码");
        simpleTextInputCellFragmentPwd.setEditIsPwd(true);

        simpleTextInputCellFragmentPwdAgain.setLabelText("密  码：");
        simpleTextInputCellFragmentPwdAgain.setEditHint("请重新输入密码");
        simpleTextInputCellFragmentPwdAgain.setEditIsPwd(true);

        simpleTextInputCellFragmentEmail.setLabelText("邮箱：");
        simpleTextInputCellFragmentEmail.setEditHint("请输入邮箱");

        btn_sumit = (Button) findViewById(R.id.register_btn);
        btn_sumit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    private void Register() {
        account = simpleTextInputCellFragmentAccount.getEditText();
        pwd = simpleTextInputCellFragmentPwd.getEditText();
        pwdAgain = simpleTextInputCellFragmentPwdAgain.getEditText();
        email = simpleTextInputCellFragmentEmail.getEditText();
        if (pwd != null && pwd.contentEquals(pwdAgain)) {
            pwd = MD5.getMD5(pwd);
            String text = account + "|" + pwd + "|" + pwdAgain + "|" + email;
            Dialog1.alertDialog("", text, this);
            SubmitRegister();
        } else {
            Dialog1.alertDialog("", "密码为空或两次输入的密码不一致", this);
        }
    }


    private void SubmitRegister() {

        OkHttpClient client = new OkHttpClient.Builder().build();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("account",account)
                .addFormDataPart("name",account)
                .addFormDataPart("email",email)
                .addFormDataPart("passwordHash",pwd)
                .build();
        Request request = new Request.Builder()
                .url("http://169.254.80.80:8080/membercenter/api/register")
                .method("post", null)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Dialog1.alertDialog("","失败",RegisterActivity.this);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Dialog1.alertDialog("","成功",RegisterActivity.this);
            }
        });

    }
}
