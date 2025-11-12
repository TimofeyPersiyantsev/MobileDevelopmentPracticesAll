package com.example.applicationallpz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applicationallpz.R;
import com.example.applicationallpz.models.AppInfo;

import java.util.List;

public class AppListAdapter extends BaseAdapter {

    private Context context;
    private List<AppInfo> appList;
    private LayoutInflater inflater;

    public AppListAdapter(Context context, List<AppInfo> appList) {
        this.context = context;
        this.appList = appList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.practice4fragment_item_app_list, parent, false);
            holder = new ViewHolder();
            holder.imageViewIcon = convertView.findViewById(R.id.imageViewIcon);
            holder.textViewName = convertView.findViewById(R.id.textViewName);
            holder.textViewDescription = convertView.findViewById(R.id.textViewDescription);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AppInfo app = appList.get(position);

        holder.imageViewIcon.setImageResource(app.getIconResId());
        holder.textViewName.setText(app.getName());
        holder.textViewDescription.setText(app.getDescription());

        return convertView;
    }

    private static class ViewHolder {
        ImageView imageViewIcon;
        TextView textViewName;
        TextView textViewDescription;
    }
}