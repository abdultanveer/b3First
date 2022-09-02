package com.example.b3first;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {
    public static String TAG = CountriesAdapter.class.getSimpleName();
    String[] mCountries;
    public CountriesAdapter(String[] countries) {
        mCountries = countries;
    }

    //virkin going to market to buy new row planks
    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG,"virkin has bought a new plank from market");
        View plank = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new CountriesViewHolder(plank);
    }

    @Override //joyce h/w is good
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        Log.i(TAG,"joyce is writing the name--"+mCountries[position]);
        holder.tvPlank.setText(mCountries[position]);
    }

    @Override //zach - keep the count of no of data items
    public int getItemCount() {
        Log.i(TAG,"zack is counting--"+mCountries.length);
        return mCountries.length;
    }

    //this is the box holding the list items
    public class CountriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvPlank;
        public CountriesViewHolder(@NonNull View plank) {
            super(plank);
            plank.setOnClickListener(this);
            Log.i(TAG,"abdul is finding the textview on the plank bought by virkin");
            tvPlank = plank.findViewById(R.id.tvListItem);

        }

        @Override
        public void onClick(View view) {
           String item = tvPlank.getText().toString();
            Toast.makeText(tvPlank.getContext(), "item clicked--"+item, Toast.LENGTH_SHORT).show();
        }
    }
}
