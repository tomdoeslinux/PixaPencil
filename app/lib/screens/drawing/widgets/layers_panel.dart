import 'dart:ui' as ui;

import 'package:app/models/bitmap_extensions.dart';
import 'package:flutter/material.dart';
import 'package:graphics/graphics.dart';
import 'package:provider/provider.dart';

import '../drawing_state.dart';

class LayersPanel extends StatelessWidget {
  const LayersPanel({super.key});

  Future<List<ui.Image>> processLayers(List<Layer> layers) async {
    final processedLayers = <ui.Image>[];

    for (final layer in layers) {
      final bitmap = layer.rootNode.process(
        GRect(x: 0, y: 0, width: 200, height: 200),
      );

      final image = await bitmap.toFlutterImage();
      processedLayers.add(image);
    }

    return processedLayers;
  }

  @override
  Widget build(BuildContext context) {
    final drawingState = Provider.of<DrawingState>(context);

    return FutureBuilder(
      future: processLayers(drawingState.layers),
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          return Row(
            children: [
              for (final image in snapshot.data!)
                RawImage(
                  image: image,
                  width: 200,
                  height: 200,
                ),
              IconButton.filled(
                padding: const EdgeInsets.all(16),
                onPressed: () {},
                icon: const Icon(Icons.add),
                style: IconButton.styleFrom(
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(8),
                  ),
                ),
              )
            ],
          );
        } else {
          return const Text("Loading...");
        }
      },
    );
  }
}
