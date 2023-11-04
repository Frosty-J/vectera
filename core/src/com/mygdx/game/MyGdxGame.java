package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {

  public static final String PREFS_NAME = "com.mygdx.game";

  SpriteBatch batch;
  Texture img;

  private final Saver saver;

  public MyGdxGame(Saver saver) {
    this.saver = saver;
  }

  @Override
  public void create () {
    batch = new SpriteBatch();
    img = new Texture("badlogic.jpg");

    Gdx.app.setLogLevel(Application.LOG_INFO);
    Gdx.graphics.setContinuousRendering(false);

    saver.openFilePicker();
  }

  @Override
  public void render () {
    ScreenUtils.clear(1, 0, 0, 1);
    batch.begin();
    batch.draw(img, 0, 0);
    batch.end();

    if (Gdx.input.justTouched()) {
      if (saver.getLocation() != null)
        Gdx.app.log("location", saver.getLocation());
      Preferences prefs = Gdx.app.getPreferences("com.mygdx.game");
      String uri = prefs.getString("filestorageuri");
      Gdx.app.log("filestorageuri", uri);
//      FileHandle file = Gdx.app.getType() == ApplicationType.Android ?
//                        Gdx.files.absolute(uri) :
//                        Gdx.files.external("hello.txt");
//      file.writeString("world", false);
    }

  }


}
