package com.example.z.helloworld.fragments.VersionFragment;

import android.app.Fragment;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.z.helloworld.R;
/**
 * Created by Z on 2016/12/3.
 */

public class VersionFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.fragment_version,null);
        TextView textVersion=(TextView)view.findViewById(R.id.textVersion);
        PackageManager pkgm=this.getActivity().getPackageManager();
        try {
            PackageInfo appinfo=pkgm.getPackageInfo(getActivity().getPackageName(),0);
            textVersion.setText(appinfo.packageName+" "+appinfo.versionName);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            textVersion.setText("EROR");
        }
        return view;
    }

}
