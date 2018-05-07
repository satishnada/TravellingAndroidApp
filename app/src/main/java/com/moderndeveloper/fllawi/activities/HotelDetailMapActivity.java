package com.moderndeveloper.fllawi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.moderndeveloper.BaseActivity;
import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.model.CommonRequestResponse;
import com.moderndeveloper.fllawi.retrofit.WebServiceCaller;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.moderndeveloper.fllawi.utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HotelDetailMapActivity extends BaseActivity implements OnMapReadyCallback {

    private View view;
    private String latitudeStr = "";
    private String longitudeStr = "";
    private String mapLocation = "";
    private GoogleMap googleMap;

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_hotel_map_location, llContainer);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            latitudeStr = bundle.getString(AppConstant.EXT_LATITUDE);
            longitudeStr = bundle.getString(AppConstant.EXT_LONGITUDE);
            mapLocation = bundle.getString(AppConstant.EXT_MAP_LOCATION);
        }

    }

    @Override
    public void initialization() {
        tvTopTitle.setText(R.string.map);
        ivTopBack.setImageResource(R.drawable.back_arrow);
        ivTopBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivTopBack:
                onBackPressed();
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (latitudeStr != null && latitudeStr.length() > 0 && longitudeStr != null && longitudeStr.length() > 0) {
            long latitude = Long.parseLong(latitudeStr);
            long longitude = Long.parseLong(longitudeStr);

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .title(mapLocation).icon(BitmapDescriptorFactory.fromResource(R.drawable.app_icon)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        goPrevious();
    }
}
