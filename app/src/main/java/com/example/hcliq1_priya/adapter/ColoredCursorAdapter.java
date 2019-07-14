package com.example.hcliq1_priya.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.widget.SimpleCursorAdapter;

public class ColoredCursorAdapter extends SimpleCursorAdapter {
    String backgroundColor;
    public ColoredCursorAdapter(Context context, int layout, String backgroundColor, Cursor c,String[] from,int[] to, int flags){
        super(context, layout, c, from, to, flags);
        this.backgroundColor = backgroundColor;
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
            view.setBackgroundColor(Color.WHITE);
    }
}
