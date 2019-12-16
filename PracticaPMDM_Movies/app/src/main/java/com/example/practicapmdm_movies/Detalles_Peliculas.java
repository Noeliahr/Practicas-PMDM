package com.example.practicapmdm_movies;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicapmdm_movies.Creditos.Cast;
import com.example.practicapmdm_movies.Creditos.Creditos;

import com.example.practicapmdm_movies.Generos.Generos;
import com.example.practicapmdm_movies.Generos.ProductionCompany;
import com.example.practicapmdm_movies.retrofit.MovieService;
import com.example.practicapmdm_movies.retrofit.RetrofitInstance;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.hanks.library.bang.SmallBangView;


public class Detalles_Peliculas extends AppCompatActivity {

    ImageView imageView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textViewStudio;
    RatingBar ratingBar;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    Creditos_Adapter creditos_adapter;
    Generos_Adapter generos_adapter;

    List<ProductionCompany> studio;

    private ArrayList<Cast> list;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_pelicula);

        Intent intent = getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        final String titulo = intent.getStringExtra("titulo");
        String puntuacion = intent.getStringExtra("puntuacion");
        final String imagen =intent.getStringExtra("imagen");
        final String imagen1 =intent.getStringExtra("imagen1");
        String descripcion= intent.getStringExtra("descripcion");
        String release=intent.getStringExtra("release");
        final String id =intent.getStringExtra("id");

        imageView=findViewById(R.id.imageView1);
        textView1=findViewById(R.id.textView1);
        ratingBar=findViewById(R.id.ratingBar2);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView8);

        Picasso.get().load("https://image.tmdb.org/t/p/w500"+imagen1).into(imageView);
        textView1.setText(titulo);

        ratingBar.setNumStars(5);
        ratingBar.setRating(Float.parseFloat( puntuacion)/2);

        textView2.setText(descripcion);

        textView3.setText(release.substring(0,4));

        final SmallBangView like_heart = findViewById(R.id.like_heart);
        FavoritosSQLiteHelper favoritosSQLiteHelper = new FavoritosSQLiteHelper(getApplicationContext(), "DBFavoritos", null, 1);
        SQLiteDatabase db = favoritosSQLiteHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Favoritos WHERE codigo='"+id+"' AND es_peli='1'",null);
        if (c.moveToFirst()) {
            like_heart.setSelected(true);
        }
        db.close();

        like_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like_heart.isSelected()) {
                    like_heart.likeAnimation(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    });
                    like_heart.setSelected(false);
                    FavoritosSQLiteHelper usdbh =
                            new FavoritosSQLiteHelper(getApplicationContext(), "DBFavoritos", null, 1);
                    SQLiteDatabase db = usdbh.getWritableDatabase();
                    db.execSQL("DELETE FROM Favoritos WHERE codigo='"+id+"' AND es_peli='1'");
                    db.close();

                } else {
                    like_heart.setSelected(true);
                    like_heart.likeAnimation(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    });
                    FavoritosSQLiteHelper usdbh =
                            new FavoritosSQLiteHelper(getApplicationContext(), "DBFavoritos", null, 1);
                    SQLiteDatabase db = usdbh.getWritableDatabase();
                    db.execSQL("INSERT INTO Favoritos (codigo, nombre, foto, es_peli) VALUES ('" + id + "', '" + titulo + "', '" + imagen +"', '1')");
                    db.close();
                }
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCreditos);

        /* Establece que recyclerView tendrá un layout lineal, en concreto vertical*/
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
//      recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        /*  Indica que cada uno de los items va a tener un tamaño fijo*/
        recyclerView.setHasFixedSize(true);

        /* Establece la  decoración por defecto de cada uno de lo items: una línea de división*/
        //recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));

        /* Instancia un objeto de la clase Peliculas_Adapter */
        creditos_adapter = new Creditos_Adapter(this);

        /* Establece el adaptador asociado a recyclerView */
        recyclerView.setAdapter(creditos_adapter);

        /* Pone la animación por defecto de recyclerView */
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadSearch(id);

        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_viewGenero);

        /* Establece que recyclerView tendrá un layout lineal, en concreto vertical*/
        recyclerView2.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView2.setLayoutManager(layoutManager2);
//      recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        /*  Indica que cada uno de los items va a tener un tamaño fijo*/
        //recyclerView2.setHasFixedSize(true);

        /* Establece la  decoración por defecto de cada uno de lo items: una línea de división*/
        //recyclerView2.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));

        /* Instancia un objeto de la clase Peliculas_Adapter */
        generos_adapter = new Generos_Adapter(this);

        /* Establece el adaptador asociado a recyclerView */
        recyclerView2.setAdapter(generos_adapter);

        /* Pone la animación por defecto de recyclerView */
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        textViewStudio=findViewById(R.id.textViewStudio);

        loadSearch2(id);

    }

    public void loadSearch(String id ) {
        /* Crea la instanncia de retrofit */
        MovieService getCreditos = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        /* Se definen los parámetros de la llamada a la función getTopRated */
        Call<Creditos> call = getCreditos.getCreditos(id,RetrofitInstance.getApiKey());
        /* Se hace una llamada asíncrona a la API */
        call.enqueue(new Callback<Creditos>() {

            @Override
            public void onResponse(Call<Creditos> call, Response<Creditos> response) {
                switch (response.code()) {
                    /* En caso de respuesta correcta */
                    case 200:
                        /* En el objeto data de la clase MovieFeed se almacena el JSON convertido a objetos*/
                        Creditos data = response.body();
                        /* Se actualizan los datos contenidos en el adaptador */
                        creditos_adapter.swap(data.getCast());
                        /* Se notifica un cambio de datos para que se refresque la vista */
                        creditos_adapter.notifyDataSetChanged();
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Creditos> call, Throwable t) {

            }
        });
    }

    public void loadSearch2(String id) {
        /* Crea la instanncia de retrofit */
        MovieService getGeneros = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        /* Se definen los parámetros de la llamada a la función getTopRated */
        //Log.i("id",String.valueOf(id));
        Call<Generos> call = getGeneros.getGeneros(id,RetrofitInstance.getApiKey(),"es");
        /* Se hace una llamada asíncrona a la API */
        call.enqueue(new Callback<Generos>() {

            @Override
            public void onResponse(Call<Generos> call, Response<Generos> response) {
                switch (response.code()) {
                    /* En caso de respuesta correcta */
                    case 200:
                        /* En el objeto data de la clase MovieFeed se almacena el JSON convertido a objetos*/
                        Generos data = response.body();
                        /* Se actualizan los datos contenidos en el adaptador */
                        generos_adapter.swap(data.getGenres());

                        studio=data.getProductionCompanies();

                        for (int i=0; i<studio.size(); i++){
                            textViewStudio.setText(textViewStudio.getText()+ " "+studio.get(i).getName());
                        }

                        /* Se notifica un cambio de datos para que se refresque la vista */
                        generos_adapter.notifyDataSetChanged();
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Generos> call, Throwable t) {

            }
        });
    }
}
