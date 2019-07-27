package com.example.planter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MyPot extends View {
    PlantFragment plantFragment;
    DatabaseHelper db;
    TextView total; //number of plants planted

    private TimerTask timerTask, timerTaskPlant;
    private ArrayList<MySprite> sprites, spritesPlant;
    private Timer timer;

    private int countPotWatering = 0;
    private int adsWatched = 0;
    private int checkSeason = 1;
    private int totalPlant = 0;

    CardView cardView;
    ImageButton btnSpring, btnFall, btnWinter, btnSummer;

    private boolean checkWatering = false;
    private boolean bDrag = false;
    private int selectedSpriteIndex = -1;
    private float oldX, oldY;
    private boolean bFloat = false;
    String usernameAccount;
    public MyPot(Context context, AttributeSet attrs) {
        super(context, attrs);
        usernameAccount = MainActivity.usernameAccount;
        db = new DatabaseHelper(this.getRootView().getContext().getApplicationContext());

        adsWatched = db.getAdsWatched(usernameAccount);
        totalPlant = db.getPlantCount(usernameAccount);
//        total.setText(totalPlant);

        sprites = new ArrayList<>();
        spritesPlant = new ArrayList<>();
        createInterstitial();

        for (int i=0; i<1; i++) {
            CreatePot(500, 500);
            CreatePlant(35, 850);
        }
        InitTimerTask();

        timer = new Timer();
        timer.schedule(timerTask, 500, 40);
        timer.schedule(timerTaskPlant, 500, 55);
    }


    private void InitTimerTask() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                for (int i=0; i<sprites.size(); i++) {
                    sprites.get(i).Update();
                }
                postInvalidate();
            }
        };
        timerTaskPlant = new TimerTask() {
            @Override
            public void run() {
                for (int i=0; i<spritesPlant.size(); i++) {
                    spritesPlant.get(i).Update();
                }
                postInvalidate();
            }
        };

    }
    MySprite potSprite, initPotSprite;
    private void CreatePot(int left, int top) {
        Bitmap[] BMPsPot, BMPsInit;
        BMPsPot = new Bitmap[65];
        BMPsInit = new Bitmap[1];
        BMPsInit[0] = BitmapFactory.decodeResource(getResources(), R.drawable.pot0);
        for (int i=0; i<65; i++)
            BMPsPot[i] = BitmapFactory.decodeResource(getResources(), R.drawable.pot0+i);
        potSprite = new MySprite(BMPsPot, left, top, 0, 0);
        initPotSprite = new MySprite(BMPsInit, left, top, 0, 0);
        sprites.add(initPotSprite);
    }
//    private void CreateSpring(int left, int top) {
//        Bitmap[] BMPsSpring;
//        BMPsSpring = new Bitmap[65];
//        BMPsSpring[0] = BitmapFactory.decodeResource(getResources(), R.drawable.pot0);
//        for (int i=0; i<65; i++)
//            BMPsSpring[i] = BitmapFactory.decodeResource(getResources(), R.drawable.pot0+i);
//        potSprite = new MySprite(BMPsPot, left, top, 0, 0);
//        initPotSprite = new MySprite(BMPsInit, left, top, 0, 0);
//        sprites.add(initPotSprite);
//    }


    // this method draws on the view
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0; i<spritesPlant.size(); i++)
            spritesPlant.get(i).Draw(canvas);
        for (int i=0; i<sprites.size(); i++)
            sprites.get(i).Draw(canvas);
//        if (checkWatering) {
//            countPotWatering++;
//        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);

        // get pointer index from the event object
        int pointerIndex = event.getActionIndex();

        // get pointer ID
        int pointerId = event.getPointerId(pointerIndex);

        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();
        int tempIdx;
        float x = event.getX();
        float y = event.getY();

        switch (maskedAction) {

            case MotionEvent.ACTION_DOWN: {
            }
            case MotionEvent.ACTION_POINTER_DOWN: {
                // TODO use data


                selectedSpriteIndex = getSelectedSpriteIndex(x, y);
                potSprite.left = 500;
                potSprite.top = 500;
                if (selectedSpriteIndex !=-1) {
                    sprites.remove(initPotSprite);
                    sprites.add(potSprite);
                    beginDrag(selectedSpriteIndex, x, y);
                }

                break;
            }

            case MotionEvent.ACTION_MOVE: { // a pointer was moved
                // TODO use data
                if (bDrag)
                    processDrag(selectedSpriteIndex, x, y);

                if ( (potSprite.top < 950 && potSprite.top > 700) && (potSprite.left > 60 && potSprite.left < 440) ) {
                    //5s = 5000
                    showInterstitial();
                    checkWatering = true;
//                    handler.postDelayed(r, 5000);
                }

//                    sprites.remove(Plant0);
//                    sprites.add(Plant1);
//                spritesPlant.add(Plant3);

                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP: {
                countPotWatering++;
                adsWatched++;
                db.updateAdsWatched(usernameAccount, adsWatched);
                //NEED TO CHECK IF HAS WATERED 8 TIMES YET -- MAYBE CHECK COLOR OF ID.PLANT8 OR COUNTPOTWATERING
                //SOMETHING HERE


                if (checkWatering == true) {
                    for (int i = 0; i < spritesPlant.size(); i++) {
                        spritesPlant.remove(spritesPlant.get(i));
                    }
                    switch (countPotWatering) {
                        case 1:
                            spritesPlant.add(Plant1);
                            //to get id from the parent layout
                            cardView = this.getRootView().findViewById(R.id.plant1);
                            cardView.setCardBackgroundColor(Color.parseColor("#7F9F2C"));
                            break;
                        case 2:
                            spritesPlant.add(Plant1);
                            spritesPlant.add(Plant2);
                            cardView = this.getRootView().findViewById(R.id.plant2);
                            cardView.setCardBackgroundColor(Color.parseColor("#7F9F2C"));
                            break;
                        case 3:
                            spritesPlant.add(Plant1);
                            spritesPlant.add(Plant2);
                            spritesPlant.add(Plant3);
                            cardView = this.getRootView().findViewById(R.id.plant3);
                            cardView.setCardBackgroundColor(Color.parseColor("#7F9F2C"));
                            break;
                        case 4:
                            spritesPlant.add(Plant1);
                            spritesPlant.add(Plant2);
                            spritesPlant.add(Plant3);
                            spritesPlant.add(Plant4);
                            cardView = this.getRootView().findViewById(R.id.plant4);
                            cardView.setCardBackgroundColor(Color.parseColor("#7F9F2C"));
                            break;
                        default:
                            spritesPlant.add(Plant0);
                            checkSeason++;
                            totalPlant++;

                            db.updatePlantCount(usernameAccount, totalPlant);
                            total = this.getRootView().findViewById(R.id.total_plant_count);
                            total.setText(Integer.toString(totalPlant));

                            btnSpring = this.getRootView().findViewById(R.id.btn_spring);
                            btnFall = this.getRootView().findViewById(R.id.btn_fall);
                            btnWinter = this.getRootView().findViewById(R.id.btn_winter);
                            btnSummer = this.getRootView().findViewById(R.id.btn_summer);
                            for (int i = 1; i<5; i++) {
                                String plant = "plant" + i;
                                int resID = getResources().getIdentifier(plant, "id", "com.example.planter");
                                cardView = this.getRootView().findViewById(resID);
                                cardView.setCardBackgroundColor(Color.parseColor("#DDB87E"));
                            }
                            switch (checkSeason) {
                                //1: spring, 2: summer, 3: fall, 4: winter
                                case 1:
                                    btnSpring.setImageResource(R.drawable.ic_btn_spring);
                                    btnSummer.setImageResource(R.drawable.ic_btn_summer_black);
                                    btnFall.setImageResource(R.drawable.ic_btn_fall_black);
                                    btnWinter.setImageResource(R.drawable.ic_btn_winter_black);
                                    break;
                                case 2:
                                    btnSpring.setImageResource(R.drawable.ic_btn_spring_black);
                                    btnSummer.setImageResource(R.drawable.ic_btn_summer);
                                    btnFall.setImageResource(R.drawable.ic_btn_fall_black);
                                    btnWinter.setImageResource(R.drawable.ic_btn_winter_black);
                                    break;
                                case 3:
                                    btnSpring.setImageResource(R.drawable.ic_btn_spring_black);
                                    btnSummer.setImageResource(R.drawable.ic_btn_summer_black);
                                    btnFall.setImageResource(R.drawable.ic_btn_fall);
                                    btnWinter.setImageResource(R.drawable.ic_btn_winter_black);
                                    break;
                                case 4:
                                    btnSpring.setImageResource(R.drawable.ic_btn_spring_black);
                                    btnSummer.setImageResource(R.drawable.ic_btn_summer_black);
                                    btnFall.setImageResource(R.drawable.ic_btn_fall_black);
                                    btnWinter.setImageResource(R.drawable.ic_btn_winter);
                                    checkSeason = 0;
                                    break;
                            }
                            countPotWatering = 0;
                            break;
                    }

                }
                checkWatering = false;
                if (bDrag) {
                    sprites.remove(initPotSprite);
                    sprites.remove(potSprite);
                    CreatePot(500, 500);
                }
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                // TODO use data


                break;
            }
        }


        return true;
    }

    private void beginDrag(int selectedSpriteIndex, float x, float y) {
        bDrag = true;
        oldX = x;
        oldY = y;
    }

    private void processDrag(int selIdx, float x, float y) {
        sprites.get(selIdx).left += x-oldX;
        sprites.get(selIdx).top += y-oldY;
        oldX = x;
        oldY = y;
        invalidate();
    }

    private int getSelectedSpriteIndex(float x, float y) {
        int selIdx = -1;
        for (int i=sprites.size()-1; i>=0; i--) {

            if (sprites.get(i).IsSelected(x,y))
            {
                selIdx = i;
                break;
            }
        }
        return selIdx;
    }

    private void endDrag(int selectedSpriteIndex, float x, float y) {
        processDrag(selectedSpriteIndex, x, y);
        bDrag = false;
        selectedSpriteIndex = -1;
    }





    //ad
    private InterstitialAd mInterstitialAd;

    public void createInterstitial() {
        MobileAds.initialize(this.getContext(),
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
            Toast.makeText(this.getContext(), "This is my Toast message!",
                    Toast.LENGTH_LONG).show();
        }

    }


    MySprite Plant0, Plant1, Plant2, Plant3, Plant4;
    private void CreatePlant(int left, int top) {
        Bitmap[] BMPsInit, BMPs, BMPs1, BMPs2, BMPs3, BMPs4, BMPs5, BMPs6, BMPs7, BMPs8, BMPs9, BMPs10, BMPs11;

        BMPsInit = new Bitmap[1];
        BMPsInit[0] = BitmapFactory.decodeResource(getResources(), R.drawable.plant0);

        BMPs = new Bitmap[29];
        BMPs[0] = BitmapFactory.decodeResource(getResources(), R.drawable.plant0);
        BMPs[1] = BitmapFactory.decodeResource(getResources(), R.drawable.plant01);
        BMPs[2] = BitmapFactory.decodeResource(getResources(), R.drawable.plant02);
        BMPs[3] = BitmapFactory.decodeResource(getResources(), R.drawable.plant03);
        BMPs[4] = BitmapFactory.decodeResource(getResources(), R.drawable.plant04);
        BMPs[5] = BitmapFactory.decodeResource(getResources(), R.drawable.plant05);
        BMPs[6] = BitmapFactory.decodeResource(getResources(), R.drawable.plant06);
        BMPs[7] = BitmapFactory.decodeResource(getResources(), R.drawable.plant07);
        BMPs[8] = BitmapFactory.decodeResource(getResources(), R.drawable.plant08);
        BMPs[9] = BitmapFactory.decodeResource(getResources(), R.drawable.plant09);
        BMPs[10] = BitmapFactory.decodeResource(getResources(), R.drawable.plant10);
        BMPs[11] = BitmapFactory.decodeResource(getResources(), R.drawable.plant11);
        BMPs[12] = BitmapFactory.decodeResource(getResources(), R.drawable.plant12);
        BMPs[13] = BitmapFactory.decodeResource(getResources(), R.drawable.plant13);
        BMPs[14] = BitmapFactory.decodeResource(getResources(), R.drawable.plant14);
        BMPs[15] = BitmapFactory.decodeResource(getResources(), R.drawable.plant15);
        BMPs[16] = BitmapFactory.decodeResource(getResources(), R.drawable.plant16);
        BMPs[17] = BitmapFactory.decodeResource(getResources(), R.drawable.plant17);
        BMPs[18] = BitmapFactory.decodeResource(getResources(), R.drawable.plant18);
        BMPs[19] = BitmapFactory.decodeResource(getResources(), R.drawable.plant19);
        BMPs[20] = BitmapFactory.decodeResource(getResources(), R.drawable.plant20);
        BMPs[21] = BitmapFactory.decodeResource(getResources(), R.drawable.plant21);
        BMPs[22] = BitmapFactory.decodeResource(getResources(), R.drawable.plant22);
        BMPs[23] = BitmapFactory.decodeResource(getResources(), R.drawable.plant23);
        BMPs[24] = BitmapFactory.decodeResource(getResources(), R.drawable.plant24);
        BMPs[25] = BitmapFactory.decodeResource(getResources(), R.drawable.plant25);
        BMPs[26] = BitmapFactory.decodeResource(getResources(), R.drawable.plant26);
        BMPs[27] = BitmapFactory.decodeResource(getResources(), R.drawable.plant27);
        BMPs[28] = BitmapFactory.decodeResource(getResources(), R.drawable.plant28);

        BMPs1 = new Bitmap[16];
        //29-45

        BMPs1[0] = BitmapFactory.decodeResource(getResources(), R.drawable.plant29);
        BMPs1[1] = BitmapFactory.decodeResource(getResources(), R.drawable.plant30);
        BMPs1[2] = BitmapFactory.decodeResource(getResources(), R.drawable.plant31);
        BMPs1[3] = BitmapFactory.decodeResource(getResources(), R.drawable.plant32);
        BMPs1[4] = BitmapFactory.decodeResource(getResources(), R.drawable.plant33);
        BMPs1[5] = BitmapFactory.decodeResource(getResources(), R.drawable.plant34);
        BMPs1[6] = BitmapFactory.decodeResource(getResources(), R.drawable.plant35);
        BMPs1[7] = BitmapFactory.decodeResource(getResources(), R.drawable.plant36);
        BMPs1[8] = BitmapFactory.decodeResource(getResources(), R.drawable.plant37);
        BMPs1[9] = BitmapFactory.decodeResource(getResources(), R.drawable.plant38);
        BMPs1[10] = BitmapFactory.decodeResource(getResources(), R.drawable.plant39);
        BMPs1[11] = BitmapFactory.decodeResource(getResources(), R.drawable.plant40);
        BMPs1[12] = BitmapFactory.decodeResource(getResources(), R.drawable.plant41);
        BMPs1[13] = BitmapFactory.decodeResource(getResources(), R.drawable.plant42);
        BMPs1[14] = BitmapFactory.decodeResource(getResources(), R.drawable.plant43);
        BMPs1[15] = BitmapFactory.decodeResource(getResources(), R.drawable.plant44);


        BMPs2 = new Bitmap[30];
        //45-74

        BMPs2[0] = BitmapFactory.decodeResource(getResources(), R.drawable.plant45);
        BMPs2[1] = BitmapFactory.decodeResource(getResources(), R.drawable.plant46);
        BMPs2[2] = BitmapFactory.decodeResource(getResources(), R.drawable.plant47);
        BMPs2[3] = BitmapFactory.decodeResource(getResources(), R.drawable.plant48);
        BMPs2[4] = BitmapFactory.decodeResource(getResources(), R.drawable.plant49);
        BMPs2[5] = BitmapFactory.decodeResource(getResources(), R.drawable.plant50);
        BMPs2[6] = BitmapFactory.decodeResource(getResources(), R.drawable.plant51);
        BMPs2[7] = BitmapFactory.decodeResource(getResources(), R.drawable.plant52);
        BMPs2[8] = BitmapFactory.decodeResource(getResources(), R.drawable.plant53);
        BMPs2[9] = BitmapFactory.decodeResource(getResources(), R.drawable.plant54);
        BMPs2[10] = BitmapFactory.decodeResource(getResources(), R.drawable.plant55);
        BMPs2[11] = BitmapFactory.decodeResource(getResources(), R.drawable.plant56);
        BMPs2[12] = BitmapFactory.decodeResource(getResources(), R.drawable.plant57);
        BMPs2[13] = BitmapFactory.decodeResource(getResources(), R.drawable.plant58);
        BMPs2[14] = BitmapFactory.decodeResource(getResources(), R.drawable.plant59);
        BMPs2[15] = BitmapFactory.decodeResource(getResources(), R.drawable.plant60);
        BMPs2[16] = BitmapFactory.decodeResource(getResources(), R.drawable.plant61);
        BMPs2[17] = BitmapFactory.decodeResource(getResources(), R.drawable.plant62);
        BMPs2[18] = BitmapFactory.decodeResource(getResources(), R.drawable.plant63);
        BMPs2[19] = BitmapFactory.decodeResource(getResources(), R.drawable.plant64);
        BMPs2[20] = BitmapFactory.decodeResource(getResources(), R.drawable.plant65);
        BMPs2[21] = BitmapFactory.decodeResource(getResources(), R.drawable.plant66);
        BMPs2[22] = BitmapFactory.decodeResource(getResources(), R.drawable.plant67);
        BMPs2[23] = BitmapFactory.decodeResource(getResources(), R.drawable.plant68);
        BMPs2[24] = BitmapFactory.decodeResource(getResources(), R.drawable.plant69);
        BMPs2[25] = BitmapFactory.decodeResource(getResources(), R.drawable.plant70);
        BMPs2[26] = BitmapFactory.decodeResource(getResources(), R.drawable.plant71);
        BMPs2[27] = BitmapFactory.decodeResource(getResources(), R.drawable.plant72);
        BMPs2[28] = BitmapFactory.decodeResource(getResources(), R.drawable.plant73);
        BMPs2[29] = BitmapFactory.decodeResource(getResources(), R.drawable.plant74);


        BMPs3 = new Bitmap[28];
        BMPs3[0] = BitmapFactory.decodeResource(getResources(), R.drawable.plant75);
        BMPs3[1] = BitmapFactory.decodeResource(getResources(), R.drawable.plant76);
        BMPs3[2] = BitmapFactory.decodeResource(getResources(), R.drawable.plant77);
        BMPs3[3] = BitmapFactory.decodeResource(getResources(), R.drawable.plant78);
        BMPs3[4] = BitmapFactory.decodeResource(getResources(), R.drawable.plant79);
        BMPs3[5] = BitmapFactory.decodeResource(getResources(), R.drawable.plant80);
        BMPs3[6] = BitmapFactory.decodeResource(getResources(), R.drawable.plant81);
        BMPs3[7] = BitmapFactory.decodeResource(getResources(), R.drawable.plant82);
        BMPs3[8] = BitmapFactory.decodeResource(getResources(), R.drawable.plant83);
        BMPs3[9] = BitmapFactory.decodeResource(getResources(), R.drawable.plant84);
        BMPs3[10] = BitmapFactory.decodeResource(getResources(), R.drawable.plant85);
        BMPs3[11] = BitmapFactory.decodeResource(getResources(), R.drawable.plant86);
        BMPs3[12] = BitmapFactory.decodeResource(getResources(), R.drawable.plant87);
        BMPs3[13] = BitmapFactory.decodeResource(getResources(), R.drawable.plant88);
        BMPs3[14] = BitmapFactory.decodeResource(getResources(), R.drawable.plant89);
        BMPs3[15] = BitmapFactory.decodeResource(getResources(), R.drawable.plant90);
        BMPs3[16] = BitmapFactory.decodeResource(getResources(), R.drawable.plant91);
        BMPs3[17] = BitmapFactory.decodeResource(getResources(), R.drawable.plant92);
        BMPs3[18] = BitmapFactory.decodeResource(getResources(), R.drawable.plant93);
        BMPs3[19] = BitmapFactory.decodeResource(getResources(), R.drawable.plant94);
        BMPs3[20] = BitmapFactory.decodeResource(getResources(), R.drawable.plant95);
        BMPs3[21] = BitmapFactory.decodeResource(getResources(), R.drawable.plant96);
        BMPs3[22] = BitmapFactory.decodeResource(getResources(), R.drawable.plant97);
        BMPs3[23] = BitmapFactory.decodeResource(getResources(), R.drawable.plant98);
        BMPs3[24] = BitmapFactory.decodeResource(getResources(), R.drawable.plant99);
        BMPs3[25] = BitmapFactory.decodeResource(getResources(), R.drawable.plant100);
        BMPs3[26] = BitmapFactory.decodeResource(getResources(), R.drawable.plant101);
        BMPs3[27] = BitmapFactory.decodeResource(getResources(), R.drawable.plant102);

//        for (int i=0; i<15; i++)
//            BMPs[i] = BitmapFactory.decodeResource(getResources(), R.drawable.plant01+i);
        Plant0 = new MySprite(BMPsInit, left, top, 0, 0);
        Plant1 = new MySprite(BMPs, left, top, 0, 0);
        Plant2 = new MySprite(BMPs1, left, top, 0, 0);
        Plant3 = new MySprite(BMPs2, left, top, 0, 0);
        Plant4 = new MySprite(BMPs3, left, top, 0, 0);
        spritesPlant.add(Plant0);
    }


}
