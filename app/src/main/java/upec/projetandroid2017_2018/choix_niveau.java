package upec.projetandroid2017_2018;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class choix_niveau extends AppCompatActivity {
public static int taille=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_niveau);
        final ImageButton prev = (ImageButton) findViewById(R.id.p);
        final Button b = (Button) findViewById(R.id.fac);
        final Button b1 = (Button) findViewById(R.id.moy);
        final Button b3 = (Button) findViewById(R.id.diff);


        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choix_niveau.this, Game.class);
                if (v == b){
                    taille=3;
                    intent.putExtra("tail",taille);

                    startActivity(intent);}
                if (v == b1){
                    taille=4;
                    intent.putExtra("tail",taille);
                    startActivity(intent);}
                if (v == b3){
                    taille=5;
                    intent.putExtra("tail",taille);
                    startActivity(intent);}

                }
            };

        b.setOnClickListener(ocl);
        b1.setOnClickListener(ocl);
        b3.setOnClickListener(ocl);


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(choix_niveau.this,MainActivity.class);
                startActivity(intent);
            }
        });





    }



    public void changeSound(View view){
        MainActivity.mpson.start();
    }
}
