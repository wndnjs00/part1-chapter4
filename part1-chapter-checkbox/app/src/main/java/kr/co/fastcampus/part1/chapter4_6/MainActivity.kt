package kr.co.fastcampus.part1.chapter4_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.co.fastcampus.part1.chapter4_6.ui.theme.Card2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Card2Theme {
                CheckBoxEx()
            }
        }
    }
}

@Composable
fun CheckBoxEx() {
    var checked by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )

        Text(
            text = "프로그래머입니까?",
            modifier = Modifier.clickable {
                checked = !checked
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Card2Theme  {
        CheckBoxEx()
    }
}