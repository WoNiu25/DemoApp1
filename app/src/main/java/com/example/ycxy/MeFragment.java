package com.example.ycxy;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.a1031.R;
import com.example.ycxy.three.Shezhi_Activity;
import com.example.ycxy.three.Xiazaidizhi_Activity;
import com.example.ycxy.three.Xiazaiwenjianguanli_Activity;
import com.example.ycxy.three.Xiugaimima_Activity;
import com.example.ycxy.three.Yijianfankui_Activity;


public class MeFragment extends Fragment{
LinearLayout xiugaimima_layout;
    LinearLayout xiazaidizhi_layout;
LinearLayout xiazaiwenjianguanli_layout;
LinearLayout shezhi_layout;
LinearLayout exit_layout;
LinearLayout yijianfankui_layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_me, container, false);

        xiugaimima_layout= view.findViewById(R.id.xiugaimima_layout);
        xiazaidizhi_layout=view.findViewById(R.id.xiazaidizhi_layout);
        xiazaiwenjianguanli_layout=view.findViewById(R.id.xiazaiwenjianguanli_layout);
        shezhi_layout=view.findViewById(R.id.shezhi_layout);
        exit_layout=view.findViewById(R.id.exit_layout);
        yijianfankui_layout=view.findViewById(R.id.yijianfankui_layout);
        xiugaimima_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Xiugaimima_Activity.class);
                startActivity(intent);
            }
        });
        xiazaidizhi_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Xiazaidizhi_Activity.class);
                startActivity(intent);
            }
        });
        xiazaiwenjianguanli_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Xiazaiwenjianguanli_Activity.class);
                startActivity(intent);
            }
        });
        shezhi_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Shezhi_Activity.class);
                startActivity(intent);
            }
        });
        yijianfankui_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), Yijianfankui_Activity.class);
                startActivity(intent);
            }
        });
        exit_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setMessage("确定要退出登录吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getActivity(),"已退出登录",Toast.LENGTH_SHORT).show();
                    }
                })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        }).create();
                builder.setCancelable(true);//设置按钮是否可以按返回键退出
                AlertDialog dialog=builder.create();
                dialog.setCanceledOnTouchOutside(true);//设置弹窗框失去焦点是否隐藏，即点击其他地方是否隐藏
                dialog.show();
            }
        });

        return view;
    }




}