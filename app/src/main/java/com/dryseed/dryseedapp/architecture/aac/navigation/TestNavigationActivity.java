package com.dryseed.dryseedapp.architecture.aac.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;

import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;

import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

/**
 * @author caiminming
 */
public class TestNavigationActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_layout);

        setupNavigationMenu();
    }

    /**
     * 注册底导航
     */
    private void setupNavigationMenu() {
        // manually inflate the graph
        NavHostFragment fragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        NavController navController = fragment.getNavController();
        NavInflater navInflater = navController.getNavInflater();
        NavGraph navGraph = navInflater.inflate(R.navigation.mobile_navigation);
        navGraph.setStartDestination(R.id.firstFragment);
        navController.setGraph(navGraph);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);

        //另一种方式
        //return Navigation.findNavController(this, R.id.fragment).navigateUp();

        return NavHostFragment.findNavController(fragment).navigateUp();
    }
}
