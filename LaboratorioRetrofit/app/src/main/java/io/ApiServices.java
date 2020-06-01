package io;

import java.util.ArrayList;

import models.Detalle;
import models.Paises;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("countries")
    Call<ArrayList<Paises>> getPaises();


    @GET("country/{pais}/status/confirmed?")
    Call<ArrayList<Detalle>> getDetallePais(@Path("pais")  String pais,@Query("from") String fecha1,
                                            @Query("to") String fecha2);
}
