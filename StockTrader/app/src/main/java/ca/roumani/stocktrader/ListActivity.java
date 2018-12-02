package ca.roumani.stocktrader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jba.roumani.lib.Stock;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        LinearLayout nameList = (LinearLayout)findViewById(R.id.area);

        Field[] fields = R.array.class.getFields();
        for(int i=0;i < fields.length;i++)
        {
            String name = fields[i].getName();

            if(!name.substring(0,1).equals("$"))
            {
                TextView names = new TextView(this);
                names.setText(name);
                nameList.addView(names);
            }
        }
    }

    public void doneButton(View x)
    {
        this.finish();
    }

}
