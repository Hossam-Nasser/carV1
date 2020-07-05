package com.example.car;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;




import java.util.ArrayList;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder> {
    private ArrayList<CarModel> Carslist = new ArrayList<>();

    public CarsAdapter(ArrayList<CarModel> cars) {
        this.Carslist=cars;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_car_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        // holder.movienametv.setText(movielist.get(position).getName());
        CarModel c=Carslist.get(position);
        {
            if (c.getImage() != null && !c.getImage().isEmpty()) {
                holder.Tv_image.setImageURI(Uri.parse(Carslist.get(position).getImage()));
            }
            holder.tv_model.setText(Carslist.get(position).getModel());
            holder.tv_color.setText(Carslist.get(position).getColor());
            holder.tv_dpl.setText(Carslist.get(position).getDpl() + "");
        }
    }

    @Override
    public int getItemCount() {
        return Carslist.size();
    }

    public void setlist(ArrayList<CarModel> cars) {
        this.Carslist = cars;

    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        ImageView Tv_image;
        TextView tv_model,tv_color,tv_dpl;
        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            //movienametv = itemView.findViewById(R.id.movie_name_tv);
            Tv_image=itemView.findViewById(R.id.custom_car_iv);
            tv_model=itemView.findViewById(R.id.custom_car_tv_model);
            tv_color=itemView.findViewById(R.id.custom_car_tv_color);
            tv_dpl=itemView.findViewById(R.id.custom_car_tv_dpl);
        }
    }
}
