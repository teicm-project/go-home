package makisp.gohome;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScenarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario);

            if (GameActivity.progress == 1) {

                TextView scenario_title = (TextView) findViewById(R.id.scenario_title);
                scenario_title.setText(R.string.scenario_title1);

                TextView scenario_story = (TextView) findViewById(R.id.scenario_story);
                scenario_story.setText(R.string.scenario_story1);

                Button button_choice1 = (Button) findViewById(R.id.button_choice1);
                button_choice1.setText(getResources().getString(R.string.button_choice11));

                Button button_choice2 = (Button) findViewById(R.id.button_choice2);
                button_choice2.setText(R.string.button_choice21);

                button_choice1.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress = GameActivity.progress + 2;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

                button_choice2.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

            }

            else if (GameActivity.progress == 2) {
                TextView scenario_title = (TextView) findViewById(R.id.scenario_title);
                scenario_title.setText(R.string.scenario_title2);

                TextView scenario_story = (TextView) findViewById(R.id.scenario_story);
                scenario_story.setText(R.string.scenario_story2);

                Button button_choice1 = (Button) findViewById(R.id.button_choice1);
                button_choice1.setText(getResources().getString(R.string.button_choice12));

                Button button_choice2 = (Button) findViewById(R.id.button_choice2);
                button_choice2.setText(R.string.button_choice22);

                //Ήχος κατά την εκκίνηση του ScenarioActivity
                final MediaPlayer mp = MediaPlayer.create(this, R.raw.steps);
                mp.start();

                button_choice1.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

                button_choice2.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                MainActivity.db.addItem(new Inventory(LoginActivity.activeUser, "Χαρτάκι"));
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

            }

            else if (GameActivity.progress == 3) {
                TextView scenario_title = (TextView) findViewById(R.id.scenario_title);
                scenario_title.setText(R.string.scenario_title3);

                TextView scenario_story = (TextView) findViewById(R.id.scenario_story);
                scenario_story.setText(R.string.scenario_story3);

                Button button_choice1 = (Button) findViewById(R.id.button_choice1);
                button_choice1.setText(getResources().getString(R.string.button_choice13));

                Button button_choice2 = (Button) findViewById(R.id.button_choice2);
                button_choice2.setText(R.string.button_choice23);

                //Ήχος κατά την εκκίνηση του ScenarioActivity
                final MediaPlayer mp = MediaPlayer.create(this, R.raw.centre);
                mp.start();

                button_choice1.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

                button_choice2.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress = GameActivity.progress + 2;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }
                );

            }

            else if (GameActivity.progress == 4) {
                TextView scenario_title = (TextView) findViewById(R.id.scenario_title);
                scenario_title.setText(R.string.scenario_title4);

                TextView scenario_story = (TextView) findViewById(R.id.scenario_story);
                scenario_story.setText(R.string.scenario_story4);

                Button button_choice1 = (Button) findViewById(R.id.button_choice1);
                button_choice1.setText(getResources().getString(R.string.button_choice14));

                Button button_choice2 = (Button) findViewById(R.id.button_choice2);
                button_choice2.setText(R.string.button_choice24);

                button_choice1.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress = GameActivity.progress + 2;
                                MainActivity.db.addItem(new Inventory(LoginActivity.activeUser, "Βιβλίο"));
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

                button_choice2.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress = GameActivity.progress + 2;
                                MainActivity.db.addItem(new Inventory(LoginActivity.activeUser, "Βιβλίο"));
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }
                );

            }

            else if (GameActivity.progress == 5) {
                TextView scenario_title = (TextView) findViewById(R.id.scenario_title);
                scenario_title.setText(R.string.scenario_title5);

                TextView scenario_story = (TextView) findViewById(R.id.scenario_story);
                scenario_story.setText(R.string.scenario_story5);

                Button button_choice1 = (Button) findViewById(R.id.button_choice1);
                button_choice1.setText(getResources().getString(R.string.button_choice15));

                Button button_choice2 = (Button) findViewById(R.id.button_choice2);
                button_choice2.setText(R.string.button_choice25);

                button_choice1.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                MainActivity.db.addItem(new Inventory(LoginActivity.activeUser, "Βιβλίο"));
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

                button_choice2.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                MainActivity.db.addItem(new Inventory(LoginActivity.activeUser, "Σκισμένες σελίδες"));
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

            }

            else if (GameActivity.progress == 6) {
                TextView scenario_title = (TextView) findViewById(R.id.scenario_title);
                scenario_title.setText(R.string.scenario_title6);

                TextView scenario_story = (TextView) findViewById(R.id.scenario_story);
                scenario_story.setText(R.string.scenario_story6);

                Button button_choice1 = (Button) findViewById(R.id.button_choice1);
                button_choice1.setText(getResources().getString(R.string.button_choice16));

                Button button_choice2 = (Button) findViewById(R.id.button_choice2);
                button_choice2.setText(R.string.button_choice26);

                button_choice1.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();

                                //Ήχος κατά την εκκίνηση του ScenarioActivity
                                final MediaPlayer mp = MediaPlayer.create(ScenarioActivity.this, R.raw.slowdoor);
                                mp.start();
                            }
                        }

                );

                button_choice2.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }
                );

            }

            else if (GameActivity.progress == 7) {
                TextView scenario_title = (TextView) findViewById(R.id.scenario_title);
                scenario_title.setText(R.string.scenario_title7);

                TextView scenario_story = (TextView) findViewById(R.id.scenario_story);
                scenario_story.setText(R.string.scenario_story7);

                Button button_choice1 = (Button) findViewById(R.id.button_choice1);
                button_choice1.setText(getResources().getString(R.string.button_choice17));

                Button button_choice2 = (Button) findViewById(R.id.button_choice2);
                button_choice2.setText(R.string.button_choice27);

                button_choice1.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

                button_choice2.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

            }

            else if (GameActivity.progress == 8) {
                TextView scenario_title = (TextView) findViewById(R.id.scenario_title);
                scenario_title.setText(R.string.scenario_title8);

                TextView scenario_story = (TextView) findViewById(R.id.scenario_story);
                scenario_story.setText(R.string.scenario_story8);

                Button button_choice1 = (Button) findViewById(R.id.button_choice1);
                button_choice1.setText(getResources().getString(R.string.button_choice18));

                Button button_choice2 = (Button) findViewById(R.id.button_choice2);
                button_choice2.setText(R.string.button_choice28);

                button_choice1.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

                button_choice2.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();

                                //Ήχος κατά την εκκίνηση του ScenarioActivity
                                final MediaPlayer mp = MediaPlayer.create(ScenarioActivity.this, R.raw.breaklock);
                                mp.start();
                            }
                        }

                );

            }

            else if (GameActivity.progress == 9) {
                TextView scenario_title = (TextView) findViewById(R.id.scenario_title);
                scenario_title.setText(R.string.scenario_title9);

                TextView scenario_story = (TextView) findViewById(R.id.scenario_story);
                scenario_story.setText(R.string.scenario_story9);

                Button button_choice1 = (Button) findViewById(R.id.button_choice1);
                button_choice1.setText(getResources().getString(R.string.button_choice19));

                Button button_choice2 = (Button) findViewById(R.id.button_choice2);
                button_choice2.setText(R.string.button_choice29);

                button_choice1.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

                button_choice2.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

            }

            else if (GameActivity.progress == 10) {
                TextView scenario_title = (TextView) findViewById(R.id.scenario_title);
                scenario_title.setText(R.string.scenario_title10);

                TextView scenario_story = (TextView) findViewById(R.id.scenario_story);
                scenario_story.setText(R.string.scenario_story10);

                Button button_choice1 = (Button) findViewById(R.id.button_choice1);
                button_choice1.setText(getResources().getString(R.string.button_choice110));

                Button button_choice2 = (Button) findViewById(R.id.button_choice2);
                button_choice2.setText(R.string.button_choice210);

                button_choice1.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

                button_choice2.setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                GameActivity.progress++;
                                String log = " " + GameActivity.progress;
                                Log.d("progress::", log);
                                MainActivity.db.updateProgress(GameActivity.cre, GameActivity.cre.getUsername(), GameActivity.progress);
                                finish();
                            }
                        }

                );

            }

            else{
                Log.i("LogMessage", "Δεν βρέθηκε marker.");
            }

    }
}
