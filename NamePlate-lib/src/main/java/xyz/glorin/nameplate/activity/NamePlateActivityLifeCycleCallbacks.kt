package xyz.glorin.nameplate.activity

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import xyz.glorin.nameplate.DebugLog
import xyz.glorin.nameplate.widget.NamePlateViewFactory
import xyz.glorin.nameplate.R
import xyz.glorin.nameplate.fragment.FragmentLifeCycleCallbacksPool

class NamePlateActivityLifeCycleCallbacks : Application.ActivityLifecycleCallbacks {
    private val fragmentCallbacksPool = FragmentLifeCycleCallbacksPool()

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        (activity as? FragmentActivity)?.let {
            fragmentCallbacksPool.getFragmentLifeCycleCallbacks(activity)?.let { cb ->
                DebugLog.d("activity ${activity.hashCode()} registered fragment lifecycle callacks: ${cb.hashCode()}")
                it.supportFragmentManager.registerFragmentLifecycleCallbacks(cb, true)
            }
        }
    }

    override fun onActivityStarted(activity: Activity) {
        if (activity.findViewById<View>(R.id.name_plate_view) != null) {
            return
        }

        val plateView = NamePlateViewFactory.createNamePlateView(activity, activity).apply {
            id = R.id.name_plate_view
        }
        (activity.findViewById<View>(android.R.id.content) as? FrameLayout)
            ?.addView(
                plateView, FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    marginStart =
                        activity.resources.getDimensionPixelOffset(R.dimen.name_plate_activity_margin_start)
                    topMargin =
                        activity.resources.getDimensionPixelOffset(R.dimen.name_plate_activity_margin_top)
                }
            )
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        fragmentCallbacksPool.queryAndRemove(activity)?.let {
            DebugLog.d("activity ${activity.hashCode()} unregistered fragment lifecycle callacks: ${it.hashCode()}")
            (activity as? FragmentActivity)?.supportFragmentManager?.unregisterFragmentLifecycleCallbacks(
                it
            )
        }
    }
}