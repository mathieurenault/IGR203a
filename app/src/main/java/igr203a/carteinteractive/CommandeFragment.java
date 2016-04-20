package igr203a.carteinteractive;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by Mathieu on 04/04/2016.
 */


public class CommandeFragment extends Fragment {

    public CommandeFragment() {
        // Required empty public constructor
    }

    private AdapteurList2 adapter2 = null;
    private ArrayList<String> commandList = null;
    private ArrayList<String> uniteProduitList = null;
    private Button validButton = null;
    private int count = 1;
    private ArrayList<String> additionTab = null;
    FragmentInterface2 mCallback2;
    private TextView total = null;

    // Container Activity must implement this interface
    public interface FragmentInterface2 {
        public void updateOtherFragment2();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.commande_fragment, container, false);

        total = (TextView) rootView.findViewById(R.id.total);

        validButton = (Button)rootView.findViewById(R.id.validButton);

        validButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Confirmation");
                builder.setMessage("Valider la commande ?");

                builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        if (commandList.size()>0) {

                            additionTab.clear();

                            SharedPreferences tabCommand = PreferenceManager.getDefaultSharedPreferences(getActivity());
                            SharedPreferences.Editor tabCommandEditor = tabCommand.edit();

                            int size2 = tabCommand.getInt("Status_size2", 0);

                            if (size2 == 0) {
                            } else {
                                for (int i = 0; i < size2; i++) {
                                    additionTab.add(tabCommand.getString("Status_2" + i, null));
                                    tabCommandEditor.remove("Status_2" + i);
                                }
                            }


                            additionTab.add("Commande " + count + " (" + String.valueOf(updateTotal()) + " €)");
                            for (int i = 0; i < commandList.size(); i++) {
                                additionTab.add(commandList.get(i));
                                additionTab.add(uniteProduitList.get(i));
                            }

                            tabCommandEditor.putInt("Status_size2", additionTab.size());
                            for (int i = 0; i < additionTab.size(); i++) {
                                tabCommandEditor.putString("Status_2" + i, additionTab.get(i));
                                Log.i("Addition TAB", additionTab.get(i));
                            }

                            tabCommandEditor.commit();
                            mCallback2.updateOtherFragment2();

                        }


                        //

                        commandList.clear();
                        uniteProduitList.clear();

                        SharedPreferences recupTableau = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor recupTableauEditor = recupTableau.edit();


                        int size = recupTableau.getInt("Status_size",0);

                        for(int i=0; i<size-1;i++){
                            recupTableauEditor.remove("Status_" + i);
                        }

                        recupTableauEditor.putInt("Status_size", 0);

                        recupTableauEditor.apply();

                        adapter2.notifyDataSetChanged();

                        count = count+1;
                        dialog.dismiss();
                        updateTotal();
                        Toast.makeText(getActivity(), "Votre commande a bien été validée !", Toast.LENGTH_LONG).show();
                    }

                });

                builder.setNegativeButton("NON", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        additionTab = new ArrayList<>();

        commandList = new ArrayList<>();
        uniteProduitList = new ArrayList<>();

        SharedPreferences recupTableau = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        int size = recupTableau.getInt("Status_size",0);
        for(int i=0;i<size;i++){
            commandList.add(recupTableau.getString("Status_" + i, null));
            uniteProduitList.add("1");
        }
        adapter2 = new AdapteurList2(commandList, this.getActivity(), uniteProduitList, this);



        //handle listview and assign adapter
        ListView lView = (ListView)rootView.findViewById(R.id.listView3);
        if (lView==null){System.out.println("null");}

        lView.setAdapter(adapter2);
        updateTotal();

        return rootView;
    }

    public void updateAdapter(){
        SharedPreferences recupTableau = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        int size = recupTableau.getInt("Status_size",0);
        commandList.clear();
        for(int i=0;i<size;i++){

            commandList.add(recupTableau.getString("Status_" + i, null));
        }
        uniteProduitList.add("1");

        adapter2.notifyDataSetChanged();

        updateTotal();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateTotal();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a = null;

        if (context instanceof Activity){
            a=(Activity) context;
        }

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback2 = (FragmentInterface2) a;
        } catch (ClassCastException e) {
            throw new ClassCastException(a.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public void updateCount(){
        count =1;
    }

    public float updateTotal() {
        float somme = 0;
        int i = -1;
        for (String produit : commandList) {
            i++;
            somme += Float.parseFloat(produit.split("/")[1]) * Float.parseFloat(uniteProduitList.get(i));
        }
        total.setText(String.valueOf(somme) + " €");

        return somme;
    }



}
