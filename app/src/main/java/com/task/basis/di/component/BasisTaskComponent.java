package com.task.basis.di.component;

import com.task.basis.di.module.BasisTaskModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = BasisTaskModule.class)

public interface BasisTaskComponent {


}
