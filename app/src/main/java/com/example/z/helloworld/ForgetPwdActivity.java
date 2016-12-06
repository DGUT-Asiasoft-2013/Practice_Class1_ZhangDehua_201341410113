package com.example.z.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.z.helloworld.fragments.VersionFragment.ForgetPasswordFragment;
import com.example.z.helloworld.fragments.VersionFragment.SimpleTextInputCellFragment;

/**
 * Created by Z on 2016/12/6.
 */

public class ForgetPwdActivity extends Activity {
    ForgetPasswordFragment step1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        step1=(ForgetPasswordFragment)getFragmentManager().findFragmentById(R.id.step1_frag_email) ;

    }
    void submit(){

    }
}
