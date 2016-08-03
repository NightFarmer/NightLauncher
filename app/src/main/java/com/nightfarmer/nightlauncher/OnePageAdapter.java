package com.nightfarmer.nightlauncher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangfan on 16-8-3.
 */
public class OnePageAdapter extends RecyclerView.Adapter<OnePageAdapter.AppViewHolder> {

    ArrayList<ItemBean> itemList;

    public OnePageAdapter(ArrayList<ItemBean> itemList) {
        this.itemList = itemList;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_app, parent, false);
        return new AppViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        ItemBean itemBean = itemList.get(position);
        holder.app_icon.setImageDrawable(itemBean.icon);
        holder.app_name.setText(itemBean.name);
        holder.itemBean = itemBean;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder{

        private final ImageView app_icon;
        private final TextView app_name;
        private ItemBean itemBean;

        public AppViewHolder(final View itemView) {
            super(itemView);
            app_icon = (ImageView) itemView.findViewById(R.id.app_icon);
            app_name = (TextView) itemView.findViewById(R.id.app_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pkg = itemBean.appInfo.activityInfo.packageName;
                    //应用的主activity类
                    String cls = itemBean.appInfo.activityInfo.name;
                    ComponentName component = new ComponentName(pkg, cls);
                    Intent intent = new Intent();
                    intent.setComponent(component);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
