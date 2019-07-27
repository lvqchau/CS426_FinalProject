package com.example.planter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PlantBackground extends View {
    private TimerTask timerTask;
    private Timer timer;
    private ArrayList<MySprite> sprites;
    public PlantBackground(Context context) {
        super(context);
    }

    public PlantBackground(Context context, AttributeSet attrs) {
        super(context, attrs);

        sprites = new ArrayList<>();

        for (int i=0; i<1; i++) {
            CreateBg(0, 0);
        }
        InitTimerTask();

        timer = new Timer();
        timer.schedule(timerTask, 500, 40);
    }
    private void InitTimerTask() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < sprites.size(); i++) {
                    sprites.get(i).Update();
                }
                postInvalidate();
            }
        };
    }
    MySprite bgSprite;
    private void CreateBg(int left, int top) {
        Bitmap[] BMPs;
        BMPs = new Bitmap[120];
        BMPs[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bg0);
        BMPs[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bg1);
        BMPs[2] = BitmapFactory.decodeResource(getResources(), R.drawable.bg2);
        BMPs[3] = BitmapFactory.decodeResource(getResources(), R.drawable.bg3);
        BMPs[4] = BitmapFactory.decodeResource(getResources(), R.drawable.bg4);
        BMPs[5] = BitmapFactory.decodeResource(getResources(), R.drawable.bg5);
        BMPs[6] = BitmapFactory.decodeResource(getResources(), R.drawable.bg6);
        BMPs[7] = BitmapFactory.decodeResource(getResources(), R.drawable.bg7);
        BMPs[8] = BitmapFactory.decodeResource(getResources(), R.drawable.bg8);
        BMPs[9] = BitmapFactory.decodeResource(getResources(), R.drawable.bg9);
        BMPs[10] = BitmapFactory.decodeResource(getResources(), R.drawable.bg10);
        BMPs[11] = BitmapFactory.decodeResource(getResources(), R.drawable.bg11);
        BMPs[12] = BitmapFactory.decodeResource(getResources(), R.drawable.bg12);
        BMPs[13] = BitmapFactory.decodeResource(getResources(), R.drawable.bg13);
        BMPs[14] = BitmapFactory.decodeResource(getResources(), R.drawable.bg14);
        BMPs[15] = BitmapFactory.decodeResource(getResources(), R.drawable.bg15);
        BMPs[16] = BitmapFactory.decodeResource(getResources(), R.drawable.bg16);
        BMPs[17] = BitmapFactory.decodeResource(getResources(), R.drawable.bg17);
        BMPs[18] = BitmapFactory.decodeResource(getResources(), R.drawable.bg18);
        BMPs[19] = BitmapFactory.decodeResource(getResources(), R.drawable.bg19);
        BMPs[20] = BitmapFactory.decodeResource(getResources(), R.drawable.bg20);
        BMPs[21] = BitmapFactory.decodeResource(getResources(), R.drawable.bg21);
        BMPs[22] = BitmapFactory.decodeResource(getResources(), R.drawable.bg22);
        BMPs[23] = BitmapFactory.decodeResource(getResources(), R.drawable.bg23);
        BMPs[24] = BitmapFactory.decodeResource(getResources(), R.drawable.bg24);
        BMPs[25] = BitmapFactory.decodeResource(getResources(), R.drawable.bg25);
        BMPs[26] = BitmapFactory.decodeResource(getResources(), R.drawable.bg26);
        BMPs[27] = BitmapFactory.decodeResource(getResources(), R.drawable.bg27);
        BMPs[28] = BitmapFactory.decodeResource(getResources(), R.drawable.bg28);
        BMPs[29] = BitmapFactory.decodeResource(getResources(), R.drawable.bg29);
        BMPs[30] = BitmapFactory.decodeResource(getResources(), R.drawable.bg30);

        BMPs[31] = BitmapFactory.decodeResource(getResources(), R.drawable.bg31);
        BMPs[32] = BitmapFactory.decodeResource(getResources(), R.drawable.bg32);
        BMPs[33] = BitmapFactory.decodeResource(getResources(), R.drawable.bg33);
        BMPs[34] = BitmapFactory.decodeResource(getResources(), R.drawable.bg34);
        BMPs[35] = BitmapFactory.decodeResource(getResources(), R.drawable.bg35);
        BMPs[36] = BitmapFactory.decodeResource(getResources(), R.drawable.bg36);
        BMPs[37] = BitmapFactory.decodeResource(getResources(), R.drawable.bg37);
        BMPs[38] = BitmapFactory.decodeResource(getResources(), R.drawable.bg38);
        BMPs[39] = BitmapFactory.decodeResource(getResources(), R.drawable.bg39);
        BMPs[40] = BitmapFactory.decodeResource(getResources(), R.drawable.bg40);

        BMPs[41] = BitmapFactory.decodeResource(getResources(), R.drawable.bg41);
        BMPs[42] = BitmapFactory.decodeResource(getResources(), R.drawable.bg42);
        BMPs[43] = BitmapFactory.decodeResource(getResources(), R.drawable.bg43);
        BMPs[44] = BitmapFactory.decodeResource(getResources(), R.drawable.bg44);
        BMPs[45] = BitmapFactory.decodeResource(getResources(), R.drawable.bg45);
        BMPs[46] = BitmapFactory.decodeResource(getResources(), R.drawable.bg46);
        BMPs[47] = BitmapFactory.decodeResource(getResources(), R.drawable.bg47);
        BMPs[48] = BitmapFactory.decodeResource(getResources(), R.drawable.bg48);
        BMPs[49] = BitmapFactory.decodeResource(getResources(), R.drawable.bg49);
        BMPs[50] = BitmapFactory.decodeResource(getResources(), R.drawable.bg50);

        BMPs[51] = BitmapFactory.decodeResource(getResources(), R.drawable.bg51);
        BMPs[52] = BitmapFactory.decodeResource(getResources(), R.drawable.bg52);
        BMPs[53] = BitmapFactory.decodeResource(getResources(), R.drawable.bg53);
        BMPs[54] = BitmapFactory.decodeResource(getResources(), R.drawable.bg54);
        BMPs[55] = BitmapFactory.decodeResource(getResources(), R.drawable.bg55);
        BMPs[56] = BitmapFactory.decodeResource(getResources(), R.drawable.bg56);
        BMPs[57] = BitmapFactory.decodeResource(getResources(), R.drawable.bg57);
        BMPs[58] = BitmapFactory.decodeResource(getResources(), R.drawable.bg58);
        BMPs[59] = BitmapFactory.decodeResource(getResources(), R.drawable.bg59);
        BMPs[60] = BitmapFactory.decodeResource(getResources(), R.drawable.bg60);

        BMPs[61] = BitmapFactory.decodeResource(getResources(), R.drawable.bg61);
        BMPs[62] = BitmapFactory.decodeResource(getResources(), R.drawable.bg62);
        BMPs[63] = BitmapFactory.decodeResource(getResources(), R.drawable.bg63);
        BMPs[64] = BitmapFactory.decodeResource(getResources(), R.drawable.bg64);
        BMPs[65] = BitmapFactory.decodeResource(getResources(), R.drawable.bg65);
        BMPs[66] = BitmapFactory.decodeResource(getResources(), R.drawable.bg66);
        BMPs[67] = BitmapFactory.decodeResource(getResources(), R.drawable.bg67);
        BMPs[68] = BitmapFactory.decodeResource(getResources(), R.drawable.bg68);
        BMPs[69] = BitmapFactory.decodeResource(getResources(), R.drawable.bg69);
        BMPs[70] = BitmapFactory.decodeResource(getResources(), R.drawable.bg70);
        BMPs[71] = BitmapFactory.decodeResource(getResources(), R.drawable.bg71);
        BMPs[72] = BitmapFactory.decodeResource(getResources(), R.drawable.bg72);
        BMPs[73] = BitmapFactory.decodeResource(getResources(), R.drawable.bg73);
        BMPs[74] = BitmapFactory.decodeResource(getResources(), R.drawable.bg74);
        BMPs[75] = BitmapFactory.decodeResource(getResources(), R.drawable.bg75);
        BMPs[76] = BitmapFactory.decodeResource(getResources(), R.drawable.bg76);
        BMPs[77] = BitmapFactory.decodeResource(getResources(), R.drawable.bg77);
        BMPs[78] = BitmapFactory.decodeResource(getResources(), R.drawable.bg78);
        BMPs[79] = BitmapFactory.decodeResource(getResources(), R.drawable.bg79);
        BMPs[80] = BitmapFactory.decodeResource(getResources(), R.drawable.bg80);

        BMPs[81] = BitmapFactory.decodeResource(getResources(), R.drawable.bg81);
        BMPs[82] = BitmapFactory.decodeResource(getResources(), R.drawable.bg82);
        BMPs[83] = BitmapFactory.decodeResource(getResources(), R.drawable.bg83);
        BMPs[84] = BitmapFactory.decodeResource(getResources(), R.drawable.bg84);
        BMPs[85] = BitmapFactory.decodeResource(getResources(), R.drawable.bg85);
        BMPs[86] = BitmapFactory.decodeResource(getResources(), R.drawable.bg86);
        BMPs[87] = BitmapFactory.decodeResource(getResources(), R.drawable.bg87);
        BMPs[88] = BitmapFactory.decodeResource(getResources(), R.drawable.bg88);
        BMPs[89] = BitmapFactory.decodeResource(getResources(), R.drawable.bg89);
        BMPs[90] = BitmapFactory.decodeResource(getResources(), R.drawable.bg90);

        BMPs[91] = BitmapFactory.decodeResource(getResources(), R.drawable.bg91);
        BMPs[92] = BitmapFactory.decodeResource(getResources(), R.drawable.bg92);
        BMPs[93] = BitmapFactory.decodeResource(getResources(), R.drawable.bg93);
        BMPs[94] = BitmapFactory.decodeResource(getResources(), R.drawable.bg94);
        BMPs[95] = BitmapFactory.decodeResource(getResources(), R.drawable.bg95);
        BMPs[96] = BitmapFactory.decodeResource(getResources(), R.drawable.bg96);
        BMPs[97] = BitmapFactory.decodeResource(getResources(), R.drawable.bg97);
        BMPs[98] = BitmapFactory.decodeResource(getResources(), R.drawable.bg98);
        BMPs[99] = BitmapFactory.decodeResource(getResources(), R.drawable.bg99);
        BMPs[100] = BitmapFactory.decodeResource(getResources(), R.drawable.bg100);

        BMPs[101] = BitmapFactory.decodeResource(getResources(), R.drawable.bg101);
        BMPs[102] = BitmapFactory.decodeResource(getResources(), R.drawable.bg102);
        BMPs[103] = BitmapFactory.decodeResource(getResources(), R.drawable.bg103);
        BMPs[104] = BitmapFactory.decodeResource(getResources(), R.drawable.bg104);
        BMPs[105] = BitmapFactory.decodeResource(getResources(), R.drawable.bg105);
        BMPs[106] = BitmapFactory.decodeResource(getResources(), R.drawable.bg106);
        BMPs[107] = BitmapFactory.decodeResource(getResources(), R.drawable.bg107);
        BMPs[108] = BitmapFactory.decodeResource(getResources(), R.drawable.bg108);
        BMPs[109] = BitmapFactory.decodeResource(getResources(), R.drawable.bg109);
        BMPs[110] = BitmapFactory.decodeResource(getResources(), R.drawable.bg110);

        BMPs[111] = BitmapFactory.decodeResource(getResources(), R.drawable.bg111);
        BMPs[112] = BitmapFactory.decodeResource(getResources(), R.drawable.bg112);
        BMPs[113] = BitmapFactory.decodeResource(getResources(), R.drawable.bg113);
        BMPs[114] = BitmapFactory.decodeResource(getResources(), R.drawable.bg114);
        BMPs[115] = BitmapFactory.decodeResource(getResources(), R.drawable.bg115);
        BMPs[116] = BitmapFactory.decodeResource(getResources(), R.drawable.bg116);
        BMPs[117] = BitmapFactory.decodeResource(getResources(), R.drawable.bg117);
        BMPs[118] = BitmapFactory.decodeResource(getResources(), R.drawable.bg118);
        BMPs[119] = BitmapFactory.decodeResource(getResources(), R.drawable.bg119);

        bgSprite = new MySprite(BMPs, left, top, 0, 0);

        sprites.add(bgSprite);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0; i<sprites.size(); i++)
            sprites.get(i).Draw(canvas);
    }
}
