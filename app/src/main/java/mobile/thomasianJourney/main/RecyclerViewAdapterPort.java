package mobile.thomasianJourney.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import mobile.thomasianJourney.main.vieweventsfragments.R;

public class RecyclerViewAdapterPort extends RecyclerView.Adapter<RecyclerViewAdapterPort.MyViewHolder> {

    Context mContext;
    List<Contact> mData;

    public RecyclerViewAdapterPort(Context context, List<Contact> data) {
        mContext = context;
        mData = data;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.sample_time_line, viewGroup, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.tv_name.setText(mData.get(i).getTitle());
        myViewHolder.tv_desc.setText(mData.get(i).getDescription());
        myViewHolder.tv_date.setText(mData.get(i).getDate());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_contact;
        private TextView tv_name;
        private TextView tv_desc;
        private TextView tv_date;
        private ImageView img;


        public MyViewHolder(View itemView){
            super(itemView);
            item_contact = itemView.findViewById(R.id.port1);
            tv_name = itemView.findViewById(R.id.tv_event_title);
            tv_desc = itemView.findViewById(R.id.tv_event_desc);
            tv_date = itemView.findViewById(R.id.tv_event_date);
//            img = itemView.findViewById(R.id.img_contact);

        }
    }

}


