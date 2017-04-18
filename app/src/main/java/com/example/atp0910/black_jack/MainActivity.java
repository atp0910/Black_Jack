package com.example.atp0910.black_jack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;

public class MainActivity extends Activity
{
    private Button playSimpleButton;
    private Button playCasinoButton;
    private Button exitButton;
    private ImageView blackjackImage;
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
        playSimpleButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, BlackJackSimple.class);
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
                startActivity(intent);
                finish();
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
}