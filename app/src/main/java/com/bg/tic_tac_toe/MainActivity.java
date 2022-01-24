package com.bg.tic_tac_toe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    //State Meanings
    //0 - X
    //1 - O
    //2 - NULL
    int activePlayer = 0;
    int [] gameState = {2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2};
    int [] [] winPositions = {{0 ,1 ,2},{3 , 4 ,5},{6, 7 , 8},
            {0 , 3 , 6},{1, 4, 7},{2, 5, 8},
            {0, 4, 8},{2, 4, 6}};

    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to Play");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //Check if any player has won
        for(int [] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2){
                //someone has won!
                String winnerStr;
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "X has won.";
                }else{
                    winnerStr = "O has won.";
                }
                //Updating status announcement with winner Status.
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
        boolean emptySquare = false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }
        if (!emptySquare && gameActive) {
            // Game is a draw
            gameActive = false;
            String winnerStr;
            winnerStr = "Game is Draw. Tap to play Again.";
            TextView status = findViewById(R.id.status);
            status.setText(winnerStr);
        }
    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i = 0; i<gameState.length; i++){
            gameState[i]= 2;
        }
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to Play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.howToPlay:
                Intent launchNewIntent1 = new Intent(MainActivity.this,howToPlay.class);
                startActivityForResult(launchNewIntent1, 0);
                break;
            case R.id.info:
                Intent launchNewIntent2 = new Intent(MainActivity.this,info.class);
                startActivityForResult(launchNewIntent2, 0);
                break;
            case R.id.share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);

                // type of the content to be shared
                sharingIntent.setType("text/plain");

                // Body of the content
                String shareBody = "Hey, check out this game. https://www.mediafire.com/file/z2j25tmf98d5vo4/Tic-Tac-Toe.apk/file ";

                // subject of the content. you can share anything
                String shareSubject = "Check this out!";

                // passing body of the content
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

                // passing subject of the content
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;
        }
        return true;
    }

}