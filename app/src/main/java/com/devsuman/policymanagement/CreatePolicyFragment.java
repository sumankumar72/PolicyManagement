package com.devsuman.policymanagement;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreatePolicyFragment extends Fragment {
    private AppCompatSpinner ddlModeOfPayment;
    private EditText txtPolicyNumber,txtName,txtSumAssured,txtTable, txtTerm,txtDoc,txtPremium;
    private TextInputLayout txtPolicyNumber_layout,txtName_layout ,txtSumAssured_layout,
            txtModeOfPolicy_layout,txtTable_layout,txtTerm_layout,txtDoc_layout,txtPremium_layout;
    private Button btnSave;
    private View view;

    public CreatePolicyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_policy, container, false);

        txtPolicyNumber_layout = (TextInputLayout)view.findViewById(R.id.txtPolicyNumber_layout);
        txtName_layout = (TextInputLayout)view.findViewById(R.id.txtName_layout);
        txtSumAssured_layout = (TextInputLayout)view.findViewById(R.id.txtSumAssured_layout);
        txtModeOfPolicy_layout = (TextInputLayout)view.findViewById(R.id.txtModeOfPolicy_layout);
        txtTable_layout = (TextInputLayout)view.findViewById(R.id.txtTable_layout);
        txtTerm_layout = (TextInputLayout)view.findViewById(R.id.txtTerm_layout);
        txtDoc_layout = (TextInputLayout)view.findViewById(R.id.txtDoc_layout);
        txtPremium_layout = (TextInputLayout)view.findViewById(R.id.txtPremium_layout);

        txtPolicyNumber = (EditText)view.findViewById(R.id.txtPolicyNumber);
        txtPolicyNumber = (EditText)view.findViewById(R.id.txtPolicyNumber);
        txtPolicyNumber = (EditText)view.findViewById(R.id.txtPolicyNumber);
        txtPolicyNumber = (EditText)view.findViewById(R.id.txtPolicyNumber);
        txtPolicyNumber = (EditText)view.findViewById(R.id.txtPolicyNumber);
        txtPolicyNumber = (EditText)view.findViewById(R.id.txtPolicyNumber);
        txtPolicyNumber = (EditText)view.findViewById(R.id.txtPolicyNumber);

        txtPolicyNumber,txtName,txtSumAssured,txtTable, txtTerm,txtDoc,txtPremium
        return view;
    }



    private boolean validate(){
        boolean flag =true;

        return flag;
    }
}
