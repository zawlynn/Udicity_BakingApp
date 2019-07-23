package com.zawlynn.udacity.bakingapp.utils;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityUtils {

    public static void addFragment(AppCompatActivity activity, int layoutId, Fragment fragment) {
        final FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutId, fragment);
        fragmentTransaction.commit();
    }

    public static void replaceFragment(AppCompatActivity activity, int layoutId, Fragment fragment) {
        final FragmentManager fragmentManager = activity.getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layoutId, fragment);
        fragmentTransaction.commit();
    }
}
