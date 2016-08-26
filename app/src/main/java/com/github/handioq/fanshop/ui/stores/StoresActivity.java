package com.github.handioq.fanshop.ui.stores;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.github.handioq.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StoresActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final LatLng MINSK_CENTER = new LatLng(53.902653, 27.552881);
    private static final LatLng SHOP_MINSK_1 = new LatLng(53.902053, 27.557116);
    private static final LatLng SHOP_MINSK_2 = new LatLng(53.907722, 27.497003);
    private static final LatLng SHOP_MINSK_3 = new LatLng(53.886140, 27.552523);
    private static final float MAP_ZOOM = 12;
    private static final boolean ENABLE_GPS_LOCATION = true;

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;

        map.addMarker(new MarkerOptions()
                .position(SHOP_MINSK_1)
                .title(getString(R.string.shop_minsk_1))
                .snippet(getString(R.string.shop_minsk_1_message)));

        map.addMarker(new MarkerOptions()
                .position(SHOP_MINSK_2)
                .title(getString(R.string.shop_minsk_2))
                .snippet(getString(R.string.shop_minsk_2_message)));

        map.addMarker(new MarkerOptions()
                .position(SHOP_MINSK_3)
                .title(getString(R.string.shop_minsk_3))
                .snippet(getString(R.string.shop_minsk_3_message)));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(MINSK_CENTER, MAP_ZOOM));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(ENABLE_GPS_LOCATION);
        } else {
            Toast.makeText(this, getString(R.string.gps_permission_denied), Toast.LENGTH_SHORT).show();
        }
    }
}
