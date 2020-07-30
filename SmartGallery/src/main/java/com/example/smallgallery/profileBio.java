package com.example.smallgallery;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class profileBio extends AppCompatActivity {

    int picChange[];
    int countCards = 6;
    Intent intent;
    ImageView image;
    Drawable[] allDrawables;
    Drawable mainDrawable;
    TextView text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_bio);
        getSupportActionBar().setTitle("Description");

        text1 = findViewById(R.id.textView2);
        text2 = findViewById(R.id.textView3);

        intent = getIntent();
        picChange = intent.getIntArrayExtra("picChoice");
        image = findViewById(R.id.imageButton12);

        mainDrawable = getResources().getDrawable(picChange[0]);
        image.setImageDrawable(mainDrawable);

        getDrawable();
        checkText();
    }

    public void getDrawable() {

        allDrawables = new Drawable[countCards];
        int[] picNumber = new int[countCards];
        String[] drawableName = {"anm8", "space1", "nat1", "arc1", "cyb1", "sci1"};

        for (int i = 0; i < countCards; i++) {
            picNumber[i] = getResources().getIdentifier(drawableName[i], "drawable", getPackageName());
            allDrawables[i] = getResources().getDrawable(picNumber[i]);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent;
        intent = new Intent(this, profile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }

    private void checkText() {

        if (mainDrawable.getConstantState().equals(allDrawables[0].getConstantState())) {
            text1.setText(getResources().getString(R.string.button_animals));
            text2.setText(getResources().getString(R.string.animal_descrip));

        } else if (mainDrawable.getConstantState().equals(allDrawables[1].getConstantState())) {
            text1.setText(getResources().getString(R.string.button_space));
            text2.setText(getResources().getString(R.string.space_descrip));

        } else if (mainDrawable.getConstantState().equals(allDrawables[2].getConstantState())){
            text1.setText(getResources().getString(R.string.button_nature));
            text2.setText(getResources().getString(R.string.nature_descrip));

        } else if (mainDrawable.getConstantState().equals(allDrawables[3].getConstantState())){
            text1.setText(getResources().getString(R.string.button_arch));
            text2.setText(getResources().getString(R.string.arch_descrip));

        } else if (mainDrawable.getConstantState().equals(allDrawables[4].getConstantState())){
            text1.setText(getResources().getString(R.string.button_cyber));
            text2.setText(getResources().getString(R.string.cyber_descrip));

        } else if (mainDrawable.getConstantState().equals(allDrawables[5].getConstantState())){
            text1.setText(getResources().getString(R.string.button_science));
            text2.setText(getResources().getString(R.string.science_descrip));
        }
    }
}