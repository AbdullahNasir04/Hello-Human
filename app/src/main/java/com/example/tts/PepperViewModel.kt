package com.example.tts

import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.`object`.conversation.Say
import com.aldebaran.qi.sdk.builder.SayBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PepperViewModel {
    private var qiContext: QiContext? = null

    fun setQiContext(context: QiContext?) {
        qiContext = context
    }

    fun sayHello() {
        qiContext?.let {
            CoroutineScope(Dispatchers.IO).launch {
                val say: Say = SayBuilder.with(qiContext)
                    .withText("Hello, human!")
                    .build()
                say.run()
            }
        }
    }
}


