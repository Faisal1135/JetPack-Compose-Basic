package com.example.composebasic


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.core.content.ContextCompat
import com.example.composebasic.components.Permission
import com.example.composebasic.components.lancherForActivity

import com.example.composebasic.ui.theme.ComposeBasicTheme
import com.google.accompanist.permissions.*



@OptIn(ExperimentalPermissionsApi::class)
class MainActivity : ComponentActivity() {
    private var cursor: Cursor? = null


    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        lancherForActivity()

//                        Request for sms permission

//                        if (smsPermissin != PackageManager.PERMISSION_GRANTED) {
//                            Permission("SMS",Manifest.permission.READ_SMS,this@MainActivity)
//
//                        }else{
//                          val shouldShow=  shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS)
//
//                            if (shouldShow) {
//                                Text("This app needs permission to read SMS")
//                            } else{
//
//
//                            }
//                        }


//                        val smsPermission = rememberPermissionState(permission =Manifest.permission.READ_SMS)
//                        if (smsPermission.status == PermissionStatus.Granted) {
//                            Permission(permission = "SMS", manifest = Manifest.permission.READ_SMS, context = this@MainActivity)
//                        }else{
//                            Text("SMS permission not granted")
//                            Button(onClick = {smsPermission.launchPermissionRequest()}) {
//                                Text("Request SMS permission")
//
//                            }
//                        }

                    }

                }
            }
        }
    }


}

@Composable
fun Greeting(name: String) {
    Column {
        Text(text = "Hello $name!", modifier = Modifier.padding(16.dp
        ), style = MaterialTheme.typography.body1)



        Text(text = "Something just like this", modifier = Modifier.padding(horizontal = 16.dp), style = MaterialTheme.typography.body2, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = {}, modifier =Modifier.background(color = MaterialTheme.colors.primary)) {
            Text(text = "Click me!")
        }
    }



}







@OptIn(ExperimentalPermissionsApi::class)
@Composable
 fun Sample(comtext:Context) {

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    if (cameraPermissionState.status.isGranted) {


        Text(text = "Camera permission granted")





    } else {
        Column {
            val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
                "The camera is important for this app. Please grant the permission."
            } else {
                "Camera not available"
            }

            Text(textToShow)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                Text("Request permission")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBasicTheme {
        Column(modifier = Modifier.fillMaxSize()) {

          Text(text ="This is Sample", textAlign = TextAlign.Center)
            Greeting("Android")

        }


    }
}