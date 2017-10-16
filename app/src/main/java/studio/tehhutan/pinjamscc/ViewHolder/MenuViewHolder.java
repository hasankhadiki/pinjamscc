package studio.tehhutan.pinjamscc.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import studio.tehhutan.pinjamscc.Interface.ItemClickListener;
import studio.tehhutan.pinjamscc.R;

/**
 * Created by tehhutan on 02/10/17.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView txtNama, txtOrganisasi, txtKegiatan, txtJamMulai, txtJamAkhir;


    private ItemClickListener itemClickListener;
    public MenuViewHolder(View itemView) {
        super(itemView);

        txtNama = (TextView)itemView.findViewById(R.id.txt_namalengkap);
        txtKegiatan = (TextView)itemView.findViewById(R.id.txt_kegiatan);
        txtOrganisasi = (TextView)itemView.findViewById(R.id.txt_organisasi);
        txtJamMulai = (TextView)itemView.findViewById(R.id.txt_jam_awal);
        txtJamAkhir = (TextView)itemView.findViewById(R.id.txt_jam_akhir);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onCLick(view, getAdapterPosition(), false);
    }
}
