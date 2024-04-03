package uz.gita.quizapp.app

import android.app.Application
import com.vicmikhailau.maskededittext.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
            Timber.plant(Timber.DebugTree())
    }
}