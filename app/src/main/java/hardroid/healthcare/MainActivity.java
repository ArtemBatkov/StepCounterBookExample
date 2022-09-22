package hardroid.healthcare;

import android.animation.Animator;

import android.content.Intent;




import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

//




   private void gotoAppMenu(){
       try{
           Intent intent = new Intent(MainActivity.this, Coordinator.class);
           startActivity(intent);
           finish();
       }
       catch (Exception e){};
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random random = new Random();
        LottieAnimationView preview = (LottieAnimationView) findViewById(R.id.preview);
        preview.setAnimation(R.raw.anim_coord);//AnimationNum
        preview.playAnimation();

        preview.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                            gotoAppMenu();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });





































    }
}



