package com.example.atp0910.black_jack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;

public class BlackJackCasino extends Activity
{
    private Button newGameButton;
    private Button backButton;
    private Button hitButton;
    private Button stayButton;
    private Button ddButton;
    private Button betButton;
    private Button splitButton;
    private Player player;
    private Player player2;
    private Dealer dealer;
    private ImageView [] playerCards;
    private ImageView [] playerSplitCards;
    private ImageView [] dealerCards;
    private ImageView chipsImage;
    private ImageView potImage;
    private TextView notification;
    private TextView dealerNotification;
    private TextView playerNotification;
    private TextView playerScore;
    private TextView dealerScore;
    private TextView playerMoneyAmount;
    private TextView potAmount;
    private double pot;
    private boolean split;
    private boolean splitScore;
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.casino_game);
        player = new Player("Player");
        player2 = new Player("Player2");
        dealer = new Dealer("Dealer");
        player2.done = true;
        split = false;
        splitScore = false;
        playerCards = new ImageView [5];
        playerSplitCards = new ImageView[5];
        dealerCards = new ImageView [5];
        newGameButton = (Button) findViewById(R.id.newGameButton);
        hitButton = (Button) findViewById(R.id.hitButton);
        stayButton = (Button) findViewById(R.id.stayButton);
        backButton = (Button) findViewById(R.id.backButton);
        betButton = (Button) findViewById(R.id.betButton);
        splitButton = (Button) findViewById(R.id.splitButton);
        ddButton = (Button) findViewById((R.id.dDownButton));
        notification = (TextView) findViewById(R.id.notification);
        dealerNotification = (TextView) findViewById(R.id.dealerNotification);
        playerNotification = (TextView) findViewById(R.id.playerNotification);
        playerScore = (TextView) findViewById(R.id.playerScore);
        dealerScore = (TextView) findViewById(R.id.dealerScore);
        chipsImage = (ImageView) findViewById(R.id.chips);
        potImage = (ImageView) findViewById(R.id.pot);
        chipsImage.setImageResource(R.drawable.chips);
        potImage.setImageResource(R.drawable.pot);
        playerMoneyAmount = (TextView) findViewById(R.id.playerMoneyAmount);
        potAmount = (TextView) findViewById(R.id.potAmount);
        playerMoneyAmount.setText(Double.toString(player.getMoneyAmount()));
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
        playerSplitCards[0] = (ImageView) findViewById(R.id.playerSplitCard0);
        playerSplitCards[1] = (ImageView) findViewById(R.id.playerSplitCard1);
        playerSplitCards[2] = (ImageView) findViewById(R.id.playerSplitCard2);
        playerSplitCards[3] = (ImageView) findViewById(R.id.playerSplitCard3);
        playerSplitCards[4] = (ImageView) findViewById(R.id.playerSplitCard4);
        betButton.setClickable(false);
        splitButton.setClickable(false);
        hitButton.setClickable(false);
        stayButton.setClickable(false);
        ddButton.setClickable(false);
        colorButtonText();
        mp = MediaPlayer.create(this, R.raw.play_game);
        mp.setLooping(true);
        mp.start();
        newGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                betButton.setClickable(true);
                splitButton.setClickable(false);
                hitButton.setClickable(false);
                stayButton.setClickable(false);
                ddButton.setClickable(false);
                colorButtonText();
                startGame();

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(BlackJackCasino.this, MainActivity.class);
                mp.stop();
                startActivity(intent);
                finish();
            }
        });
    }
    public void startGame()
    {
        if(player.getMoneyAmount() < 5)
        {
            notification.setText("You are out of money.");
        }
        else {
            for(int i = 0; i < 5; i++)
            {
                playerCards[i].setImageResource(R.color.game_green);
                playerSplitCards[i].setImageResource(R.color.game_green);
                dealerCards[i].setImageResource(R.color.game_green);
            }
            splitScore = false;
            bet(5.00);
            playerNotification.setText("");
            dealerNotification.setText("");
            playerScore.setText("");
            dealerScore.setText("");
            notification.setText("Place Bet.(Increment = 5.00)");
            newGameButton.setText("Deal");
            newGameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    betButton.setClickable(false);
                    splitButton.setClickable(false);
                    hitButton.setClickable(true);
                    stayButton.setClickable(true);
                    ddButton.setClickable(false);
                    colorButtonText();
                    initialCards();
                }
            });
            betButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bet(5.00);
                }
            });
            splitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    betButton.setClickable(false);
                    splitButton.setClickable(false);
                    hitButton.setClickable(true);
                    stayButton.setClickable(true);
                    ddButton.setClickable(false);
                    colorButtonText();
                    split();
                }
            });
            ddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    betButton.setClickable(false);
                    splitButton.setClickable(false);
                    hitButton.setClickable(false);
                    stayButton.setClickable(false);
                    ddButton.setClickable(false);
                    colorButtonText();
                    doubleDown(player);
                }
            });
        }
    }
    public void initialCards()
    {
        hitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                betButton.setClickable(false);
                splitButton.setClickable(false);
                hitButton.setClickable(true);
                stayButton.setClickable(true);
                ddButton.setClickable(false);
                colorButtonText();
                hit(player);
            }
        });
        stayButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                betButton.setClickable(false);
                splitButton.setClickable(false);
                hitButton.setClickable(false);
                stayButton.setClickable(false);
                ddButton.setClickable(false);
                colorButtonText();
                stay(player);
            }
        });
        potAmount.setText(Double.toString(pot));
        notification.setText("");
        playerScore.setText("0");
        dealerScore.setText("0");
        checkDeck();
        for(int i = 0; i < 5; i++)
        {
            playerCards[i].setImageResource(R.color.game_green);
            playerSplitCards[i].setImageResource(R.color.game_green);
            dealerCards[i].setImageResource(R.color.game_green);
        }
        player.newHand();
        player2.newHand();
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
                notification.setText("");
                betButton.setClickable(false);
                splitButton.setClickable(false);
                hitButton.setClickable(false);
                stayButton.setClickable(false);
                ddButton.setClickable(false);
                colorButtonText();
                forfeit();
            }
        });
        if(blackJack(player))
        {
            player.done = true;
            endPlayerTurn();
        }
        if(player.getHand().getCard(0).getVal() == player.getHand().getCard(1).getVal())
        {
            splitButton.setClickable(true);
            colorButtonText();
        }
        if(player.getScore() == 9 || player.getScore() == 10 || player.getScore() == 11)
        {
            ddButton.setClickable(true);
            colorButtonText();
        }
        if(dealer.getHand().getCard(0).getVal() == 14)
        {
            betButton.setText("Insurance");
            betButton.setClickable(true);
            colorButtonText();
            betButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    player.insurance = true;
                    betButton.setText("Bet");
                    betButton.setClickable(false);
                    colorButtonText();
                }
            });
        }
    }
    public void setCardImages(Person p)
    {
        for(int i = 0; i < p.getHand().getCount(); i++)
        {
            Card c = p.getHand().getCard(i);
            if(p == player2)
            {
                playerSplitCards[i].setImageResource(getResources().getIdentifier(c.toString(),"drawable", getApplicationContext().getPackageName()));
            }
            else if(p instanceof Player)
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
    public void dealerPlay()
    {
        setCardImages(dealer);
        updateScore(dealer);
        if (blackJack(dealer))
        {
            //do nothing
        }
        else if(bust(player))
        {
            //do nothing
        }
        else
        {
            while (dealer.getScore() < 17 && !charlie(dealer))
            {
                hit(dealer);
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
            p.done = true;
            if(p instanceof Player)
            {
                endPlayerTurn();
            }
        }
        else if(charlie(p))
        {
            p.done = true;
            if(p instanceof Player)
            {
                endPlayerTurn();
            }
        }
    }
    public void stay(Person p)
    {
        p.done = true;
        endPlayerTurn();
    }
    public void bet(double amount) {
        if (player.getMoneyAmount() <= 0)
        {
            notification.setText("No money left to bet");
        } else {
            pot += player.bet(amount) * 2;
            potAmount.setText(Double.toString(pot));
            playerMoneyAmount.setText(Double.toString(player.getMoneyAmount()));
        }
    }
    public void forfeit()
    {
        notification.setText("Player Forfeits.");
        lose();
        newGameButton.setText("New Game");
        newGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                betButton.setClickable(false);
                splitButton.setClickable(false);
                hitButton.setClickable(false);
                stayButton.setClickable(false);
                ddButton.setClickable(false);
                pot = 0;
                potAmount.setText(Double.toString(pot));
                colorButtonText();
                startGame();
            }
        });
    }
    public void doubleDown(Person p)
    {
        bet(pot/2);
        hit(p);
        stay(p);
    }
    public void split()
    {
        player2.done = false;
        split = true;
        splitScore = true;
        Card c = player.getHand().getCard(1);
        player.getHand().removeCard(1);
        player2.getHand().addCard(c);
        setCardImages(player2);
        playerCards[1].setImageResource(R.color.game_green);
        updateScore(player);
        updateScore(player2);
    }
    public void playSplitHand()
    {
        split = false;
        notification.setText("Play Hand 2");
        betButton.setClickable(false);
        splitButton.setClickable(false);
        hitButton.setClickable(true);
        stayButton.setClickable(true);
        ddButton.setClickable(false);
        colorButtonText();
        hitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                notification.setText("");
                betButton.setClickable(false);
                splitButton.setClickable(false);
                hitButton.setClickable(true);
                stayButton.setClickable(true);
                ddButton.setClickable(false);
                colorButtonText();
                hit(player2);
            }
        });
        stayButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                notification.setText("");
                betButton.setClickable(false);
                splitButton.setClickable(false);
                hitButton.setClickable(false);
                stayButton.setClickable(false);
                ddButton.setClickable(false);
                colorButtonText();
                stay(player2);
            }
        });
        ddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                notification.setText("");
                betButton.setClickable(false);
                splitButton.setClickable(false);
                hitButton.setClickable(false);
                stayButton.setClickable(false);
                ddButton.setClickable(false);
                colorButtonText();
                doubleDown(player2);
            }
        });
        newGameButton.setText("Forfeit");
        newGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                notification.setText("");
                betButton.setClickable(false);
                splitButton.setClickable(false);
                hitButton.setClickable(false);
                stayButton.setClickable(false);
                ddButton.setClickable(false);
                colorButtonText();
                forfeit();
            }
        });
        if(player2.getScore() == 9 || player2.getScore() == 10 || player2.getScore() == 11)
        {
            ddButton.setClickable(true);
            colorButtonText();
        }
    }
    public void updateScore(Person p)
    {
        if(p instanceof Player)
        {
            if(splitScore)
            {
                playerScore.setText(Integer.toString(player.getScore())+", "+player2.getScore());
            }
            else
            {
                playerScore.setText(Integer.toString(player.getScore()));
            }
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
        if(split)
        {
            if(p.getScore() > 21)
            {
                playerNotification.setText("Bust!");
                return true;
            }
        }
        else
        {
            if (p.getScore() > 21)
            {
                setCardImages(dealer);
                if (p instanceof Dealer)
                {
                    dealerNotification.setText("Bust!");
                }
                else
                {
                    playerNotification.setText("Bust!");
                }
                return true;
            }
        }
        return false;
    }
    public boolean blackJack(Person p)
    {
        if(split)
        {
            if(p.getHand().getCount() == 2 && p.getScore() == 21)
            {
                playerNotification.setText("BlackJack!");
                return true;
            }
        }
        else
        {
            if (p.getHand().getCount() == 2 && p.getScore() == 21)
            {
                setCardImages(dealer);
                if (p instanceof Player)
                {
                    playerNotification.setText("BlackJack!");
                }
                else if (p instanceof Dealer)
                {
                    dealerNotification.setText("BlackJack!");
                }
                return true;
            }
        }

        return false;
    }
    public boolean charlie(Person p)
    {
        if(split)
        {
            if(p.getHand().getCount() >= 5 && p.getScore() <= 21)
            {
                playerNotification.setText("5 Card Charlie!");
                return true;
            }
        }
        else
            {
            if (p.getHand().getCount() >= 5 && p.getScore() <= 21)
            {
                setCardImages(dealer);
                if (p instanceof Player)
                {
                    playerNotification.setText("5 Card Charlie!");
                }
                else if (p instanceof Dealer)
                {
                    dealerNotification.setText("5 Card Charlie!");
                }
                return true;
            }
        }
        return false;
    }
    public void checkDeck()
    {
        if(dealer.getDeck().getNumCards() <= 20)
        {
            dealer.freshDeck();
            notification.setText("Deck Below 75%.\nDealer has changed decks.");
        }
    }
    public void win()
    {
        notification.append(player.getName()+" Wins!\n");
        player.winMoney(pot);
        playerMoneyAmount.setText(Double.toString(player.getMoneyAmount()));
    }
    public void lose()
    {
        notification.append(player.getName()+" Loses.\n");
    }
    public void endPlayerTurn()
    {
        betButton.setClickable(false);
        splitButton.setClickable(false);
        hitButton.setClickable(false);
        stayButton.setClickable(false);
        ddButton.setClickable(false);
        colorButtonText();
        if(split)
        {
            playSplitHand();
        }
        if(player.done && player2.done)
        {
            dealerPlay();
            if(splitScore && !split)
            {
                checkWinner(player);
                checkWinner(player2);
            }
            else if(!splitScore && !split)
            {
                checkWinner(player);
            }
        }
    }
    public void colorButtonText()
    {
        if(!betButton.isClickable())
        {
            betButton.setTextColor(getResources().getColor(R.color.unclickable_text_color));
        }
        else
        {
            betButton.setTextColor(getResources().getColor(R.color.player_text_color));
        }
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
        if(!ddButton.isClickable())
        {
            ddButton.setTextColor(getResources().getColor(R.color.unclickable_text_color));
        }
        else
        {
            ddButton.setTextColor(getResources().getColor(R.color.player_text_color));
        }
        if(!splitButton.isClickable())
        {
            splitButton.setTextColor(getResources().getColor(R.color.unclickable_text_color));
        }
        else
        {
            splitButton.setTextColor(getResources().getColor(R.color.player_text_color));
        }
    }
    public void checkWinner(Person p)
    {
        if(p == player)
        {
            notification.setText("");
        }
        if (charlie(dealer)) {
            lose();
        } else if (blackJack(dealer)) {
            if(player.insurance && p == player)
            {
                player.winMoney(pot);
                playerMoneyAmount.setText(Double.toString(player.getMoneyAmount()));
                playerNotification.setText("Insurance payout!");
            }
            lose();
        } else if (bust(p)) {
            lose();
        } else if (blackJack(p)) {
            win();
        } else if (charlie(p)) {
            win();
        } else if (bust(dealer) && !bust(p)) {
            win();
        } else if (p.getScore() > dealer.getScore() && !bust(p)) {
            win();
        } else {
            lose();
        }
        newGameButton.setText("New Game");
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pot = 0;
                potAmount.setText(Double.toString(pot));
                betButton.setClickable(true);
                splitButton.setClickable(false);
                hitButton.setClickable(false);
                stayButton.setClickable(false);
                ddButton.setClickable(false);
                pot = 0;
                potAmount.setText(Double.toString(pot));
                colorButtonText();
                startGame();
            }
        });
    }
}