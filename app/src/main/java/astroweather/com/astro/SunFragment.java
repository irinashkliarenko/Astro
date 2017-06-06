package astroweather.com.astro;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SunFragment extends Fragment {

    private EditText latitude;
    private EditText longitude;
    private EditText sunriseTime;
    private EditText sunriseAthimuth;
    private EditText sunsetTime;
    private EditText sunsetAthimuth;
    private EditText twilightTime;
    private EditText dawnTime;
    private TextClock time;

    private AppPreferenceManager appPreferenceManager;
    int refreshFrequency;
    double savedLongitude;
    double savedLatitude;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sun_fragment, container, false);

        time = (TextClock) view.findViewById(R.id.time);
        longitude = (EditText) view.findViewById(R.id.Longitude);
        latitude = (EditText) view.findViewById(R.id.Latitude);
        sunriseTime = (EditText) view.findViewById(R.id.SunriseTime);
        sunriseAthimuth = (EditText) view.findViewById(R.id.SunriseAthimuth);
        sunsetTime = (EditText) view.findViewById(R.id.SunsetTime);
        sunsetAthimuth = (EditText) view.findViewById(R.id.SunsetAzimuth);
        twilightTime = (EditText) view.findViewById(R.id.TwilightTime);
        dawnTime = (EditText) view.findViewById(R.id.DawnTime);

        return view;
    }

    public void setSunData(double longitude, double latitude) {
        ArrayList<String> sunInfo = new Calculator(longitude, latitude).setSunData();
        this.longitude.setText(sunInfo.get(0));
        this.latitude.setText(sunInfo.get(1));
        this.sunriseTime.setText(sunInfo.get(2));
        this.sunriseAthimuth.setText(sunInfo.get(3));
        this.sunsetTime.setText(sunInfo.get(4));
        this.sunsetAthimuth.setText(sunInfo.get(5));
        this.twilightTime.setText(sunInfo.get(6));
        this.dawnTime.setText(sunInfo.get(7));

    }

    @Override
    public void onStart() {
        super.onStart();

        appPreferenceManager = new AppPreferenceManager(getActivity().getApplicationContext());
        savedLongitude = appPreferenceManager.loadLongitude();
        savedLatitude = appPreferenceManager.loadLatitude();
        refreshFrequency = appPreferenceManager.loadRefreshFrequency();

        setSunData(savedLongitude, savedLatitude);
        final Handler handler=new Handler();
        Runnable updateTask=new Runnable() {
            @Override
            public void run() {
                setSunData(savedLongitude, savedLatitude);
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                time.setText(sdf.format(cal.getTime()));

                handler.postDelayed(this, (long) refreshFrequency * 1000);
            }
        };
        handler.postDelayed(updateTask, (long) refreshFrequency * 1000);

    }
}