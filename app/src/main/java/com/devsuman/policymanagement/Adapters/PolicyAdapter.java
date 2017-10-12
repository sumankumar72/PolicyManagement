package com.devsuman.policymanagement.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devsuman.policymanagement.Model.ItemClickListener;
import com.devsuman.policymanagement.Model.Policy;
import com.devsuman.policymanagement.R;

import java.util.List;

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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View view;
        public TextView txtPolicyNumber;

        public ViewHolder(View v)
        {
            super(v);
            txtPolicyNumber = (TextView)itemView.findViewById(R.id.txtPolicyNumber);
            view=v;
        }
        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
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
