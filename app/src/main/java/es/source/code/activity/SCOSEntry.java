package es.source.code.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import cristo.example.com.myapplication.R;

public class SCOSEntry extends AppCompatActivity {

    float  XDown;
    private VelocityTracker vt;
    private Context mycontext;
    private View welcome;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);

    }
    public boolean  onTouchEvent(MotionEvent event) {

        createVelocityTracher(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                XDown =event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                float XMove=event.getRawX();
                int distanceX =(int)(XDown-XMove);
                int XSpeed=getScrollVelocity();
                if (distanceX>200&&XSpeed>150){
                    mycontext=this;
                    welcome=findViewById(R.id.welcome);

//                    sharedPreferences=getSharedPreferences("count",MODE_PRIVATE);
//                    int count=sharedPreferences.getInt("count",0);
//                    Log.d("print",String.valueOf(count));
//                    if(count==0) {
                        Intent intent = new Intent(mycontext, MainScreen.class);
                        intent.putExtra("stringInScosEntry", "FromEntry");
                        startActivity(intent);
                        this.finish();
//                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return  true;
    }
    private void createVelocityTracher(MotionEvent event){
        if (vt==null){
            vt=VelocityTracker.obtain();
        }
        vt.addMovement(event);
    }
    private int getScrollVelocity() {
        vt.computeCurrentVelocity(1000);
        int v=(int)vt.getXVelocity();
        return Math.abs(v);
    }
}
