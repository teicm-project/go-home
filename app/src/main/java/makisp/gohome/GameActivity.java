package makisp.gohome;

import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.games.Game;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;

public class GameActivity extends FragmentActivity implements OnMapReadyCallback {


    public Button buttonItems;
    public Button buttonProfile;
    private GoogleMap mMap;

    ///// Μια μεταβλητή σαν χάρτης που κραταει πληροφορια για ολα τα σημάδια /////
    private HashMap<Integer, Marker> visibleMarkers = new HashMap<>();
    public static int progress;
    private List<Markers> markers;
    public static Credential cre;


    ///// Event Handler για άνοιγμα του Inventory /////


    public void PressItemButton() {

        buttonItems = (Button) findViewById(R.id.buttonItems);
        buttonItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(GameActivity.this, MainActivity.class));
            }
        });
    }


    ///// Event Handler για άνοιγμα του ProfileActivity /////


    public void PressProfileButton() {

        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(GameActivity.this, MainActivity.class));
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        PressItemButton();
        PressProfileButton();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ///// Listener για όταν πατήθει κάποιο σημάδι να ανοίγει το σενάριο Activity /////
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.isVisible()) {
                    Intent intent = new Intent(GameActivity.this, ScenarioActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        markers = MainActivity.db.getAllMarkers();
        cre = MainActivity.db.getCredential(LoginActivity.activeUser);
        progress = cre.getProgress();
        addMarkersToMap(markers);
    }


    ///// Όταν κάνει επαναφορά στον χάρτη εμφανίζει το σώστο σημάδι /////
    @Override
    protected void onResume() {
        super.onResume();
        if((progress >= 2 && progress <= 5) && mMap != null) {
            visibleMarkers.get(progress - 1).setVisible(false);
            visibleMarkers.get(progress).setVisible(true);
        }
        Log.i("LogMessage", "onResume");
    }

    ///// Mέθοδος που το τοποθετεί όλα τα σήμαδια πάνω στο χάρτη και κάνει κρυφά αυτά που θα ακολουθήσουν για την ιστορία του χρήστη /////
    private void addMarkersToMap(List<Markers> markers){
        if(mMap != null){
            for(Markers marker : markers) {
                if(!visibleMarkers.containsKey(marker.getId())){
                    final LatLng latLon = new LatLng(marker.getLatitude(), marker.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLon);
                    markerOptions.title("Super Market");
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    visibleMarkers.put(marker.getId(), mMap.addMarker(markerOptions));
                    if(marker.getId() != progress) {
                        visibleMarkers.get(marker.getId()).setVisible(false);
                    }
                }
            }
        }
    }
}
