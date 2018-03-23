package com.developervishalsehgal.animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class SplashScreen extends BaseActivity {

    View dot1, dot2, dot3;
    AnimatorSet anim1, anim2, anim3, finalSet, mainSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        mainSet = new AnimatorSet();

        dotsAnimation(1, true);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
            }
        }, 4000);


    }

    private void initViews() {
        dot1 = findViewById(R.id.dot_one);
        dot1.setScaleX(0);
        dot1.setScaleY(0);
        dot1.setVisibility(View.VISIBLE);

        dot2 = findViewById(R.id.dot_two);
        dot2.setScaleX(0);
        dot2.setScaleY(0);
        dot2.setVisibility(View.VISIBLE);

        dot3 = findViewById(R.id.dot_three);
        dot3.setScaleX(0);
        dot3.setScaleY(0);
        dot3.setVisibility(View.VISIBLE);
    }

    private void dotsAnimation(float value, boolean order) {
        Interpolator interpolator = new FastOutSlowInInterpolator();
        long duration = 375L;
        long delay = duration / 3;

        anim1 = new AnimatorSet();
        anim1.play(ObjectAnimator.ofFloat(dot1, "scaleX", value))
                .with(ObjectAnimator.ofFloat(dot1, "scaleY", value));
        anim1.setDuration(duration);
        anim1.setInterpolator(interpolator);
        anim1.setStartDelay(delay);
        anim2 = new AnimatorSet();
        anim2.play(ObjectAnimator.ofFloat(dot2, "scaleX", value))
                .with(ObjectAnimator.ofFloat(dot2, "scaleY", value));
        anim2.setDuration(duration);
        anim2.setInterpolator(interpolator);
        anim2.setStartDelay(delay * 2);
        anim3 = new AnimatorSet();
        anim3.play(ObjectAnimator.ofFloat(dot3, "scaleX", value))
                .with(ObjectAnimator.ofFloat(dot3, "scaleY", value));
        anim3.setDuration(duration);
        anim3.setInterpolator(interpolator);
        anim3.setStartDelay(delay * 3);

        finalSet = new AnimatorSet();

        finalSet.play(anim1).with(anim2).with(anim3);
        finalSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                initViews();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                dot1.setVisibility(View.INVISIBLE);
                dot2.setVisibility(View.INVISIBLE);
                dot3.setVisibility(View.INVISIBLE);
                dotsAnimation(1, true);
            }
        });
        finalSet.start();
    }

}
