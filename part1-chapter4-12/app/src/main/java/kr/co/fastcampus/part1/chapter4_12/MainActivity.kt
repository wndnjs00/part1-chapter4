package kr.co.fastcampus.part1.chapter4_12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import kr.co.fastcampus.part1.chapter4_12.ui.theme.BottomAppBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomAppBarTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BottomAppBarEx()
                }
            }
        }
    }
}

@Composable
fun BottomAppBarEx() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    var counter by remember {
        mutableStateOf(0)
    }

    // 단계 1: `Scaffold`에 `scaffoldState`를 설정합니다.
    Scaffold (
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomAppBar(){
                Text("헬로")
                Button(onClick = {
                    coroutineScope.launch {
                        // onClick내에서는 coroutineScope.launch를 바로 호출할 수 있음!!
                        // showSnackbar는 다 기본값으로 설정되어있어서, message값만 설정하면됨
                        // coroutineScope.launch(코루틴 스코프)를 쓰는 이유는, showSnackbar가 suspend fun이기 때문
                        scaffoldState.snackbarHostState.showSnackbar("안녕하세요")
                    }
                }) {
                    Text("인사하기")
                }
                Button(
                    onClick = {
                        counter++
                    }) {
                    Text("더하기")
                }
                Button(
                    onClick = {
                        counter--
                    }) {
                    Text("빼가")
                }
            }
        }
    ) {
        // onClick밖에서 coroutineScope.launch를 호출하고 싶으면, LaunchEffect를 사용해야함(효과 API)
        // LaunchEffect는 Key를 작성해줘야함
        // Key가 바뀌기 전까지는 다시 호출하지 않는다. (scafflodState.snackbarHostState가 바뀌기 전까지는 한번만 호출해라)
//        LaunchedEffect(scaffoldState.snackbarHostState) {
//            coroutineScope.launch {
//                scaffoldState.snackbarHostState.showSnackbar("안녕하세요")
//            }
//        }

        Box(modifier = Modifier.fillMaxSize()){
            Text(
                text = "카운터는 ${counter}회입니다",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    // 단계 2: `bottomBar` 파라미터에 `BottomAppBar`를 넣읍시다.
    // 내용은 텍스트와 버튼을 넣어 봅시다. 버튼에는 `snackBar`를
    // 연동해 메시지를 출력합니다.

    // 단계 3: 더하기와 빼기 버튼을 추가로 만들고 `MutableState`
    // 만듭시다. `Scaffold`의 `content`에 `Text`를 넣어 카운터를 출력하게
    // 합시다.
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BottomAppBarTheme {
        BottomAppBarEx()
    }
}