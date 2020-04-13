package com.example.aichong2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aichong2.Bean.UserBean;
import com.example.aichong2.Util.DBUtils;

public class RegisterActivity extends AppCompatActivity {

    private EditText re_name;
    private EditText re_password;
    private EditText re_confirm;

    private Button bt_register;

    UserBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        re_name = (EditText) findViewById(R.id.register_account);
        re_password = (EditText) findViewById(R.id.register_pwd);
        re_confirm = (EditText) findViewById(R.id.register_pwd_confirm);
        bt_register = (Button) findViewById(R.id.register_confirm);


        bt_register.setOnClickListener(new RegisterActivity.LoginListener());
    }

    class LoginListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            final String name = re_name.getText().toString();
            final String password = re_password.getText().toString();
            final String confirm = re_confirm.getText().toString();

            if (name.equals("") || password.equals("")||confirm.equals("")) {
                // 弹出消息框
                new AlertDialog.Builder(RegisterActivity.this).setTitle("错误")
                        .setMessage("帐号或密码不能空").setPositiveButton("确定", null)
                        .show();
            } else if(!password.equals(confirm)){
                new AlertDialog.Builder(RegisterActivity.this).setTitle("错误")
                        .setMessage("两次输入的密码不同，请确认后重新输入").setPositiveButton("确定", null)
                        .show();
            }else{



                new Thread(new Runnable() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void run() {
                        String res = DBUtils.register("SELECT * FROM `user` WHERE U_name='"+name+"'","INSERT INTO `user` (`U_password`, `U_name`) VALUES ('"+password+"', '"+name+"')",name);
//                        System.out.println("SELECT * FROM `user` WHERE U_name='"+name+"' AND U_password='"+password+"'");
                        if (res == "failed2") {
//                            System.out.println("没有找到啊啊啊啊啊啊啊啊啊");

                            Looper.prepare();
                            Toast.makeText(getApplicationContext(), "错误!用户名重复，请更改后重试！", (int) 0.5).show();
                            Looper.loop();
                            Looper.myLooper().quit();


                        }else if(res == "success"){
                            Looper.prepare();
                            Toast.makeText(getApplicationContext(), "注册成功！欢迎您！请登录！", 1).show();
                            try {
                                Thread.sleep(1500);
                                Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(intent);
                                Looper.loop();
                                Looper.myLooper().quit();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Looper.prepare();
                            Toast.makeText(getApplicationContext(), "错误!系统故障！", (int) 0.5).show();
                            Looper.loop();
                            Looper.myLooper().quit();

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
}
