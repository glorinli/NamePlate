package xyz.glorin.nameplate

import android.app.Application
import android.content.Context

object NamePlate {
    private val activityLifeCycleCallback: Application.ActivityLifecycleCallbacks =
        NamePlateActivityLifeCycleCallbacks()

    @JvmStatic
    fun init(context: Context, configuration: NamePlateConfiguration?) {
        (context.applicationContext as Application).registerActivityLifecycleCallbacks(
            activityLifeCycleCallback
        )
    }
}