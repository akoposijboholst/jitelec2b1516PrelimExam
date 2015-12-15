package tech.sidespell.prelimexam;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private ToggleButton mBtnToggleButton;
    private SeekBar mSeekBar;
    private TextView mSeekView;
    private RadioGroup mRadioGroup;
    private TextView mTimerView;
    private int timeRemaining;
    private int addMin;
    private int delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnToggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekView = (TextView) findViewById(R.id.seekView);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mTimerView = (TextView) findViewById(R.id.timerView);

        timeRemaining = Integer.parseInt(mTimerView.getText().toString());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBtnToggleButton.setOnCheckedChangeListener(this);

        mSeekBar.setMax(1000);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                mSeekView.setText(String.valueOf(progress));
                delay = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            //do something
            switch (mRadioGroup.getCheckedRadioButtonId()) {

                case R.id.increment:
                    addMin = 1;
                    break;
                case R.id.decrement:
                    addMin = -1;
                    break;
            }

            final Handler handler = new Handler();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    timeRemaining += addMin;
                    mTimerView.setText(String.valueOf(timeRemaining));
                    handler.postDelayed(this, delay);
                }
            };

            handler.postDelayed(runnable, delay);
        } else {
            //do something
        }
    }
}
