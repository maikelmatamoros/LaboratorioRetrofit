package cr.ac.ucr.laboratorioretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import adapters.PaisesListAdapter;
import io.ApiAdapter;
import models.Paises;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<ArrayList<Paises>> {
    private  ArrayList<Paises> paises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Cargando Datos, por favor espere...", Toast.LENGTH_SHORT).show();
        Call<ArrayList<Paises>> call=ApiAdapter.getApiService().getPaises();
        call.enqueue(this);



    }

    @Override
    public void onResponse(Call<ArrayList<Paises>> call, Response<ArrayList<Paises>> response) {
        if(response.isSuccessful()){
            paises=response.body();
            Collections.sort(paises, new Comparator<Paises>(){

                @Override
                public int compare(Paises o1, Paises o2) {
                    return o1.getCountry().compareToIgnoreCase(o2.getCountry());
                }
            });
            PaisesListAdapter adapter=new PaisesListAdapter(paises,this);
            RecyclerView listaPaises= (RecyclerView) findViewById(R.id.rcPaises);
            listaPaises.setLayoutManager(new LinearLayoutManager(this));
            listaPaises.setAdapter(adapter);

        }
    }

    @Override
    public void onFailure(Call<ArrayList<Paises>> call, Throwable t) {
        Toast.makeText(this, "Ocurrió un error, trate más tarde", Toast.LENGTH_SHORT).show();
    }
}
