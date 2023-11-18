package com.example.action_process_text

import android.content.Intent

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
//import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

public class ActionProcessTextPlugin : io.flutter.app.FlutterActivity(), FlutterPlugin, ActivityAware {
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    print("-------onAttachedToEngine")
    channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "action_process_text")
    //channel.setMethodCallHandler(this);
  }

  // override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
  //    super.configureFlutterEngine(flutterEngine)
  //     methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "Example")
  //     methodChannel.setMethodCallHandler(this);
  // }

  // override fun onMethodCall(call: MethodCall, result: Result) {
  //     result.success("HELLO")
  // }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    print("-------onAttachedToActivity")
    if (binding.activity.intent.action == Intent.ACTION_PROCESS_TEXT) {
      val selectedText = binding.activity.intent.getStringExtra(Intent.EXTRA_PROCESS_TEXT) ?: ""
      channel.invokeMethod("copiedText", selectedText)
    }
    //currentActivity = binding.activity
  }

  override fun onDetachedFromActivityForConfigChanges() {
    //currentActivity = null
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    //currentActivity = binding.activity
  }

  override fun onDetachedFromActivity() {
    //currentActivity = null
  }
}