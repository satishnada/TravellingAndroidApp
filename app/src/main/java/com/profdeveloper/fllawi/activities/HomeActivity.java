package com.profdeveloper.fllawi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.profdeveloper.BaseActivity;
import com.profdeveloper.fllawi.adapters.DestinationsListAdapter;
import com.profdeveloper.fllawi.adapters.HomeListAdapter;
import com.profdeveloper.fllawi.adapters.OffersAndPriceListAdapter;
import com.profdeveloper.fllawi.adapters.OurServicesListAdapter;
import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.PreferenceData;
import com.profdeveloper.fllawi.utils.Utility;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    private View view;
    private RecyclerView rvOurServices, rvTopDestination, rvTopOffers;
    private HomeListAdapter homeListAdapter;
    private OurServicesListAdapter ourServicesListAdapter;
    private DestinationsListAdapter destinationsListAdapter;
    private OffersAndPriceListAdapter offersAndPriceListAdapter;
    private ImageView ivTopHomeBack, ivTopHomeSearch;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_toolbar;
    private TextView tvEnglishTop;
    private ImageView ivTopMenu,ivTopSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_home, llContainer);
        mActivity = this;

        isHomeRunning = true;

        ivTopMenu = view.findViewById(R.id.ivTopMenu);
        ivTopSearch = view.findViewById(R.id.ivTopSearch);
        tvEnglishTop = view.findViewById(R.id.tvEnglishTop);
        tvEnglishTop.setOnClickListener(this);

        ivTopMenu.setOnClickListener(this);
        ivTopSearch.setOnClickListener(this);

        collapsing_toolbar = view.findViewById(R.id.collapsing_toolbar);
        toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(5);
        actionBar.setDisplayShowHomeEnabled(true);

        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        getSupportActionBar().setTitle("");

        rvOurServices = view.findViewById(R.id.rvOurServices);
        rvTopDestination = view.findViewById(R.id.rvTopDestination);
        rvTopOffers = view.findViewById(R.id.rvTopOffers);
        rvOurServices.setLayoutManager(Utility.getLayoutManagerHorizontal(mActivity));
        ourServicesListAdapter = new OurServicesListAdapter(mActivity);
        rvOurServices.setAdapter(ourServicesListAdapter);

        rvTopDestination.setLayoutManager(Utility.getLayoutManagerHorizontal(mActivity));
        ArrayList<String> topDestinations = new ArrayList<>();
        topDestinations.add("");
        topDestinations.add("");
        topDestinations.add("");
        topDestinations.add("");
        destinationsListAdapter = new DestinationsListAdapter(mActivity, topDestinations);
        rvTopDestination.setAdapter(destinationsListAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
        rvTopOffers.setLayoutManager(gridLayoutManager);
        ArrayList<String> topOffers = new ArrayList<>();
        topOffers.add(getString(R.string.offer_one));
        topOffers.add(getString(R.string.offer_two));
        topOffers.add(getString(R.string.offer_three));
        topOffers.add(getString(R.string.offer_five));
        offersAndPriceListAdapter = new OffersAndPriceListAdapter(mActivity, topOffers);
        rvTopOffers.setAdapter(offersAndPriceListAdapter);

       // ivTopHomeBack.setOnClickListener(this);
       // ivTopHomeSearch.setOnClickListener(this);
        setLanguage();
    }

    private void setLanguage(){
        if (PreferenceData.getUserLanguage() != null){
            if (PreferenceData.getUserLanguage().equalsIgnoreCase("ar")){
                changeLanguage("ar");
                tvEnglishTop.setText("Eng");
            }else{
                tvEnglishTop.setText("التسجيل");
                changeLanguage("en");
            }
        }else{
            tvEnglishTop.setText("التسجيل");
            changeLanguage("en");
        }
    }

    private void setLanguageOnClick(){
        if (PreferenceData.getUserLanguage() != null){
            if (PreferenceData.getUserLanguage().equalsIgnoreCase("ar")){
                changeLanguage("en");
                PreferenceData.setUserLang("en");
                tvEnglishTop.setText("التسجيل");
            }else{
                tvEnglishTop.setText("Eng");
                PreferenceData.setUserLang("ar");
                changeLanguage("ar");
            }
        }else{
            PreferenceData.setUserLang("en");
            tvEnglishTop.setText("التسجيل");
            changeLanguage("en");
        }
    }

 /*   private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.profile_pic);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {

            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(R.attr.colorPrimaryDark);
            }
        });
    }

    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }*/

    public void onOurServiceItemClick(int position){
        if (position == 0){ // Accommodation
            Intent searchIntent = new Intent(mActivity, SearchActivity.class);
            searchIntent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_ACCOMMODATION);
            startActivity(searchIntent);
            goNext();
        } else if (position == 1){ // Things to do
            Intent searchIntent = new Intent(mActivity, SearchActivity.class);
            searchIntent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_THING_TO_DO);
            startActivity(searchIntent);
            goNext();
        } else if (position == 2){ // Coupon
            Intent searchIntent = new Intent(mActivity, SearchActivity.class);
            searchIntent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_COUPON);
            startActivity(searchIntent);
            goNext();
        } else if (position == 3){ // Transportation
           /* Intent searchIntent = new Intent(mActivity, SearchActivity.class);
            searchIntent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_TRANSPORTATION);
            startActivity(searchIntent);
            goNext();*/
        } else if (position == 4){ // Event
         /*   Intent searchIntent = new Intent(mActivity, SearchActivity.class);
            searchIntent.putExtra(AppConstant.EXT_IS_FROM,AppConstant.IS_FROM_EVENT);
            startActivity(searchIntent);
            goNext();*/
        }
    }

    public void onItemClick() {
        Intent intent = new Intent(mActivity, HotelDetailsActivity.class);
        startActivity(intent);
        goNext();
    }

    @Override
    public void initialization() {
        hideTopBar();
        checkLocal();
    }

    @Override
    public void onClick(View v) {

       switch (v.getId()) {
           case R.id.tvEnglishTop:
               setLanguageOnClick();
               recreate();
               break;
            case R.id.ivTopMenu:
                closeSideMenu();
                break;
            case R.id.ivTopSearch:
                Intent searchIntent = new Intent(mActivity, SearchActivity.class);
                startActivity(searchIntent);
                goNext();
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(Gravity.START)) {
            drawer_layout.closeDrawer(Gravity.LEFT);
        } else {
            if (doubleBackToExitPressedOnce) {
                finish();
                goPrevious();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Utility.showError(getString(R.string.press_again_to_exit));
            //showSuccessSnackBar(getString(R.string.press_again_to_exit));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }
}
