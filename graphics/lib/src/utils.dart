import 'dart:io';
import 'dart:typed_data';
import 'package:image/image.dart';
import 'core/bitmap.dart';
import 'pipeline/node.dart';
import 'package:http/http.dart' as http;

// TODO width doesn't need to be specified
Future<Bitmap> loadBitmapFromImage(
  String filename, [
  int? width,
  int? height,
]) async {
  final file = File(filename);
  final fileBytes = await file.readAsBytes();
  final bytes = Uint8List.fromList(fileBytes);

  final image = decodeImage(bytes);
  final pixels = image!.getBytes();

  final BitmapConfig config;

  if (image.numChannels == 3) {
    config = BitmapConfig.rgb;
  } else if (image.numChannels == 4) {
    config = BitmapConfig.rgba;
  } else {
    throw ArgumentError("Unsupported number of channels");
  }

  print("width: ${image.width}, height: ${image.height}, numChannels: ${image.numChannels}");

  return Bitmap.fromPixels(
    pixels,
    width ?? image.width,
    height ?? image.height,
    config: config,
  );
}

Future<void> saveBitmapToLocalDir(Bitmap bmp, String name) async {
  final img = Image.fromBytes(
    width: bmp.width,
    height: bmp.height,
    bytes: bmp.buffer,
    order:
        bmp.config == BitmapConfig.rgba ? ChannelOrder.rgba : ChannelOrder.rgb,
  );

  List<int> pngBytes = encodePng(img);

  await File(name).writeAsBytes(pngBytes);
}

Future<void> exportGraphToPNG(Node rootNode, String fileName) async {
  final dotContentBuffer = StringBuffer();
  dotContentBuffer.writeln('digraph G {');

  void writeNodeToBuffer(Node node) {
    final nodeTitle = 'Node${node.id}';
    dotContentBuffer
        .writeln('\t$nodeTitle [label="id ${node.id}\n${node.runtimeType}"]');

    if (node.inputNode != null) {
      dotContentBuffer.writeln(
          '\t$nodeTitle -> Node${node.inputNode!.id} [label="input"];');
      writeNodeToBuffer(node.inputNode!);
    }

    if (node.auxNode != null) {
      dotContentBuffer
          .writeln('\t$nodeTitle -> Node${node.auxNode!.id} [label="aux"];');
      writeNodeToBuffer(node.auxNode!);
    }
  }

  writeNodeToBuffer(rootNode);
  dotContentBuffer.writeln("}");

  final dotContent = dotContentBuffer.toString();
  final url = Uri.https(
    "quickchart.io",
    "/graphviz",
    {'format': 'png', 'graph': dotContent},
  );

  final response = await http.get(url);
  final file = File('$fileName.png');

  await file.writeAsBytes(response.bodyBytes);
}
