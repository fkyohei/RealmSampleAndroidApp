package com.example.g_2015.realmsample;

import com.example.g_2015.realmsample.model.Task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by g-2015 on 2015/03/04.
 */
public final class TaskAdapter extends RealmAdapter<Task, TaskAdapter.ViewHolder> {
    private final static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    private final static Date DATE = new Date();

    public static class ViewHolder extends RealmAdapter.ViewHolder {
        public final CheckBox checkBox;
        public final TextView createdAt;
        public final TextView todoText;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.check_box);
            createdAt = (TextView) itemView.findViewById(R.id.created_at);
            todoText = (TextView) itemView.findViewById(R.id.todo_text);
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
        return new ViewHolder(inflater.inflate(R.layout.task_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Task item = getItem(position);
        DATE.setTime(item.getCreated());
        holder.createdAt.setText(FORMAT.format(DATE));
        holder.todoText.setText(item.getTask());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }

}