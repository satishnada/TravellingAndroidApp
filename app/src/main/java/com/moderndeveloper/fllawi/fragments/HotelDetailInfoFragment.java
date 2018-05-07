package com.moderndeveloper.fllawi.fragments;

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

import com.moderndeveloper.fllawi.R;
import com.moderndeveloper.fllawi.activities.HotelDetailsActivity;
import com.moderndeveloper.fllawi.adapters.AllowedListAdapter;
import com.moderndeveloper.fllawi.adapters.FacilitiesListAdapter;
import com.moderndeveloper.fllawi.adapters.HotelReviewsListAdapter;
import com.moderndeveloper.fllawi.adapters.IncludeListAdapter;
import com.moderndeveloper.fllawi.adapters.NotAllowedListAdapter;
import com.moderndeveloper.fllawi.adapters.NotIncludeListAdapter;
import com.moderndeveloper.fllawi.adapters.RequireListAdapter;
import com.moderndeveloper.fllawi.model.AccommodationDetails.Amenity;
import com.moderndeveloper.fllawi.model.AccommodationDetails.Data;
import com.moderndeveloper.fllawi.utils.AppConstant;
import com.moderndeveloper.fllawi.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class HotelDetailInfoFragment extends Fragment {

    private RecyclerView rvRoomFacilities,rvAllowed,rvNotAllowed,
            rvInclude,rvNotInclude,rvRequirement;
    private GridLayoutManager gridLayoutManager;
    private FacilitiesListAdapter facilitiesListAdapter;
    private LinearLayout llContinueBooking;
    private Data hotelDetail;
    private ArrayList<Amenity> amenitiesList = new ArrayList<>();
    private List<String> allowList = new ArrayList<>();
    private TextView tvDescriptionText,tvPrice,tvCheckInOutTimeDetail,
            tvCancellationPolicyDetail,tvNoDataFoundFacility,
            tvNoDataFoundAllowed,tvNoDataFoundNotAllowed,tvNoDataFoundInclude,
            tvNoDataFoundNotInclude,tvNoDataFoundRequirement;
    private LinearLayout llCheckInOutTime,llCancellationPolicy,
            llAllowed,llNotAllowed,llInclude,llNotInclude,llRequirement;

    private String pricePerNight = "";

    public static Fragment getInstance(Data detailInfo) {
        HotelDetailInfoFragment infoFragment = new HotelDetailInfoFragment();
        Bundle bundle = new Bundle();
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

        tvCheckInOutTimeDetail = view.findViewById(R.id.tvCheckInOutTimeDetail);
        tvNoDataFoundAllowed = view.findViewById(R.id.tvNoDataFoundAllowed);
        tvNoDataFoundNotAllowed = view.findViewById(R.id.tvNoDataFoundNotAllowed);
        tvNoDataFoundNotInclude = view.findViewById(R.id.tvNoDataFoundNotInclude);
        tvNoDataFoundInclude = view.findViewById(R.id.tvNoDataFoundInclude);
        tvNoDataFoundRequirement = view.findViewById(R.id.tvNoDataFoundRequirement);

        llCancellationPolicy = view.findViewById(R.id.llCancellationPolicy);
        llAllowed = view.findViewById(R.id.llAllowed);
        llNotAllowed = view.findViewById(R.id.llNotAllowed);
        llInclude = view.findViewById(R.id.llInclude);
        llNotInclude = view.findViewById(R.id.llNotInclude);
        llRequirement = view.findViewById(R.id.llRequirement);

        rvAllowed = view.findViewById(R.id.rvAllowed);
        rvNotAllowed = view.findViewById(R.id.rvNotAllowed);
        rvInclude = view.findViewById(R.id.rvInclude);
        rvNotInclude = view.findViewById(R.id.rvNotInclude);
        rvRequirement = view.findViewById(R.id.rvRequirement);

        tvCancellationPolicyDetail = view.findViewById(R.id.tvCancellationPolicyDetail);
        tvNoDataFoundFacility = view.findViewById(R.id.tvNoDataFoundFacility);

        Bundle bundle = getArguments();
        if (bundle != null) {
            hotelDetail = (Data) bundle.getSerializable(AppConstant.EXT_HOTEL_INFO_DATA);

            if (hotelDetail != null) {
                amenitiesList = (ArrayList<Amenity>) hotelDetail.getArrData().getAmenities();
                allowList = hotelDetail.getArrAllowable();
                pricePerNight = hotelDetail.getPrice()+"";
                tvPrice.setText("$ "+hotelDetail.getPrice()+"");
                tvDescriptionText.setText(hotelDetail.getArrData().getDescription());
                tvCheckInOutTimeDetail.setText("Check in Time : "+hotelDetail.getArrData().getAccomodationRule().getCheckIn()+"\nCheck out Time : "+hotelDetail.getArrData().getAccomodationRule().getCheckOut());
                String cancellationPolicy = "Cancellation price "+hotelDetail.getArrData().getCancellationCharge() +" before hours "+ hotelDetail.getArrData().getCancellationDuration() +" no refund before hours "+hotelDetail.getArrData().getNoRefundDuration();
                tvCancellationPolicyDetail.setText(cancellationPolicy);

                //Facility and Rooms
                if (hotelDetail.getArrData().getAmenities() != null && !hotelDetail.getArrData().getAmenities().isEmpty()){
                    rvRoomFacilities.setVisibility(View.VISIBLE);
                    tvNoDataFoundFacility.setVisibility(View.GONE);
                    ViewGroup.LayoutParams params = rvRoomFacilities.getLayoutParams();
                    params.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * amenitiesList.size());
                    rvRoomFacilities.setLayoutParams(params);
                    gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                    rvRoomFacilities.setLayoutManager(gridLayoutManager);
                    facilitiesListAdapter = new FacilitiesListAdapter(getActivity(), amenitiesList);
                    rvRoomFacilities.setAdapter(facilitiesListAdapter);
                }else{
                    rvRoomFacilities.setVisibility(View.GONE);
                    tvNoDataFoundFacility.setVisibility(View.VISIBLE);
                }

                //Allowed List Data
                if (hotelDetail.getArrAllowable() != null && !hotelDetail.getArrAllowable().isEmpty()){
                    rvAllowed.setVisibility(View.VISIBLE);
                    tvNoDataFoundAllowed.setVisibility(View.GONE);
                    LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(10 * allowList.size()));
                    parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * allowList.size());
                    rvAllowed.setLayoutParams(parms);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                    rvAllowed.setLayoutManager(gridLayoutManager);
                    AllowedListAdapter allowedListAdapter = new AllowedListAdapter(getActivity(),hotelDetail.getArrAllowable());
                    rvAllowed.setAdapter(allowedListAdapter);
                }else{
                    rvAllowed.setVisibility(View.GONE);
                    tvNoDataFoundAllowed.setVisibility(View.VISIBLE);
                }

                //Not Allowed List Data
                if (hotelDetail.getArrNotAllowable() != null && !hotelDetail.getArrNotAllowable().isEmpty()){
                    rvNotInclude.setVisibility(View.VISIBLE);
                    tvNoDataFoundNotAllowed.setVisibility(View.GONE);
                    LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(10 * allowList.size()));
                    parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * hotelDetail.getArrNotAllowable().size());
                    rvNotAllowed.setLayoutParams(parms);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                    rvNotAllowed.setLayoutManager(gridLayoutManager);
                    NotAllowedListAdapter notAllowedListAdapter = new NotAllowedListAdapter(getActivity(),hotelDetail.getArrNotAllowable());
                    rvNotAllowed.setAdapter(notAllowedListAdapter);
                }else{
                    rvNotInclude.setVisibility(View.GONE);
                    tvNoDataFoundNotAllowed.setVisibility(View.VISIBLE);
                }

                //Include List Data
                if (hotelDetail.getArrIncluded() != null && !hotelDetail.getArrIncluded().isEmpty()){
                    rvInclude.setVisibility(View.VISIBLE);
                    tvNoDataFoundInclude.setVisibility(View.GONE);
                    LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(10 * allowList.size()));
                    parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * hotelDetail.getArrIncluded().size());
                    rvInclude.setLayoutParams(parms);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                    rvInclude.setLayoutManager(gridLayoutManager);
                    IncludeListAdapter includeListAdapter = new IncludeListAdapter(getActivity(),hotelDetail.getArrIncluded());
                    rvInclude.setAdapter(includeListAdapter);
                }else{
                    rvInclude.setVisibility(View.GONE);
                    tvNoDataFoundInclude.setVisibility(View.VISIBLE);
                }

                //Not Include List Data
                if (hotelDetail.getArrNotIncluded() != null && !hotelDetail.getArrNotIncluded().isEmpty()){
                    rvNotInclude.setVisibility(View.VISIBLE);
                    tvNoDataFoundNotInclude.setVisibility(View.GONE);
                    LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(10 * allowList.size()));
                    parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * hotelDetail.getArrNotIncluded().size());
                    rvNotInclude.setLayoutParams(parms);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                    rvNotInclude.setLayoutManager(gridLayoutManager);
                    NotIncludeListAdapter notIncludeListAdapter = new NotIncludeListAdapter(getActivity(),hotelDetail.getArrNotIncluded());
                    rvNotInclude.setAdapter(notIncludeListAdapter);
                }else{
                    rvNotInclude.setVisibility(View.GONE);
                    tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
                }

                //Require List Data
                if (hotelDetail.getArrRequirment() != null && !hotelDetail.getArrRequirment().isEmpty()){
                    rvRequirement.setVisibility(View.VISIBLE);
                    tvNoDataFoundRequirement.setVisibility(View.GONE);
                    LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,(10 * allowList.size()));
                    parms.height = (int) (getActivity().getResources().getDimension(R.dimen._25sdp) * hotelDetail.getArrRequirment().size());
                    rvRequirement.setLayoutParams(parms);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                    rvRequirement.setLayoutManager(gridLayoutManager);
                    RequireListAdapter requireListAdapter = new RequireListAdapter(getActivity(),hotelDetail.getArrRequirment());
                    rvRequirement.setAdapter(requireListAdapter);
                }else{
                    rvRequirement.setVisibility(View.GONE);
                    tvNoDataFoundNotInclude.setVisibility(View.VISIBLE);
                }

                llContinueBooking = view.findViewById(R.id.llContinueBooking);

                llContinueBooking.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((HotelDetailsActivity) getActivity()).onBookingClick();
                    }
                });
            }
        }

        return view;
    }

}
