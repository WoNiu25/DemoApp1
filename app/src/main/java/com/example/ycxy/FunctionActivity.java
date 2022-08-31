package com.example.ycxy;


import android.annotation.SuppressLint;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a1031.R;

public class FunctionActivity extends Activity implements View.OnClickListener {

    private FameFragment firstFragment = null;// 用于显示第一个界面
    private FileFragment secondFragment = null;// 用于显示第二个界面
    private MeFragment thirdFragment = null;// 用于显示第三个界面

    private View firstLayout = null;// 第一个显示布局
    private View secondLayout = null;// 第二个显示布局
    private View thirdLayout = null;// 第三个显示布局

    private ImageView shouye_image = null;
    private ImageView yingyong_image = null;
    private ImageView wode_image = null;

    private TextView shouye_text = null;
    private TextView yingyong_text = null;
    private TextView wode_text = null;

    private FragmentManager fragmentManager = null;// 用于对Fragment进行管理
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//要求窗口没有title
        super.setContentView(R.layout.activity_function);
        // 初始化布局元素
        initViews();
        fragmentManager = getFragmentManager();//用于对Fragment进行管理
        // 设置默认的显示界面
        setTabSelection(0);


    }

    /**
     * 在这里面获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件
     */
    @SuppressLint("NewApi")
    public void initViews() {
        fragmentManager = getFragmentManager();
        firstLayout = findViewById(R.id.shouye_layout);
        secondLayout = findViewById(R.id.yingyong_layout);
        thirdLayout = findViewById(R.id.wode_layout);

        shouye_image = (ImageView) findViewById(R.id.shouye_image);
        yingyong_image = (ImageView) findViewById(R.id.yingyong_image);
        wode_image = (ImageView) findViewById(R.id.wode_image);

        shouye_text = (TextView) findViewById(R.id.shouye_text);
        yingyong_text = (TextView) findViewById(R.id.yingyong_text);
        wode_text = (TextView) findViewById(R.id.wode_text);


        //处理点击事件
        firstLayout.setOnClickListener(this);
        secondLayout.setOnClickListener(this);
        thirdLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shouye_layout:
                setTabSelection(0);// 当点击了我的主页tab时，选中第1个tab
                break;
            case R.id.yingyong_layout:
                setTabSelection(1);// 当点击了购物车tab时，选中第2个tab
                break;
            case R.id.wode_layout:
                setTabSelection(2);// 当点击了我的订单tab时，选中第3个tab
                break;
            default:
                break;
        }

    }

    /**
     * 根据传入的index参数来设置选中的tab页 每个tab页对应的下标。0表示主页，1表示支出，2表示收入，3表示设置
     */
    @SuppressLint("NewApi")
    private void setTabSelection(int index) {
        clearSelection();// 每次选中之前先清除掉上次的选中状态
        FragmentTransaction transaction = fragmentManager.beginTransaction();// 开启一个Fragment事务
        hideFragments(transaction);// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        switch (index) {
            case 0:
                // 当点击了我的主页tab时改变控件的图片和文字颜色
                shouye_image.setImageResource(R.drawable.shouyue);//修改布局中的图片
                shouye_text.setTextColor(Color.parseColor("#0090ff"));//修改字体颜色

                if (firstFragment == null) {
                    // 如果FirstFragment为空，则创建一个并添加到界面上
                    firstFragment = new FameFragment();
                    transaction.add(R.id.content, firstFragment);

                } else {
                    // 如果FirstFragment不为空，则直接将它显示出来

                    transaction.show(firstFragment);//显示的动作
                }

                break;
            // 以下和firstFragment类同
            case 1:
                yingyong_image.setImageResource(R.drawable.yingyong);
                yingyong_text.setTextColor(Color.parseColor("#0090ff"));
                if (secondFragment == null) {
                    secondFragment = new FileFragment();


                    transaction.add(R.id.content, secondFragment);
                } else {
                    transaction.show(secondFragment);
                }
                break;
            case 2:
                wode_image.setImageResource(R.drawable.wode);
                wode_text.setTextColor(Color.parseColor("#0090ff"));
                if (thirdFragment == null) {
                    thirdFragment = new MeFragment();
                    transaction.add(R.id.content, thirdFragment);
                } else {
                    transaction.show(thirdFragment);
                }
                break;
        }
        transaction.commit();

    }

    /**
     * 清除掉所有的选中状态
     */
    private void clearSelection() {
        shouye_image.setImageResource(R.drawable.shouye1);
        shouye_text.setTextColor(Color.parseColor("#82858b"));

        wode_image.setImageResource(R.drawable.yingyong1);
        wode_text.setTextColor(Color.parseColor("#82858b"));

        yingyong_image.setImageResource(R.drawable.wode1);
        yingyong_text.setTextColor(Color.parseColor("#82858b"));

       // setting_image.setImageResource(R.drawable.erweima);
        //setting_text.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都设置为隐藏状态 用于对Fragment执行操作的事务
     */
    @SuppressLint("NewApi")
    private void hideFragments(FragmentTransaction transaction) {
        if (firstFragment != null) {
            transaction.hide(firstFragment);
        }
        if (secondFragment != null) {
            transaction.hide(secondFragment);
        }
        if (thirdFragment != null) {
            transaction.hide(thirdFragment);
        }
    }

    //封装一个AlertDialog
    private void exitDialog() {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("温馨提示")
                .setMessage("您确定要退出程序吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                }).create();
        dialog.show();//显示对话框
    }

    /**
     * 返回菜单键监听事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//如果是返回按钮
            exitDialog();
        }
        return super.onKeyDown(keyCode, event);
    }



}