package astroweather.com.astro;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextClock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class MoonFragment extends Fragment {
    private EditText latitude;
    private EditText longitude;
    private EditText moonrise;
    private EditText moonset;
    private EditText newMoon;
    private EditText fullMoon;
    private EditText faze;
    private EditText lunarDay;
    private EditText lunarDayData;
    private TextClock time;

    private AppPreferenceManager appPreferenceManager;
    int refreshFrequency;
    double savedLongitude;
    double savedLatitude;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.moon_fragment, container, false);

        time = (TextClock) view.findViewById(R.id.time);
        longitude = (EditText) view.findViewById(R.id.Longitude);
        latitude = (EditText) view.findViewById(R.id.Latitude);
        moonrise = (EditText) view.findViewById(R.id.Moonrise);
        moonset = (EditText) view.findViewById(R.id.Moonset);
        newMoon = (EditText) view.findViewById(R.id.NewMoon);
        fullMoon = (EditText) view.findViewById(R.id.FullMoon);
        faze = (EditText) view.findViewById(R.id.Faze);
        lunarDay = (EditText) view.findViewById(R.id.LunarDay);
        lunarDayData = (EditText) view.findViewById(R.id.LunarDayData);

        return view;
    }

    public void setMoonData(double longitude, double latitude) {
        ArrayList<String> moonInfo = new Calculator(longitude, latitude).setMoonData();
        this.longitude.setText(moonInfo.get(0));
        this.latitude.setText(moonInfo.get(1));
        this.moonrise.setText(moonInfo.get(2));
        this.moonset.setText(moonInfo.get(3));
        this.newMoon.setText(moonInfo.get(4));
        this.fullMoon.setText(moonInfo.get(5));
        this.faze.setText(moonInfo.get(6));
        this.lunarDayData.setText(moonInfo.get(7));
    }

    @Override
    public void onStart() {
        super.onStart();

        appPreferenceManager = new AppPreferenceManager(getActivity().getApplicationContext());
        savedLongitude = appPreferenceManager.loadLongitude();
        savedLatitude = appPreferenceManager.loadLatitude();
        refreshFrequency = appPreferenceManager.loadRefreshFrequency();

        setMoonData(savedLongitude, savedLatitude);
        final Handler handler=new Handler();
        Runnable updateTask=new Runnable() {
            @Override
            public void run() {
                setMoonData(savedLongitude, savedLatitude);
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                time.setText(sdf.format(cal.getTime()));

                handler.postDelayed(this, (long) refreshFrequency * 1000);
            }
        };
        handler.postDelayed(updateTask, (long) refreshFrequency * 1000);

    }
}
