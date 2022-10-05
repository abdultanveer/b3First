package com.example.b3first.di;

import javax.inject.Singleton;

import dagger.Component;

//component = real estate agent who know who's the seller and whos the buyer
//buyer = DependencyInjActivity.  seller = SharedPrefModule

@Singleton
@Component(modules = {SharedPrefModule.class})
public interface MyComponent {

    void inject(DependencyInjActivity activity);

}
