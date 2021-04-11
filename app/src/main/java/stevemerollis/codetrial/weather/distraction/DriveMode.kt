package stevemerollis.codetrial.weather.distraction

import androidx.annotation.IntDef
import stevemerollis.codetrial.weather.distraction.DriveMode.DRIVE_MODE_DRIVE
import stevemerollis.codetrial.weather.distraction.DriveMode.DRIVE_MODE_IDLE
import stevemerollis.codetrial.weather.distraction.DriveMode.DRIVE_MODE_PARK
import stevemerollis.codetrial.weather.distraction.PendingDriveMode.DD_PENDING_DRIVE
import stevemerollis.codetrial.weather.distraction.PendingDriveMode.DD_PENDING_NULL
import stevemerollis.codetrial.weather.distraction.PendingDriveMode.DD_PENDING_PARK

@Target(AnnotationTarget.TYPE, AnnotationTarget.VALUE_PARAMETER)
@IntDef(DRIVE_MODE_PARK, DRIVE_MODE_IDLE,  DRIVE_MODE_DRIVE)
annotation class DriverMode

object DriveMode {
    const val DRIVE_MODE_PARK = 0
    const val DRIVE_MODE_IDLE = 1
    const val DRIVE_MODE_DRIVE = 2
}

@Target(AnnotationTarget.TYPE, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
@IntDef(DD_PENDING_PARK, DD_PENDING_DRIVE, DD_PENDING_NULL)
@Retention(AnnotationRetention.SOURCE)
annotation class DdTarget

object PendingDriveMode{
    const val DD_PENDING_PARK = 0
    const val DD_PENDING_IDLE = 1
    const val DD_PENDING_DRIVE = 2
    const val DD_PENDING_NULL = 3
}