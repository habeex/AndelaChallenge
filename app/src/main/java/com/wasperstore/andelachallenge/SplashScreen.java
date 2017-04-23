package com.wasperstore.andelachallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final ImageView imageView = (ImageView) findViewById(R.id.rot);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        imageView.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(an2);
                finish();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_slide_in_frot_left, R.anim.abc_fade_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
