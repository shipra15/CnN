package mykaarma.cnn;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<List<Button>> buttonList = new ArrayList<List<Button>>();
    Boolean activateTouchListener = true;
    private static Integer MIN_INDEX = 0;
    private static Integer MAX_INDEX = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Button> buttonList1 = new ArrayList<Button>();
        buttonList1.add((Button) findViewById(R.id.button4));
        buttonList1.add((Button) findViewById(R.id.button3));
        buttonList1.add((Button) findViewById(R.id.button2));

        buttonList.add(buttonList1);

        List<Button> buttonList2 = new ArrayList<Button>();
        buttonList2.add((Button) findViewById(R.id.button6));
        buttonList2.add((Button)findViewById(R.id.button5));
        buttonList2.add((Button) findViewById(R.id.button10));

        buttonList.add(buttonList2);

        List<Button> buttonList3 = new ArrayList<Button>();
        buttonList3.add((Button) findViewById(R.id.button13));
        buttonList3.add((Button) findViewById(R.id.button12));
        buttonList3.add((Button) findViewById(R.id.button11));

        buttonList.add(buttonList3);

        for(List<Button> list: buttonList) {
            for(Button button: list) {
                addTouchListener(button, buttonList.indexOf(list), list.indexOf(button));
            }
        }
    }

    private void addTouchListener(final Button button, final int xIndex, final int yIndex) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activateTouchListener) {
                    button.setText("X");
                    button.setEnabled(false);
                    activateTouchListener = false;

                    //check for win.
                    if(isWin("X", xIndex, yIndex)) {
                        activateTouchListener = false;
                        return;
                    }

                    for(List<Button> list: buttonList) {
                        for(Button button1: list) {
                            if(button1.getText().equals("")) {
                                button1.setText("O");
                                button1.setEnabled(false);
                                if(isWin("O", xIndex, yIndex)) {
                                    activateTouchListener = false;
                                    return;
                                }
                                activateTouchListener = true;
                                break;
                            }

                        }
                        if(activateTouchListener) {
                            break;
                        }
                    }

                }
            }
        });
    }

    private Boolean isWin(String string, int xIndex, int yIndex) {

        int xIncrement = 0;
        int yIncrement = 0;

        if(xIndex == 1 && yIndex == 1) {
            return isWin(string, 0,0) || isWin(string,0,2);
        }

        //check for horizontal and vertical win

        //keeping xIndex constant
        yIncrement = 1;
        if(isWin(string, xIndex, yIndex, xIncrement, yIncrement)) {
            return true;
        }

        //keeping yIndex constant
        yIncrement = 0;
        xIncrement = 1;
        if(isWin(string, xIndex, yIndex, xIncrement, yIncrement)) {
            return true;
        }

        if((xIndex + yIndex)%2 == 0) {
            Boolean increaseX = (xIndex == MIN_INDEX);
            Boolean decreaseX = (xIndex == MAX_INDEX);
            Boolean increaseY = (yIndex == MIN_INDEX);
            Boolean decreaseY = (yIndex == MAX_INDEX);

            if(increaseX || decreaseX) {
                if(increaseX == true) {
                    xIncrement = 1;
                } else {
                    xIncrement = -1;
                }

                if(increaseY) {
                    yIncrement = 1;
                } else {
                    yIncrement = -1;
                }

                if(isWin(string, xIndex, yIndex, xIncrement, yIncrement)) {
                    return true;
                }
            }
        }

        return false;
    }

    private Boolean isWin(String string, int xIndex, int yIndex, int xIncrement, int yIncrement) {
        if(buttonList.get((xIndex+2*xIncrement)%3).get((yIndex+2*yIncrement)%3).getText().equals(string) &&
                buttonList.get((xIndex+xIncrement)%3).get((yIndex+yIncrement)%3).getText().equals(string)) {
            buttonList.get(xIndex).get(yIndex).setTextColor(Color.GREEN);
            buttonList.get((xIndex+2*xIncrement)%3).get((yIndex+2*yIncrement)%3).setTextColor(Color.GREEN);
            buttonList.get((xIndex+xIncrement)%3).get((yIndex+yIncrement)%3).setTextColor(Color.GREEN);
            return true;
        }
        return false;
    }
}
