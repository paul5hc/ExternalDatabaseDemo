package com.javahelps.com.javahelps.externaldatabasedemo;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.listView);
        ImageView painting = (ImageView) findViewById(R.id.imageView);
        int paintingID = 10003;

        // Database Access
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        String imageName = databaseAccess.imageName(paintingID);
        List<String> data = databaseAccess.getData(paintingID);
        databaseAccess.close();

        Resources res = getResources();
        int resID = res.getIdentifier(imageName , "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID );
        painting.setImageDrawable(drawable );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        this.listView.setAdapter(adapter);
    }
}
