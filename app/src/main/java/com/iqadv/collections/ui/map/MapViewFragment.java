package com.iqadv.collections.ui.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iqadv.collections.R;
import com.iqadv.collections.databinding.FragmentMapViewBinding;
import com.iqadv.collections.databinding.ItemRestaurantDetailMapBinding;
import com.iqadv.collections.model.restaurantDetails.RestaurantModel;
import com.iqadv.collections.ui.adapters.TagAdapter;
import com.iqadv.collections.utlils.AppPermissions;
import com.iqadv.collections.utlils.Constants;
import com.iqadv.collections.utlils.LoadingDialog;

import java.util.ArrayList;
import java.util.Arrays;

public class MapViewFragment extends Fragment implements OnMapReadyCallback {

    private FragmentMapViewBinding binding;
    private ItemRestaurantDetailMapBinding detailMapBinding;
    private GoogleMap googleMap;
    private boolean isLocationPermissionOk;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location currentLocation;
    private CircleOptions circle;
    private MapViewModel mapViewModel;
    private RestaurantModel restaurantModelDetails;
    private ArrayList<RestaurantModel> restaurantModelArrayList;
    private NavController navController;
    private AlertDialog alertDialog;
    private LoadingDialog loadingDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMapViewBinding.inflate(inflater, container, false);

        loadingDialog = new LoadingDialog(requireActivity());
        circle = new CircleOptions();
        restaurantModelArrayList = new ArrayList<>();
        mapViewModel = new MapViewModel();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        navController = Navigation.findNavController(view);
        binding.ivCurrentLocation.setOnClickListener(view1 -> moveCameraToLocation(currentLocation));
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);

        googleMap.setMyLocationEnabled(false);

        if (AppPermissions.isLocationOk(requireContext())) {
            isLocationPermissionOk = true;
            setUpGoogleMap();

        } else if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Location Permission")
                    .setMessage("Near me required location permission to show you near by places")
                    .setPositiveButton("Ok", (dialog, which)
                            -> requestLocation())
                    .create().show();
        } else {
            requestLocation();
        }
    }

    private void requestLocation() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.ACCESS_BACKGROUND_LOCATION}, Constants.LOCATION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isLocationPermissionOk = true;
                setUpGoogleMap();
            } else {
                isLocationPermissionOk = false;
                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setUpGoogleMap() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isLocationPermissionOk = false;
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.setOnMarkerClickListener(this::onMarkerClick);

        setUpLocationUpdate();
    }

    private boolean onMarkerClick(Marker marker) {

        if (!(marker.getTitle().equals("Current Location"))) {

            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            ViewGroup viewGroup = requireView().findViewById(android.R.id.content);
            detailMapBinding = ItemRestaurantDetailMapBinding.inflate(LayoutInflater.from(requireView().getContext()), viewGroup, false);
            builder.setView(detailMapBinding.getRoot());

            alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = alertDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);

            detailMapBinding.icFavourite.frameFavourite.setOnClickListener(view -> {
                detailMapBinding.icFavourite.ivUnFavourite.setVisibility(View.GONE);
                detailMapBinding.icFavourite.ivFavourite.setVisibility(View.VISIBLE);
            });

            detailMapBinding.btnMoreDetailsMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavDirections action = MapViewFragmentDirections.actionMapViewFragmentToResturantProfileFragment(restaurantModelDetails);
                    navController.navigate(action);
                    alertDialog.dismiss();
                }
            });

            for (int i = 0; i < restaurantModelArrayList.size(); i++) {
                if (restaurantModelArrayList.get(i).getName().equals(marker.getTitle())) {
                    Log.e("mapName", restaurantModelArrayList.get(i).getName());
                    detailMapBinding.tvRestaurantMapName.setText(restaurantModelArrayList.get(i).getName());
                    detailMapBinding.tvDeliveryCost.setText(restaurantModelArrayList.get(i).getDelivery());
                    detailMapBinding.tvDeliverTime.setText(restaurantModelArrayList.get(i).getDeliveryTime());
                    Glide.with(requireActivity()).load(
                            Constants.IMAGE_URL + restaurantModelArrayList.get(i).getPic()).into(
                            detailMapBinding.rivRestaurantMap);

                    String[] separated = restaurantModelArrayList.get(i).getTags().split("\\$");
                    Log.e("sep", separated[0]);
                    TagAdapter tagAdapter = new TagAdapter(requireActivity(), Arrays.asList(separated));
                    detailMapBinding.recyclerViewTags.setLayoutManager(new LinearLayoutManager(requireActivity(),
                            LinearLayoutManager.HORIZONTAL, false));
                    detailMapBinding.recyclerViewTags.setAdapter(tagAdapter);
                    restaurantModelDetails = restaurantModelArrayList.get(i);


                }

            }
            alertDialog.show();

        }
        return false;
    }

    private void setUpLocationUpdate() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult != null) {
                    for (Location location : locationResult.getLocations()) {
                        Log.d("TAG", "onLocationResult: " + location.getLongitude() + " " + location.getLatitude());
                    }
                }
                if (locationResult != null) {
                    super.onLocationResult(locationResult);
                }
            }
        };
        startLocationUpdates();

    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isLocationPermissionOk = false;
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(requireContext(), "Location updated started", Toast.LENGTH_SHORT).show();
                    }

                });

        getCurrentLocation();
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isLocationPermissionOk = false;
            return;
        }
        loadingDialog.startLoading();

        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
            circle.center(new LatLng(location.getLatitude(), location.getLongitude()));
            circle.strokeWidth(4f);
            circle.strokeColor(Color.argb(200, 255, 150, 0));
            circle.fillColor(Color.argb(100, 255, 150, 0));
            circle.radius(1000.5);

            googleMap.addCircle(circle);
            currentLocation = location;
            moveCameraToLocation(location);

            mapViewModel.getRestaurants(Constants.HomeFeatures).observe(requireActivity(),
                    restaurantsResponse -> {
                        for (int i = 0; i < restaurantsResponse.getRestaurants().size(); i++) {
                            addMarker(restaurantsResponse.getRestaurants().get(i), i);
                            restaurantModelArrayList.addAll(restaurantsResponse.getRestaurants());

                        }
                    });

            loadingDialog.stopLoading();

        });
    }

    private void moveCameraToLocation(Location location) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new
                LatLng(location.getLatitude(), location.getLongitude()), 14);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                .title("Current Location");

        googleMap.addMarker(markerOptions);
        googleMap.animateCamera(cameraUpdate);

    }

    private void addMarker(RestaurantModel googlePlaceModel, int position) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(Double.parseDouble(googlePlaceModel.getLat()),
                        Double.parseDouble(googlePlaceModel.getLng())))
                .title(googlePlaceModel.getName());
        markerOptions.icon(getCustomIcon());
        googleMap.addMarker(markerOptions).setTag(position);
    }

    private BitmapDescriptor getCustomIcon() {
        Drawable background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_location);
        background.setTint(getResources().getColor(R.color.orange, null));
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
//340