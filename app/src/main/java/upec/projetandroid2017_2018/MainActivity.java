package upec.projetandroid2017_2018;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private LinearLayout linearLayout ;
    private ConstraintLayout main ;
    private Button setting;
    private Button play;
    private Switch Smusic , Stouch;
    private Switch Ssound;
    private MediaPlayer mp;
    public static MediaPlayer mpson;
    public static boolean Sound ;
    public static boolean Touch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Sound = true;
        Touch = true;
        linearLayout = (LinearLayout) findViewById(R.id.layout);
        setting  = (Button) findViewById(R.id.setting);
        play  = (Button) findViewById(R.id.jeu);
        Smusic = (Switch) findViewById(R.id.switch2);
        Ssound = (Switch) findViewById(R.id.switch1);
        Stouch = (Switch) findViewById(R.id.Stouch);
        mp = MediaPlayer.create(getApplicationContext(),R.raw.my_sound);
        mpson = MediaPlayer.create(getApplicationContext(),R.raw.sound_click);
        main = (ConstraintLayout)findViewById(R.id.main);
        mp.start();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, choix_niveau.class);
                startActivity(intent);
            }
        });



        Stouch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (Stouch.isChecked()){
                    Touch=true;
                }
                else{
                    Touch=false;
                }
            }

        });

        Smusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (Smusic.isChecked()){
                    mp.start();
                }
                else{
                    mp.pause();
                }
            }

        });

        Ssound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (Ssound.isChecked()){
                    mpson.start();
                }
            }
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setVisibility(View.INVISIBLE);
                setting.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
            }
        });
    }

    public void getSetting(View view) {
        linearLayout.setVisibility(View.VISIBLE);
        setting.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);
    }

    public void changeSound(View view){
        mpson.start();
    }

}