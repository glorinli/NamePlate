package xyz.glorin.nameplate

import android.app.Application
import android.content.Context
import xyz.glorin.nameplate.activity.NamePlateActivityLifeCycleCallbacks

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