import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun EditTaskDueDate(taskDueDate: LocalDate, onDateChange: (LocalDate) -> Unit) {
    var selectedDate by remember { mutableStateOf(taskDueDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH))) }

    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year = taskDueDate.year
    val month = taskDueDate.monthValue - 1
    val day = taskDueDate.dayOfMonth

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            val newDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
            selectedDate = newDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH))
            onDateChange(newDate)
        },
        year,
        month,
        day
    )

    // Display the date in a TextField
    TextField(
        value = selectedDate,
        onValueChange = {},
        readOnly = true,
        modifier = Modifier
            .width (120.dp)
            .height(50.dp)
            .clickable { datePickerDialog.show() },
        singleLine = true,
        shape = RectangleShape,
        textStyle = TextStyle(
        )

    )
}

@Preview(showBackground = true)
@Composable
fun PreviewEditTaskDueDate() {
    val today = LocalDate.now()

    EditTaskDueDate(taskDueDate = today, onDateChange = {})
}