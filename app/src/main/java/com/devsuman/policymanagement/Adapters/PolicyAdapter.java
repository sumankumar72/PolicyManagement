package com.devsuman.policymanagement.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devsuman.policymanagement.Model.ItemClickListener;
import com.devsuman.policymanagement.Model.Policy;
import com.devsuman.policymanagement.R;

import java.util.List;
import java.util.Random;

/**
 * Created by Node1 on 10/12/2017.
 */

public class PolicyAdapter extends RecyclerView.Adapter<PolicyAdapter.ViewHolder> {
    public List<Policy> dataSet=null;
    private static ItemClickListener clickListener;

    public PolicyAdapter(List<Policy> policies)
    {
        dataSet=policies;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public View view;
        public TextView txtPolicyNumber, txtCustomerName, txtPremium, txtDoc,icon_text;
        public ImageView icon_profile;

        public ViewHolder(View v)
        {
            super(v);
            txtPolicyNumber = (TextView)itemView.findViewById(R.id.txtPolicyNumber);
            txtCustomerName = (TextView)itemView.findViewById(R.id.txtCustomerName);
            txtPremium = (TextView)itemView.findViewById(R.id.txtPremium);
            txtDoc = (TextView)itemView.findViewById(R.id.txtDoc);
            icon_text = (TextView)itemView.findViewById(R.id.icon_text);
            icon_profile = (ImageView)itemView.findViewById(R.id.icon_profile);

            view=v;
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

    private void applyProfilePicture(ViewHolder holder, Policy policy) {
        holder.icon_profile.setImageResource(R.drawable.bg_circle);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        holder.icon_profile.setColorFilter(color);
        holder.icon_text.setVisibility(View.VISIBLE);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.policy_list, parent, false);
        PolicyAdapter.ViewHolder viewHolder = new PolicyAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Policy policy = dataSet.get(position);
        holder.txtPolicyNumber.setText(policy.getPolicyNumber());
        holder.txtCustomerName.setText(policy.getName());
        holder.txtDoc.setText("DOC - "+policy.getDOC());
        holder.txtPremium.setText(policy.getPremium().toString()+" to be collect");
        holder.icon_text.setText(policy.getName().substring(0, 1).toUpperCase());
        applyProfilePicture(holder, policy);
    }

    @Override
    public int getItemCount() {
        if(dataSet==null)
            return 0;
        else
            return dataSet.size();
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
