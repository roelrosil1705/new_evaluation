package com.cloudwalkdigital.activation.evaluationapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudwalkdigital.activation.evaluationapp.R;
import com.cloudwalkdigital.activation.evaluationapp.activities.EventActivity;
import com.cloudwalkdigital.activation.evaluationapp.activities.ProjectsActivity;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Laiza-PC on 21/04/2016.
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ItemHolder> {
    private List<String> itemsName;
    private OnItemClickListener onItemClickListener;
    private LayoutInflater layoutInflater;
//    private Context mcon;
    private Context context;

    public ProjectAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        itemsName = new ArrayList<String>();
    }

    @Override
    public ProjectAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.project_list, parent, false);
        return new ItemHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(ProjectAdapter.ItemHolder holder, int position) {
        holder.setItemName(itemsName.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsName.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public OnItemClickListener getOnItemClickListener(){
        return onItemClickListener;
    }

    public interface OnItemClickListener{
        public void onItemClick(ItemHolder item, int position);
    }

    public void add(int location, String iName, String iNumber, String iDate){
        itemsName.add(location, iName + ":" + iNumber + ":" + iDate);
        notifyItemInserted(location);
    }

    public void remove(int location){
        if(location >= itemsName.size())
            return;

        itemsName.remove(location);
        notifyItemRemoved(location);
    }

//    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//        private ProjectAdapter parent;
//        TextView textItemName;
//
//        public ItemHolder(View itemView, ProjectAdapter parent) {
//            super(itemView);
//            itemView.setOnClickListener(this);
//            this.parent = parent;
//            textItemName = (TextView) itemView.findViewById(R.id.listed_item);
//        }
//
//        public void setItemName(CharSequence name){
//            textItemName.setText(name);
//        }
//
//        public CharSequence getItemName(){
//            return textItemName.getText();
//        }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ProjectAdapter parent;
        TextView textItemName, textJoId, textDate ;

        public ItemHolder(View itemView, ProjectAdapter parent) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.parent = parent;
            Log.e("parent >>>", String.valueOf(parent));
//            textItemName = (TextView) itemView.findViewById(R.id.listed_item);
            textItemName = (TextView) itemView.findViewById(R.id.listed_itemname);
            textJoId = (TextView) itemView.findViewById(R.id.listed_itemnumber);
            textDate = (TextView) itemView.findViewById(R.id.listed_itemdate);
        }

        public void setItemName(CharSequence name){
//            Log.e(">>>>>>>>>>>>>>>", name.toString());
            String CurrentString = name.toString();
            String[] separated = CurrentString.split(":");
//            StringTokenizer tokens = new StringTokenizer(name.toString(), ":");
            textItemName.setText(separated[0]);
//            Log.e("textItemName >>>", separated[0]);
            textJoId.setText(separated[1]);
//            Log.e("textJoId >>>", separated[1]);
            textDate.setText(separated[2]);
//            Log.e("textDate >>>", separated[2]);
        }

        public CharSequence getItemName (){
            return textJoId.getText();
        }



        @Override
        public void onClick(View v) {
//            Log.e("textItemName >>>", "daan");
//            final OnItemClickListener listener = parent.getOnItemClickListener();
//            if(listener != null){
//                listener.onItemClick(this, getPosition());
//                context.startActivity(new Intent(activity, EventActivity.class));
//
//
//            }
//            final Intent intent;
//            intent = new Intent(v.getContext(), EventActivity.class);
//            v.getContext().startActivity(intent);
            v.getContext().startActivity(new Intent(v.getContext(), EventActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));

        }


    }
}
