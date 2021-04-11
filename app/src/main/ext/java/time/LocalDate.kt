package java.time

import androidx.annotation.RequiresApi
import java.time.format.DateTimeFormatter

@RequiresApi(29)
fun LocalDate.formattedIso(): String = LocalDate.now(ZoneId.of("Etc/UTC")).run {
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSZ").format(this)
}