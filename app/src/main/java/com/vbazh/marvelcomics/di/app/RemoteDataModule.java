package com.vbazh.marvelcomics.di.app;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vbazh.marvelcomics.BuildConfig;
import com.vbazh.marvelcomics.data.network.MarvelApiService;
import com.vbazh.marvelcomics.utils.HashUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RemoteDataModule {

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HashUtil hashUtil, Cache cache) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            HttpUrl url = chain.request().url().newBuilder()
                    .addQueryParameter("ts", hashUtil.getTs())
                    .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
                    .addQueryParameter("hash", hashUtil.getHash())
                    .build();

            Request.Builder requestBuilder = chain.request().newBuilder().url(url);
            return chain.proceed(requestBuilder.build());
        });
        httpClient.addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC));

        httpClient.cache(cache);

        return httpClient.build();
    }

    @Provides
    @Singleton
    Cache provideCache(Context context) {
        int cacheSize = 8 * 1024 * 1024;
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    MarvelApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(MarvelApiService.class);
    }
}
