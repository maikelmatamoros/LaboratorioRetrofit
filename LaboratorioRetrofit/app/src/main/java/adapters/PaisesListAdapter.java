package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;


import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.laboratorioretrofit.DetallesActivity;
import cr.ac.ucr.laboratorioretrofit.MainActivity;
import cr.ac.ucr.laboratorioretrofit.R;
import models.Paises;

public class PaisesListAdapter extends RecyclerView.Adapter<PaisesListAdapter.PaisesViewHolder> {


    private List<Paises> paisesList;
    private Context context;



    public PaisesListAdapter(ArrayList<Paises> paisesList, Context context) {
        this.paisesList = paisesList;
        this.context = context;
    }


    /*
    Permite construir cada item en base al layout que asigne
    */
    @NonNull
    @Override
    public PaisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_paises_layout, parent, false);
        PaisesViewHolder viewHolder = new PaisesViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaisesViewHolder holder, int position) {

        final Paises pais = paisesList.get(position);
        holder.country.setText("Country: "+pais.getCountry());
        holder.slug.setText("Slug: " +pais.getSlug());
        holder.iso.setText("ISO2: "+pais.getISO2());

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(), DetallesActivity.class);
                intent.putExtra("pais", pais.getCountry());
                v.getContext().startActivity(intent);
            }
        });
    }

    /*
     Me permite conocer el tamaño de la lista en tiempo real
     */
    @Override
    public int getItemCount() {
        //Toast.makeText(context, "Tamaño de lista: "+paisesList.size(), Toast.LENGTH_SHORT).show();
        return paisesList.size();
    }


    //View holder para lograr llenar el contenido de cada item
    public static class PaisesViewHolder extends RecyclerView.ViewHolder {

        public TextView country;
        public TextView slug;
        public TextView iso;
        public ConstraintLayout itemLayout;

        public PaisesViewHolder(@NonNull View itemView) {
            super(itemView);
            this.country = (TextView) itemView.findViewById(R.id.tv_country);
            this.slug = (TextView) itemView.findViewById(R.id.tv_slug);
            this.iso = (TextView) itemView.findViewById(R.id.tv_iso);
            this.itemLayout = (ConstraintLayout) itemView.findViewById(R.id.cl_paises);
        }
    }
}
