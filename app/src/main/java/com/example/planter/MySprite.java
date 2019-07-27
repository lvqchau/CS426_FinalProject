package com.example.planter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class MySprite {
    public int nBMPs;
    public int iBMP;
    public Bitmap[] BMPs;
    public int left;
    public int top;
    public int width;
    public int height;

    public MySprite(Bitmap[] bmps, int left, int top, int width, int height)
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

    int d2 = 1;

    public void Update()
    {
        iBMP = (iBMP +1) % nBMPs;
        if (State==1)
        {
            if (d>=10 || d<=-10)
                d2*=-1;
            d+=d2;
        }

    }
    public int State = 0;
    public int d = 0;
    public void Draw(Canvas canvas)
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



    public boolean IsSelected(float x, float y)
    {
        if (x>=left && x <=left + width && y >= top && y <= top+height)
            return true;
        return false;
    }
}
