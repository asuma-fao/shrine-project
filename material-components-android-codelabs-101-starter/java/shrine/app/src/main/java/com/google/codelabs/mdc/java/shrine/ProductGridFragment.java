package com.google.codelabs.mdc.java.shrine;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.codelabs.mdc.java.shrine.network.ProductEntry;
import com.google.codelabs.mdc.java.shrine.staggeredgridlayout.StaggeredProductCardRecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductGridFragment extends Fragment implements NavigationHost{
   private static final String TAG = "ProductGridFragment";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater menuInflater) {
         menuInflater.inflate(R.menu.shr_toolbar_menu,menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.shr_product_grid_fragment, container, false);
       MaterialButton btn= view.findViewById(R.id.material_btn_fragHome) ;
       btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             Toast.makeText(getActivity(), "has pulsado home", Toast.LENGTH_SHORT).show();

          }
       });
       MaterialButton salirButton = view.findViewById(R.id.material_btn_frag_log);

       // Set an error if the password is less than 8 characters.
       salirButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {


                ((NavigationHost) getActivity()).navigateTo(new LoginFragment(), false); // Navigate to the next Fragment
             }

       });
       MaterialButton tiempoButton = view.findViewById(R.id.material_btn_frag_tiempo);

       // Set an error if the password is less than 8 characters.
       tiempoButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            // if()

             Toast.makeText(getActivity(), "has pulsado tiempo", Toast.LENGTH_SHORT).show();

             Intent myIntent = new Intent(getActivity(),TiempoActivity.class);
             getActivity().startActivity(myIntent);  // Navigate to the next Fragment
          }

       });


       //set up the tool bar
        setUpToolbar(view);

        // setup recyclerView
        //TODO RecyclerView link recycleview with view model

        // Set up the RecyclerView
        //esto para el grid layout
       RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, RecyclerView.VERTICAL, false));
        ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(
           ProductEntry.initProductEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));
        //esto  es para recycleview con card y el que viene  es para straggred
        /*RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 2 ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        StaggeredProductCardRecyclerViewAdapter adapter = new StaggeredProductCardRecyclerViewAdapter(
           ProductEntry.initProductEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_large);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));*/
        //set cut corner backgroud for API 32+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.findViewById(R.id.product_grid)
               .setBackgroundResource(R.drawable.shr_product_grid_background_shape);
        }
        return view;
    }



    private void setUpToolbar(View view){
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null){
            activity.setSupportActionBar(toolbar);
        }
        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
           getContext(), view.findViewById(R.id.product_grid),
           new AccelerateDecelerateInterpolator(),
           getContext().getResources().getDrawable(R.drawable.shr_branded_menu),
           getContext().getResources().getDrawable(R.drawable.shr_close_menu)));
        //getContext().getResources().getDrawable(R.drawable.shr_branded_menu), // Menu open icon
        //           getContext().getResources().getDrawable(R.drawable.shr_close_menu))); // Menu close icon
    }

   @Override
   public void navigateTo(Fragment fragment, boolean addToBackstack) {
      FragmentTransaction transaction =
        getActivity().getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.container, fragment);

      if (addToBackstack) {
         transaction.addToBackStack(null);
      }

      transaction.commit();
   }
   }

