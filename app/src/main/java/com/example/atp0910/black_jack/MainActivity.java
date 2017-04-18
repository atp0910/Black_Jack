package com.example.atp0910.black_jack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.media.MediaPlayer;

public class MainActivity extends Activity
{
    private Button playSimpleButton;
    private Button playCasinoButton;
    private Button exitButton;
    private ImageView blackjackImage;
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playSimpleButton = (Button) findViewById(R.id.playSimpleButton);
        playCasinoButton = (Button) findViewById(R.id.playCasinoButton);
        exitButton = (Button) findViewById(R.id.exitButton);
        blackjackImage = (ImageView) findViewById(R.id.blackjackImage);
        blackjackImage.setImageResource(R.drawable.blackjack);
        mp = MediaPlayer.create(this, R.raw.game_menu);
        mp.setLooping(true);
        mp.start();
        playSimpleButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, BlackJackSimple.class);
                mp.stop();
                startActivity(intent);
                finish();
            }
        });
        playCasinoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, BlackJackCasino.class);
                mp.stop();
                startActivity(intent);
                finish();
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mp.stop();
                finish();
            }
        });
    }
}