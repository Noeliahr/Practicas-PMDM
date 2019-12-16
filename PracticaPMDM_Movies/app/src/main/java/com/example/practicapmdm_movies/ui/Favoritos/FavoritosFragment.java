package com.example.practicapmdm_movies.ui.Favoritos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicapmdm_movies.Creditos.Creditos;
import com.example.practicapmdm_movies.Creditos_Adapter;
import com.example.practicapmdm_movies.FavoritosAdapter;
import com.example.practicapmdm_movies.FavoritosSQLiteHelper;
import com.example.practicapmdm_movies.R;
import com.example.practicapmdm_movies.retrofit.MovieService;
import com.example.practicapmdm_movies.retrofit.RetrofitInstance;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritosFragment extends Fragment {

    private FavoritosViewModel notificationsViewModel;
    FavoritosAdapter favoritosAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        RecyclerView recyclerView;

        FavoritosSQLiteHelper favoritosSQLiteHelper = new FavoritosSQLiteHelper(root.getContext(), "DBFavoritos", null, 1);
        SQLiteDatabase db = favoritosSQLiteHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Favoritos",null);
        if (c.moveToFirst()) {

            recyclerView = (RecyclerView) root.findViewById(R.id.recycler_viewFavoritos);

            /* Establece que recyclerView tendrá un layout lineal, en concreto vertical*/
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);

            /*  Indica que cada uno de los items va a tener un tamaño fijo*/
            recyclerView.setHasFixedSize(true);

            /* Instancia un objeto de la clase Peliculas_Adapter */
            favoritosAdapter = new FavoritosAdapter(root.getContext(), c);
            /* Establece el adaptador asociado a recyclerView */
            recyclerView.setAdapter(favoritosAdapter);
            favoritosAdapter.swap(c);
            /* Pone la animación por defecto de recyclerView */
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        }
        db.close();
        return root;
    }

}