package makisp.gohome;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

import static makisp.gohome.LoginActivity.onlineUser;

public class GameActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    public Button buttonItems;
    public Button buttonProfile;
    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

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
               startActivity(new Intent(GameActivity.this, ItemsActivity.class));
            }
        });
    }

    ///// Event Handler για άνοιγμα του ProfileActivity /////
    public void PressProfileButton() {

        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(GameActivity.this, ProfileActivity.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        PressItemButton();
        PressProfileButton();

        //Αλλάζει την μεταβλητή του Username ανάλογα με τον συνδεδεμένο χρήστη
        TextView t = (TextView) findViewById(R.id.textUsername);
        t.setText(onlineUser);

        ///// Ελέγχει την έκδοση του Android και αν είναι Android M ζήτάει permission για να /////
        ///// χρησιμοποιήσει το gps /////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        ///// Ελέγχει αν το GPS είναι απενεργοποιημένο και ζητάει από τον χρήστη να το ενεργοποιήσει /////
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }

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

        ///// Αρχικοποιεί τα Google Play Services που χρειάζονται για να βρεί την τοποθεσία /////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        ///// Listener για όταν πατήθει κάποιο σημάδι να ανοίγει το σενάριο Activity /////
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.isVisible()) {
                    boolean checkDistance = markerDistance();
                    if(checkDistance){
                        Intent intent = new Intent(GameActivity.this, ScenarioActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext() , "Πλησίασε πιο κοντά στο σημείο!", Toast.LENGTH_LONG).show();
                    }

                }
                return false;
            }
        });
        markers = MainActivity.db.getAllMarkers();
        cre = MainActivity.db.getCredential(LoginActivity.activeUser);
        progress = cre.getProgress();
        addMarkersToMap(markers);

        fillProgressBar();
    }

    ///// Όταν κάνει επαναφορά στον χάρτη εμφανίζει το σώστο σημάδι /////
    @Override
    protected void onResume() {
        super.onResume();
        if((progress >= 2 && progress <= 5) && mMap != null) {
            visibleMarkers.get(progress - 1).setVisible(false);
            visibleMarkers.get(progress).setVisible(true);
        }
        fillProgressBar();
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
                    markerOptions.title("Quest");
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    visibleMarkers.put(marker.getId(), mMap.addMarker(markerOptions));
                    if(marker.getId() != progress) {
                        visibleMarkers.get(marker.getId()).setVisible(false);
                    }
                }
            }
        }
    }

    ///// Συνάρτηση που ζητάει από τον χρήστη αν θέλει να ανοίξει το  GPS /////
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Το GPS είναι απενεργοποιημένο, θα ήθελες να το ενεργοποιήσεις;")
                .setCancelable(false)
                .setPositiveButton("Ναι", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Όχι", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    ///// Δημιουργεί τον Google Api Client /////
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        // Συνδέει τον Client με το Api
        mGoogleApiClient.connect();
    }

    ///// Ζητάει το permission από τον χρήστη (Μόνο για Android M) /////
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    ///// Ελέγχει αν δώθηκε το permission /////
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // Αν το request ακυρωθεί το grandResults θα είναι empty
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Δίνεται το permission
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {
                    // To permission δεν δώθηκε
                    Toast.makeText(this, "Δεν δώθηκαν δικαιώματα!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    ///// Κάνει ανανέωση την τοποθεσία του χρήστη σε συχνά χρονικά διαστήματα /////
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Οι υπηρεσίες τοποθεσίας έχουν ανασταλεί. Παρακαλώ ξανά συνδεθείτε.", Toast.LENGTH_LONG).show();
    }

    ///// Όποτε αλλάζει η τοποθεσία παίρνουμε τις συντεταγμένες του χρήστη /////
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        // Μετακινεί την κάμερα στο σημείο του χρήστη
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));

        // Σταματάει να κάνει update την τοποθεσία
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if(connectionResult.hasResolution()) {
            try{
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            }catch(IntentSender.SendIntentException e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "Οι υπηρεσίες τοποθεσίας έχουν ανασταλεί. Παρακαλώ ξανά συνδεθείτε" + connectionResult.getErrorCode(), Toast.LENGTH_LONG).show();
        }
    }

    //Συνάρτηση για γέμισμα του ProgressBar ανάλογα με την πρόοδο του χρήστη
    public void fillProgressBar(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ProgressBar p = (ProgressBar) findViewById(R.id.barHorizontal);
                p.setProgress(progress - 1);
            }
        });
        thread.start();
    }

    //Μέθοδος υπολογισμού της απόστασης του χρήστη από το marker
    public boolean markerDistance() {
        int Radius = 6371;
        LatLng userLatLng = getUserLocation(mLastLocation);
        LatLng markerLatLng = getMarkerLocation();

        double lat1 = userLatLng.latitude;
        double lat2 = markerLatLng.latitude;
        double lon1 = userLatLng.longitude;
        double lon2 = markerLatLng.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double finalDistance = Radius * c * 1000;

        if(finalDistance <= 50.0){
            return true;
        }
        else{
            return false;
        }
    }

    //Μέθοδος για συντεταγμένες του χρήστη
    public LatLng getUserLocation(Location location){
        final LatLng userLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        return userLatLng;
    }

    //Μέθοδος για συντεταγμένες του ενεργού marker
    private LatLng getMarkerLocation(){
        LatLng markerLatLng = null;
        for(Markers marker : markers) {
            if(marker.getId() == progress) {
                markerLatLng = new LatLng(marker.getLatitude(), marker.getLongitude());
            }
        }
        return markerLatLng;
    }
}
