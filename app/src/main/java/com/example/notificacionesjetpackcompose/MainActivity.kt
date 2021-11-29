package com.example.notificacionesjetpackcompose

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notificacionesjetpackcompose.ui.theme.NotificacionesJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificacionesJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                   Greeting()

                }
            }
        }
    }
}

@Composable
fun Greeting() {

    val context = LocalContext.current
    val channelId = "Channel"
    val notificationId = 0

    val myBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.nosfe)

   // val myBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_foreground)
    //val bigText = "Contrary to popular belief, lorem ipsum is not simply random text. It has roots in a piece of classical latin literature from 45 BC, making it over 2000 year old."

    LaunchedEffect(Unit) {
        createNotificationChannel(channelId, context)
    }
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally ) {
       Button(
           modifier = Modifier
               .padding(20.dp)
               .size(100.dp),

           colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
           shape = RoundedCornerShape(25),
           onClick = {
               simpleNotification(channelId = channelId, context = context, notificationId = notificationId, textContent = "Notificación Simple", textTitle = "Notificación")

           }) {
           Text(text = "Notificación Simple")
           
       }



        Button(
            modifier = Modifier
                .padding(20.dp)
                .size(100.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            shape = RoundedCornerShape(50),
            onClick = {
                textoLargoNotification(channelId = channelId, context = context, notificationId = notificationId,
                    textContent = "Notificación texto largo, Notificación texto largo, Notificación texto largo, Notificación texto largo, Notificación texto largo, Notificación texto largo, ",
                    textTitle = "Notificación")

            }) {
            Text(text = "Notificación texto largo")

        }



        Button(
            modifier = Modifier
                .padding(20.dp)
                .size(100.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
            shape = RoundedCornerShape(10),
            onClick = {
                textoConIconoNotificacion(iconoGrande = myBitmap, channelId = channelId, context = context, notificationId = notificationId, textContent = "Notificación con icono", textTitle = "Notificación",)

            }) {
            Text(text = "Notificación con icono")

        }


        Button(
            modifier = Modifier
                .padding(20.dp)
                .size(100.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
            shape = RoundedCornerShape(5),
            onClick = {
                imagenGrandeNotification(iconoGrande = myBitmap, channelId = channelId, context = context, notificationId = notificationId, textContent = "Notificación imagen grande", textTitle = "Notificación",)

            }) {
            Text(text = "Notificación imagen grande")

        }


   }
}



fun imagenGrandeNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    iconoGrande: Bitmap,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.notificacion)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setLargeIcon(iconoGrande)
        .setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(iconoGrande)
                .bigLargeIcon(null)
        )
        .setPriority(priority)
    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, builder.build())
    }
}


fun textoConIconoNotificacion(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    iconoGrande: Bitmap,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.notificacion)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setLargeIcon(iconoGrande)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(
                    textContent
                )
        )
        .setPriority(priority)
    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, builder.build())
    }
}



fun simpleNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.notificacion)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(priority)
    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, builder.build())
    }
}


fun textoLargoNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.notificacion)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(textContent)
        )
        .setPriority(priority)
    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, builder.build())
    }
}




fun createNotificationChannel(channelId: String, context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Name"
        val desc = "Desc"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = desc
        }
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotificacionesJetpackComposeTheme {
        Greeting()
    }
}