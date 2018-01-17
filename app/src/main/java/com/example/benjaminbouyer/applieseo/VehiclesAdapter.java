package com.example.benjaminbouyer.applieseo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.benjaminbouyer.applieseo.models.Starship;
import com.example.benjaminbouyer.applieseo.models.Vehicle;

import java.util.ArrayList;

/**
 * Device adapter
 */
public class VehiclesAdapter extends ArrayAdapter<Vehicle> {

    /**
     * Declare an inner interface to listen click event on device items
     */
    public interface OnDeviceSelectedListener {
        void handle(final Vehicle vehicle);
    }

    private final OnDeviceSelectedListener onDeviceSelectedListener;

    public VehiclesAdapter(@NonNull final Context context, final ArrayList<Vehicle> vehicles, final OnDeviceSelectedListener listener) {
        super(context, R.layout.starships_list_item, vehicles);
        onDeviceSelectedListener = listener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View holder = convertView;
        if (convertView == null) {
            final LayoutInflater vi = LayoutInflater.from(getContext());
            holder = vi.inflate(R.layout.vehicles_list_item, null);
        }

        final Vehicle vehicle = getItem(position);
        if (vehicle == null) {
            return holder;
        }

        // display the name
        final TextView deviceName = holder.findViewById(R.id.vehicleName);
        if (deviceName != null) {
            deviceName.setText(vehicle.name);
        }

        // When this device item is clicked, trigger the listener
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (onDeviceSelectedListener != null) {
                    onDeviceSelectedListener.handle(vehicle);
                }
            }
        });

        return holder;
    }
}