package com.example.planter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class MySprite {
    private int nBMPs;
    private int iBMP;
    private Bitmap[] BMPs;
    private int left;
    private int top;
    private int width;
    private int height;

    private MySprite(Bitmap[] bmps, int left, int top, int width, int height)
    {
        BMPs = bmps;
        nBMPs = bmps.length;
        iBMP = 0;
        this.left = left;
        this.top = top;
        if (width == 0 && height==0)
        {
            width = bmps[0].getWidth();
            height = bmps[0].getHeight();
        }
        this.width = width;
        this.height = height;
    }

    private int d2 = 1;

    private void Update()
    {
        iBMP = (iBMP +1) % nBMPs;
        if (State==1)
        {
            if (d>=10 || d<=-10)
                d2*=-1;
            d+=d2;
        }

    }
    private int State = 0;
    private int d = 0;
    private void Draw(Canvas canvas)
    {
        if (State ==0)
            canvas.drawBitmap(BMPs[iBMP], left, top, null);
        else
        {
            Rect sourceRect = new Rect(0, 0, width-1, height-1);
            Rect destRect = new Rect(left-d, top-d, left+width+2*d-1, top+height+2*d-1);
            canvas.drawBitmap(BMPs[iBMP],sourceRect, destRect, null);
        }


    }



    private boolean IsSelected(float x, float y)
    {
        if (x>=left && x <=left + width && y >= top && y <= top+height)
            return true;
        return false;
    }
}
