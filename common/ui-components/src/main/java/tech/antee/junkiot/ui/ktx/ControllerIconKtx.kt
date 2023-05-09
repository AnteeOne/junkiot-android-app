package tech.antee.junkiot.ui.ktx

import androidx.annotation.DrawableRes
import tech.antee.junkiot.controll.common.models.ControllerType
import tech.antee.junkiot.ui_components.R

@DrawableRes
fun ControllerType.iconId() = when (this) {
    ControllerType.LightSensor -> R.drawable.ic_light_sensor
}
