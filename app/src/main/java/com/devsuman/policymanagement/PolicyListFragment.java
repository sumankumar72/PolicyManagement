package com.devsuman.policymanagement;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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
import com.google.firebase.database.Query;
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
    private String username="sumankumar72@gmail.com";


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
            Query query  = policyDbReference.orderByChild("userName").equalTo(username);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
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
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    if (ft != null) {
                        ft.replace(R.id.content, new PaymentFragment());
                        ft.commit();
                    }
                }
            }
        });
    }

}
