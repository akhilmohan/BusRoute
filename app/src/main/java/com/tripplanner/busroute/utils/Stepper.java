package com.tripplanner.busroute.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tripplanner.busroute.R;

import org.w3c.dom.Text;

/**
 * Created by akhilmohan on 07/11/17.
 */

public class Stepper extends RelativeLayout {
    private TextView txtStop;
    private ImageView ImgVLine;
    private ImageView imgDot;
    private String[] stops;
    private ViewGroup mLinearLayout;

    public Stepper(Context context) {
        super(context);
        init();
    }

    public Stepper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Stepper(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Stepper, defStyle, 0);
        try
        {
            CharSequence[] entries = a.getTextArray(R.styleable.Stepper_android_entries);
            if (entries != null)
            {

            }
        }
        finally
        {
            a.recycle();
        }
        init();
    }
    private void init() {
        inflate(getContext(), R.layout.stepperlayout, this);
        mLinearLayout = (ViewGroup)findViewById(R.id.StepperView);
    }

    public void setStops(String[] _stops){
        stops = _stops;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params.topMargin = -125;
        for(int i=0;i<_stops.length;i++){
            View v = inflater.inflate(R.layout.stepperrow, null);
            ((TextView)(v.findViewById(R.id.txtStop))).setText(_stops[i]);
            if(i!=0)
                mLinearLayout.addView(v,params);
            else
                mLinearLayout.addView(v);

        }
    }
}
