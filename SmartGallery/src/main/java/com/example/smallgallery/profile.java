package com.example.smallgallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.example.smallgallery.Adapters.MyAdapter;
import com.example.smallgallery.Interfaces.OnItemClickListener;
import java.util.ArrayList;


public class profile extends AppCompatActivity {

    int buttonPressed;
    int[] image_ids;
    MyAdapter adapter;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageView = findViewById(R.id.image);
        Intent intentFrom = getIntent();
        buttonPressed = intentFrom.getIntExtra("ButtonPressed", 0);

        checkArray();

        ImageButton button = findViewById(R.id.imageButton);
        Drawable pic = getResources().getDrawable(image_ids[0]);
        button.setImageDrawable(pic);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<CreateList> createLists = prepareData();
        adapter = new MyAdapter(createLists);
        recyclerView.setAdapter(adapter);

        Stupid();
    }

    public void checkArray(){

        if (buttonPressed == 1) {
            image_ids = new int[]{R.drawable.anm1, R.drawable.anm2, R.drawable.anm3, R.drawable.anm4,
                    R.drawable.anm5, R.drawable.anm6, R.drawable.anm7,
                    R.drawable.anm8, R.drawable.anm9, R.drawable.anm10};
            getSupportActionBar().setTitle(getResources().getString(R.string.button_animals));

        } else if (buttonPressed == 2) {
            image_ids = new int[]{R.drawable.space1, R.drawable.space2, R.drawable.space3, R.drawable.space4,
                    R.drawable.space5, R.drawable.space6, R.drawable.space7,
                    R.drawable.space8,R.drawable.space9, R.drawable.space10};
            getSupportActionBar().setTitle(getResources().getString(R.string.button_space));

        } else if (buttonPressed == 3) {
            image_ids = new int[]{R.drawable.nat1, R.drawable.nat2, R.drawable.nat3, R.drawable.nat4,
                    R.drawable.nat5, R.drawable.nat6, R.drawable.nat7,
                    R.drawable.nat8, R.drawable.nat9, R.drawable.nat10};
            getSupportActionBar().setTitle(getResources().getString(R.string.button_nature));

        } else if (buttonPressed == 4) {
            image_ids = new int[]{R.drawable.arc1, R.drawable.arc2, R.drawable.arc3, R.drawable.arc4,
                    R.drawable.arc5, R.drawable.arc6, R.drawable.arc7,
                    R.drawable.arc8, R.drawable.arc9, R.drawable.arc10};
            getSupportActionBar().setTitle(getResources().getString(R.string.button_arch));

        } else if (buttonPressed == 5) {
            image_ids = new int[]{R.drawable.cyb1, R.drawable.cyb2, R.drawable.cyb3, R.drawable.cyb4,
                    R.drawable.cyb5, R.drawable.cyb6, R.drawable.cyb7,
                    R.drawable.cyb8, R.drawable.cyb9, R.drawable.cyb10};
            getSupportActionBar().setTitle(getResources().getString(R.string.button_cyber));

        } else if (buttonPressed == 6) {
            image_ids = new int[]{R.drawable.sci1, R.drawable.sci2, R.drawable.sci3, R.drawable.sci4,
                    R.drawable.sci5, R.drawable.sci6, R.drawable.sci7,
                    R.drawable.sci8, R.drawable.sci9, R.drawable.sci10};
            getSupportActionBar().setTitle(getResources().getString(R.string.button_science));

        }
    }

    public ArrayList<CreateList> prepareData(){

        ArrayList<CreateList> theimage = new ArrayList<>();

            for (int i = 1; i < image_ids.length; i++) {
                CreateList createList = new CreateList();
                createList.setImage_ID(image_ids[i]);
                theimage.add(createList);
            }
        return theimage;
    }

    public void Stupid(){
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int item) {
                Intent intent = new Intent (profile.this, profileBig.class);
                intent.putExtra("array", image_ids);
                intent.putExtra("position", item);
                startActivity(intent);

            }
        });
    }
    public void onBioClick(View view){
        Intent intentBio = new Intent(this, profileBio.class);
        intentBio.putExtra("picChoice", image_ids);
        startActivity(intentBio);
    }
}