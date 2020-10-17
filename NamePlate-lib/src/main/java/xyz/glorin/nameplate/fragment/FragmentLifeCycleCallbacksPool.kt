package xyz.glorin.nameplate.fragment

import android.app.Activity
import android.util.SparseArray
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class FragmentLifeCycleCallbacksPool {
    private val callbacks = SparseArray<FragmentManager.FragmentLifecycleCallbacks>()

    fun getFragmentLifeCycleCallbacks(activity: FragmentActivity): FragmentManager.FragmentLifecycleCallbacks? {
        return callbacks[activity.hashCode()] ?: NamePlateFragmentLifeCycleCallbacks().also {
            callbacks.put(activity.hashCode(), it)
        }
    }

    fun queryAndRemove(activity: Activity): FragmentManager.FragmentLifecycleCallbacks? {
        return callbacks[activity.hashCode()]?.also {
            callbacks.remove(activity.hashCode())
        }
    }
}