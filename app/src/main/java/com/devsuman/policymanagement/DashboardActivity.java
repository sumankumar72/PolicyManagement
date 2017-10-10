package com.devsuman.policymanagement;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devsuman.policymanagement.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardActivity extends AppCompatActivity {
    private String PREFS_NAME="PolicyManagementPref";
    private String UserName;
    private DatabaseReference mRef, policyDbReference;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    pushFragment(new PolicyListFragment());
                    return true;
                case R.id.navigation_dashboard:
                    pushFragment(new CreatePolicyFragment());
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }

            return false;
        }

    };

    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.content, fragment);
                ft.commit();
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mRef = FirebaseDatabase.getInstance().getReference("Users");
        policyDbReference = FirebaseDatabase.getInstance().getReference("Policies");


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getLoginDetails();

        if(UserName==""){
            loginDialog();
            return;
        }
        pushFragment(new PolicyListFragment());
    }

    private void loginDialog(){
        final EditText txtEmail, txtPassword, txtName;
        final TextInputLayout txtPassword_layout, txtEmail_layout, txtName_layout;
        Button btnLogin;
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.login_dialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);

        txtEmail = (EditText) dialog.findViewById(R.id.txtEmail);
        txtPassword = (EditText) dialog.findViewById(R.id.txtPassword);
        txtName = (EditText) dialog.findViewById(R.id.txtName);

        btnLogin = (Button)dialog.findViewById(R.id.btnLogin);

        txtPassword_layout = (TextInputLayout)dialog.findViewById(R.id.txtPassword_layout);
        txtEmail_layout = (TextInputLayout)dialog.findViewById(R.id.txtEmail_layout);
        txtName_layout = (TextInputLayout)dialog.findViewById(R.id.txtName_layout);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(txtName.getText().toString().trim())) {
                    txtName_layout.setError("Name is required!");
                    return;
                }

                if(TextUtils.isEmpty(txtEmail.getText().toString().trim())) {
                    txtEmail_layout.setError("Email Id is required!");
                    return;
                }
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches()){
                    txtEmail_layout.setError("Enter valid email!");
                    return;
                }

                if(TextUtils.isEmpty(txtPassword.getText().toString().trim())) {
                    txtPassword_layout.setError("Password is required!");
                    return;
                }

                User user = new User(txtEmail.getText().toString().trim(),
                        txtPassword.getText().toString().trim(),
                        txtName.getText().toString().trim());

                String id = mRef.push().getKey();
                mRef.child(id).setValue(user);
                saveLogindetail(user.UserName);
                dialog.dismiss();
                pushFragment(new PolicyListFragment());
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    private void getLoginDetails(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        UserName = settings.getString("username", "");
    }

    private void saveLogindetail(String userName){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", userName);
        editor.commit();
    }

    private void getPolicy(){

    }

    private void CreatePolicy(){

    }

}
