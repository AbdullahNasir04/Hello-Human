package com.example.tts

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import com.example.tts.ui.theme.TTSTheme

class MainActivity : RobotActivity(), RobotLifecycleCallbacks {
    private lateinit var vm: PepperViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QiSDK.register(this, this)
        vm = PepperViewModel()
        setContent {
            TTSTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PepperApp(vm = vm)
                }
            }
        }
    }

    override fun onDestroy() {
        QiSDK.unregister(this, this)
        super.onDestroy()
    }

    override fun onRobotFocusGained(qiContext: QiContext) {
        vm.setQiContext(qiContext)
    }

    override fun onRobotFocusLost() {
        vm.setQiContext(null)
    }

    override fun onRobotFocusRefused(reason: String) {
        // Handle focus refusal
    }
}

@Composable
fun PepperApp(vm: PepperViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { vm.sayHello() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Say Hello")
        }
    }
}




