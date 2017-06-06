package astroweather.com.astro;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;


public class MyPagerAdapter extends FragmentStatePagerAdapter {

    static SunFragment sunFragment = new SunFragment();
    static MoonFragment moonFragment = new MoonFragment();

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return sunFragment;
            case 1:
                return moonFragment;
        }
        return sunFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}