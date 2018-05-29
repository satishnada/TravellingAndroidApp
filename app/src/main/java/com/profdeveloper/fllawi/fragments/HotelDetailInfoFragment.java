package com.profdeveloper.fllawi.fragments;

import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.profdeveloper.fllawi.R;
import com.profdeveloper.fllawi.activities.HotelDetailsActivity;
import com.profdeveloper.fllawi.adapters.AllowedListAdapter;
import com.profdeveloper.fllawi.adapters.FacilitiesListAdapter;
import com.profdeveloper.fllawi.adapters.HotelReviewsListAdapter;
import com.profdeveloper.fllawi.adapters.IncludeListAdapter;
import com.profdeveloper.fllawi.adapters.NotAllowedListAdapter;
import com.profdeveloper.fllawi.adapters.NotIncludeListAdapter;
import com.profdeveloper.fllawi.adapters.RequireListAdapter;
import com.profdeveloper.fllawi.model.AccommodationDetails.Amenity;
import com.profdeveloper.fllawi.model.AccommodationDetails.Data;
import com.profdeveloper.fllawi.utils.AppConstant;
import com.profdeveloper.fllawi.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class HotelDetailInfoFragment extends Fragment {

    private RecyclerView rvRoomFacilities, rvAllowed, rvNotAllowed,
            rvInclude, rvNotInclude, rvRequirement;
    private GridLayoutManager gridLayoutManager;
    private FacilitiesListAdapter facilitiesListAdapter;
    private LinearLayout llContinueBooking;
    private Data accommodationDetail;
    private com.profdeveloper.fllawi.model.couponDetails.Data couponDetail;
    private com.profdeveloper.fllawi.model.ThingToDoDetails.Data thingToDoDetail;
    private ArrayList<Amenity> amenitiesList = new ArrayList<>();
    private List<String> allowList = new ArrayList<>();
    private TextView tvDescriptionText, tvPrice,tvCheckInOutTime, tvCheckInOutTimeDetail,
            tvCancellationPolicyDetail, tvNoDataFoundFacility,
            tvNoDataFoundAllowed, tvNoDataFoundNotAllowed, tvNoDataFoundInclude,
            tvNoDataFoundNotInclude, tvNoDataFoundRequirement,tvWhyWithUsDetail,tvWhyWithUs,
            tvAdditionalInfoDetails,tvOtherRuleDetails;
    private LinearLayout llCheckInOutTime, llCancellationPolicy,
            llAllowed, llNotAllowed, llInclude, llNotInclude, llRequirement;
    private String pricePerNight = "";
    private int isFrom = 1;
    private LinearLayout llRoomsAndFacility,llWhyWithUs,llAdditionalInfo,llOtherRule;
    private View viewDividerRoomAndFacility ,viewCheckInOut,viewWhyWithUse,
                 viewRequire,viewAdditionalInfo,viewAllowDivider,viewNotAllowDivider;

    public static Fragment getInstance(int isFrom, Data detailInfo) {
        HotelDetailInfoFragment infoFragment = new HotelDetailInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.EXT_IS_FROM, isFrom);
        bundle.putSerializable(AppConstant.EXT_HOTEL_INFO_DATA, detailInfo);
        infoFragment.setArguments(bundle);
        return infoFragment;
    }

    public static Fragment getInstance(int isFrom, com.profdeveloper.fllawi.model.ThingToDoDetails.Data detailInfo) {
        HotelDetailInfoFragment infoFragment = new HotelDetailInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.EXT_IS_FROM, isFrom);
        bundle.putSerializable(AppConstant.EXT_HOTEL_INFO_DATA, detailInfo);
        infoFragment.setArguments(bundle);
        return infoFragment;
    }

    public static Fragment getInstance(int isFrom, com.profdeveloper.fllawi.model.couponDetails.Data detailInfo) {
        HotelDetailInfoFragment infoFragment = new HotelDetailInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstant.EXT_IS_FROM, isFrom);
        bundle.putSerializable(AppConstant.EXT_HOTEL_INFO_DATA, detailInfo);
        infoFragment.setArguments(bundle);
        return infoFragment;
    }

    public HotelDetailInfoFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotel_detail_info, container, false);

        rvRoomFacilities = view.findViewById(R.id.rvRoomFacilities);
        tvDescriptionText = view.findViewById(R.id.tvDescriptionText);
        tvPrice = view.findViewById(R.id.tvPrice);
        llCheckInOutTime = view.findViewById(R.id.llCheckInOutTime);
        viewCheckInOut = view.findViewById(R.id.viewCheckInOut);
        llRoomsAndFacility = view.findViewById(R.id.llRoomsAndFacility);
        viewDividerRoomAndFacility = view.findViewById(R.id.viewDividerRoomAndFacility);

        tvCheckInOutTime = view.findViewById(R.id.tvCheckInOutTime);
        tvCheckInOutTimeDetail = view.findViewById(R.id.tvCheckInOutTimeDetail);
        tvNoDataFoundAllowed = view.findViewById(R.id.tvNoDataFoundAllowed);
        tvNoDataFoundNotAllowed = view.findViewById(R.id.tvNoDataFoundNotAllowed);
        tvNoDataFoundNotInclude = view.findViewById(R.id.tvNoDataFoundNotInclude);
        tvNoDataFoundInclude = view.findViewById(R.id.tvNoDataFoundInclude);
        tvNoDataFoundRequirement = view.findViewById(R.id.tvNoDataFoundRequirement);
        tvWhyWithUs = view.findViewById(R.id.tvWhyWithUs);
        tvWhyWithUsDetail = view.findViewById(R.id.tvWhyWithUsDetail);
        viewWhyWithUse = view.findViewById(R.id.viewWhyWithUse);
        viewRequire = view.findViewById(R.id.viewRequire);
        viewAdditionalInfo = view.findViewById(R.id.viewAdditionalInfo);
        llAdditionalInfo = view.findViewById(R.id.llAdditionalInfo);
        tvAdditionalInfoDetails = view.findViewById(R.id.tvAdditionalInfoDetails);
        llOtherRule = view.findViewById(R.id.llOtherRule);
        tvOtherRuleDetails = view.findViewById(R.id.tvOtherRuleDetails);

        llWhyWithUs = view.findViewById(R.id.llWhyWithUs);
        llCancellationPolicy = view.findViewById(R.id.llCancellationPolicy);
        llAllowed = view.findViewById(R.id.llAllowed);
        llNotAllowed = view.findViewById(R.id.llNotAllowed);
        llInclude = view.findViewById(R.id.llInclude);
        llNotInclude = view.findViewById(R.id.llNotInclude);
        llRequirement = view.findViewById(R.id.llRequirement);
        viewAllowDivider = view.findViewById(R.id.viewAllowDivider);
        viewNotAllowDivider = view.findViewById(R.id.viewNotAllowDivider);

        rvAllowed = view.findViewById(R.id.rvAllowed);
        rvNotAllowed = view.findViewById(R.id.rvNotAllowed);
        rvInclude = view.findViewById(R.id.rvInclude);
        rvNotInclude = view.findViewById(R.id.rvNotInclude);
        rvRequirement = view.findViewById(R.id.rvRequirement);

        tvCancellationPolicyDetail = view.findViewById(R.id.tvCancellationPolicyDetail);
        tvNoDataFoundFacility = view.findViewById(R.id.tvNoDataFoundFacility);

        llContinueBooking = view.findViewById(R.id.llContinueBooking);

        llContinueBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HotelDetailsActivity) getActivity()).onBookingClick(isFrom);
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            isFrom = bundle.getInt(AppConstant.EXT_IS_FROM);
            if (isFrom == AppConstant.IS_FROM_ACCOMMODATION) {
                accommodationDetail = (Data) bundle.getSerializable(AppConstant.EXT_HOTEL_INFO_DATA);
                setAccommodationDetails();
            } else if (isFrom == AppConstant.IS_FROM_THING_TO_DO) {
               thingToDoDetail = (com.profdeveloper.fllawi.model.ThingToDoDetails.Data) bundle.getSerializable(AppConstant.EXT_HOTEL_INFO_DATA);
                setThingToDoDetails();
            } else if (isFrom == AppConstant.IS_FROM_COUPON) {
                couponDetail = (com.profdeveloper.fllawi.model.couponDetails.Data) bundle.getSerializable(AppConstant.EXT_HOTEL_INFO_DATA);
                setCouponDetails();
            }
        }

        return view;
    }

    private void setAccommodationDetails() {

        llWhyWithUs.setVisibility(View.GONE);
        viewWhyWithUse.setVisibility(View.GONE);
        viewRequire.setVisibility(View.GONE);
        llAdditionalInfo.setVisibility(View.GONE);
        viewAdditionalInfo.setVisibility(View.GONE);
        llOtherRule.setVisibility(View.GONE);

        if (accommodationDetail != null) {
            amenitiesList = (ArrayList<Amenity>) accommodationDetail.getArrData().getAmenities();
            allowList = accommodationDetail.getArrAllowable();
            pricePerNight = accommodationDetail.getPrice() + "";
            tvPrice.setText("$ " + accommodationDetail.getPrice() + "");
            tvDescriptionText.setText(accommodationDetail.getArrData().getDescription());
            tvCheckInOutTimeDetail.setText("Check in Time : " + accommodationDetail.getArrData().getAccomodationRule().getCheckIn() + "\nCheck out Time : " + accommodationDetail.getArrData().getAccomodationRule().getCheckOut());
            String cancellationPolicy = "Cancellation price " + accommodationDetail.getArrData().getCancellationCharge() + " before hours " + accommodationDetail.getArrData().getCancellationDuration() + " no refund before hours " + accommodationDetail.getArrData().getNoRefundDuration();
            tvCancellationPolicyDetail.setText(cancellationPolicy);

            //Facility and Rooms
            if (accommodationDetail.getArrData().getAmenities() != null && !accommodationDetail.getArrData().getAmenities().isEmpty()) {
                rvRoomFacilities.setVisibility(View.VISIBLE);
                tvNoDataFoundFacility.setVisibility(View.GONE);
                ViewGroup.LayoutParams params = rvRoomFacilities.getLayoutParams();
                params.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * amenitiesList.size());
                rvRoomFacilities.setLayoutParams(params);
                gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvRoomFacilities.setLayoutManager(gridLayoutManager);
                facilitiesListAdapter = new FacilitiesListAdapter(getActivity(), amenitiesList);
                rvRoomFacilities.setAdapter(facilitiesListAdapter);
            } else {
                rvRoomFacilities.setVisibility(View.GONE);
                tvNoDataFoundFacility.setVisibility(View.VISIBLE);
            }

            //Allowed List Data
            if (accommodationDetail.getArrAllowable() != null && !accommodationDetail.getArrAllowable().isEmpty()) {
                rvAllowed.setVisibility(View.VISIBLE);
                tvNoDataFoundAllowed.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * allowList.size());
                rvAllowed.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvAllowed.setLayoutManager(gridLayoutManager);
                AllowedListAdapter allowedListAdapter = new AllowedListAdapter(getActivity(), accommodationDetail.getArrAllowable());
                rvAllowed.setAdapter(allowedListAdapter);
            } else {
                rvAllowed.setVisibility(View.GONE);
                tvNoDataFoundAllowed.setVisibility(View.VISIBLE);
            }

            //Not Allowed List Data
            if (accommodationDetail.getArrNotAllowable() != null && !accommodationDetail.getArrNotAllowable().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotAllowed.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._32sdp) * accommodationDetail.getArrNotAllowable().size());
                rvNotAllowed.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvNotAllowed.setLayoutManager(gridLayoutManager);
                NotAllowedListAdapter notAllowedListAdapter = new NotAllowedListAdapter(getActivity(), accommodationDetail.getArrNotAllowable());
                rvNotAllowed.setAdapter(notAllowedListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotAllowed.setVisibility(View.VISIBLE);
            }

            //Include List Data
            if (accommodationDetail.getArrIncluded() != null && !accommodationDetail.getArrIncluded().isEmpty()) {
                rvInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundInclude.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * accommodationDetail.getArrIncluded().size());
                rvInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvInclude.setLayoutManager(gridLayoutManager);
                IncludeListAdapter includeListAdapter = new IncludeListAdapter(getActivity(), accommodationDetail.getArrIncluded());
                rvInclude.setAdapter(includeListAdapter);
            } else {
                rvInclude.setVisibility(View.GONE);
                tvNoDataFoundInclude.setVisibility(View.VISIBLE);
            }

            //Not Include List Data
            if (accommodationDetail.getArrNotIncluded() != null && !accommodationDetail.getArrNotIncluded().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotInclude.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * accommodationDetail.getArrNotIncluded().size());
                rvNotInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvNotInclude.setLayoutManager(gridLayoutManager);
                NotIncludeListAdapter notIncludeListAdapter = new NotIncludeListAdapter(getActivity(), accommodationDetail.getArrNotIncluded());
                rvNotInclude.setAdapter(notIncludeListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }

            //Require List Data
            if (accommodationDetail.getArrRequirment() != null && !accommodationDetail.getArrRequirment().isEmpty()) {
                rvRequirement.setVisibility(View.VISIBLE);
                tvNoDataFoundRequirement.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * accommodationDetail.getArrRequirment().size());
                rvRequirement.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvRequirement.setLayoutManager(gridLayoutManager);
                RequireListAdapter requireListAdapter = new RequireListAdapter(getActivity(), accommodationDetail.getArrRequirment());
                rvRequirement.setAdapter(requireListAdapter);
            } else {
                rvRequirement.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }
        }
    }

    // Our Info : text DONE
    // Cancellation Policy : text DONE
    // Why with us : text DONE need to hide in coupon
    // Allowed : list DONE
    // Not Allowed : list DONE
    // Include : list DONE
    // Not Included : list DONE
    // Requirement : list DONE
    // Additional information : text DONE
    // Other Rule  : text DONE

     private void setThingToDoDetails() {
        if (thingToDoDetail != null) {
            allowList = thingToDoDetail.getArrAllowable();
            pricePerNight = thingToDoDetail.getPrice() + "";
            tvPrice.setText("$ " + thingToDoDetail.getPrice() + "");
            tvDescriptionText.setText(thingToDoDetail.getArrData().getDescription());
            //tvCheckInOutTimeDetail.setText("Check in Time : " + thingToDoDetail.getArrData().getAccomodationRule().getCheckIn() + "\nCheck out Time : " + thingToDoDetail.getArrData().getAccomodationRule().getCheckOut());
            String cancellationPolicy = "Cancellation price " + thingToDoDetail.getArrData().getCancellationCharge() + " before hours " + thingToDoDetail.getArrData().getCancellationDuration() + " no refund before hours " + thingToDoDetail.getArrData().getNoRefundDuration();
            tvCancellationPolicyDetail.setText(cancellationPolicy);

            if (thingToDoDetail.getArrData().getAdditionalInformation() != null){
                llAdditionalInfo.setVisibility(View.VISIBLE);
                tvAdditionalInfoDetails.setText(thingToDoDetail.getArrData().getAdditionalInformation());
            }else{
                llAdditionalInfo.setVisibility(View.GONE);
            }

            if (thingToDoDetail.getArrData().getOtherRules() != null){
                tvOtherRuleDetails.setText(thingToDoDetail.getArrData().getOtherRules()+"");
            }else{
                tvOtherRuleDetails.setText(" There is no other rules");
            }

            llRoomsAndFacility.setVisibility(View.GONE);
            viewDividerRoomAndFacility.setVisibility(View.GONE);

            llCheckInOutTime.setVisibility(View.GONE);
            viewCheckInOut.setVisibility(View.GONE);

            llWhyWithUs.setVisibility(View.VISIBLE);
            tvWhyWithUsDetail.setText(thingToDoDetail.getArrData().getWhyYou());
            viewWhyWithUse.setVisibility(View.VISIBLE);

            //Allowed List Data
            if (thingToDoDetail.getArrAllowable() != null && !thingToDoDetail.getArrAllowable().isEmpty()) {
                rvAllowed.setVisibility(View.VISIBLE);
                tvNoDataFoundAllowed.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * allowList.size());
                rvAllowed.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvAllowed.setLayoutManager(gridLayoutManager);
                AllowedListAdapter allowedListAdapter = new AllowedListAdapter(getActivity(), thingToDoDetail.getArrAllowable());
                rvAllowed.setAdapter(allowedListAdapter);
            } else {
                rvAllowed.setVisibility(View.GONE);
                tvNoDataFoundAllowed.setVisibility(View.VISIBLE);
            }

            //Not Allowed List Data
            if (thingToDoDetail.getArrNotAllowable() != null && !thingToDoDetail.getArrNotAllowable().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotAllowed.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * thingToDoDetail.getArrNotAllowable().size());
                rvNotAllowed.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvNotAllowed.setLayoutManager(gridLayoutManager);
                NotAllowedListAdapter notAllowedListAdapter = new NotAllowedListAdapter(getActivity(), thingToDoDetail.getArrNotAllowable());
                rvNotAllowed.setAdapter(notAllowedListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotAllowed.setVisibility(View.VISIBLE);
            }

            //Include List Data
            if (thingToDoDetail.getArrIncluded() != null && !thingToDoDetail.getArrIncluded().isEmpty()) {
                rvInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundInclude.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * thingToDoDetail.getArrIncluded().size());
                rvInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvInclude.setLayoutManager(gridLayoutManager);
                IncludeListAdapter includeListAdapter = new IncludeListAdapter(getActivity(), thingToDoDetail.getArrIncluded());
                rvInclude.setAdapter(includeListAdapter);
            } else {
                rvInclude.setVisibility(View.GONE);
                tvNoDataFoundInclude.setVisibility(View.VISIBLE);
            }

            //Not Include List Data
            if (thingToDoDetail.getArrNotIncluded() != null && !thingToDoDetail.getArrNotIncluded().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotInclude.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * thingToDoDetail.getArrNotIncluded().size());
                rvNotInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvNotInclude.setLayoutManager(gridLayoutManager);
                NotIncludeListAdapter notIncludeListAdapter = new NotIncludeListAdapter(getActivity(), thingToDoDetail.getArrNotIncluded());
                rvNotInclude.setAdapter(notIncludeListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }

            //Require List Data
            if (thingToDoDetail.getArrRequirment() != null && !thingToDoDetail.getArrRequirment().isEmpty()) {
                rvRequirement.setVisibility(View.VISIBLE);
                tvNoDataFoundRequirement.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * thingToDoDetail.getArrRequirment().size());
                rvRequirement.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvRequirement.setLayoutManager(gridLayoutManager);
                RequireListAdapter requireListAdapter = new RequireListAdapter(getActivity(), thingToDoDetail.getArrRequirment());
                rvRequirement.setAdapter(requireListAdapter);
            } else {
                rvRequirement.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }
        }
    }

    //

    private void setCouponDetails() {
        if (couponDetail != null) {
            tvDescriptionText.setText(couponDetail.getArrData().getDescription());
            allowList = couponDetail.getArrData().getArrAllowable();
            llCancellationPolicy.setVisibility(View.GONE);
            llCheckInOutTime.setVisibility(View.GONE);
            viewCheckInOut.setVisibility(View.GONE);
            llRoomsAndFacility.setVisibility(View.GONE);
            llAdditionalInfo.setVisibility(View.GONE);
            viewAdditionalInfo.setVisibility(View.GONE);
            llOtherRule.setVisibility(View.GONE);
            llWhyWithUs.setVisibility(View.GONE);
            viewWhyWithUse.setVisibility(View.GONE);

 /*           llAllowed.setVisibility(View.GONE);
            viewAllowDivider.setVisibility(View.GONE);
            llNotAllowed.setVisibility(View.GONE);
            viewNotAllowDivider.setVisibility(View.GONE);*/
            //Allowed List Data
            if (couponDetail.getArrData().getArrAllowable() != null && !couponDetail.getArrData().getArrAllowable().isEmpty()) {
                rvAllowed.setVisibility(View.VISIBLE);
                tvNoDataFoundAllowed.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * allowList.size());
                rvAllowed.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvAllowed.setLayoutManager(gridLayoutManager);
                AllowedListAdapter allowedListAdapter = new AllowedListAdapter(getActivity(), allowList);
                rvAllowed.setAdapter(allowedListAdapter);
            } else {
                rvAllowed.setVisibility(View.GONE);
                tvNoDataFoundAllowed.setVisibility(View.VISIBLE);
            }

            //Not Allowed List Data
            if (couponDetail.getArrData().getArrNotAllowable() != null && !couponDetail.getArrData().getArrNotAllowable().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotAllowed.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * couponDetail.getArrData().getArrNotAllowable().size());
                rvNotAllowed.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvNotAllowed.setLayoutManager(gridLayoutManager);
                NotAllowedListAdapter notAllowedListAdapter = new NotAllowedListAdapter(getActivity(), couponDetail.getArrData().getArrNotAllowable());
                rvNotAllowed.setAdapter(notAllowedListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotAllowed.setVisibility(View.VISIBLE);
            }

            //Include List Data
            if (couponDetail.getArrData().getArrIncluded() != null && !couponDetail.getArrData().getArrIncluded().isEmpty()) {
                rvInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundInclude.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * couponDetail.getArrData().getArrIncluded().size());
                rvInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvInclude.setLayoutManager(gridLayoutManager);
                IncludeListAdapter includeListAdapter = new IncludeListAdapter(getActivity(), couponDetail.getArrData().getArrIncluded());
                rvInclude.setAdapter(includeListAdapter);
            } else {
                rvInclude.setVisibility(View.GONE);
                tvNoDataFoundInclude.setVisibility(View.VISIBLE);
            }


            //Not Include List Data
            rvNotInclude.setVisibility(View.GONE);
            tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            /*if (couponDetail.getArrData().getArrNotIncluded() != null && !couponDetail.getArrNotIncluded().isEmpty()) {
                rvNotInclude.setVisibility(View.VISIBLE);
                tvNoDataFoundNotInclude.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * couponDetail.getArrNotIncluded().size());
                rvNotInclude.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvNotInclude.setLayoutManager(gridLayoutManager);
                NotIncludeListAdapter notIncludeListAdapter = new NotIncludeListAdapter(getActivity(), couponDetail.getArrNotIncluded());
                rvNotInclude.setAdapter(notIncludeListAdapter);
            } else {
                rvNotInclude.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }*/

            //Require List Data
            rvRequirement.setVisibility(View.GONE);
            tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            /*if (couponDetail.getArrRequirment() != null && !couponDetail.getArrRequirment().isEmpty()) {
                rvRequirement.setVisibility(View.VISIBLE);
                tvNoDataFoundRequirement.setVisibility(View.GONE);
                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (10 * allowList.size()));
                parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * couponDetail.getArrRequirment().size());
                rvRequirement.setLayoutParams(parms);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                rvRequirement.setLayoutManager(gridLayoutManager);
                RequireListAdapter requireListAdapter = new RequireListAdapter(getActivity(), couponDetail.getArrRequirment());
                rvRequirement.setAdapter(requireListAdapter);
            } else {
                rvRequirement.setVisibility(View.GONE);
                tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
            }*/
        }
    }

}
