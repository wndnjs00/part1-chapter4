package kr.co.fastcampus.part1.chapter4_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part1.chapter4_6.ui.theme.Card2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Card2Theme {
                Gretting()
            }
        }
    }
}

@Composable
fun Gretting() {
    // 스탭 1
    // TextField를 Text위에 만들어보자
    // 이땐 TextField에 입력이 되지않음
//    Column(modifier = Modifier.padding(16.dp)) {
//        TextField(value = "Tom", onValueChange = {})
//
//        Text(text = "Hello Android")
//    }

    // 스탭 2
    // Text에 TextField의 입력을 출력시켜보자
//    var name by remember { mutableStateOf("Tom") }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        TextField(value = name, onValueChange = {name = it})
//
//        Text(text = "Hello $name")
//    }


    // 스탭 3
    // TextField에 lable 추가해보기 (설명느낌)
//    var name by remember { mutableStateOf("Tom") }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        TextField(value = name,
//            label = {
//                Text("이름")
//            },
//            onValueChange = {name = it}
//        )
//
//        Spacer(modifier = Modifier.size(8.dp))
//
//        Text(text = "Hello $name")
//    }


    // 스탭 4
    // OutlinedTextField 사용해보기
    var name by remember { mutableStateOf("Tom") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(value = name,
            label = {
                Text("이름")
            },
            onValueChange = {name = it}
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(text = "Hello $name")
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Card2Theme  {
        Gretting()
    }
}