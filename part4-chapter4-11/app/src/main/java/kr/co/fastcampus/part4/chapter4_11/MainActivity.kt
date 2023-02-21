package kr.co.fastcampus.part4.chapter4_11

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
import kr.co.fastcampus.part4.chapter4_11.ui.theme.SnackbarTheme

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

    // 단계 1: scaffoldState를 만들고 Scaffold에 설정합시다.
    // scaffoldState를 만들기 위해 `rememberScaffoldState`를 사용합니다.
    Scaffold {
        // 단계 2: "더하기" 버튼을 만들어 봅시다.
        // action에서 counter를 증가시킵시다.

        // 단계 4: 버튼의 onClick에서 `coroutineScope.launch`를
        // 사용합니다.

        // 단계 5: 스낵바를 사용하기 위해 `scaffoldState.snackbarHostState.showSnackbar`
        // 사용합니다.

        // `message`에 카운터를 출력합시다.
        // `actionLabel`를 "닫기"로 지정합시다.
        // `duration`에 `SnackbarDuration.Short`를 사용합니다.
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SnackbarTheme {
        SnackbarEx()
    }
}