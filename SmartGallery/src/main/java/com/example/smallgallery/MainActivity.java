package com.example.smallgallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int buttonPressed = 0;
    int countCards = 6;
    Intent intent;
    CardView[] card;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Choose a card:");

        setCards();
        setImages();
    }

    public void setCards() {
        int[] cardNumber = new int[countCards];
        card = new CardView[countCards];

        for (int i = 0; i < cardNumber.length; i++) {
            String cardID = "card" +  i;
            cardNumber[i] = getResources().getIdentifier(cardID, "id", getPackageName());
            card[i] = findViewById(cardNumber[i]);
        }
    }

    public void setImages() {
        Random rand = new Random();
        int rndInt1 = rand.nextInt(10) + 1;
        int rndInt2 = rand.nextInt(10) + 1;

        String[] imgName = {"anm" + rndInt1, "space" + rndInt2, "nat" + rndInt1, "arc" + rndInt2, "cyb" + rndInt1, "sci" + rndInt2};

        int[] pic = new int[countCards];
        int[] picNumber = new int[countCards];

        for (int i = 0; i < countCards; i++) {
            String picID = "imageView" + i;

            pic[i] = getResources().getIdentifier(imgName[i], "drawable", getPackageName());
            picNumber[i] = getResources().getIdentifier(picID, "id", getPackageName());

            image = findViewById(picNumber[i]);
            image.setImageResource(pic[i]);
        }
    }

    public void onClick(View view) {

        for (int i = 0; i < countCards; i++) {
            if (card[i].isPressed()) {
                buttonPressed = i + 1;
            }
        }

        intent = new Intent(this, profile.class);
        intent.putExtra("ButtonPressed", buttonPressed);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
        setImages();
    }
}