package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.luizromariofilho.TraducaoColaborativaMobile.entities.Texto;

import java.util.List;

/**
 * Created by Luiz Romario Filho on 11/22/2014.
 */
public class TextoListAdapter extends BaseAdapter {
    private List<Texto> list;
    private Context context;

    public TextoListAdapter(List<Texto> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Texto texto = (Texto) this.getItem(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_listagem_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.txtTextoOriginal);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgStatus);
        if(texto.getTraduzido()){
            imageView.setBackgroundResource(R.drawable.ok);
        }else{
            imageView.setBackgroundResource(R.drawable.not);
        }
        textView.setText(texto.getTextoOriginal());

        return view;
    }
}
