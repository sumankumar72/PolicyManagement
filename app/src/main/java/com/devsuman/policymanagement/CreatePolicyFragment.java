package com.devsuman.policymanagement;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.devsuman.policymanagement.Model.Policy;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


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

    private String username="sumankumar72@gmail.com";
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private DatabaseReference policyDbReference;

    public CreatePolicyFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_policy, container, false);

        policyDbReference = FirebaseDatabase.getInstance().getReference("Policies");

        txtPolicyNumber_layout = (TextInputLayout)view.findViewById(R.id.txtPolicyNumber_layout);
        txtName_layout = (TextInputLayout)view.findViewById(R.id.txtName_layout);
        txtSumAssured_layout = (TextInputLayout)view.findViewById(R.id.txtSumAssured_layout);
        txtModeOfPolicy_layout = (TextInputLayout)view.findViewById(R.id.txtModeOfPolicy_layout);
        txtTable_layout = (TextInputLayout)view.findViewById(R.id.txtTable_layout);
        txtTerm_layout = (TextInputLayout)view.findViewById(R.id.txtTerm_layout);
        txtDoc_layout = (TextInputLayout)view.findViewById(R.id.txtDoc_layout);
        txtPremium_layout = (TextInputLayout)view.findViewById(R.id.txtPremium_layout);

        txtPolicyNumber = (EditText)view.findViewById(R.id.txtPolicyNumber);
        txtName = (EditText)view.findViewById(R.id.txtName);
        txtSumAssured = (EditText)view.findViewById(R.id.txtSumAssured);
        txtTable = (EditText)view.findViewById(R.id.txtTable);
        txtTerm = (EditText)view.findViewById(R.id.txtTerm);
        txtDoc = (EditText)view.findViewById(R.id.txtDoc);
        txtPremium = (EditText)view.findViewById(R.id.txtPremium);

        ddlModeOfPayment = (AppCompatSpinner)view.findViewById(R.id.ddlModeOfPayment);
        initDatePicker();

        btnSave= (Button)view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    savePolicy();
                }
            }
        });
        return view;
    }

    private void initDatePicker(){
        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date =  new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                txtDoc.setText(sdf.format(calendar.getTime()));
            }
        };


        final DatePickerDialog datePicker = new DatePickerDialog(getActivity(), date, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datePicker.getDatePicker().setMaxDate(System.currentTimeMillis());

        txtDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show();
            }
        });
    }


    private boolean validate() {
        boolean flag = true;

        txtPolicyNumber_layout.setError("");
        txtName_layout.setError("");
        txtSumAssured_layout.setError("");
        txtTable_layout.setError("");
        txtTerm_layout.setError("");
        txtDoc_layout.setError("");
        txtPremium_layout.setError("");


        if (TextUtils.isEmpty(txtPolicyNumber.getText().toString())) {
            txtPolicyNumber_layout.setError("This field is required!");
            return false;
        }
        if (TextUtils.isEmpty(txtName.getText().toString())) {
            txtName_layout.setError("This field is required!");
            return false;
        }
        if (TextUtils.isEmpty(txtSumAssured.getText().toString())) {
            txtSumAssured_layout.setError("This field is required!");
            return false;
        }
        if (Integer.parseInt(txtSumAssured.getText().toString()) <= 0) {
            txtSumAssured_layout.setError("Sum assured should be grater that zero(0)!");
            return false;
        }

        if (TextUtils.isEmpty(txtTable.getText().toString())) {
            txtTable_layout.setError("This field is required!");
            return false;
        }
        if (TextUtils.isEmpty(txtTerm.getText().toString())) {
            txtTerm.setError("This field is required!");
            return false;
        }
        if (TextUtils.isEmpty(txtDoc.getText().toString())) {
            txtDoc_layout.setError("This field is required!");
            return false;
        }
        if (TextUtils.isEmpty(txtPremium.getText().toString())) {
            txtPremium_layout.setError("This field is required!");
            return false;
        }
        if (Integer.parseInt(txtPremium.getText().toString()) <= 0) {
            txtPremium_layout.setError("Premium should be grater that zero(0)!");
            return false;
        }

        return flag;
    }

    private void savePolicy(){

        try {
            Policy policy = new Policy(username,
                    txtPolicyNumber.getText().toString().trim(),
                    txtName.getText().toString().trim(),
                    Float.parseFloat(txtSumAssured.getText().toString()),
                    ddlModeOfPayment.getSelectedItem().toString(),
                    txtTable.getText().toString().trim(),
                    txtTerm.getText().toString().trim(),
                    txtDoc.getText().toString().trim(),
                    Float.parseFloat(txtPremium.getText().toString())
            );
            //String id = policyDbReference.push().getKey();
            policyDbReference.push().setValue(policy);
        }
        catch (Exception ex){
            String s = ex.getMessage();
            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        }
    }
}
