package com.example.recylerviewdemo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Country> countries;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        countries = new ArrayList<Country>();


        countries = new ArrayList<>();
        countries.add(new Country("Brazil", "Brasília", R.drawable.brazil, 211000000, 8515767, 24.1, 2.6));
        countries.add(new Country("China", "Beijing", R.drawable.china, 1393000000, 9596961, 145.6, 18.2));
        countries.add(new Country("India", "New Delhi", R.drawable.india, 1366000000, 3287263, 415.6, 17.7));
        countries.add(new Country("Indonesia", "Jakarta", R.drawable.indo, 270600000, 1904569, 142.0, 3.5));
        countries.add(new Country("United Kingdom", "London", R.drawable.uk, 66700000, 243610, 273.4, 0.9));
        countries.add(new Country("United States", "Washington, D.C.", R.drawable.us, 331000000, 9833517, 33.7, 4.3));
        countries.add(new Country("Vietnam", "Hà Nội", R.drawable.vn, 97000000, 331212, 293.7, 1.1));



        recyclerView = findViewById(R.id.rvc_Country);
        recyclerView.setAdapter(new CountryAdapter(this.countries));
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


    }
}