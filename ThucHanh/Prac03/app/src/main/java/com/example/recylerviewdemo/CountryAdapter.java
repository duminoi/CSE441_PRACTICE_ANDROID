package com.example.recylerviewdemo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    // nhận dữ liệu truyền lưu vào v
    private List<Country> countries;


    public CountryAdapter(List<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.flag.setImageResource(country.getFlag());
        holder.txtName.setText(country.getCountryName());
        holder.txtCapital.setText(country.getCountryCapital());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("countryName", country.getCountryName());
            intent.putExtra("countryCapital", country.getCountryCapital());
            intent.putExtra("flag", country.getFlag());
            intent.putExtra("population", country.getPopulation());
            intent.putExtra("area", country.getArea());
            intent.putExtra("density", country.getDensity());
            intent.putExtra("worldShare", country.getWorldShare());
            v.getContext().startActivity(intent);
        });
    }



    @Override
    public int getItemCount() {
        return countries.size();
    }

    //Định nghịa Viewholder
    static class CountryViewHolder extends RecyclerView.ViewHolder {
        ImageView flag;
        TextView txtName;
        TextView txtCapital;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.imageView);
            txtName = itemView.findViewById(R.id.txt_country_Name);
            txtCapital = itemView.findViewById(R.id.txt_country_Capital);

        }
    }
}
