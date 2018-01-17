package com.example.benjaminbouyer.applieseo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.benjaminbouyer.applieseo.models.People;

import java.util.ArrayList;

/**
 * Device adapter
 */
public class PeopleAdapter extends ArrayAdapter<People> {

    /**
     * Declare an inner interface to listen click event on device items
     */
    public interface OnDeviceSelectedListener {
        void handle(final People people);
    }

    private final OnDeviceSelectedListener onDeviceSelectedListener;

    public PeopleAdapter(@NonNull final Context context, final ArrayList<People> peoples, final OnDeviceSelectedListener listener) {
        super(context, R.layout.characters_list_item, peoples);
        onDeviceSelectedListener = listener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View holder = convertView;
        if (convertView == null) {
            final LayoutInflater vi = LayoutInflater.from(getContext());
            holder = vi.inflate(R.layout.characters_list_item, null);
        }

        final People people = getItem(position);
        if (people == null) {
            return holder;
        }

        // display the name
        final TextView deviceName = holder.findViewById(R.id.characterName);
        if (deviceName != null) {
            deviceName.setText(people.name);
        }

        // When this device item is clicked, trigger the listener
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (onDeviceSelectedListener != null) {
                    onDeviceSelectedListener.handle(people);
                }
            }
        });

        return holder;
    }
}