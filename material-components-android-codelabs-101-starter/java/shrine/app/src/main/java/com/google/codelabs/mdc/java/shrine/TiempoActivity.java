package com.google.codelabs.mdc.java.shrine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class TiempoActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_tiempo);
      Intent intent = getIntent();

      if (savedInstanceState == null) {
         getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.container, new TiempoFragment())
            .commit();
      }
   }

}
