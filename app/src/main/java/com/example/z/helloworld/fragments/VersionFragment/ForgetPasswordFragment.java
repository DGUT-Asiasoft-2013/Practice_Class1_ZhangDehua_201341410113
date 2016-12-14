package com.example.z.helloworld.fragments.VersionFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.z.helloworld.R;

/**
 * Created by Z on 2016/12/6.
 */
//
public class ForgetPasswordFragment extends Fragment {

    SimpleTextInputCellFragment step1;
    Button btn_submit;

    TextView label;
    EditText edi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.fragment_forget_password_step1,null);
            step1=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.step1_frag);
            btn_submit=(Button)view.findViewById(R.id.step1_btn_submit);
            //label=(TextView)
            return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        try{
            step1.setEditHint("输入邮箱");
        }catch (Exception e){

        }

    }
}
