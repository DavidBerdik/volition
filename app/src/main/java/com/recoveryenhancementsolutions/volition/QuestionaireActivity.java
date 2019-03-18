package com.recoveryenhancementsolutions.volition;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 *
 * */


public class QuestionaireActivity extends AppCompatActivity {

    public static int answerCounter = 0;
    public static int yesAnswers = 0;
    public static int noAnswers = 0;
    public static int severityLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView qOne = (TextView) findViewById(R.id.questionOne);
        final TextView qTwo = (TextView) findViewById(R.id.questionTwo);
        final TextView qThree = (TextView) findViewById(R.id.questionThree);
        final TextView qFour = (TextView) findViewById(R.id.questionFour);
        final TextView qFive = (TextView) findViewById(R.id.questionFive);
        final TextView qSix = (TextView) findViewById(R.id.questionSix);
        final TextView qSeven = (TextView) findViewById(R.id.questionSeven);
        final TextView qEight = (TextView) findViewById(R.id.questionEight);
        final TextView qNine = (TextView) findViewById(R.id.questionNine);
        final TextView qTen = (TextView) findViewById(R.id.questionTen);
        final TextView qEleven = (TextView) findViewById(R.id.questionEleven);

        final TextView severityResult = (TextView) findViewById(R.id.severityResponse);

        Button YESbtn = (Button) findViewById(R.id.YESbtn);
        Button NObtn = (Button) findViewById(R.id.NObtn);

        qTwo.setTextColor(qTwo.getTextColors().withAlpha(0));
        qThree.setTextColor(qThree.getTextColors().withAlpha(0));
        qFour.setTextColor(qFour.getTextColors().withAlpha(0));
        qFive.setTextColor(qFive.getTextColors().withAlpha(0));
        qSix.setTextColor(qSix.getTextColors().withAlpha(0));
        qSeven.setTextColor(qSeven.getTextColors().withAlpha(0));
        qEight.setTextColor(qEight.getTextColors().withAlpha(0));
        qNine.setTextColor(qNine.getTextColors().withAlpha(0));
        qTen.setTextColor(qTen.getTextColors().withAlpha(0));
        qEleven.setTextColor(qEleven.getTextColors().withAlpha(0));

        severityResult.setTextColor(severityResult.getTextColors().withAlpha(0));

        YESbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerCounter++;
                yesAnswers++;

                if (answerCounter == 1) {
                    qOne.setTextColor(qOne.getTextColors().withAlpha(0));
                    qTwo.setTextColor(qTwo.getTextColors().withAlpha(100));
                }

                if (answerCounter == 2) {
                    qTwo.setTextColor(qTwo.getTextColors().withAlpha(0));
                    qThree.setTextColor(qThree.getTextColors().withAlpha(100));
                }

                if (answerCounter == 3) {
                    qThree.setTextColor(qThree.getTextColors().withAlpha(0));
                    qFour.setTextColor(qFour.getTextColors().withAlpha(100));
                }

                if (answerCounter == 4) {
                    qFour.setTextColor(qFour.getTextColors().withAlpha(0));
                    qFive.setTextColor(qFive.getTextColors().withAlpha(100));
                }

                if (answerCounter == 5) {
                    qFive.setTextColor(qFive.getTextColors().withAlpha(0));
                    qSix.setTextColor(qSix.getTextColors().withAlpha(100));
                }

                if (answerCounter == 6) {
                    qSix.setTextColor(qSix.getTextColors().withAlpha(0));
                    qSeven.setTextColor(qSeven.getTextColors().withAlpha(100));
                }

                if (answerCounter == 7) {
                    qSeven.setTextColor(qSeven.getTextColors().withAlpha(0));
                    qEight.setTextColor(qEight.getTextColors().withAlpha(100));
                }

                if (answerCounter == 8) {
                    qEight.setTextColor(qEight.getTextColors().withAlpha(0));
                    qNine.setTextColor(qNine.getTextColors().withAlpha(100));
                }

                if (answerCounter == 9) {
                    qNine.setTextColor(qNine.getTextColors().withAlpha(0));
                    qTen.setTextColor(qTen.getTextColors().withAlpha(100));
                }

                if (answerCounter == 10) {
                    qTen.setTextColor(qTen.getTextColors().withAlpha(0));
                    qEleven.setTextColor(qEleven.getTextColors().withAlpha(100));
                }

                if (answerCounter == 11) {
                    qEleven.setTextColor(qEleven.getTextColors().withAlpha(0));
                    severityResult.setTextColor(severityResult.getTextColors().withAlpha(100));

                    severityLevel = yesAnswers - noAnswers;

                    if (severityLevel <= 3) {
                        severityResult.setText("Mild");
                    }

                    if (severityLevel > 3 && severityLevel <= 5 ) {
                        severityResult.setText("Moderate");
                    }

                    if (severityLevel >= 6) {
                        severityResult.setText("Severe");
                    }
                }
            }
        });

        NObtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerCounter++;
                noAnswers++;

                if (answerCounter == 1) {
                    qOne.setTextColor(qOne.getTextColors().withAlpha(0));
                    qTwo.setTextColor(qTwo.getTextColors().withAlpha(100));
                }

                if (answerCounter == 2) {
                    qTwo.setTextColor(qTwo.getTextColors().withAlpha(0));
                    qThree.setTextColor(qThree.getTextColors().withAlpha(100));
                }

                if (answerCounter == 3) {
                    qThree.setTextColor(qThree.getTextColors().withAlpha(0));
                    qFour.setTextColor(qFour.getTextColors().withAlpha(100));
                }

                if (answerCounter == 4) {
                    qFour.setTextColor(qFour.getTextColors().withAlpha(0));
                    qFive.setTextColor(qFive.getTextColors().withAlpha(100));
                }

                if (answerCounter == 5) {
                    qFive.setTextColor(qFive.getTextColors().withAlpha(0));
                    qSix.setTextColor(qSix.getTextColors().withAlpha(100));
                }

                if (answerCounter == 6) {
                    qSix.setTextColor(qSix.getTextColors().withAlpha(0));
                    qSeven.setTextColor(qSeven.getTextColors().withAlpha(100));
                }

                if (answerCounter == 7) {
                    qSeven.setTextColor(qSeven.getTextColors().withAlpha(0));
                    qEight.setTextColor(qEight.getTextColors().withAlpha(100));
                }

                if (answerCounter == 8) {
                    qEight.setTextColor(qEight.getTextColors().withAlpha(0));
                    qNine.setTextColor(qNine.getTextColors().withAlpha(100));
                }

                if (answerCounter == 9) {
                    qNine.setTextColor(qNine.getTextColors().withAlpha(0));
                    qTen.setTextColor(qTen.getTextColors().withAlpha(100));
                }

                if (answerCounter == 10) {
                    qTen.setTextColor(qTen.getTextColors().withAlpha(0));
                    qEleven.setTextColor(qEleven.getTextColors().withAlpha(100));
                }

                if (answerCounter == 11) {
                    qEleven.setTextColor(qEleven.getTextColors().withAlpha(0));
                    severityResult.setTextColor(severityResult.getTextColors().withAlpha(100));

                    severityLevel = yesAnswers - noAnswers;

                    if (severityLevel <= 3) {
                        severityResult.setText("Mild");
                    }

                    if (severityLevel > 3 && severityLevel <= 5 ) {
                        severityResult.setText("Moderate");
                    }

                    if (severityLevel >= 6) {
                        severityResult.setText("Severe");
                    }
                }
            }
        });




    }
}
