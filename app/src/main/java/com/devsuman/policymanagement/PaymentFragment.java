package com.devsuman.policymanagement;


import android.app.Dialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.devsuman.policymanagement.Adapters.PaymentAdapter;
import com.devsuman.policymanagement.Model.Payments;
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
public class PaymentFragment extends Fragment {
    private DatabaseReference paymentDbReference;
    private List<Payments> payments= new ArrayList<>();
    private String PolicyNumber="P1234", UserName="sumankumar72@gmail.com";
    private View view;
    private FloatingActionButton makeNewPayment;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PaymentAdapter adapter;

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_payment, container, false);
        paymentDbReference = FirebaseDatabase.getInstance().getReference("Payments");
        paymentDbReference.keepSynced(true);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        makeNewPayment = (FloatingActionButton)view.findViewById(R.id.makeNewPayment);
        makeNewPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewPaymentDialog();
            }
        });
        getPolicies();
        return view;
    }


    private void getPolicies(){
        try {
            // Read from the database
            Query query  = paymentDbReference.child(PolicyNumber);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        for (DataSnapshot p :dataSnapshot.getChildren()){
                            Payments payment = p.getValue(Payments.class);
                            payments.add(payment);
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

    private void addNewPaymentDialog(){
        //
        final Dialog dialog = new Dialog(getActivity());
        final TextView txtAmount, txtPaymentDate;
        final TextInputLayout txtAmount_layout,txtPaymentDate_layout;
        final Button btnMakePayment;

        dialog.setContentView(R.layout.payment_dialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);

        btnMakePayment = (Button)dialog.findViewById(R.id.btnMakePayment);
        txtAmount = (TextView)dialog.findViewById(R.id.txtAmount);
        txtPaymentDate = (TextView)dialog.findViewById(R.id.txtAmount);
        txtAmount_layout =  (TextInputLayout)dialog.findViewById(R.id.txtAmount_layout);
        txtPaymentDate_layout=  (TextInputLayout)dialog.findViewById(R.id.txtPaymentDate_layout);

        btnMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPaymentDate_layout.setError("");
                txtAmount_layout.setError("");

                if(TextUtils.isEmpty(txtAmount.getText().toString().trim())){
                    txtAmount_layout.setError("This field is required!");
                    return;
                }
                if(Float.parseFloat(txtAmount.getText().toString().trim())<=0){
                    txtAmount_layout.setError("Amount should be grater than zero!");
                    return;
                }
                if(TextUtils.isEmpty(txtPaymentDate.getText().toString().trim())){
                    txtPaymentDate_layout.setError("This field is required!");
                    return;
                }
                Payments payments = new Payments(
                        PolicyNumber, UserName,
                        Float.parseFloat(txtAmount.getText().toString()),
                        txtPaymentDate.getText().toString()
                );
                paymentDbReference.child(PolicyNumber).push().setValue(payments);
                Toast.makeText(getActivity(), "Payment added successfully", Toast.LENGTH_SHORT).show();
                dialog.hide();
            }
        });
        dialog.show();
    }


    private void setAdapter()
    {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PaymentAdapter(payments);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }


}
