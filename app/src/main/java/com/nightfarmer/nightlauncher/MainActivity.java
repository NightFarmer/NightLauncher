package com.nightfarmer.nightlauncher;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        List<ResolveInfo> mApps = loadApps();
        ArrayList<ItemBean> itemBeans = new ArrayList<>();
        for (ResolveInfo appInfo :
                mApps) {
            ItemBean itemBean = new ItemBean();
            itemBean.name = appInfo.loadLabel(getPackageManager()).toString();
            itemBean.icon = appInfo.loadIcon(getPackageManager());
            itemBean.appInfo = appInfo;
            itemBeans.add(itemBean);
        }
        recyclerView.setAdapter(new OnePageAdapter(itemBeans));
    }


    private List<ResolveInfo> loadApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        return getPackageManager().queryIntentActivities(mainIntent, 0);
    }

//    void addWidget() {
//        int appWidgetId = mAppWidgetHost.allocateAppWidgetId();
//        Intent pickIntent = new Intent(AppWidgetManager.ACTION_APPWIDGET_PICK);
//        pickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
//        // start the pick activity
//        startActivityForResult(pickIntent, REQUEST_PICK_APPWIDGET);
//    }

    public void onSetWallpaper() {
        //生成一个设置壁纸的请求
        final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
        Intent chooser = Intent.createChooser(pickWallpaper,"chooser_wallpaper");
        //发送设置壁纸的请求
        startActivity(chooser);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_settings:
                onSetWallpaper();
                return true;
        }
        return false;
    }
}
