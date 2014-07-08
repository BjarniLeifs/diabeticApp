package ru;

/*
 * Created by Sindri on 06/07/14.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import jBerry.MySugar.R;
import ru.stuff.AbstractDynamicGridAdapter;

public class CustomArrayAdapter extends AbstractDynamicGridAdapter
{
    Context context;
    ArrayList<CustomClass> arrayList;

    ImageView imageView;
    TextView textView;

    public CustomArrayAdapter(Context context, ArrayList<CustomClass> arrayList)
    {
        super();
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount()
    {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return arrayList.get(position);
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public void reorderItems(int originalPosition, int newPosition) {

    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        CustomClass custom=arrayList.get(position);

        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(R.layout.custom, null, false);

        assert convertView != null;
        imageView=(ImageView)convertView.findViewById(R.id.imageView1);
        textView=(TextView)convertView.findViewById(R.id.textView1);

        imageView.setImageBitmap(custom.getBitmap());
        textView.setText(custom.getText());

        return convertView;

    }

}