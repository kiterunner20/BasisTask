package com.task.basis.di.component;

import com.task.basis.di.module.BasisTaskModule;
import com.task.basis.ui.BasisSwipeActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = BasisTaskModule.class)

public interface BasisTaskComponent {

    void inject(BasisSwipeActivity basisSwipeActivity);
}
