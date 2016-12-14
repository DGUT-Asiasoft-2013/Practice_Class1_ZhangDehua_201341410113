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
    ForgetPasswordFragment step11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
