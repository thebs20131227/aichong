package com.example.aichong2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aichong2.Bean.UserBean;
import com.example.aichong2.Util.DBUtils;
import com.example.aichong2.Util.SharedPreferencesUtil;


public class MainActivity extends AppCompatActivity {

    private EditText ed_name;
    private EditText ed_password;
    private Button bt_register;
    private Button bt_forget;
    private Button bt_login;
    private CheckBox isRememberPwd;
    UserBean user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_name = (EditText) findViewById(R.id.d_l_account);
        ed_password = (EditText) findViewById(R.id.d_l_pwd);
        bt_register = (Button) findViewById(R.id.d_l_register);
        bt_forget = (Button) findViewById(R.id.d_l_forget);
        bt_login = (Button) findViewById(R.id.d_l_login);
        isRememberPwd = (CheckBox) findViewById(R.id.login_remember);



        // 自动填充
        SharedPreferencesUtil spu = new SharedPreferencesUtil(this);
        Boolean isRemember = (Boolean) spu.getParam("isRememberPwd",false);
        // SharedPreference获取用户账号密码，存在则填充
        String name = (String) spu.getParam("name","");
        String pwd = (String)spu.getParam("pwd","");
        if(!name.equals("") && !pwd.equals("")) {
            if (isRemember) {
                ed_name.setText(name);
                ed_password.setText(pwd);
                isRememberPwd.setChecked(true);
            }
        }


        // 跳转到忘记密码界面
        bt_forget.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });

        // 跳转到注册界面
        bt_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        bt_login.setOnClickListener(new LoginListener());
    }

    class LoginListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            final String name = ed_name.getText().toString();
            final String password = ed_password.getText().toString();
            OptionHandle(name, password);// 处理自动登录及记住密码
            if (name.equals("") || password.equals("")) {
                new AlertDialog.Builder(MainActivity.this).setTitle("错误")
                        .setMessage("帐号或密码不能空").setPositiveButton("确定", null)
                        .show();
            } else {
                new Thread(new Runnable() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void run() {
                         user = DBUtils.login("SELECT * FROM `user` WHERE U_name='"+name+"' AND U_password='"+password+"'");
//                        System.out.println("SELECT * FROM `user` WHERE U_name='"+name+"' AND U_password='"+password+"'");
                        if (user == null) {
//                            System.out.println("没有找到啊啊啊啊啊啊啊啊啊");
                            Looper.prepare();
                            Toast.makeText(getApplicationContext(), "错误！请输入正确的账号或密码", (int) 0.5).show();
                            Looper.loop();
                            Looper.myLooper().quit();
                        }else {
                            Looper.prepare();
                            Toast.makeText(getApplicationContext(), "登录成功！欢迎您！", 1).show();
                            try {
                                Thread.sleep(1500);
                                Intent intent =new Intent(MainActivity.this,Foster_care.class);
                                startActivity(intent);
                                Looper.loop();
                                Looper.myLooper().quit();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    void OptionHandle(String name,String pwd){
        SharedPreferences.Editor editor = getSharedPreferences("UserData",MODE_PRIVATE).edit();
        SharedPreferencesUtil spu = new SharedPreferencesUtil(this);
        if(isRememberPwd.isChecked()){
            editor.putBoolean("isRememberPwd",true);
            // 保存账号密码
            spu.setParam("name",name);
            spu.setParam("pwd",pwd);
        }else{
            editor.putBoolean("isRememberPwd",false);
        }

        editor.apply();
    }
}

