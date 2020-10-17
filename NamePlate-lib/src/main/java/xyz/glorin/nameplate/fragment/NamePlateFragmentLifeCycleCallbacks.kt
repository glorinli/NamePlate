package xyz.glorin.nameplate.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import xyz.glorin.nameplate.widget.NamePlateViewFactory
import xyz.glorin.nameplate.R

class NamePlateFragmentLifeCycleCallbacks : FragmentManager.FragmentLifecycleCallbacks() {
    override fun onFragmentViewCreated(
        fm: FragmentManager,
        f: Fragment,
        v: View,
        savedInstanceState: Bundle?
    ) {
        super.onFragmentViewCreated(fm, f, v, savedInstanceState)

        (v as? ViewGroup)?.let {
            val plateView = NamePlateViewFactory.createNamePlateView(f.requireActivity(), f).apply {
                id = R.id.name_plate_view
                setBackgroundResource(R.drawable.name_plate_bg_fragment)
            }
            it.addView(
                plateView,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )

            plateView.layoutParams?.run {
                (this as? ViewGroup.MarginLayoutParams)?.apply {
                    marginStart =
                        it.resources.getDimensionPixelSize(R.dimen.name_plate_activity_margin_start)
                    marginEnd =
                        it.resources.getDimensionPixelSize(R.dimen.name_plate_activity_margin_start)
                    topMargin =
                        it.resources.getDimensionPixelSize(R.dimen.name_plate_activity_margin_top)
                }
                (this as? FrameLayout.LayoutParams)?.gravity = Gravity.END
                (this as? LinearLayout.LayoutParams)?.gravity = Gravity.END
                (this as? RelativeLayout.LayoutParams)?.addRule(RelativeLayout.ALIGN_PARENT_END)
            }

            plateView.requestLayout()
        }
    }
}