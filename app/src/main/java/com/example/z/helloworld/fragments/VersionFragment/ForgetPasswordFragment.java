package com.example.z.helloworld.fragments.VersionFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.z.helloworld.R;

/**
 * Created by Z on 2016/12/6.
 */
//
public class ForgetPasswordFragment extends Fragment {

    SimpleTextInputCellFragment step1;
    TextView label;
    EditText edi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.fragment_forget_password_step1,container);
            step1=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.step1_frag_email);
            step1.setLabelText("邮箱：");
            step1.setEditHint("请输入邮箱地址");
            //label=(TextView)
            return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}