package cr.ac.ucr.laboratorioretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import adapters.DetalleListAdapter;
import io.ApiAdapter;
import models.Detalle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesActivity extends AppCompatActivity implements Callback<ArrayList<Detalle>> {
    private ArrayList<Detalle> detalles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        Toast.makeText(this, "Cargando Datos, por favor espere...", Toast.LENGTH_SHORT).show();
        String pais= getIntent().getExtras().getString("pais");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendario=Calendar.getInstance();
        String fecha1=fmt.format(calendario.getTime());
        calendario.setTime(calendario.getTime());
        calendario.add(Calendar.DAY_OF_YEAR,-1);
        String fecha2=fmt.format(calendario.getTime());

        Call<ArrayList<Detalle>> call= ApiAdapter.getApiService().getDetallePais(pais,fecha2,fecha1);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Detalle>> call, Response<ArrayList<Detalle>> response) {
        if (response.isSuccessful()){
            detalles=response.body();

            DetalleListAdapter adapter=new DetalleListAdapter(detalles,this);
            RecyclerView listaDetalles= (RecyclerView) findViewById(R.id.rcDetalles);
            listaDetalles.setLayoutManager(new LinearLayoutManager(this));
            listaDetalles.setAdapter(adapter);
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Detalle>> call, Throwable t) {
        Toast.makeText(this, "Ocurrió un error, trate más tarde", Toast.LENGTH_SHORT).show();
    }
}
