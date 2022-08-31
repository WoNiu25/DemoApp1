package com.example.ycxy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a1031.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //声明控件
    private Button mBtnLogin;
    private EditText mEtUser;
    private EditText mEtPassword;
    private boolean hide =true;   //设置为隐藏
    private ImageView eye;  //定义控件



    //是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useThemestatusBarColor = false;
    //是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
    protected boolean useStatusBarColor = true;
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            ((View) decorView).setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colortheme));//设置状态栏背景色
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            Toast.makeText(this, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBar();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //找到控件
        mBtnLogin = findViewById(R.id.login);
        mEtUser = findViewById(R.id.edit_userName);
        mEtPassword = findViewById(R.id.edit_password);

        //密码的可见与不可见
        eye = findViewById(R.id.eye);
        eye.setImageResource(R.drawable.mm2);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.eye:
                        if (hide == true){
                            eye.setImageResource(R.drawable.mm1);  //可见样貌
                            HideReturnsTransformationMethod method = HideReturnsTransformationMethod.getInstance(); //可见
                            mEtPassword.setTransformationMethod(method);
                            hide = false;
                        }else{
                            eye.setImageResource(R.drawable.mm2);  //不可见样貌
                            TransformationMethod method = PasswordTransformationMethod.getInstance();  //隐藏
                            mEtPassword.setTransformationMethod(method);
                            hide = true;
                        }
                        int index = mEtPassword.getText().toString().length();
                        mEtPassword.setSelection(index);
                        break;
                }
            }
        });

        /*//实现直接跳转，方法1
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(MainActivity.this,FunctionActivity.class);
                startActivity(intent);
            }
        });*/

        //匹配对应的用户名和密码才能跳转

        mBtnLogin.setOnClickListener(this);
    }

    public void onClick(View v){
        //需要获取输入的用户名和密码
        String username = mEtUser.getText().toString();
        String password = mEtPassword.getText().toString();
        Intent intent = null;

        //假设正确的账号和密码分别是lfh,123456
        if(username.equals("ycxy")&&password.equals("123")){
            //如果正确的话进行跳转
            intent = new Intent(MainActivity.this,FunctionActivity.class);
            startActivity(intent);
        }else{
            //不正确
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
