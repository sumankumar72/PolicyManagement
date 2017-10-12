package com.devsuman.policymanagement;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devsuman.policymanagement.Adapters.PolicyAdapter;
import com.devsuman.policymanagement.Model.ItemClickListener;
import com.devsuman.policymanagement.Model.Policy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class PolicyListFragment extends Fragment {
    private DatabaseReference policyDbReference;
    private View view;
    private List<Policy> policies= new ArrayList<>();


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PolicyAdapter adapter;

    public PolicyListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        policyDbReference = FirebaseDatabase.getInstance().getReference("Policies");
        view = inflater.inflate(R.layout.fragment_policy_list, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        getPolicies();
        return view;
    }


    private void getPolicies(){
        try {
            // Read from the database
            policyDbReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    //Policy value = dataSnapshot.getValue(Policy.class);
                    try {
                        for (DataSnapshot p :dataSnapshot.getChildren()){
                            Policy policy = p.getValue(Policy.class);
                            policies.add(policy);
                        }
                        setAdapter();
                    }catch (Exception e){
                        e.getStackTrace();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }


    private void setAdapter()
    {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PolicyAdapter(policies);
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });
    }

}
