package com.example.g_2015.realmsample;

import com.example.g_2015.realmsample.model.Task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by g-2015 on 2015/03/04.
 */
public final class TaskAdapter extends RealmAdapter<Task, TaskAdapter.ViewHolder> {

    public static class ViewHolder extends RealmAdapter.ViewHolder {
        public final TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    public static interface OnItemClickListener {
        public void onItemClick(Task task);
    }

    private final LayoutInflater inflater;
    private final OnItemClickListener listener;

    public TaskAdapter(Context context, OnItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.abc_list_menu_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Task item = getItem(position);
//        holder.title.setText(item.title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }

}