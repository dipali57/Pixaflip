package com.example.pixaflip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {

    // creating a variable for array list and context.
    ArrayList<MyState> courseModalArrayList;
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void setData(ArrayList<MyState> courseModalArrayList)
    {
        this.courseModalArrayList=courseModalArrayList;
    }
    // creating a constructor for our variables.
    public StateAdapter(ArrayList<MyState> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_states_report, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        MyState modal = courseModalArrayList.get(position);
        holder.Location.setText(modal.getLoc());
      /*  holder.CasesI.setText(modal.getConfirmedCasesIndian());
        holder.CasesF.setText(modal.getConfirmedCasesForeign());
        holder.Discharged.setText(modal.getDischarged());
        holder.Deaths.setText(modal.getDeaths());
        holder.Total.setText(modal.getTotalConfirmed());*/

    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        TextView Location, CasesI, CasesF,Discharged,Deaths,Total;
        //private ImageView courseIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            Location = itemView.findViewById(R.id.idLocation);
         /*   CasesI = itemView.findViewById(R.id.t2);
            CasesF = itemView.findViewById(R.id.t3);
            Discharged = itemView.findViewById(R.id.t4);
            Deaths = itemView.findViewById(R.id.t5);
            Total = itemView.findViewById(R.id.t6);*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
