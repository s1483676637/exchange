package com.bs.exchange.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bmob.exchange.R;
import com.bs.exchange.bean.UserBean;
import com.bs.exchange.ui.activity.AddressListActivity;
import com.bs.exchange.ui.activity.LoginActivity;
import com.bs.exchange.ui.activity.UserInfoActivity;
import com.bs.exchange.ui.activity.WorkListActivity;

import cn.bmob.v3.BmobUser;


public class SettingFragment extends Fragment implements View.OnClickListener {
    private FragmentActivity context;
    private LinearLayout ll_logout;
    private LinearLayout ll_person;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_set, container,
                false);
        context = getActivity();
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView tv_id = view.findViewById(R.id.tv_id);
        tv_id.setText("ID:"+BmobUser.getCurrentUser(UserBean.class).getUsername());
        ll_logout = view.findViewById(R.id.ll_logout);
        ll_person = view.findViewById(R.id.ll_person);
        ll_person.setOnClickListener(this);
        ll_logout.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_logout:
                new AlertDialog.Builder(getActivity())
                        .setTitle("TIPS")
                        .setMessage("Are you sure you want to log out?")
                        .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                BmobUser.logOut();
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                                getActivity().finish();

                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();

                break;
            case R.id.ll_person:
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
        }
    }
}
