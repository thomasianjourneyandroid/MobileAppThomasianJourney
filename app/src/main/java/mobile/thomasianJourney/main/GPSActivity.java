package mobile.thomasianJourney.main;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import mobile.thomasianJourney.main.vieweventsfragments.R;

public class GPSActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener listener;
    private Context context;
    private Dialog dlg;
    private ImageView closeButton;
    private Button scanagain;
    public double defaultLongitude =  120.989498;
    public double defaultLatitude = 14.609882;
    //    14.580826, 121.085796
    //public double defaultLongitude =  121.085796;
    //public double defaultLatitude = 14.580826;
    public double ScopeRadiusMeters = 336;
    Intent intent;

    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        b = (Button)findViewById(R.id.btnyr1) ;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        dlg = new Dialog(this);
        intent = getIntent();

        listener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {

                //t.append("\n " + location.getLongitude() + " " + location.getLatitude());
                //Log.i("GPS2","Location Found = " + location.getLongitude() + " " + location.getLatitude() );
                double ActualDistance = CoordinateDistanceMeters(location.getLatitude(),location.getLongitude(),
                        defaultLatitude,defaultLongitude
                );
                //Log.i("GPS ","GPS = "+location.getLatitude()+","+location.getLongitude()+"");
                //t.append(" " + location.getLongitude() + " " + location.getLatitude());
                //Log.i("Distance","Actual Distance = "+ActualDistance+"");
                boolean isAccept = isAcceptDistance(ScopeRadiusMeters, ActualDistance);
                //edit
                //isAccept = true;
                if(!isAccept){
                    errorlocation();
                    onPause();

                }else{
                    //Toast.makeText(GPSActivate.this,"Acceptable = "+ isAccept , Toast.LENGTH_LONG).show();
                    onPause();
                    Intent intent2 = new Intent(GPSActivity.this, VerifyLoginCred.class);
                    String actID =  intent.getExtras().getString("activityId");
                    intent2.putExtra("activityId", actID);
                    startActivity(intent2);
                    finish();

                }

                //Toast.makeText(GPSActivate.this,"Acceptable = "+ isAccept , Toast.LENGTH_LONG).show();



                //14.606364, 121.121967
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gpssearch();
            }
        });
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
        }
        gpssearch();
    }

    void errorlocation(){
        dlg.setContentView(R.layout.dialog_errorlocation);
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        scanagain = (Button) dlg.findViewById(R.id.okbtn);
        closeButton = (ImageView) dlg.findViewById(R.id.closeDialogErrorLocation);
        try {
            dlg.show();
        }catch(Exception err){
            err.printStackTrace();
        }
        scanagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GPSActivity.this, GPSActivity.class);
                startActivity(i);
                finish();
            }
        });
        closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                //configure_button();
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //noinspection MissingPermission
                        //Toast.makeText(GPSActivate.this, "Tried2", Toast.LENGTH_LONG).show();
                        gpssearch();
                        //Toast.makeText(GPSActivate.this, "Tried3", Toast.LENGTH_LONG).show();

                    }
                });
                break;
            default:
                break;
        }
    }
    public static double CoordinateDistanceMeters(double lat1,double lon1,double lat2,double lon2){
        float R = 6371; // km (change this constant to get miles)
        double dLat = (lat2-lat1) * Math.PI / 180;
        double dLon = (lon2-lon1) * Math.PI / 180;
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(lat1 * Math.PI / 180 ) * Math.cos(lat2 * Math.PI / 180 ) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        //if (d>1) return Math.round(d)+"km";
        //else if (d<=1) return Math.round(d*1000)+"m";
        return d * 1000;
    }
    public static boolean isAcceptDistance(double scope, double actual){
        return actual <= scope;
    }
    void gpssearch(){

        try {
            Toast.makeText(GPSActivity.this,"GPS Searching" , Toast.LENGTH_LONG).show();
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 800, 0, listener);
            Toast.makeText(GPSActivity.this,"GPS Searching" , Toast.LENGTH_LONG).show();
        }catch (SecurityException e){
            Toast.makeText(GPSActivity.this, "Cannot find location", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(listener);
    }

}
