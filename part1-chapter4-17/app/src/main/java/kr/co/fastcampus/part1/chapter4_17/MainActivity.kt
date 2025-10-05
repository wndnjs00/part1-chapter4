package kr.co.fastcampus.part1.chapter4_17

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kr.co.fastcampus.part1.chapter4_17.ui.theme.EffectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EffectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    EffectEx()
                }
            }
        }
    }
}

@Composable
fun EffectEx(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current) {
    val scaffoldState = rememberScaffoldState()

    // 단계 1: `LaunchedEffect`을 이용해서 스낵바를 이용해 봅시다.
    // 파라미터에는 `scaffoldState.snackbarHostState`를 전달합시다.
    // "헬로 컴포즈"라고 출력합시다.
    // `LaunchedEffect`는 `CouroutineScope`를 만들기 때문에 스코프를 별도로
    // 만들 필요는 없습니다.
    // setContent에다가 바로 설정했기 때문에, 앱 실행하면 바로 스낵바 호출
    LaunchedEffect(scaffoldState.snackbarHostState) {
        scaffoldState.snackbarHostState.showSnackbar("스낵바 메시지 나와라")
    }


    // 단계 2: `DisposableEffect`를 호출하고 파리미터로 `lifecycleOwner`를
    // 전달합니다.

    // `LifecycleEventObserver`를 상속받고 두 상태에 대해 로그를 남깁니다.
    // `Lifecycle.Event.ON_START`, `Lifecycle.Event.ON_STOP`

    // 블록 내에서 `lifecycleOwner.lifecycle.addObserver`로 옵저버를 추가하고
    // onDispose에서 옵저버를 제거합니다.

    DisposableEffect(lifecycleOwner) { // lifecycleOwner가 바꼈을때 리셋됨 -> 리셋될때 onDispose에서 호출해서 옵저버 해제
        // 여기는 할당 (source부분은 안쓸거기때문에 _로 비워두기)
        val observer = LifecycleEventObserver { _, event ->
            when(event){
                Lifecycle.Event.ON_START -> {
                    Log.d("라이프사이클", "ON_START")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.d("라이프사이클", "ON_STOP")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.d("라이프사이클", "ON_PAUSE")
                }
                Lifecycle.Event.ON_RESUME -> {
                    Log.d("라이프사이클", "ON_RESUME")
                }
                else -> {
                    Log.d("라이프사이클", "그외!")
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        // 여기는 옵저버 제거
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


    Scaffold(
        scaffoldState = scaffoldState
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EffectTheme {
        EffectEx()
    }
}