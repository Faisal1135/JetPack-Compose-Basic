package com.example.composebasic.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager


import androidx.activity.compose.rememberLauncherForActivityResult

import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

import com.example.composebasic.utils.SmsData

import com.example.composebasic.utils.getSmsWithContext

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale





@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permission(
    permission: String,
    manifest:String,
    context:Context,
    ) {
    val permision = rememberPermissionState(permission = manifest)

    if (permision.status.isGranted) {

        val sms= getSmsWithContext(context)
        if (sms.isNotEmpty()) {
            LazyColumn{
                items(sms.size){index->
                    Box(modifier =Modifier.height(56.dp), contentAlignment = Alignment.Center){
                    Column(horizontalAlignment = Alignment.CenterHorizontally ){
                        Text(text = sms[index].message)
                        Text(text = sms[index].phoneNumber)
                    }


                }
            }
        }
    } else {
        Column {
            val textToShow = if (permision.status.shouldShowRationale) {
                "$permission is important for this app. Please grant the permission."
            } else {
                "$permission not available"
            }

            Text(textToShow)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { permision.launchPermissionRequest() }) {
                Text("Request permission")
            }
        }
    }

}}



@Composable
fun lancherForActivity(){
    val context = LocalContext.current


    var isPersmionHas by remember { mutableStateOf(ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.READ_SMS
    )==PackageManager.PERMISSION_GRANTED ) }
    var smsDatas by remember { mutableStateOf<List<SmsData>>(emptyList()) }
    val lancher=  rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()){isGranted->
      isPersmionHas = isGranted
    }

  if(!isPersmionHas)   Button(
        onClick = {
            // Check permission
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_SMS
                ) -> {
                    getSmsWithContext(context).let {
                        smsDatas= it
                    }
                    // Some works that require permission

                }
                else -> {
                    // Asking for permission
                    lancher.launch(Manifest.permission.READ_SMS)
                }
            }
        }
    ) {
        Text(text = "Check and Request Permission")
    }
    if(isPersmionHas){
        smsDatas = getSmsWithContext(context)
    }

    if(smsDatas.isEmpty()){
        Text(text = "No SMS")
    }else{
        LazyColumn{
            items(smsDatas.size){index->
                Box(modifier =Modifier.fillMaxSize().padding(10.dp), contentAlignment = Alignment.Center){
                    Column(horizontalAlignment = Alignment.CenterHorizontally ){
                        Text(text = smsDatas[index].phoneNumber)
                        Divider(modifier = Modifier.height(8.dp))
                        Text(text = smsDatas[index].message)
                    }
                }
            }
        }
    }
}

