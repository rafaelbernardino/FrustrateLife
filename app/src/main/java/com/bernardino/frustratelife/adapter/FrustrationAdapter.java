package com.bernardino.frustratelife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bernardino.frustratelife.R;
import com.bernardino.frustratelife.listener.OnClickListener;
import com.bernardino.frustratelife.listener.OnItemLongClickListener;
import com.bernardino.frustratelife.persistence.bean.Frustration;

import java.util.List;

/**
 * Created by Rafael on 18/03/2017.
 */

public class FrustrationAdapter extends RecyclerView.Adapter<FrustrationAdapter.FrustrationViewHolder> {
    private Context context;
    private List<Frustration> listFrustration;
    private OnClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public FrustrationAdapter(Context context, List<Frustration> listFrustration, OnClickListener clickListener) {
        this.context = context;
        this.listFrustration = listFrustration;
        this.clickListener = clickListener;
    }

    @Override
    public FrustrationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.frustration_row, parent, false);
        return new FrustrationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FrustrationViewHolder holder, final int position) {
        holder.tvTitle.setText(listFrustration.get(position).getTitle());
        holder.tvDescription.setText(listFrustration.get(position).getDescription());
        holder.tvWhatToDo.setText(listFrustration.get(position).getWhatToDo());

        if (clickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(holder.itemView, position);
                }
            });
        }

//        if (longClickListener != null) {
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                   longClickListener.onItemLongClick(holder.getAdapterPosition());
//                    return false;
//                }
//            });
//        }
    }

    @Override
    public int getItemCount() {
        return listFrustration.size();
    }

    public Frustration getItem(int position) {
        return listFrustration.get(position);
    }

    public static class FrustrationViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        TextView tvWhatToDo;

        public FrustrationViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitleId);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescriptionId);
            tvWhatToDo = (TextView) itemView.findViewById(R.id.tvWhatToDoId);
        }
    }
}
