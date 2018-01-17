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
import com.example.benjaminbouyer.applieseo.models.Starship;

import java.util.ArrayList;

/**
 * Device adapter
 */
public class StarshipsAdapter extends ArrayAdapter<Starship> {

    /**
     * Declare an inner interface to listen click event on device items
     */
    public interface OnDeviceSelectedListener {
        void handle(final Starship starship);
    }

    private final OnDeviceSelectedListener onDeviceSelectedListener;

    public StarshipsAdapter(@NonNull final Context context, final ArrayList<Starship> starships, final OnDeviceSelectedListener listener) {
        super(context, R.layout.starships_list_item, starships);
        onDeviceSelectedListener = listener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View holder = convertView;
        if (convertView == null) {
            final LayoutInflater vi = LayoutInflater.from(getContext());
            holder = vi.inflate(R.layout.starships_list_item, null);
        }

        final Starship starship = getItem(position);
        if (starship == null) {
            return holder;
        }

        // display the name
        final TextView deviceName = holder.findViewById(R.id.starshipName);
        if (deviceName != null) {
            deviceName.setText(starship.name);
        }

        // When this device item is clicked, trigger the listener
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (onDeviceSelectedListener != null) {
                    onDeviceSelectedListener.handle(starship);
                }
            }
        });

        return holder;
    }
}