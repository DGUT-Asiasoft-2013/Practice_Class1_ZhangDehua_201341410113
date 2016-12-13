package com.example.z.helloworld;

import android.app.Activity;
import android.os.Bundle;

import com.example.z.helloworld.fragments.VersionFragment.ForgetPasswordFragment;
import com.example.z.helloworld.fragments.VersionFragment.SimpleTextInputCellFragment;

/**
 * Created by Z on 2016/12/6.
 */

public class ForgetPwdActivity extends Activity {
    SimpleTextInputCellFragment step1;
    ForgetPasswordFragment step2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        step1=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.step1_frag_email);
        step2=(ForgetPasswordFragment)getFragmentManager().findFragmentById(R.id.step1_frag_email) ;
    }

    @Override
    protected void onResume() {
        super.onResume();
        step1.setEditHint("请输入邮箱");
        step1.setLabelText("邮箱：");
    }
}
