package com.example.z.helloworld.fragments.VersionFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.z.helloworld.R;

/**
 * Created by Z on 2016/12/5.
 */

public class SimpleTextInputCellFragment extends BaseInputcellMethod {
    @Nullable

    TextView label;
    EditText edit;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_inputcell_simpletext,container);
        label=(TextView)view.findViewById(R.id.text_simple);
        edit=(EditText)view.findViewById(R.id.edit_simple);
        return view;
    }

    public void setLabelText(String text){
        label.setText(text);
    }
    public void setEditHint(String text){
        edit.setHint(text);
    }
    public void setEditIsPwd(boolean pwd){
        if (pwd=false){
            edit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            // 使光标始终在最后位置
            Editable etable = edit.getText();
            Selection.setSelection(etable, etable.length());
        }else {
            // 显示为密码
            edit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            // 使光标始终在最后位置
            Editable etable = edit.getText();
            Selection.setSelection(etable, etable.length());
        }
        }
    public String  getEditText(){
        return edit.getText().toString();
    }
}
