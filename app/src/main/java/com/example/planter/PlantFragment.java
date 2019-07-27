package com.example.planter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


//public class PlantFragment extends Fragment implements View.OnClickListener
public class PlantFragment extends Fragment  {
    public PlantFragment() {
        // Required empty public constructor
    }
    private InterstitialAd mInterstitialAd;

    public void createInterstitial() {
        MobileAds.initialize(this.getActivity(),
                "ca-app-pub-3940256099942544~3347511713");
        mInterstitialAd = new InterstitialAd(this.getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                loadInterstitial();
            }

            @Override
            public void onAdClosed() {
                loadInterstitial();
            }
        });
        loadInterstitial();
    }

    public void loadInterstitial(){

        AdRequest interstitialRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(interstitialRequest);
    }

    public void showInterstitial(){
        if (mInterstitialAd.isLoaded())
            mInterstitialAd.show();
        else {
            Toast.makeText(this.getActivity(), "This is my Toast message!",
                    Toast.LENGTH_LONG).show();
            loadInterstitial();
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plant, container, false);
//        ImageButton button = (ImageButton) view.findViewById(R.id.btn_spring);
//        button.setOnClickListener(this);
//        createInterstitial();

//        if (MainActivity.mTwoPane) {
//            Fragment newFragment = new PlantFragment();
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.replace(R.id.item_detail_container, newFragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
//        }

//    private View.OnClickListener onButtonClick = new View.OnClickListener(){
//
//        @Override
//        public void onClick(View v){
////            if (mInterstitialAd.isLoaded()) {
////                mInterstitialAd.show();
////            } else {
//            Toast.makeText(MainActivity.this, "This is my Toast message!",
//                    Toast.LENGTH_LONG).show();
////            }
//        }
//    };
        return view;
    }
//    @Override
//    public void onClick(View v) {
//        showInterstitial();
//    }
}
