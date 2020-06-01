package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;


import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.laboratorioretrofit.R;
import models.Detalle;

public class DetalleListAdapter extends RecyclerView.Adapter<DetalleListAdapter.DetalleViewHolder> {


    private List<Detalle> detallesList;
    private Context context;



    public DetalleListAdapter(ArrayList<Detalle> detallesList, Context context) {
        this.detallesList = detallesList;
        this.context = context;
    }


    /*
    Permite construir cada item en base al layout que asigne
    */
    @NonNull
    @Override
    public DetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_detalle_casos, parent, false);
        DetalleViewHolder viewHolder = new DetalleViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetalleViewHolder holder, int position) {

        final Detalle detalle = detallesList.get(position);
        holder.provincia.setText("Province: " + detalle.getProvince());
        holder.city.setText("City: " + detalle.getCity());
        holder.casos.setText("Cases: "+detalle.getCases());
        holder.status.setText("Status: "+detalle.getStatus());
        holder.fecha.setText("Date: "+detalle.getDate());
    }

    /*
     Me permite conocer el tamaño de la lista en tiempo real
     */
    @Override
    public int getItemCount() {

        return detallesList.size();
    }


    //View holder para lograr llenar el contenido de cada item
    public static class DetalleViewHolder extends RecyclerView.ViewHolder {

        public TextView provincia;
        public TextView status;
        public TextView casos;
        public TextView fecha;
        public TextView city;
        public ConstraintLayout itemLayout;

        public DetalleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.provincia = (TextView) itemView.findViewById(R.id.tv_provincia);
            this.casos = (TextView) itemView.findViewById(R.id.tv_casos);
            this.status = (TextView) itemView.findViewById(R.id.tv_status);
            this.fecha = (TextView) itemView.findViewById(R.id.tv_fecha);
            this.city = (TextView) itemView.findViewById(R.id.tv_city);
            this.itemLayout = (ConstraintLayout) itemView.findViewById(R.id.cl_casos);
        }
    }
}
