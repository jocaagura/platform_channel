package co.com.pragma.platform_channel

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity

// importamos las librerias de configuracion necesarias
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    // este nombre debe ser igual al definido en el pla
    private val CHANNEL = "co.com.pragma.platformchannel"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            Log.i("JOAO DEPURACION", call.method)


            if (call.method == "version") {
                val fluttermsg = call.arguments.toString()
                Log.i("JOAO DEPURACION", getVersion(fluttermsg))
                result.success(getVersion(fluttermsg))
            } else if (call.method == "bateria") {
            result.success(getBatteryLevel())
        }else {
                result.notImplemented()
            }


        }
    }


    private fun getVersion(msg: String?): String {
        return "version ${Build.VERSION.SDK_INT}, code name ${Build.VERSION.CODENAME}\n$msg"
    }

    private fun getBatteryLevel(): String {
        var batteryPercent = "0%.."
        val batteryLevel: Int
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
            batteryPercent = "$batteryLevel%"
        } else {
            val intent = ContextWrapper(applicationContext).registerReceiver(null, IntentFilter(
                Intent.ACTION_BATTERY_CHANGED))
            batteryLevel = intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100 / intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            batteryPercent = "$batteryLevel%"
        }
        return batteryPercent
    }

}


