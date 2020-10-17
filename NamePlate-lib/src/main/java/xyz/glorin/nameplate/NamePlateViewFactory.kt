package xyz.glorin.nameplate

import android.content.Context
import android.view.View
import android.widget.TextView

object NamePlateViewFactory {
    fun createNamePlateView(context: Context, any: Any): View {
        return TextView(context).apply {
            text = "${any::class.java.simpleName}@${any.hashCode().toString(16)}"
            setBackgroundResource(R.drawable.name_plate_bg_activity)
            setPadding(
                context.resources.getDimensionPixelOffset(R.dimen.name_plate_activity_padding_start),
                0,
                context.resources.getDimensionPixelOffset(R.dimen.name_plate_activity_padding_end),
                0
            )
        }
    }
}