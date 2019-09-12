package com.google.codelabs.mdc.java.shrine;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class TiempoFragment extends Fragment {
   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setHasOptionsMenu(true);
   }
   @Override
   public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
      menuInflater.inflate(R.menu.shr_toolbar_menu,menu);
      super.onCreateOptionsMenu(menu, menuInflater);
   }


   @Override
   public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.shr_tiempo_fragment, container, false);
      MaterialCardView cardView = (MaterialCardView) view.findViewById(R.id.materialcard);


       TextView title_tiempo_card = view.findViewById(R.id.card_title_tiempo);
       title_tiempo_card.setText(getString(R.string.titulo));
       TextView ciudad_tiempo_card = view.findViewById(R.id.card_overline_ciudad);
       title_tiempo_card.setText(getText(R.string.ciudad));
      TextView info_tiempo_card = view.findViewById(R.id.card_body);
      title_tiempo_card.setText(getString(R.string.ciudad));
      ImageView imagen_card_tiempo_frag = view.findViewById(R.id.card_header_imagenView) ;


      MaterialButton btn_like= (MaterialButton)view.findViewById(R.id.card_button_like) ;
      btn_like.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Toast.makeText(getActivity(), "has pulsado like", Toast.LENGTH_SHORT).show();

         }
      });

      MaterialButton btn_share= (MaterialButton)view.findViewById(R.id.card_button_share) ;
      btn_share.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Toast.makeText(getActivity(), "has pulsado share", Toast.LENGTH_SHORT).show();

         }
      });

      MaterialButton salirButton = view.findViewById(R.id.material_btn_frag_log);

   /*   // Set an error if the password is less than 8 characters.
      salirButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {


            ((NavigationHost) getActivity()).navigateTo(new LoginFragment(), false); // Navigate to the next Fragment
         }

      });
*/

      //set up the tool bar
      setUpToolbar(view);

      return view;
   }



   private void setUpToolbar(View view){
      Toolbar toolbar = view.findViewById(R.id.app_bar);
      AppCompatActivity activity = (AppCompatActivity) getActivity();
      if (activity != null){
         activity.setSupportActionBar(toolbar);
      }
      toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
         getContext(), view.findViewById(R.id.tiempo_frg),
         new AccelerateDecelerateInterpolator(),
         getContext().getResources().getDrawable(R.drawable.shr_branded_menu),
         getContext().getResources().getDrawable(R.drawable.shr_close_menu)));
      //getContext().getResources().getDrawable(R.drawable.shr_branded_menu), // Menu open icon
      //           getContext().getResources().getDrawable(R.drawable.shr_close_menu))); // Menu close icon
   }
   }
