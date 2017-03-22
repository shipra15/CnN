package mykaarma.cnn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Button> buttonList = new ArrayList<Button>();
    Boolean activateTouchListener = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonList.add((Button) findViewById(R.id.button4));
        buttonList.add((Button) findViewById(R.id.button3));
        buttonList.add((Button) findViewById(R.id.button2));

        buttonList.add((Button) findViewById(R.id.button6));
        buttonList.add((Button)findViewById(R.id.button5));
        buttonList.add((Button) findViewById(R.id.button10));

        buttonList.add((Button) findViewById(R.id.button13));
        buttonList.add((Button) findViewById(R.id.button12));
        buttonList.add((Button) findViewById(R.id.button11));

        for(Button button: buttonList) {
            addTouchListener(button, buttonList.indexOf(button));
        }
    }

    private void addTouchListener(final Button button, int index) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activateTouchListener) {
                    button.setText("X");
                    button.setEnabled(false);
                    activateTouchListener = false;

                    //check for win.


                    for(Button button1: buttonList) {
                        if(button1.getText().equals("")) {
                            button1.setText("O");
                            button1.setEnabled(false);
                            activateTouchListener = true;
                            break;
                        }
                    }
                }
            }
        });
    }

    private Boolean isWin(String string, int index) {

        //check for horizontal and vertical win


        return false;
    }
}
