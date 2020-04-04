package com.bs.exchange.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.bmob.exchange.R;
import com.bs.exchange.base.BaseActivity;
import com.bs.exchange.bean.UserBean;
import com.bs.exchange.utils.SharedPreferencesUtils;
import com.bs.exchange.utils.StatusBarUtil;

//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    EditText etName;
    EditText etPwd;
    TextView btLogin;
    Button btnRegister;

    private final String USER_NAME = "user_name";
    private final String PWD = "pwd";

    private LoginActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        StatusBarUtil.setTranslucent(context, 0);

        initView();

        String username = SharedPreferencesUtils.getString(this, USER_NAME, "");
        String pwd = SharedPreferencesUtils.getString(this, PWD, "");
        etName.setText(username);
        etPwd.setText(pwd);
    }

    private void initView() {
        etName = findViewById(R.id.et_name);
        etPwd = findViewById(R.id.et_pwd);
        btLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        btLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                final String name = etName.getText().toString();
                final String password = etPwd.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(context, "Please enter the username!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(context, "Please enter the password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                showProgressDialog(context, "Login...");
                UserBean bu2 = new UserBean();
                bu2.setUsername(name);
                bu2.setPassword(password);
                bu2.login(new SaveListener<UserBean>() {

                    @Override
                    public void done(UserBean bmobUser, BmobException e) {
                        hidProgressDialog();
                        if (e == null) {
                            Toast.makeText(context, "Login success!", Toast.LENGTH_SHORT).show();
                            onOpen(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();

                        } else {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                break;
            case R.id.btn_register:
                onOpen(new Intent(context, RegisterActivity.class));
                break;

        }
    }

}
