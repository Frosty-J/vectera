package com.mygdx.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.utils.Null;

import java.util.Objects;

// Based on: https://medium.com/@/c1e4fdf3d2cb
public class AndroidSaver implements Saver {

  private Uri baseDocumentTreeUri;

  private final AndroidApplication app;

  public AndroidSaver(AndroidApplication app) {
    this.app = app;
  }

  @Override
  public void openFilePicker() {
    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
    app.startActivity(intent);
  }

  @Override @Null
  public String getLocation() {
    if (baseDocumentTreeUri == null) return null;
    return baseDocumentTreeUri.toString();
  }

  public void handleIntent(Intent data) {
    baseDocumentTreeUri = data.getData();

    // Take persistable Uri Permission for future use
    final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION |
                          Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
    app.getContext().getContentResolver().takePersistableUriPermission(
      Objects.requireNonNull(data.getData()), takeFlags);
    SharedPreferences preferences = app.getContext().getSharedPreferences(
      MyGdxGame.PREFS_NAME, Context.MODE_PRIVATE);
    preferences.edit().putString("filestorageuri", data.getData().toString()).apply();
  }

}
