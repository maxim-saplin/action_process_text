import 'package:flutter/material.dart';
import 'dart:async';
import 'package:action_process_text/action_process_text.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  MyAppState createState() => MyAppState();
}

class MyAppState extends State<MyApp> {
  MyAppState() {
    initActionProcessText();
  }

  String inputText = '';
  Future<void> initActionProcessText() async {
    inputText = await ActionProcessText.getInputText;
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: ActionProcessText.calledFromIntent
          ? MainPage(inputText)
          : const Home(),
    );
  }
}

class MainPage extends StatefulWidget {
  final String text;
  const MainPage(this.text, {Key? key}) : super(key: key);
  @override
  MainPageState createState() => MainPageState();
}

class MainPageState extends State<MainPage> {
  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        appBar: AppBar(
          centerTitle: true,
          title: const Text('ACTION_PROCESS_TEXT Example'),
        ),
        body: Center(
          child: Column(mainAxisAlignment: MainAxisAlignment.center, children: [
            const Text('Selected Text:\n'),
            Text(widget.text),
          ]),
        ),
      ),
    );
  }
}

class Home extends StatelessWidget {
  const Home({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Text('Not called from native side.'),
    );
  }
}
