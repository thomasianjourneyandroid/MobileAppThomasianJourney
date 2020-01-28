package mobile.thomasianJourney.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import mobile.thomasianJourney.main.vieweventsfragments.R;


public class ScanSuccess extends AppCompatActivity {
    private Button vhome_btn;
    private Button vport_btn;
    private LottieAnimationView LottieScan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_success);

        //ANIMATION LOTTIE
        LottieScan = findViewById(R.id.mainlottieScan);

        LottieScan.setScale(7f);
        LottieScan.setVisibility(View.VISIBLE);
        LottieScan.setAnimation(R.raw.qr);
        LottieScan.playAnimation();

//        vhome_btn = (Button) findViewById(R.id.vhome_btn);
//        vhome_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openSecondActivity();
//            }
//        });
//
//        vport_btn = (Button) findViewById(R.id.vport_btn);
//        vport_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openPortfolio();
//            }
//        });
    }
//    public void openSecondActivity() {
//        Intent intent = new Intent(this,SecondActivity.class);
//        startActivity(intent);
//        finish();
//    }

//    public void openPortfolio() {
//        Intent intent = new Intent(this,Portfolio.class);
//        startActivity(intent);
//        finish();
//    }

    //ANIMATION

    public void HomeAnim(View view) {
        if (view == findViewById(R.id.vhome_btn)) {
            //back to home
            startActivity(new Intent(this, SecondActivity.class));
            //add animation
            Animatoo.animateCard(this);
            finish();
        }
    }

    public void PortAnim(View view) {
        if (view == findViewById(R.id.vport_btn)) {
            //go to portfolio
            startActivity(new Intent(this, Portfolio.class));
            //add animation
            Animatoo.animateCard(this);
            finish();
        }
    }
}
