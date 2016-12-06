package com.example.z.helloworld.fragments.VersionFragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.z.helloworld.R;

/**
 * Created by Z on 2016/12/5.
 */

public class PictureInputcellFragment extends BaseInputcellMethod {
    ImageView imageView;
    TextView labelText;
    TextView hintText;
    final int REQUETCODE_CAMEA=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frament_inputcell_picture,container);
        //getFragmentManager().findFragmentById()
        labelText=(TextView)view.findViewById(R.id.text_pictureFragment);
        hintText=(TextView)view.findViewById(R.id.text_onthepicture);
        imageView=(ImageView) view.findViewById(R.id.img_picture);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnImagerViewClick();
            }
        });

        return view;
    }

    public void OnImagerViewClick(){
//        String [] items={"相册","拍照"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
//                .setTitle(labelText.getText())
//                //.setMessage(hintText.getText())
//                .setItems(items, new OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                })
//                .show();
    }

    public void TakePhoto(){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUETCODE_CAMEA);
    }
    void picFromAlum(){

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_CANCELED) return;
        if(requestCode==REQUETCODE_CAMEA){
            Bitmap bmp=(Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bmp);

        }
    }

    @Override
    void setLabelText(String text) {

    }

    @Override
    void setEditHint(String text) {

    }
}
