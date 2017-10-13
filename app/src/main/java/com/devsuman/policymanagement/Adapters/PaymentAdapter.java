package com.devsuman.policymanagement.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devsuman.policymanagement.Model.Payments;
import com.devsuman.policymanagement.R;

import java.util.List;

/**
 * Created by Node1 on 10/13/2017.
 */

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder>  {
    public List<Payments> dataSet=null;

    public PaymentAdapter(List<Payments> payments)
    {
        dataSet=payments;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView txtPolicyNumber, txtAmount, txtPaymentDate;

        public ViewHolder(View v)
        {
            super(v);
            txtPolicyNumber = (TextView)itemView.findViewById(R.id.txtPolicyNumber);
            txtAmount = (TextView)itemView.findViewById(R.id.txtAmount);
            txtPaymentDate = (TextView)itemView.findViewById(R.id.txtPaymentDate);
            view=v;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_list_row, parent, false);
        PaymentAdapter.ViewHolder viewHolder = new PaymentAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Payments payments = dataSet.get(position);
        holder.txtPolicyNumber.setText(payments.getPolicyNumber());
        holder.txtAmount.setText(payments.getAmount().toString());
        holder.txtPaymentDate.setText(payments.getPaymentDate());
    }

    @Override
    public int getItemCount() {
        if(dataSet==null)
            return 0;
        else
            return dataSet.size();
    }
}
