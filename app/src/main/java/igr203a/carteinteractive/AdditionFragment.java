package igr203a.carteinteractive;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import android.widget.Button;
/**
 * Created by Mathieu on 04/04/2016.
 */


public class AdditionFragment extends Fragment {

    private ArrayList<String> produitTab = null;
    private ArrayList<String> quantiteTab = null;
    private ArrayList<Integer> indiceTab = null;
    private AdapteurList3 adapter = null;
    private Button payerButton = null;
    FragmentInterface3 mCallback3;

    // Container Activity must implement this interface
    public interface FragmentInterface3 {
        public void updateOtherFragment3();
    }

    public AdditionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.addition_fragment, container, false);
        produitTab = new ArrayList<>();
        quantiteTab = new ArrayList<>();
        indiceTab = new ArrayList<>();

        payerButton = (Button)rootView.findViewById(R.id.payerButton);

        payerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ICI METTRE UN POPUP, SI CONFIRMATION ON PASS EN DESSOUS



                produitTab.clear();
                quantiteTab.clear();
                indiceTab.clear();

                SharedPreferences recupTableau = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor recupTableauEditor = recupTableau.edit();


                int size = recupTableau.getInt("Status_size2",0);

                for(int i=0; i<size-1;i++){
                    recupTableauEditor.remove("Status_2" + i);
                }

                recupTableauEditor.putInt("Status_size2", 0);

                recupTableauEditor.apply();

                adapter.notifyDataSetChanged();
                mCallback3.updateOtherFragment3();

            }
        });

        SharedPreferences tabCommand = PreferenceManager.getDefaultSharedPreferences(getActivity());

        int size2 = tabCommand.getInt("Status_size2", 0);
        for (int i = 0; i < size2; i++) {
            if (tabCommand.getString("Status_2" + i, null).startsWith("Commande")) {
                indiceTab.add(i);
            }
        }



        if (indiceTab.size() == 1) {
            for (int i = 0; i < size2; i++) {

                if (i == indiceTab.get(0)) {
                    produitTab.add(tabCommand.getString("Status_2" + i, null));
                    quantiteTab.add("Quantité");
                } else if (indiceTab.get(0) < i  && (i - indiceTab.get(0) - 1) % 2 == 0) {
                    produitTab.add(tabCommand.getString("Status_2" + i, null));
                    quantiteTab.add(tabCommand.getString("Status_2" + (i+1), null));

                }

            }}else{

            for (int i = 0; i < size2; i++) {
                for (int j = 0; j < indiceTab.size() - 1; j++) {
                    if (i == indiceTab.get(j)) {
                        produitTab.add(tabCommand.getString("Status_2" + i, null));
                        quantiteTab.add("Quantité");
                    } else if (indiceTab.get(j) < i && i < indiceTab.get(j + 1) && (i - indiceTab.get(j) - 1) % 2 == 0) {
                        produitTab.add(tabCommand.getString("Status_2" + i, null));
                        quantiteTab.add(tabCommand.getString("Status_2" + (i+1), null));

                    }
                }
                if (i==indiceTab.get(indiceTab.size()-1)) {
                    produitTab.add(tabCommand.getString("Status_2" + i, null));
                    quantiteTab.add("Quantité");
                }
                if (indiceTab.get(indiceTab.size() - 1) < i && (i - indiceTab.get(indiceTab.size() - 1) - 1) % 2 == 0) {
                    produitTab.add(tabCommand.getString("Status_2" + i, null));
                    quantiteTab.add(tabCommand.getString("Status_2" + (i+1), null));

                }

                for (int j = 0; j < produitTab.size(); j++) {

                    System.out.println("ON EST ARRIVE " + produitTab.get(j) + " " + quantiteTab.get(j));

                }


            }
        }




        adapter = new AdapteurList3(produitTab, this.getActivity(),quantiteTab);



        //handle listview and assign adapter
        ListView lView = (ListView)rootView.findViewById(R.id.listView3);
        if (lView==null){System.out.println("null");}

        lView.setAdapter(adapter);

        return rootView;


    }

    public void updateAdapter() {

        produitTab.clear();
        indiceTab.clear();
        quantiteTab.clear();
        SharedPreferences tabCommand = PreferenceManager.getDefaultSharedPreferences(getActivity());

        int size2 = tabCommand.getInt("Status_size2", 0);
        for (int i = 0; i < size2; i++) {
            if (tabCommand.getString("Status_2" + i, null).startsWith("Commande")) {
                indiceTab.add(i);
            }
        }

        for (int k = 0; k < indiceTab.size(); k++) {
            System.out.println("T   E    S    T " + indiceTab.get(k));
        }

        if (indiceTab.size() == 1) {
            for (int i = 0; i < size2; i++) {

                if (i == indiceTab.get(0)) {
                    produitTab.add(tabCommand.getString("Status_2" + i, null));
                    quantiteTab.add("Quantité");
                } else if (indiceTab.get(0) < i  && (i - indiceTab.get(0) - 1) % 2 == 0) {
                    produitTab.add(tabCommand.getString("Status_2" + i, null));
                    quantiteTab.add(tabCommand.getString("Status_2" + (i+1), null));

                }

            }}else{

            for (int i = 0; i < size2; i++) {
                for (int j = 0; j < indiceTab.size() - 1; j++) {
                    if (i == indiceTab.get(j)) {
                        produitTab.add(tabCommand.getString("Status_2" + i, null));
                        quantiteTab.add("Quantité");
                    } else if (indiceTab.get(j) < i && i < indiceTab.get(j + 1) && (i - indiceTab.get(j) - 1) % 2 == 0) {
                        produitTab.add(tabCommand.getString("Status_2" + i, null));
                        quantiteTab.add(tabCommand.getString("Status_2" + (i+1), null));

                    }
                }
                if (i==indiceTab.get(indiceTab.size()-1)){
                    produitTab.add(tabCommand.getString("Status_2" +i,null));
                    quantiteTab.add("Quantité");
                }
                if (indiceTab.get(indiceTab.size() - 1) < i && (i - indiceTab.get(indiceTab.size() - 1) - 1) % 2 == 0) {
                    produitTab.add(tabCommand.getString("Status_2" + i, null));
                    quantiteTab.add(tabCommand.getString("Status_2" + (i+1), null));

                }

                for (int j = 0; j < produitTab.size(); j++) {

                    System.out.println("ON EST ARRIVE " + produitTab.get(j) + " " + quantiteTab.get(j));

                }


            }
        }
        adapter.notifyDataSetChanged();
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
            mCallback3 = (FragmentInterface3) a;
        } catch (ClassCastException e) {
            throw new ClassCastException(a.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}

