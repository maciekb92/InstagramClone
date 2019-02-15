package com.e.instagramclone;

import com.parse.Parse;
import android.app.Application;

/**
  * Created using parseplatform.org docs
  */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("hmoCZ1Hx6HHNuHeuSsC48rZzv0uecHJkyW3yNJTs")
                .clientKey("L5a0mzjQW1t5KHq6Fu45Sms3iHNcvccsWlSwEHbv")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }

}
