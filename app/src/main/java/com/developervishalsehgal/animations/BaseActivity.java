package com.developervishalsehgal.animations;

import android.animation.Animator;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void circularReveal(final int id) {
        //Applying the circular reveal effect on the activity.
        final View rootLayout = findViewById(id);
        rootLayout.setVisibility(View.INVISIBLE);


        ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    rootLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                    int centerX = (rootLayout.getLeft() + rootLayout.getRight()) / 2;
                    int centerY = (rootLayout.getTop() + rootLayout.getBottom()) / 2;

                    int startRadius = 0;
                    int endRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

                    Animator animator = ViewAnimationUtils.createCircularReveal(rootLayout, centerX, centerY, startRadius, endRadius);
                    animator.setDuration(1000);
                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            if (id == R.id.splash_layout) {
                                startActivity(new Intent(getApplication(),MainActivity.class));
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    rootLayout.setVisibility(View.VISIBLE);
                    animator.start();
                }
            });
        }


    }

}
