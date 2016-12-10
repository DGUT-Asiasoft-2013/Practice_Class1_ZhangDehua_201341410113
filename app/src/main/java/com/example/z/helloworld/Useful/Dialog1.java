package com.example.z.helloworld.Useful;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;


/**
 * Created by Z on 2016/12/9.
 */

public class Dialog1 {
    public  static void alertDialog(String title, String message, final Context context) {
        Dialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
        dialog.show();
    }
}


