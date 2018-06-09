package com.profdeveloper.fllawi.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.adapters.MenuListAdapter;
import com.profdeveloper.fllawi.utils.PreferenceData;
import com.profdeveloper.fllawi.utils.SharedPreferenceUtil;
import com.profdeveloper.fllawi.utils.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SideMenuFragment extends Fragment {

    private LinearLayout llUserProfile;
    private RecyclerView rvMenuList;
    private MenuListAdapter menuListAdapter;
    private ImageView ivProfilePic;
    private TextView ivUserName;

    public SideMenuFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_side_menu, container, false);

        llUserProfile = view.findViewById(R.id.llUserProfile);

        rvMenuList = view.findViewById(R.id.rvMenuList);
        rvMenuList.setLayoutManager(Utility.getLayoutManager(getActivity()));
        menuListAdapter = new MenuListAdapter(getActivity());
        rvMenuList.setAdapter(menuListAdapter);

        ivUserName = view.findViewById(R.id.ivUserName);
        ivProfilePic = view.findViewById(R.id.ivProfilePic);

        if (!PreferenceData.isLogin()){
            ivUserName.setText(getString(R.string.app_name));
        }else{
            String userName = PreferenceData.getUserName();
            String userProfilePic = PreferenceData.getUserProfilePic();
            if (userName != null){
                ivUserName.setText(userName);
            }

        /*if (userProfilePic != null){
            ImageLoader.getInstance().displayImage(userProfilePic,ivProfilePic);
        }*/

        /*llUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onClickUserProfile();
            }
        });*/
        }

        return view;
    }

}
