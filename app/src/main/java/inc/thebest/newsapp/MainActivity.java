package inc.thebest.newsapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    //private ActionBar toolbar;
    private ImageView newsLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar = getSupportActionBar();

        final BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //load current news fragment by defaualt
        Fragment fragment = new CurrentNewsFragment();
        loadFragment(fragment);

        //toolbar.setTitle("Current News");

        newsLogo = findViewById(R.id.newsLogo);

        newsLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when the news logo is clicked clear the fragment backstack and bring the user to the main fragment
                FragmentManager fm = getSupportFragmentManager();
                for(int i=0; i<fm.getBackStackEntryCount(); i++){
                    fm.popBackStack();
                }

                //this line of code will select the first item of the bottom navigation bar
                navigation.getMenu().getItem(0).setChecked(true);

                Fragment fragment = new CurrentNewsFragment();
                loadFragment(fragment);

            }
        });
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch(item.getItemId()){
                case R.id.navigation_current:
                    //toolbar.setTitle("Current News");
                    fragment = new CurrentNewsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_sports:
                    //toolbar.setTitle("Sports");
                    fragment = new SportsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_business:
                    //toolbar.setTitle("Business");
                    fragment = new BusinessFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_lifestyle:
                    //toolbar.setTitle("Lifestyle");
                    fragment = new LifestyleFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_archives:
                    //toolbar.setTitle("Archive");
                    fragment = new ArchivesFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
