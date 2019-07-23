package com.zawlynn.udacity.bakingapp.di.module;

import com.zawlynn.udacity.bakingapp.constants.Constants;
import com.zawlynn.udacity.bakingapp.data.network.ApiService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    ApiService apiService;

    @Provides
    public ApiService proviceApi(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor(){
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC);
    }
}
