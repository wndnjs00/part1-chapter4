package kr.co.fastcampus.part1.chapter4_13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part1.chapter4_13.ui.theme.StateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    PyeongToSquareMeter()
                }
            }
        }
    }
}

@Composable
fun PyeongToSquareMeter() {
    var pyeong by rememberSaveable {
        mutableStateOf("23")
    }

    // remember는 cache를 리턴
    // remember는 컨피규레이션이 바꼈을때는 유지되지 않음 -> 컨피규레이션의 대표적인 예는 화면회전
    // 그래서 모든값을 remembersavable로 해주는것f은 좋지는 않음 (여기서는 2개밖에 없기때문에 써주도록함)
    var squaremeter by rememberSaveable {
        mutableStateOf((23 * 3.306).toString())
    }


    // PyeongToSquareMeterStateless() 호출해서, 여기의 onPyeongChange에 상태위임하기
    // 마지막값은 람다로 써줄수있음 [onPyeongChange: (String) -> Unit로 정의해놨기 때문에]
    // 여기에는 상태를 다루는 부분만 작성!!
    PyeongToSquareMeterStateless(
        pyeong,
        squaremeter,
    ) {
        if (it.isBlank()) {
            pyeong = ""
            squaremeter = ""
            return@PyeongToSquareMeterStateless
        }
        // Float로 변환하고, 안되는건 null로 반환 //null이면 강제종료
        val numericValue = it.toFloatOrNull() ?: return@PyeongToSquareMeterStateless
        pyeong = it
        squaremeter = (numericValue * 3.306).toString()
    }


    // 이 부분은 상태가 다 몰려있는 부분!

    // 단계 1: remember를 이용해 상태를 만들고 평 값을 입력하면
    // 제곱미터가 출력되도록 화면을 구성하시오.
    // 평을 제곱미터로 바꾸기 위해서는 3.306을 곱하면 됩니다.
    // compose는 상태가 바뀌면 업데이트가 되지 않기때문에, 상태값을 넣어줘야함 => 그래야지 입력값이 바뀜

    // but, 지금은 OutlinedTextField 안에 remember의 값이 다 있음(pyeong,squaremeter)
    // 이런방식은 좋지 않음 (최대한 상태범위를 좁혀야 좋은방식) => 상태를 윗단으로 끌어올리는 방식이 아래에 있는 stateHoisting 이라고함

//    Column(modifier = Modifier.padding(16.dp)) {
//        OutlinedTextField(
//            value = pyeong,
//            onValueChange = {
//                if (it.isBlank()){
//                    pyeong = ""
//                    squaremeter = ""
//                    return@OutlinedTextField
//                }
//                // Float로 변환하고, 안되는건 null로 반환 //null이면 강제종료
//                val numericValue = it.toFloatOrNull() ?: return@OutlinedTextField
//                pyeong = it
//                squaremeter = (numericValue * 3.306).toString()
//            }, label = {
//                Text("평")
//            }
//        )
//        OutlinedTextField(
//            value = squaremeter,
//            onValueChange = {},
//            label = {
//                Text("제곱미터")
//            }
//        )
}

// stateHoisting 구현!!!
// 단계 2: `Composable` 함수를 만들고 `Column`의 항목들을 옮기세요.
// 단 상태는 옮기지 말아야 합니다. (onPyeongChange에 상태를 전부 위임!!)

// 파라미터는 아래와 같이 구성합니다.
// `pyeong: String, squareMeter: String, onPyeongChange: (String) -> Unit`
// 상태를 가져오지 못하게 String으로 바꿔서 쓴것!

// 따라서 최종적으로 PyeongToSquareMeterStateless는 상태를 전혀 알지못하게됨
// 즉, 여기는 UI부분만 작성 -> 결국 UI와 상태가 분리됨!! good!!
// 그래서 이렇게 작성하면, 상태가 없기 때문에 이 함수를 통해, 테스트코드를 쉽게 작성할 수 있음

@Composable
fun PyeongToSquareMeterStateless(
    pyeong: String,
    squareMeter: String,
    onPyeongChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = onPyeongChange,
            label = {
                Text("평")
            }
        )
        OutlinedTextField(
            value = squareMeter,
            onValueChange = {},
            label = {
                Text("제곱미터")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateTheme {
        PyeongToSquareMeter()
    }
}