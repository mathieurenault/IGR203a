package igr203a.carteinteractive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import java.lang.Object;import java.lang.Override;import java.lang.String;import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
/**
 * Created by Gautier on 03/03/2015.
 */

public class AdapteurList extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    AdapterInterface buttonListener;
    private boolean alert = false;



    public interface AdapterInterface {
        public void updateCommande();
    }



    public AdapteurList(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public AdapteurList (ArrayList<String> list, Context context, AdapterInterface buttonListener)
    {
        this.list=list;
        this.context=context;
        this.buttonListener = buttonListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.my_custom_layout, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        String split[] = null;
        split = list.get(position).split("/");
        listItemText.setText(split[0] + " (" + split[1] + " €)");

        ImageView image = (ImageView)view.findViewById(R.id.imageView);
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(split[2], "drawable", context.getPackageName());
        Log.i("ressource", String.valueOf(resourceId));
        image.setImageResource(resourceId);

        //Handle buttons and add onClickListeners
        Button addButton = (Button)view.findViewById(R.id.add_btn);
        //Button addBtn = (Button)view.findViewById(R.id.add_btn);

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                SharedPreferences nomP = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor nomPEditor = nomP.edit();

                SharedPreferences recupTableau = PreferenceManager.getDefaultSharedPreferences(context);

                int size = recupTableau.getInt("Status_size",0);

                ArrayList<String> tableauProduit = new ArrayList<String>();

                String nomProduit = list.get(position);

                System.out.println(nomProduit);


                if(size==0){
                    tableauProduit.add(nomProduit);
                }
                else {
                    for(int i=0;i<size;i++){
                        tableauProduit.add(recupTableau.getString("Status_" + i, null));
                        if (nomProduit.equals(recupTableau.getString("Status_" + i,null))){alert=true;}
                    }
                    tableauProduit.add(nomProduit);
                }


                System.out.println(tableauProduit.get(0));

                nomPEditor.putInt("Status_size", tableauProduit.size());


                if (alert==true){Toast.makeText(context, "Produit déjà en cours de commande ! Séléctionnez la quantité dans le menu Commande", Toast.LENGTH_SHORT).show();
                    alert=false;
                }

                else{
                    for(int i=0; i<size;i++){
                        nomPEditor.remove("Status_" + i);

                    }
                    for(int i=0;i<tableauProduit.size();i++){
                        System.out.println("Test" + tableauProduit.get(i));
                        nomPEditor.putString("Status_" + i, tableauProduit.get(i));
                    }

                    nomPEditor.apply();

                    buttonListener.updateCommande();
                    notifyDataSetChanged();



                }}



        });

        return view;
    }
}