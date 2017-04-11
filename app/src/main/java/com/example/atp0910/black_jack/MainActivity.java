package com.example.atp0910.black_jack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity
{
    Button newGameButton;
    Button hitButton;
    Button stayButton;
    private Player player;
    private Dealer dealer;
    private ImageView [] playerCards;
    private ImageView [] dealerCards;
    private TextView notification;
    private TextView playerScore;
    private TextView dealerScore;
    private Card c;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newGameButton = (Button) findViewById(R.id.newGameButton);
        hitButton = (Button) findViewById(R.id.hitButton);
        stayButton = (Button) findViewById(R.id.stayButton);
        notification = (TextView) findViewById(R.id.notificationTextView);
        playerScore = (TextView) findViewById(R.id.playerScore);
        dealerScore = (TextView) findViewById(R.id.dealerScore);
        player = new Player("Player");
        dealer = new Dealer("Dealer");
        playerCards = new ImageView [5];
        dealerCards = new ImageView [5];
        playerCards[0] = (ImageView) findViewById(R.id.playerCard0);
        playerCards[1] = (ImageView) findViewById(R.id.playerCard1);
        playerCards[2] = (ImageView) findViewById(R.id.playerCard2);
        playerCards[3] = (ImageView) findViewById(R.id.playerCard3);
        playerCards[4] = (ImageView) findViewById(R.id.playerCard4);
        dealerCards[0] = (ImageView) findViewById(R.id.dealerCard0);
        dealerCards[1] = (ImageView) findViewById(R.id.dealerCard1);
        dealerCards[2] = (ImageView) findViewById(R.id.dealerCard2);
        dealerCards[3] = (ImageView) findViewById(R.id.dealerCard3);
        dealerCards[4] = (ImageView) findViewById(R.id.dealerCard4);
        hitButton.setClickable(false);
        stayButton.setClickable(false);
        newGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                initialCards();
                hitButton.setClickable(true);
                stayButton.setClickable(true);
            }
        });
        hitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                  hit(player);
            }
        });
        stayButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                stay();
            }
        });
    }
    public void initialCards()
    {
        notification.setText("");
        playerScore.setText("0");
        dealerScore.setText("0");
        checkDeck();
        for(int i = 0; i < 5; i++)
        {
            playerCards[i].setImageResource(R.drawable.back_blue);
            dealerCards[i].setImageResource(R.drawable.back_blue);
        }
        player.newHand();
        dealer.newHand();
        hit(player);
        hit(dealer);
        hit(player);
        hit(dealer);
        dealerCards[1].setImageResource(R.drawable.back_blue);
        dealerScore.setText("");
        if(blackJack(dealer))
        {
            dealerCards[1].setImageResource(getResources().getIdentifier(dealer.getHand().getCard(1).toString(),"drawable", getApplicationContext().getPackageName()));
            notification.setText(dealer.getName()+" has BlackJack!");
        }
        else if(blackJack(player))
        {
            notification.setText(player.getName()+" has BlackJack!");
        }
    }
    public void setCardImages(Person p)
    {
        for(int i = 0; i < p.getHand().getCount(); i++)
        {
            c = p.getHand().getCard(i);
            if(p instanceof Player)
            {
                playerCards[i].setImageResource(getResources().getIdentifier(c.toString(),"drawable", getApplicationContext().getPackageName()));
            }
            else if(p instanceof Dealer)
            {
                dealerCards[i].setImageResource(getResources().getIdentifier(c.toString(),"drawable", getApplicationContext().getPackageName()));
            }
            else
            {
                notification.setText("Error Loading Card Image");
            }
        }
    }
    public void hit(Person p)
    {
        c = dealer.dealCard();
        p.hit(c);
        setCardImages(p);
        updateScore(p);
        if(bust(p))
        {
            notification.setText(p.getName()+" Busts!");
        }
        else if(charlie(p))
        {
            notification.setText(p.getName()+" has 5 Card Charlie!");
        }
    }
    public void stay()
    {
        hitButton.setClickable(false);
        stayButton.setClickable(false);
        setCardImages(dealer);
        updateScore(dealer);
        while(dealer.getScore() < 17)
        {
            hit(dealer);
        }
        checkWinner();
    }
    public void updateScore(Person p)
    {
        if(p instanceof Player)
        {
            playerScore.setText(Integer.toString(player.getScore()));
        }
        else if(p instanceof Dealer)
        {
            dealerScore.setText(Integer.toString(dealer.getScore()));
        }
        else
        {
            notification.setText("Error updating Score: "+p.toString());
        }
    }
    public boolean bust(Person p)
    {
        if(p.getScore() > 21)
        {
            hitButton.setClickable(false);
            stayButton.setClickable(false);
            return true;
        }
        return false;
    }
    public boolean blackJack(Person p)
    {
        if(p.getHand().getCount() == 2 && p.getScore() == 21)
        {
            hitButton.setClickable(false);
            stayButton.setClickable(false);
            return true;
        }
        return false;
    }
    public boolean charlie(Person p)
    {
        if(p.getHand().getCount() >= 5 && p.getScore() <= 21)
        {
            hitButton.setClickable(false);
            stayButton.setClickable(false);
            return true;
        }
        return false;
    }
    public void checkDeck()
    {
        if(dealer.getDeck().getNumCards() <= 20)
        {
            dealer.freshDeck();
        }
    }
    public void win()
    {
        notification.append("\n"+player.getName()+" Wins!");
    }
    public void lose()
    {
        notification.append("\n"+player.getName()+" Loses.");
    }
    public void checkWinner()
    {
        hitButton.setClickable(false);
        stayButton.setClickable(false);
        if(player.getScore() > dealer.getScore())
        {

        }
    }
}
