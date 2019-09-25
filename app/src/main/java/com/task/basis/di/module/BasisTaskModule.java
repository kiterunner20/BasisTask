package com.task.basis.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.task.basis.BasisTaskService;
import com.task.basis.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class BasisTaskModule {

    private final Context context;

    public BasisTaskModule(Context context) {
        this.context = context;
    }

    /*Daggger has annotation processers . This method will return the retrofit instance.
      RxJavaCallAdapterFactory will return an instance which creates observables  */
    @Provides
    BasisTaskService provideService(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://gist.githubusercontent.com/anishbajpai014/d482191cb4fff429333c5ec64b38c197/raw/b11f56c3177a9ddc6649288c80a004e7df41e3b9/HiringTask.json")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        return retrofit.create(BasisTaskService.class);
    }

    //HTTP Logging interceptor is for logging the request and response.
    // This method is returning an instance of okhttpclient
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return new OkHttpClient.Builder().writeTimeout(60000, TimeUnit.MILLISECONDS)
                .connectTimeout(60000, TimeUnit.MILLISECONDS)
                .readTimeout(60000, TimeUnit.MILLISECONDS)
                .addInterceptor(logging)
                .build();
    }


}
