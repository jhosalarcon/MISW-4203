import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {

    public static class  ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreAlbum,cantante;
        ImageView imgAlbum;


        public ViewHolder(@NonNull View itemView) {
            super(intemView);
            nombreAlbum = (TextView)itemView.findViewById(R.id.tvAlbum);
            cantante = (TextView)itemView.findViewById(R.id.tvCantante);
            imgAlbum = (ImageView)itemView.findViewById(R.id.imageAlbum);
        }
    }

    public List<CantanteModelo> albumLista;

    public RecyclerViewAdaptador(List<CantanteModelo> albumLista) {
        this.albumLista = albumLista;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cover,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombreAlbum.setText(albumLista.get(position).getNombreAlbum());
        holder.cantante.setText(albumLista.get(position).getCantante());
        holder.imgAlbum.setImageResource(albumLista.get(position).getImgAlbum());
    }

    @Override
    public int getItemCount() {
        return albumLista.size();
    }
}
