package ru.backStore;

/*
 * Created by Sindri on 06/07/14.
 */

import android.graphics.Bitmap;

public class CustomClass
{
    Bitmap bitmap;
    String text;

    public CustomClass(Bitmap bitmap, String text)
    {
        super();
        this.bitmap = bitmap;
        this.text = text;
    }

    public Bitmap getBitmap()
    {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap)
    {
        this.bitmap = bitmap;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }




}
