package kr.co.fastcampus.part1.chapter4_11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kr.co.fastcampus.part1.chapter4_11.ui.theme.SnackbarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackbarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SnackbarEx()
                }
            }
        }
    }
}

@Composable
fun SnackbarEx() {
    var counter by remember { mutableStateOf(0) }

    // 단계 3: couroutineScope를 만듭시다.
    // `rememberCoroutineScope`를 사용합니다.
    val coroutineScope = rememberCoroutineScope()

    // 단계 1: scaffoldState를 만들고 Scaffold에 설정합시다.
    // scaffoldState를 만들기 위해 `rememberScaffoldState`를 사용합니다.
    // rememberScaffoldState는 remember랑 Scaffold가 같이 있는거
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState) {
        // 방법1
        Button(onClick = {
            counter++
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "카운터는 ${counter}야",
                    actionLabel = "증가",
                    duration = SnackbarDuration.Short
                )
            }
        }){
            Text("더하기")
        }

        //방법2
//        LaunchedEffect(scaffoldState.snackbarHostState) {
//            coroutineScope.launch{
//                scaffoldState.snackbarHostState.showSnackbar(
//                    message = "카운터는 ${counter}야",
//                    actionLabel = "증가",
//                    duration = SnackbarDuration.Short
//                )
//            }
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SnackbarTheme {
        SnackbarEx()
    }
}