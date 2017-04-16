package com.example.atp0910.black_jack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
public class BlackJackSimple extends Activity
{
    private Button newGameButton;
    private Button hitButton;
    private Button stayButton;
    private Button backButton;
    private Player player;
    private Dealer dealer;
    private ImageView [] playerCards;
    private ImageView [] dealerCards;
    private TextView playerNotification;
    private TextView dealerNotification;
    private TextView playerScore;
    private TextView dealerScore;
    private TextView numWins;
    private TextView numLoss;
    private int wins;
    private int losses;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_game);
        wins = 0;
        losses = 0;
        newGameButton = (Button) findViewById(R.id.newGameButton);
        hitButton = (Button) findViewById(R.id.hitButton);
        stayButton = (Button) findViewById(R.id.stayButton);
        backButton = (Button) findViewById(R.id.backButton);
        playerNotification = (TextView) findViewById(R.id.playerNotification);
        dealerNotification = (TextView) findViewById(R.id.dealerNotification);
        playerScore = (TextView) findViewById(R.id.playerScore);
        dealerScore = (TextView) findViewById(R.id.dealerScore);
        numWins = (TextView) findViewById(R.id.numberWins);
        numLoss = (TextView) findViewById(R.id.numberlosses);
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
        colorButtonText();
        newGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hitButton.setClickable(true);
                stayButton.setClickable(true);
                colorButtonText();
                initialCards();
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
                hitButton.setClickable(false);
                stayButton.setClickable(false);
                colorButtonText();
                stay();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(BlackJackSimple.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void initialCards()
    {
        playerNotification.setText("");
        dealerNotification.setText("");
        playerScore.setText("0");
        dealerScore.setText("0");
        checkDeck();
        for(int i = 0; i < 5; i++)
        {
            playerCards[i].setImageResource(R.color.game_green);
            dealerCards[i].setImageResource(R.color.game_green);
        }
        player.newHand();
        dealer.newHand();
        hit(player);
        hit(dealer);
        hit(player);
        hit(dealer);
        dealerCards[1].setImageResource(R.drawable.back_blue);
        dealerScore.setText("");
        newGameButton.setText("Forfeit");
        newGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hitButton.setClickable(false);
                stayButton.setClickable(false);
                colorButtonText();
                forfeit();
            }
        });
        if(blackJack(player))
        {
            playerNotification.setText(player.getName()+" has BlackJack!");
            setCardImages(dealer);
            checkWinner();
        }
    }
    public void forfeit()
    {
        playerNotification.setText("Player Forfeits.");
        dealerNotification.setText("");
        lose();
        newGameButton.setText("Deal");
        newGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hitButton.setClickable(true);
                stayButton.setClickable(true);
                colorButtonText();
                initialCards();
            }
        });
    }
    public void setCardImages(Person p)
    {
        for(int i = 0; i < p.getHand().getCount(); i++)
        {
            Card c = p.getHand().getCard(i);
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
                playerNotification.setText("Error Loading Card Image");
            }
        }
    }
    public void hit(Person p)
    {
        Card c = dealer.dealCard();
        p.hit(c);
        setCardImages(p);
        updateScore(p);
        if(bust(p))
        {
            if(p instanceof Player)
            {
                playerNotification.setText(p.getName() + " Busts!");
                checkWinner();
            }
            else
            {
                dealerNotification.setText(p.getName() + " Busts!");
            }
        }
        else if(charlie(p))
        {
            if(p instanceof Player)
            {
                playerNotification.setText(p.getName() + " has 5 Card Charlie!");
                checkWinner();
            }
            else
            {
                dealerNotification.setText(p.getName() + " has 5 Card Charlie!");
            }
        }
    }
    public void stay()
    {
        dealerNotification.setText("");
        setCardImages(dealer);
        updateScore(dealer);
        if(blackJack(dealer))
        {
            dealerCards[1].setImageResource(getResources().getIdentifier(dealer.getHand().getCard(1).toString(),"drawable", getApplicationContext().getPackageName()));
            dealerNotification.setText(dealer.getName()+" has BlackJack!");
        }
        else
        {
            while (dealer.getScore() < 17)
            {
                hit(dealer);
            }
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
            playerNotification.setText("Error updating Score: "+p.toString());
        }
    }
    public boolean bust(Person p)
    {
        if(p.getScore() > 21)
        {
            setCardImages(dealer);
            return true;
        }
        return false;
    }
    public boolean blackJack(Person p)
    {
        if(p.getHand().getCount() == 2 && p.getScore() == 21)
        {
            setCardImages(dealer);
            return true;
        }
        return false;
    }
    public boolean charlie(Person p)
    {
        if(p.getHand().getCount() >= 5 && p.getScore() <= 21)
        {
            setCardImages(dealer);
            return true;
        }
        return false;
    }
    public void checkDeck()
    {
        if(dealer.getDeck().getNumCards() <= 20)
        {
            dealer.freshDeck();
            dealerNotification.setText("Deck Below 75%.\nDealer has changed decks.");
        }
    }
    public void win()
    {
        playerNotification.append("\n"+player.getName()+" Wins!");
        dealerNotification.append("\n"+dealer.getName()+" Loses.");
        player.addWin();
        numWins.setText(Integer.toString(player.getWins()));
    }
    public void lose()
    {
        playerNotification.append("\n"+player.getName()+" Loses.");
        dealerNotification.append("\n"+dealer.getName()+" Wins!");
        player.addLoss();
        numLoss.setText(Integer.toString(player.getLosses()));
    }
    public void checkWinner()
    {
        hitButton.setClickable(false);
        stayButton.setClickable(false);
        colorButtonText();
        if(charlie(dealer))
        {
            lose();
        }
        else if(blackJack(dealer))
        {
            lose();
        }
        else if(bust(player))
        {
            lose();
        }
        else if(blackJack(player))
        {
            win();
        }
        else if(charlie(player))
        {
            win();
        }
        else if(bust(dealer) && !bust(player))
        {
            win();
        }
        else if(player.getScore() > dealer.getScore() && !bust(player))
        {
            win();
        }
        else
        {
            lose();
        }
        newGameButton.setText("Deal");
        newGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hitButton.setClickable(true);
                stayButton.setClickable(true);
                colorButtonText();
                initialCards();
            }
        });
    }
    public void colorButtonText()
    {
        if(!hitButton.isClickable())
        {
            hitButton.setTextColor(getResources().getColor(R.color.unclickable_text_color));
        }
        else
        {
            hitButton.setTextColor(getResources().getColor(R.color.player_text_color));
        }
        if(!stayButton.isClickable())
        {
            stayButton.setTextColor(getResources().getColor(R.color.unclickable_text_color));
        }
        else
        {
            stayButton.setTextColor(getResources().getColor(R.color.player_text_color));
        }
    }
}