package com.example.z.helloworld.fragments.VersionFragment.pages;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.example.z.helloworld.api.Server;
import com.example.z.helloworld.api.User;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Z on 2016/12/12.
 */

public class AvatarView extends View {

    public AvatarView(Context context) {
        super(context);
    }

    public AvatarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    Paint paint;
    float radius;
    float srcWidth, srcHeight;
    Handler mainThreadHandler=new Handler();
    public  void setBitmap(Bitmap bmp){
        if(bmp==null) {
            paint = new Paint();
            paint.setColor(Color.GRAY);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(1);
            paint.setPathEffect(new DashPathEffect(new float[]{5, 10, 15, 20}, 0));
            paint.setAntiAlias(true);
        }else{
            paint = new Paint();
            paint.setShader(new BitmapShader(bmp, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
            paint.setAntiAlias(true);

            srcWidth = bmp.getWidth();
            srcHeight = bmp.getHeight();
        }

        invalidate();

    }
    public void load(User user){
        load(Server.serverAddress + user.getAvatar());
    }
    public void load(String url){
        OkHttpClient client = Server.getSharedClient();
        Request request =new Request.Builder()
                .url(url)
                .method("get", null)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call arg0, Response arg1) throws IOException {
                try{
                    byte[] bytes = arg1.body().bytes();
                    final Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    mainThreadHandler.post(new Runnable() {
                        public void run() {
                            setBitmap(bmp);
                        }
                    });
                }catch(Exception ex){
                    Toast.makeText(getContext(),ex.getLocalizedMessage().toString(),Toast.LENGTH_SHORT);
                }
            }
            @Override
            public void onFailure(Call arg0, IOException arg1) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(paint!=null){
            canvas.save();

            float dstWidth = getWidth();
            float dstHeight = getHeight();

            float scaleX = srcWidth / dstWidth;
            float scaleY = srcHeight / dstHeight;

            canvas.scale(1/scaleX, 1/scaleY);

            canvas.drawCircle(srcWidth/2, srcHeight/2, Math.min(srcWidth, srcHeight)/2, paint);
            canvas.restore();
        }
    }
}
