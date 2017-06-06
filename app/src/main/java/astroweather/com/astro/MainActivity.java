package astroweather.com.astro;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    MyPagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        if(screenSize!=Configuration.SCREENLAYOUT_SIZE_LARGE && screenSize!=Configuration.SCREENLAYOUT_SIZE_XLARGE &&
                getResources().getConfiguration().orientation!=Configuration.ORIENTATION_LANDSCAPE){

            viewPager = (ViewPager) findViewById(R.id.ViewPager);
            mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(mPagerAdapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("Settings").setIntent(new Intent(this, SettingsActivity.class));
//        menu.add("Settings").setIntent(new Intent(this, NewSettingsActivity.class));
        return true;
    }

    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
}
