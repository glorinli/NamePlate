package xyz.glorin.nameplate

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        NamePlate.init(this, null)
    }
}