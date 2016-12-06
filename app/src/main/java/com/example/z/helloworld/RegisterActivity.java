package com.example.z.helloworld;

import android.app.Activity;
import android.os.Bundle;

import com.example.z.helloworld.fragments.VersionFragment.SimpleTextInputCellFragment;

/**
 * Created by Z on 2016/12/5.
 */

public class RegisterActivity extends Activity {

    SimpleTextInputCellFragment simpleTextInputCellFragmentAccount;
    SimpleTextInputCellFragment simpleTextInputCellFragmentPwd;
    SimpleTextInputCellFragment simpleTextInputCellFragmentPwdAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        simpleTextInputCellFragmentAccount=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_account);
        simpleTextInputCellFragmentPwd=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_pwd);
        simpleTextInputCellFragmentPwdAgain=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_pwdagain);
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
    }
}
