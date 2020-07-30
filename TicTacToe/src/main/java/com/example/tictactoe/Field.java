package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;

public class Field extends Activity implements onClick {

    boolean win = false;
    int cellFound = 0;
    boolean checkChar = false;
    int checkX = 0, mode = 0;
    ScrollView game;
    Drawable imageX, imageO, empty;
    TextView bottom, top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);

        Intent intentFrom = getIntent();
        mode = intentFrom.getIntExtra("mode", 0);

        top = findViewById(R.id.textView1);
        bottom = findViewById(R.id.textView2);
        game = findViewById(R.id.gameLayout);

        game.setBackgroundResource(R.drawable.backgroud_default);

        imageX = getResources().getDrawable(R.drawable.x);
        imageO = getResources().getDrawable(R.drawable.o);
        empty = getResources().getDrawable(R.drawable.blank);

        checkMode();
    }

    protected void checkMode() {

        if (mode == 1) {
            top.setText(R.string.mode_user);

        } else if (mode == 2) {
            top.setText(R.string.mode_ai);
        }

        createField();
    }

    private ImageButton[] arrayOfIDs() {

        return new ImageButton[]{findViewById(R.id.imageButton1), findViewById(R.id.imageButton2), findViewById(R.id.imageButton3),
                findViewById(R.id.imageButton4), findViewById(R.id.imageButton5), findViewById(R.id.imageButton6),
                findViewById(R.id.imageButton7), findViewById(R.id.imageButton8), findViewById(R.id.imageButton9)};

    }

    private void createField() {
        Drawable blank = getResources().getDrawable(R.drawable.blank);

        for (int i = 0; i < arrayOfIDs().length; i++) {
            arrayOfIDs()[i].setImageDrawable(blank);
        }
    }

    protected void userUser() {

        if (!checkWin()) {
            for (int i = 0; i < arrayOfIDs().length; i++) {

                if (arrayOfIDs()[i].isPressed() && !seekXY(i) && checkX % 2 == 0) {
                    arrayOfIDs()[i].setImageDrawable(imageX);
                    checkX++;
                    break;

                } else if (arrayOfIDs()[i].isPressed() && !seekXY(i) && checkX % 2 != 0) {
                    arrayOfIDs()[i].setImageDrawable(imageO);
                    checkX++;
                    break;

                }
            }
            checkWin();
        }
    }

    protected void userAi() {
        if (!checkWin()) {
            for (int i = 0; i < arrayOfIDs().length; i++) {
                if (arrayOfIDs()[i].isPressed() && !seekXY(i) && checkX % 2 == 0) {
                    arrayOfIDs()[i].setImageDrawable(imageX);
                    checkChar = true;
                    checkX++;
                }
            }
            checkWin();

            if (!checkWin() && checkChar) {
                if (checkX == 1 && emptyCell(firstMove())) {
                    arrayOfIDs()[firstMove()].setImageDrawable(imageO);
                } else {
                    arrayOfIDs()[blockCell()].setImageDrawable(imageO);
                }
                checkChar = false;
                checkX++;
            }
            checkWin();
        }
    }


    protected int rndNumber() {
        Random random = new Random();

        int rnd = random.nextInt(9);

        while (seekXY(rnd)) {
            rnd = random.nextInt(9);
        }

        return rnd;
    }

    protected int firstMove() {
        int[] array = new int[]{4, 0, 2, 6, 8};
        int count = 0;

        for (int i = 0; i < array.length; i++) {
            if (emptyCell(i)) {
                count = i;
                break;
            }
        }

        return array[count];
    }

    protected int blockCell() {

        int bestCell = 100;

        while (bestCell == 100) {
            for (int i = 0; i < arrayOfIDs().length - 2; i += 3) {

                if (seekXO(i, cellFound) && imageEquity(i, i + 1) && emptyCell(i + 2)) {
                    bestCell = i + 2;

                } else if (seekXO(i + 1, cellFound) && imageEquity(i + 1, i + 2) && emptyCell(i)) {
                    bestCell = i;

                } else if (seekXO(i, cellFound) && imageEquity(i, i + 2) && emptyCell(i + 1)) {
                    bestCell = i + 1;

                }
            }

            for (int i = 0; i < 3; i++) {

                if (seekXO(i, cellFound) && imageEquity(i, i + 3) && emptyCell(i + 6)) {
                    bestCell = i + 6;

                } else if (seekXO(i + 3, cellFound) && imageEquity(i + 3, i + 6) && emptyCell(i)) {
                    bestCell = i;

                } else if (seekXO(i, cellFound) && imageEquity(i, i + 6) && emptyCell(i + 3)) {
                    bestCell = i + 3;

                }
            }

            if (seekXO(4, cellFound) && imageEquity(4, 0) && emptyCell(8)) {
                bestCell = 8;
            } else if (seekXO(4, cellFound) && imageEquity(4, 2) && emptyCell(6)) {
                bestCell = 6;
            } else if (seekXO(4, cellFound) && imageEquity(4, 6) && emptyCell(2)) {
                bestCell = 2;
            } else if (seekXO(4, cellFound) && imageEquity(4, 8) && emptyCell(0)) {
                bestCell = 0;
            } else if (seekXO(0, cellFound) && imageEquity(0, 8) && emptyCell(4)) {
                bestCell = 4;
            } else if (seekXO(2, cellFound) && imageEquity(2, 6) && emptyCell(4)) {
                bestCell = 4;
            }

            if (bestCell == 100 && cellFound == 0){
                cellFound = 1;
            } else if (bestCell == 100 && cellFound == 1){
                cellFound = 0;
                bestCell = rndNumber();
            }
        }

        return bestCell;
    }

    protected boolean checkWin() {

        for (int i = 0; i < arrayOfIDs().length - 2; i += 3) {

            if (seekXY(i) && imageEquity(i, i + 1) && imageEquity(i, i + 2)) {
                bottom.setText(checkXY(i) + " " + "is a Winner!!!");
                win = true;
            }
        }

        for (int i = 0; i < 3; i++) {

            if (seekXY(i) && imageEquity(i, i + 3) && imageEquity(i, i + 6)) {
                bottom.setText(checkXY(i) + " " + "is a Winner!!!");
                win = true;
            }
        }

        if (seekXY(4) && imageEquity(4, 0) && imageEquity(4, 8)
                || seekXY(4) && imageEquity(4, 2) && imageEquity(4, 6)) {

            bottom.setText(checkXY(4) + " " + "is a Winner!!!");
            win = true;
        }

        if (checkDraw(checkX) && !win) {
            bottom.setText("Draw");
            win = true;
        }

        if (win) {
            game.setBackgroundResource(R.drawable.background_game);
        }

        return win;
    }

    protected boolean checkDraw(int i) {
        return i == 9;
    }

    private char checkXY(int i) {
        char checkXY = '_';

        if (arrayOfIDs()[i].getDrawable().getConstantState().equals(imageX.getConstantState())) {
            checkXY = 'X';
        } else if (arrayOfIDs()[i].getDrawable().getConstantState().equals(imageO.getConstantState())) {
            checkXY = 'O';
        }
        return checkXY;
    }

    private boolean seekXY(int i) {
        return arrayOfIDs()[i].getDrawable().getConstantState().equals(imageX.getConstantState())
                || arrayOfIDs()[i].getDrawable().getConstantState().equals(imageO.getConstantState());
    }

    private boolean seekXO(int i, int cellFound){
        if (cellFound == 1){
            return arrayOfIDs()[i].getDrawable().getConstantState().equals(imageO.getConstantState());
        } else {
            return arrayOfIDs()[i].getDrawable().getConstantState().equals(imageX.getConstantState());
        }
    }

    private boolean emptyCell(int i) {
        return arrayOfIDs()[i].getDrawable().getConstantState().equals(empty.getConstantState());
    }

    private boolean imageEquity(int a, int b) {
        return arrayOfIDs()[a].getDrawable().getConstantState().equals(arrayOfIDs()[b].getDrawable().getConstantState());
    }

    @Override
    public void onCLick(View v) {
        Button reset = findViewById(R.id.button);

        if (reset.isPressed()) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

        if (mode == 1) {
            userUser();
        } else if (mode == 2) {
            userAi();
        }
    }
}

