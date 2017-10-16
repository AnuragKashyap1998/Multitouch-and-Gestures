package anurag.myapplication;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    HashMap<Integer,View> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         map=new HashMap<>();
        relativeLayout=(RelativeLayout) findViewById(R.id.relativeLayout);
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int index=motionEvent.getActionIndex();
                int id=motionEvent.getPointerId(index);

                if(motionEvent.getActionMasked()==MotionEvent.ACTION_DOWN)
                {
                    float x=motionEvent.getX(index);
                    float y=motionEvent.getY(index);
                    View newView=new View(MainActivity.this);
                    newView.setBackgroundColor(Color.BLUE);
                    newView.setLayoutParams(new LinearLayoutCompat.LayoutParams(200,200));
                    newView.setX(x-100);
                    newView.setY(y-100);
                    map.put(id,newView);
                    relativeLayout.addView(newView);
                }
                else if(motionEvent.getActionMasked()==MotionEvent.ACTION_POINTER_DOWN)
                {
                    float x=motionEvent.getX(index);
                    float y=motionEvent.getY(index);
                    View newView=new View(MainActivity.this);
                    newView.setBackgroundColor(Color.BLUE);
                    newView.setLayoutParams(new LinearLayoutCompat.LayoutParams(200,200));
                    newView.setX(x-100);
                    newView.setY(y-100);
                    map.put(id,newView);
                    relativeLayout.addView(newView);
                }else if(motionEvent.getActionMasked()==MotionEvent.ACTION_POINTER_UP)
                {
                    View viewRemoved=map.get(id);
                    map.remove(id);
                    relativeLayout.removeView(viewRemoved);
                }else if(motionEvent.getActionMasked()==MotionEvent.ACTION_UP)
                {
                    View viewRemoved=map.get(id);
                    map.remove(id);
                    relativeLayout.removeView(viewRemoved);
                }else if(motionEvent.getActionMasked()==MotionEvent.ACTION_MOVE)
                {
                    for(int i=0;i<motionEvent.getPointerCount();i++)
                    {
                        //int index1=motionEvent.getActionIndex();
                        View newView=map.get(motionEvent.getPointerId(i));
                        float x=motionEvent.getX(i);
                        float y=motionEvent.getY(i);
                        newView.setX(x-100);
                        newView.setY(y-100);
                    }
                }
                return true;
            }
        });
    }
}
