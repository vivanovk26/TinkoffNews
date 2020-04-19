package com.vivanov.tinkoffnews.app

import android.app.Application
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.vivanov.tinkoffnews.data.OkHttpClientProvider
import com.vivanov.tinkoffnews.di.DependenciesProviderImpl

/**
 * @author Vladimir Ivanov
 */
class TinkoffNewsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        DependenciesProviderImpl.setup(this)
        setupFresco()
    }

    private fun setupFresco() {
        val diskCacheConfig = DiskCacheConfig.newBuilder(this)
            .setBaseDirectoryName("cache")
            .setMaxCacheSizeOnVeryLowDiskSpace((1024 * 1024 * 8).toLong())
            .setMaxCacheSizeOnLowDiskSpace((1024 * 1024 * 16).toLong())
            .setMaxCacheSize((1024 * 1024 * 64).toLong())
            .build()
        val imagePipelineConfig =
            OkHttpImagePipelineConfigFactory.newBuilder(this, OkHttpClientProvider().getInstance())
                .setMainDiskCacheConfig(diskCacheConfig)
                .build()
        Fresco.initialize(this, imagePipelineConfig)
    }
}
