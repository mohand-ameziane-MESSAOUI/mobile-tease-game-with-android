package upec.projetandroid2017_2018;

import android.content.ClipData;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {
    int t = 0;
    int n =5;
    int z = 0;
    int clX = 0;
    int clY = 0;
    int clX1 = 0;
    int clY1 = 0;
    int score =100;

    Button restart;
    Chronometer simpleChronometer;
    GridLayout ly;
    View.OnClickListener ocl;
    View.OnClickListener ota;

    Button[][] buttons = new Button[n][n];
    ArrayList l = new ArrayList();
    ArrayList tabR = new ArrayList();
    Random r = new Random();
    View.OnTouchListener otl;
    OnDragListener odl;
    View.OnLongClickListener locl;
    ImageButton aide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        restart = (Button) findViewById(R.id.restart);
        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        simpleChronometer.start();
        Intent it;
        it = getIntent();
        n = it.getIntExtra("tail", choix_niveau.taille);

        for (int i = 1; i < (n * n) ; i++) {
            tabR.add(i);
        }
        java.util.Collections.shuffle(tabR);
        ly = (GridLayout) findViewById(R.id.gridL);
        ly.setRowCount(n);
        ly.setColumnCount(n);

        aide = (ImageButton) findViewById(R.id.aide);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game.this,choix_niveau.class);
                startActivity(intent);
            }
        });

       ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("coucou");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (buttons[i][j] != null) {
                            if (buttons[i][j] == v) {
                                clX = i;
                                clY = j;

                            }
                        }
                    }
                }
                ButtonLIs2(clX,clY);
                if(gagner()){
                    TextView t = (TextView)findViewById(R.id.score);

                    LinearLayout l = (LinearLayout) findViewById(R.id.layout);
                    l.setVisibility(View.VISIBLE);
                    t.setText("    "+score+"%");
                }
            }
        };
        //creatbutton(ocl);


      //  ajoutlayout();


        otl= new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ClipData data= ClipData.newPlainText("","");
                View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, myShadowBuilder, v, 0);
                return true;

            }
        };


        ota= new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int z=0;
                int prob1=0;
                int prob2=0;
                int prob3=0;
                int prob4=0;
                String p;
                boolean stop=false;
                if(score >=0){
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (buttons[i][j] != null&&stop==false) {
                            z++;
                            String s = Integer.toString(z);
                            if (!(buttons[i][j].getText().equals(s))){
                                prob1 =i;
                                prob2=j;
                                p = buttons[i][j].getText().toString();
                                stop=true;
                            }

                            }
                        }
                    }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (buttons[i][j] != null) {
                            String s = Integer.toString(z);
                            if (buttons[i][j].getText().equals(s)){
                                prob3 =i;
                                prob4=j;
                            }

                        }
                    }
                }
                System.out.println("jai aider frero");
                permute(prob1,prob2,prob3,prob4);

                }
            else{
                view.setVisibility(View.INVISIBLE);
                }
            }

        };
        aide.setOnClickListener(ota);

        odl = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragEvent = event.getAction();
                Button b = (Button) event.getLocalState();
                //System.out.println("coucou drag");
                switch (dragEvent){
                    case DragEvent.ACTION_DRAG_ENTERED:
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n; j++) {
                                if (buttons[i][j] != null) {
                                    if (buttons[i][j] == v) {
                                        clX = i;
                                        clY = j;

                                    }
                                }
                            }
                        }
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n; j++) {
                                if (buttons[i][j] != null) {
                                    if (buttons[i][j] == b) {
                                        clX1 = i;
                                        clY1 = j;

                                    }
                                }
                            }
                        }
                        ButtonLIs(clX,clY,clX1, clY1);
                        if(gagner()){
                            TextView t = (TextView)findViewById(R.id.score);

                        LinearLayout l = (LinearLayout) findViewById(R.id.layout);
                        l.setVisibility(View.VISIBLE);
                            t.setText("    "+score+"%");
                        }





                }
                return true;
            }
        };

            creatbutton(otl);


        ajoutlayout();

    }






    public void creatbutton(View.OnTouchListener otl){
        if (n==3)t=200;
        if (n==4)t=150;
        if (n==5)t=125;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!(i==n-1&&j==n-1)) {
                    Button tb = new Button(this);

                    z= z+1;
                    tb.setId(z);
                    System.out.println("mon id est "+z);
                    tb.setText(""+tabR.get(z-1));
                    tb.setWidth(GridLayout.LayoutParams.WRAP_CONTENT);
                    tb.setMinimumWidth(t);
                    tb.setWidth(t);
                    tb.setHeight(t);
                    if(MainActivity.Touch==true){
                    tb.setOnTouchListener(otl);}
                    else{
                    tb.setOnClickListener(ocl);}
                    tb.setVisibility(View.VISIBLE);
                    buttons[i][j]=tb;
                    //l.add(tb);
                    }
                else {
                    Button tb = new Button(this);
                    tb.setId(z+1);
                    System.out.println("mon id est "+z);
                    tb.setText("moh");
                    tb.setWidth(GridLayout.LayoutParams.WRAP_CONTENT);
                    tb.setMinimumWidth(t);
                    tb.setWidth(t);
                    tb.setHeight(t);
                    if(MainActivity.Touch==true){
                    tb.setOnDragListener(odl);}
                    tb.setBackground(null);
                    tb.setVisibility(View.VISIBLE);
                   // tb.setHighlightColor(3);

                   buttons[i][j]=tb;
                    //l.add(tb);
                        }
            }}
    }
    public void ajoutlayout(){
        int x =0;
        ly.removeAllViews();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){


                Button tb = (Button) buttons[i][j];
                ly.addView(tb,x);
                x++;
            }}
    }

    public boolean gagner(){
        int z=1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!(i==n-1&&j==n-1)) {
                    String s = Integer.toString(z);
                    if (!(buttons[i][j].getText().equals(s))){

                        return false;
                    }
                    z=z+1;
                }}
        }

        return true;
    }
    public void ButtonLIs(int clX,int clY,int clX1,int clY1) {
        if((clX==clX1 && clY==clY1+1)|| (clX==clX1 && clY==clY1-1)  || (clX==clX1+1 && clY==clY1)|| (clX==clX1-1 && clY==clY1)){

        Button bi =buttons[clX][clY];
        buttons[clX][clY] =buttons[clX1][clY1] ;
        buttons[clX1][clY1] =bi;
            ajoutlayout();}}

public void ButtonLIs2(int clX,int clY) {
    if (clX < n - 1)
        if (buttons[clX + 1][clY].getText() == "moh")

        {
            Button bt = buttons[clX][clY];
            buttons[clX][clY] = buttons[clX + 1][clY];
            buttons[clX + 1][clY] = bt;
        }
    if (clX > 0)
        if (buttons[clX - 1][clY].getText() == "moh")

        {
            Button bt = buttons[clX][clY];
            buttons[clX][clY] = buttons[clX - 1][clY];
            buttons[clX - 1][clY] = bt;
        }
    if (clY < n - 1)
        if (buttons[clX][clY + 1].getText() == "moh")

        {
            Button bt = buttons[clX][clY];
            buttons[clX][clY] = buttons[clX][clY + 1];
            buttons[clX][clY + 1] = bt;
        }
    if (clY > 0)
        if (buttons[clX][clY - 1].getText() == "moh")

        {
            Button bt = buttons[clX][clY];
            buttons[clX][clY] = buttons[clX][clY - 1];
            buttons[clX][clY - 1] = bt;
        }


    ajoutlayout();
}




    public void permute(int clX,int clY,int clX1,int clY1){

score=score-10;
                    Button bi =buttons[clX][clY];
                    buttons[clX][clY] =buttons[clX1][clY1] ;
                    buttons[clX1][clY1] =bi;
        ajoutlayout();
        if(gagner()){
            simpleChronometer.stop();

            TextView t = (TextView)findViewById(R.id.score);


           long   timer = SystemClock.elapsedRealtime()-simpleChronometer.getBase();

      if((timer/1000)>30){
          if((timer/1000)>60)
            score = score-10;
          score=score-5;

            }
            LinearLayout l = (LinearLayout) findViewById(R.id.layout);
            l.setVisibility(View.VISIBLE);
            t.setText("    "+score+"%");


        }
    }

};






