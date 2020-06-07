package com.mndt.ghost.mndtivpokemon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.mndt.ghost.mndtivpokemon.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 2016/8/31.
 */
public class ImageAdapter extends BaseAdapter implements Filterable {
    private LayoutInflater myInflater;
    private ImageAdapterData mImageAdapterData;
    private ImageAdapterData imageAdapterData;
    private MyFilter filter;
    ImageView image;
    TextView name;

    public ImageAdapter(Context context, Integer[] image, String[] name) {
        myInflater = LayoutInflater.from(context);
        imageAdapterData = new ImageAdapterData();
        imageAdapterData.pokemonName =  new ArrayList(Arrays.asList(name));
        imageAdapterData.pokemonImage = new ArrayList(Arrays.asList(image));
        mImageAdapterData = imageAdapterData;
    }

    @Override
    public int getCount() {
        return  imageAdapterData.pokemonName.size();
    }

    @Override
    public Object getItem(int i) {
        return  imageAdapterData.pokemonName.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = myInflater.inflate(R.layout.list_item, null);
        image = (ImageView) view.findViewById(R.id.image_list_view);
        name = (TextView) view.findViewById(R.id.view_list_name);
        image.setImageResource(imageAdapterData.pokemonImage.get(i));
        name.setText(imageAdapterData.pokemonName.get(i));
        return view;
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter  = new MyFilter();
        }
        return filter;
    }

    private class MyFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults result = new FilterResults();

            if(constraint != null && constraint.toString().length() > 0)
            {
                ImageAdapterData filteredItems = new ImageAdapterData();
                for(int i = 0, l = imageAdapterData.pokemonName.size(); i < l; i++)
                {
                    String m = imageAdapterData.pokemonName.get(i);
                    int n =  imageAdapterData.pokemonImage.get(i);
                    int index = m.indexOf(constraint.toString());
                    if((index != -1) || m.contains(constraint)) {
                        filteredItems.pokemonName.add(m);
                        filteredItems.pokemonImage.add(n);
                    }
                }
                if(filteredItems.pokemonName.size() > 0) {
                    result.count = filteredItems.pokemonName.size();
                    result.values = filteredItems;
                }
                else {
                    synchronized(this) {
                        ImageAdapterData list = mImageAdapterData;
                        result.values = list;
                        result.count = list.pokemonName.size();
                    }
                }
            }
            else {
                synchronized(this) {
                    ImageAdapterData list = mImageAdapterData;
                    result.values = list;
                    result.count = list.pokemonName.size();
                }
            }
            return result;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            imageAdapterData = (ImageAdapterData)results.values;
            if(results.count>0){
                notifyDataSetChanged();
            }
            else{
                notifyDataSetInvalidated();
            }
        }
    }
}




