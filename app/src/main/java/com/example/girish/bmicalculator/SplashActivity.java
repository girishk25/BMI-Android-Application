package com.example.girish.bmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    TextView tv1;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv1=findViewById(R.id.tv1);
        animation = AnimationUtils.loadAnimation(this,R.anim.a1);
        tv1.startAnimation(animation);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                Intent a = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(a);
                finish();;
            }

        }).start();
    }
}
