package com.tile.janv.userinterface1;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_restart) {
            // TODO: implement restart code
            Toast.makeText(getApplicationContext(), "Menu button restart has been clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.button_option_about)
    public void onAboutButtonClicked() {
        new AlertDialog.Builder(WelcomeActivity.this)
                .setMessage(R.string.about_dialog_message)
                .setTitle(R.string.about_dialog_title)
                .create()
                .show();
    }

    @OnClick(R.id.button_option_exit)
    public void onExitButtonClicked() {
        // exit the application
        // see http://stackoverflow.com/questions/4756835/how-to-launch-home-screen-programmatically-in-android
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @OnClick(R.id.button_option_continue)
    public void onResetGameButtonClicked() {
        startPlayActivity(true);
    }

    @OnClick(R.id.button_option_new_game)
    public void onNewGameButtonClicked() {
        startPlayActivity(false);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void startPlayActivity(boolean reset) {
        Intent intent = new Intent(this, PlayActivity.class);
        Bundle extra = new Bundle();
        extra.putBoolean(PlayActivity.RESET, reset);
        intent.putExtras(extra);
        startActivity(intent, extra);
    }
}
