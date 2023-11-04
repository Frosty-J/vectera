package com.mygdx.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {

  private final AndroidSaver saver = new AndroidSaver(this);

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    initialize(new MyGdxGame(saver), config);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == Activity.RESULT_OK) {
      saver.handleIntent(data);
    } else {
      String msg = "Unexpected (probably just cancelled): " + resultCode;
      Gdx.app.log("AndroidLauncher", msg);
    }
  }

}
