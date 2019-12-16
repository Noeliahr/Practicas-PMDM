package com.example.practicapmdm_movies.ui.Serie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicapmdm_movies.Peliculas_Adapter;
import com.example.practicapmdm_movies.R;
import com.example.practicapmdm_movies.Series.Series;
import com.example.practicapmdm_movies.Series_Adapter;
import com.example.practicapmdm_movies.models.MovieFeed;
import com.example.practicapmdm_movies.retrofit.MovieService;
import com.example.practicapmdm_movies.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesFragment extends Fragment {

    RecyclerView recyclerView, recyclerView2;

    Series_Adapter seriesAdapter, seriesAdapter1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view2);

        /* Establece que recyclerView tendrá un layout lineal, en concreto vertical*/
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));

        /*  Indica que cada uno de los items va a tener un tamaño fijo*/
        recyclerView.setHasFixedSize(true);

        /* Establece la  decoración por defecto de cada uno de lo items: una línea de división*/
        // recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        /* Instancia un objeto de la clase Peliculas_Adapter */
        seriesAdapter = new Series_Adapter(root.getContext());

        /* Establece el adaptador asociado a recyclerView */
        recyclerView.setAdapter(seriesAdapter);

        /* Pone la animación por defecto de recyclerView */
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadSearch();

        recyclerView2 = (RecyclerView) root.findViewById(R.id.recycler_view);

        /* Establece que recyclerView tendrá un layout lineal, en concreto vertical*/
        recyclerView2.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));

        /*  Indica que cada uno de los items va a tener un tamaño fijo*/
        recyclerView2.setHasFixedSize(true);

        /* Establece la  decoración por defecto de cada uno de lo items: una línea de división*/
        // recyclerView2.addItemDecoration(new DividerItemDecoration(recyclerView2.getContext(), DividerItemDecoration.VERTICAL));

        /* Instancia un objeto de la clase Peliculas_Adapter */
        seriesAdapter1 = new Series_Adapter(root.getContext());

        /* Establece el adaptador asociado a recyclerView */
        recyclerView2.setAdapter(seriesAdapter1);

        /* Pone la animación por defecto de recyclerView */
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        loadSearch2();

        return root;
    }
    public void loadSearch () {
        /* Crea la instanncia de retrofit */
        MovieService getMovie = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        /* Se definen los parámetros de la llamada a la función getTopRated */
        Call<Series> call = getMovie.getSeriesPopular(RetrofitInstance.getApiKey(), "es");
        /* Se hace una llamada asíncrona a la API */
        call.enqueue(new Callback<Series>() {

            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                switch (response.code()) {
                    /* En caso de respuesta correcta */
                    case 200:
                        /* En el objeto data de la clase MovieFeed se almacena el JSON convertido a objetos*/
                        Series data = response.body();
                        /* Se actualizan los datos contenidos en el adaptador */
                        seriesAdapter.swap(data.getResults());
                        /* Se notifica un cambio de datos para que se refresque la vista */
                        seriesAdapter.notifyDataSetChanged();
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
// Toast.makeText(MainActivity.this, "Error cargando películas", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void loadSearch2 () {
        /* Crea la instanncia de retrofit */
        MovieService getMovie = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        /* Se definen los parámetros de la llamada a la función getTopRated */
        Call<Series> call = getMovie.getSeriestop_rated(RetrofitInstance.getApiKey(), "es");
        /* Se hace una llamada asíncrona a la API */
        call.enqueue(new Callback<Series>() {


            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                switch (response.code()) {
                    /* En caso de respuesta correcta */
                    case 200:
                        /* En el objeto data de la clase MovieFeed se almacena el JSON convertido a objetos*/
                        Series data = response.body();
                        /* Se actualizan los datos contenidos en el adaptador */
                        seriesAdapter1.swap(data.getResults());
                        /* Se notifica un cambio de datos para que se refresque la vista */
                        seriesAdapter1.notifyDataSetChanged();
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
// Toast.makeText(MainActivity.this, "Error cargando películas", Toast.LENGTH_SHORT).show();
            }
        });
    }
}