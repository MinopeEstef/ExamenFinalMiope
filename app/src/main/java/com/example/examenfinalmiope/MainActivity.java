package com.example.examenfinalmiope;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinalmiope.Adapter.HolidayAdapter;
import com.example.examenfinalmiope.DataBaseHelper.DataBaseHelper;
import com.example.examenfinalmiope.Entity.Country;
import com.example.examenfinalmiope.Entity.Holiday;
import com.example.examenfinalmiope.Entity.ResponseDB;
import com.example.examenfinalmiope.Manager.ApiManager;
import com.example.examenfinalmiope.Service.HolidayService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText EditText_year;
    private Spinner spinner_country;
    private Button btnGetHolidays,btnBd;
    private RecyclerView rvHolidays;
    private List<Holiday> holidayList;
    private HolidayAdapter holidayAdapter;
    String inicial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText_year = findViewById(R.id.EditText_year);
        spinner_country = findViewById(R.id.spinner_country);
        btnGetHolidays = findViewById(R.id.btnGetHolidays);
        rvHolidays = findViewById(R.id.rvHolidays);
        holidayList = new ArrayList<>();
        holidayAdapter = new HolidayAdapter(holidayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvHolidays.setLayoutManager(layoutManager);
        rvHolidays.setAdapter(holidayAdapter);
        btnBd = findViewById(R.id.btnBd);

        initializeSpinner();

        btnGetHolidays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditText_year.getText().toString().isEmpty()){
                    return;
                }
                int year = Integer.parseInt(EditText_year.getText().toString());
                HolidayService holidayService = ApiManager.getHolidayService();
                Call<List<Holiday>> call = holidayService.getHolidays(year, inicial);
                call.enqueue(new Callback<List<Holiday>>() {
                    @Override
                    public void onResponse(Call<List<Holiday>> call, Response<List<Holiday>> response) {
                        if (response.isSuccessful()) {
                            List<Holiday> holidays = response.body();
                            if (holidays != null) {
                                holidayList.clear();
                                holidayList.addAll(holidays);
                                holidayAdapter.notifyDataSetChanged();

                                holidays.forEach(item -> {
                                    DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                                    if (dataBaseHelper.getHolidayItem(item)){
                                        Toast.makeText(MainActivity.this, "Datos Duplicados, no se inserta.", Toast.LENGTH_SHORT).show();
                                    }else {
                                        ResponseDB info = dataBaseHelper.postHoliday(item);
                                        if (info.isStatus()){
                                            Toast.makeText(MainActivity.this, "Se guardo", Toast.LENGTH_SHORT).show();
                                         }
                                    }
                                });

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Holiday>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error al obtener los feriados.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnBd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holidayList.clear();
                EditText_year.setText("");
                spinner_country.setSelection(0);
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                holidayList.addAll(dataBaseHelper.getHolidayList());
                holidayAdapter.notifyDataSetChanged();
            }
        });

    }

    private void initializeSpinner() {
        Spinner spinnerCountries = findViewById(R.id.spinner_country);

        List<Country> countryList = new ArrayList<>();
        countryList.add(new Country("Andorra", "AD"));
        countryList.add(new Country("Albania", "AL"));
        countryList.add(new Country("Argentina", "AR"));
        countryList.add(new Country("Austria", "AT"));
        countryList.add(new Country("Australia", "AU"));
        countryList.add(new Country("Åland Islands", "AX"));
        countryList.add(new Country("Bosnia and Herzegovina", "BA"));
        countryList.add(new Country("Barbados", "BB"));
        countryList.add(new Country("Belgium", "BE"));
        countryList.add(new Country("Bulgaria", "BG"));
        countryList.add(new Country("Benin", "BJ"));
        countryList.add(new Country("Bolivia", "BO"));
        countryList.add(new Country("Brazil", "BR"));
        countryList.add(new Country("Bahamas", "BS"));
        countryList.add(new Country("Botswana", "BW"));
        countryList.add(new Country("Belarus", "BY"));
        countryList.add(new Country("Belize", "BZ"));
        countryList.add(new Country("Canada", "CA"));
        countryList.add(new Country("Switzerland", "CH"));
        countryList.add(new Country("Chile", "CL"));
        countryList.add(new Country("China", "CN"));
        countryList.add(new Country("Colombia", "CO"));
        countryList.add(new Country("Costa Rica", "CR"));
        countryList.add(new Country("Cuba", "CU"));
        countryList.add(new Country("Cyprus", "CY"));
        countryList.add(new Country("Czechia", "CZ"));
        countryList.add(new Country("Germany", "DE"));
        countryList.add(new Country("Denmark", "DK"));
        countryList.add(new Country("Dominican Republic", "DO"));
        countryList.add(new Country("Ecuador", "EC"));
        countryList.add(new Country("Estonia", "EE"));
        countryList.add(new Country("Egypt", "EG"));
        countryList.add(new Country("Spain", "ES"));
        countryList.add(new Country("Finland", "FI"));
        countryList.add(new Country("Faroe Islands", "FO"));
        countryList.add(new Country("France", "FR"));
        countryList.add(new Country("Gabon", "GA"));
        countryList.add(new Country("United Kingdom", "GB"));
        countryList.add(new Country("Grenada", "GD"));
        countryList.add(new Country("Guernsey", "GG"));
        countryList.add(new Country("Gibraltar", "GI"));
        countryList.add(new Country("Greenland", "GL"));
        countryList.add(new Country("Gambia", "GM"));
        countryList.add(new Country("Greece", "GR"));
        countryList.add(new Country("Guatemala", "GT"));
        countryList.add(new Country("Guyana", "GY"));
        countryList.add(new Country("Honduras", "HN"));
        countryList.add(new Country("Croatia", "HR"));
        countryList.add(new Country("Haiti", "HT"));
        countryList.add(new Country("Hungary", "HU"));
        countryList.add(new Country("Indonesia", "ID"));
        countryList.add(new Country("Ireland", "IE"));
        countryList.add(new Country("Isle of Man", "IM"));
        countryList.add(new Country("Iceland", "IS"));
        countryList.add(new Country("Italy", "IT"));
        countryList.add(new Country("Jersey", "JE"));
        countryList.add(new Country("Jamaica", "JM"));
        countryList.add(new Country("Japan", "JP"));
        countryList.add(new Country("South Korea", "KR"));
        countryList.add(new Country("Liechtenstein", "LI"));
        countryList.add(new Country("Lesotho", "LS"));
        countryList.add(new Country("Lithuania", "LT"));
        countryList.add(new Country("Luxembourg", "LU"));
        countryList.add(new Country("Latvia", "LV"));
        countryList.add(new Country("Morocco", "MA"));
        countryList.add(new Country("Monaco", "MC"));
        countryList.add(new Country("Moldova", "MD"));
        countryList.add(new Country("Montenegro", "ME"));
        countryList.add(new Country("Madagascar", "MG"));
        countryList.add(new Country("North Macedonia", "MK"));
        countryList.add(new Country("Mongolia", "MN"));
        countryList.add(new Country("Montserrat", "MS"));
        countryList.add(new Country("Malta", "MT"));
        countryList.add(new Country("Mexico", "MX"));
        countryList.add(new Country("Mozambique", "MZ"));
        countryList.add(new Country("Namibia", "NA"));
        countryList.add(new Country("Niger", "NE"));
        countryList.add(new Country("Nigeria", "NG"));
        countryList.add(new Country("Nicaragua", "NI"));
        countryList.add(new Country("Netherlands", "NL"));
        countryList.add(new Country("Norway", "NO"));
        countryList.add(new Country("New Zealand", "NZ"));
        countryList.add(new Country("Panama", "PA"));
        countryList.add(new Country("Peru", "PE"));
        countryList.add(new Country("Papua New Guinea", "PG"));
        countryList.add(new Country("Poland", "PL"));
        countryList.add(new Country("Puerto Rico", "PR"));
        countryList.add(new Country("Portugal", "PT"));
        countryList.add(new Country("Paraguay", "PY"));
        countryList.add(new Country("Romania", "RO"));
        countryList.add(new Country("Serbia", "RS"));
        countryList.add(new Country("Russia", "RU"));
        countryList.add(new Country("Sweden", "SE"));
        countryList.add(new Country("Singapore", "SG"));
        countryList.add(new Country("Slovenia", "SI"));
        countryList.add(new Country("Svalbard and Jan Mayen", "SJ"));
        countryList.add(new Country("Slovakia", "SK"));
        countryList.add(new Country("San Marino", "SM"));
        countryList.add(new Country("Suriname", "SR"));
        countryList.add(new Country("El Salvador", "SV"));
        countryList.add(new Country("Tunisia", "TN"));
        countryList.add(new Country("Turkey", "TR"));
        countryList.add(new Country("Ukraine", "UA"));
        countryList.add(new Country("United States", "US"));
        countryList.add(new Country("Uruguay", "UY"));
        countryList.add(new Country("Vatican City", "VA"));
        countryList.add(new Country("Venezuela", "VE"));
        countryList.add(new Country("Vietnam", "VN"));
        countryList.add(new Country("South Africa", "ZA"));
        countryList.add(new Country("Zimbabwe", "ZW"));

        ArrayAdapter<Country> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountries.setAdapter(adapter);

        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Country selectedCountry = (Country) adapterView.getItemAtPosition(position);
                inicial = selectedCountry.getInitials();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Acciones adicionales si no se selecciona ningún país
            }
        });
    }

}
