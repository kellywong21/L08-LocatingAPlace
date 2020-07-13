package sg.edu.rp.webservices.l08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity{

    Button btnNorth,btnCentral,btnEast;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                LatLng poi_Singapore= new LatLng(1.352083,103.819839);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore, (float) 10.5));

                LatLng poi_North_Marker= new LatLng(1.451470,103.824490);
                Marker NorthMarker = map.addMarker(new MarkerOptions().position(poi_North_Marker).title("HQ North").snippet("Blk 588C Montreal Drive, 753588\nOperating Hours: 10am-5pm\nTel: 65433456").icon(BitmapDescriptorFactory.fromResource(R.drawable.star1)));

                LatLng poi_Central_Marker = new LatLng(1.302850,103.833430);
                Marker CentralMarker = map.addMarker(new MarkerOptions().position(poi_Central_Marker).title("Central Branch").snippet("2 Orchard Turn, 238801\nOperating Hours: 11am-8pm\nTel: 67788652").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_East_Marker = new LatLng(1.352310,103.950060);
                Marker EastMarker = map.addMarker(new MarkerOptions().position(poi_East_Marker).title("East Branch").snippet("201D Tampines Street 21, 524201\nOperating Hours: 9am-5pm\nTel: 66776677").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);


                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED){
                    map.setMyLocationEnabled(true);
                }else{
                    Log.e("GMap - Permission","GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
                }



                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String title = marker.getSnippet();
                        Toast.makeText(MainActivity.this,title,Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });


            }


        });





        btnNorth = findViewById(R.id.btnNorth);
        btnCentral = findViewById(R.id.btnCentral);
        btnEast = findViewById(R.id.btnEast);

        btnNorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LatLng poi_North= new LatLng(1.451470,103.824490);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,15));
            }
        });

        btnCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng poi_Central = new LatLng(1.302850,103.833430);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,15));
            }
        });

        btnEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng poi_East = new LatLng(1.352310,103.950060);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,15));
            }
        });


    }


}
