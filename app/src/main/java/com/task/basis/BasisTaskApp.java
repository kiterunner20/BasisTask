package com.task.basis;

import android.app.Application;

import com.task.basis.di.module.BasisTaskModule;
import com.task.basis.di.component.BasisTaskComponent;

public class BasisTaskApp extends Application {

    /* Dagger instance is created and avoid all the boiler plate code .
       The basisTaskComponent instance is called from the activity.
       Dagger will generate all the code with the annotation proccessors and take care
       of dependency injection.*/

    private static BasisTaskComponent basisTaskComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        basisTaskComponent = DaggerBasisTaskComponent.builder().basisTaskModule(new BasisTaskModule(this)).build();
    }

    public static BasisTaskComponent getBasisTaskComponent() {
        return basisTaskComponent;
    }

}
