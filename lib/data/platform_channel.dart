import 'package:flutter/services.dart';

class PragmaPlatformChannel {
  // Debemos enviar un mensaje al host de la plataforma nativa
  // El nombre de identificaodr para el platform channel debe ser unico para evitar colisiones con
  // otros platform channels
  final MethodChannel _methodChannel =
      const MethodChannel('co.com.pragma.platformchannel');

  /// [version] obtendremos la version de android del dispositivo que estamos trabajando
  /// tanto integer como su definicion en texto
  Future<String> version() async {
    String versionObtenida = 'No se ha podido establecer la versión';

    /// Emitimos el mensaje para la comunicacion con el codigo nativo
    try {
      final result = await _methodChannel.invokeMethod('version', {"nombre":"Jose", "Apellido": "Pinzón"});
      versionObtenida = result;
    } catch (e) {
      versionObtenida = e.toString();
    }

    return versionObtenida;
  }

  Future<String> nivelBateria() async {
    String porcentajeBateria = '0% - NA';

    /// Emitimos el mensaje para la comunicacion con el codigo nativo
    try {
      porcentajeBateria = await _methodChannel.invokeMethod('bateria');

    } catch (e) {
      porcentajeBateria = e.toString();
    }

    return porcentajeBateria;
  }

}
