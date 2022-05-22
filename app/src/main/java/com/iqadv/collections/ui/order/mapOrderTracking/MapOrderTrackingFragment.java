package com.iqadv.collections.ui.order.mapOrderTracking;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.Task;
import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentMapOrderTrackingBinding;
import com.iqadv.collections.utlils.Constants;
import com.iqadv.collections.utlils.LoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapOrderTrackingFragment extends Fragment {
    SupportMapFragment supportMapFragment;
    GoogleMap mGoogleMap;
    LatLng latLng2;
    private FragmentMapOrderTrackingBinding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private AlertDialog alertDialog;
    private LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapOrderTrackingBinding.inflate(inflater, container, false);
        loadingDialog = new LoadingDialog(requireActivity());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        supportMapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.fragmentMapTracking);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        if (ActivityCompat.checkSelfPermission(requireActivity()
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, Constants.LOCATION_REQUEST_CODE);
        }
        latLng2 = new LatLng(30.300954, 31.755047);

    }

    private void getCurrentLocation() {
        @SuppressLint("MissingPermission")
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {

            if (location != null) {
                supportMapFragment.getMapAsync(googleMap -> {
                    mGoogleMap = googleMap;
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(latLng).title("here"));
                    googleMap.addMarker(new MarkerOptions().position(latLng2).title("mess"));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                    drawRoute(latLng, latLng2);
                    googleMap.setOnMarkerClickListener(this::onMarkerClick);

                });
            }

        });
    }

    private boolean onMarkerClick(Marker marker) {
        if (!(marker.getTitle().equals("Current Location"))) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            ViewGroup viewGroup = requireView().findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(requireView().getContext())
                    .inflate(R.layout.item_oder_map_tracking, viewGroup, false);

            builder.setView(dialogView);

            alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = alertDialog.getWindow();

            window.setGravity(Gravity.BOTTOM);
//            for (int i = 0; i < addressModelArrayList.size(); i++) {
//                if (addressModelArrayList.get(i).getRestaurant_name().equals(marker.getTitle())) {
//                    Log.e("mapName", addressModelArrayList.get(i).getRestaurant_name());
//                    detailMapBinding.tvRestaurantMapName.setText(addressModelArrayList.get(i).getRestaurant_name());
//                    detailMapBinding.tvDeliveryCost.setText(addressModelArrayList.get(i).getDelivery_cost());
//                    detailMapBinding.tvDeliverTime.setText(addressModelArrayList.get(i).getDelivery_time());
//                    detailMapBinding.tvCategory1.setText(addressModelArrayList.get(i).getRest_Category_1());
//                    detailMapBinding.tvCategory2.setText(addressModelArrayList.get(i).getRest_Category_2());
//                    detailMapBinding.tvCategory3.setText(addressModelArrayList.get(i).getRest_Category_3());
//                    detailMapBinding.rivRestaurantMap.setImageResource(addressModelArrayList.get(i).getRestaurant_image());
//                    restaurantModelDetails = addressModelArrayList.get(i);
//                }
//            }
            alertDialog.show();

        }
        return false;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Constants.LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }

    void drawRoute(LatLng source, LatLng destination) {
        StringRequest request = new StringRequest(0, "https://maps.googleapis.com/maps/api/directions/json?origin=" + source.latitude + "," + source.longitude +
                "&destination=" + destination.latitude + "," + destination.longitude + "&key=AIzaSyAZTJpSbPKLHSxIE6V96XxZqhcpSn-zzFM&language=ar",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject js = new JSONObject(response);
                            JSONArray routes = js.getJSONArray("routes");
                            JSONObject route = routes.getJSONObject(0);
                            JSONObject leg = route.getJSONArray("legs").getJSONObject(0);
                            String distance = leg.getJSONObject("distance").getString("text");
                            String duration = leg.getJSONObject("duration").getString("text");
//                            TextView lbl_distance = findViewById(R.id.lbl_distance);
//                            TextView lbl_duration = findViewById(R.id.lbl_duration);

                            String points = route.getJSONObject("overview_polyline").getString("points");
                            mGoogleMap.addPolyline(new PolylineOptions().addAll(decodePoly(points)).color(Color.parseColor("#FE724C")).width(12).geodesic(true));
//                            lbl_distance.setText(distance);
//                            lbl_duration.setText(duration);

                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

    private ArrayList<LatLng> decodePoly(String encoded) {
        ArrayList<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }


}