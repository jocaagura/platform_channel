import 'package:flutter/material.dart';
import 'package:platform_channel/data/platform_channel.dart';


class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  String _infoMsg = 'Presiona el boton para obtener la version de android';
  final PragmaPlatformChannel _pragmaPlatformChannel = PragmaPlatformChannel();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Container(),
          Text(_infoMsg),
          TextButton(onPressed: ()async{

            _infoMsg = await _pragmaPlatformChannel.version();
            setState(() {
            });

          }, child: const Text('Obtener versión'),

          ),
          TextButton(onPressed: ()async{

            _infoMsg = await _pragmaPlatformChannel.nivelBateria();
            setState(() {
            });

          }, child: const Text('nivel bateria'),

          ),
        ],
      ),
    );
  }
}
