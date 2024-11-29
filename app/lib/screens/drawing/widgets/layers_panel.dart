import 'dart:ui' as ui;

import 'package:app/models/bitmap_extensions.dart';
import 'package:flutter/material.dart';
import 'package:graphics/graphics.dart';
import 'package:provider/provider.dart';
import "package:graphics/src/utils.dart";

import '../drawing_state.dart';

class LayersPanel extends StatelessWidget {
  const LayersPanel({super.key});

  Future<List<ui.Image>> processLayers(
      List<Layer> layers, DrawingState drawingState) async {
    final processedLayers = <ui.Image>[];

    for (final layer in layers) {
      final bitmap = layer.rootNode.process(
        GRect(
          x: 0,
          y: 0,
          width: drawingState.canvasWidth,
          height: drawingState.canvasHeight,
        ),
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
      future: processLayers(drawingState.layers, drawingState),
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          return SingleChildScrollView(
            scrollDirection: Axis.horizontal,
            child: Row(
              children: [
                for (final (i, image) in snapshot.data!.indexed)
                  GestureDetector(
                    onTap: () {
                      drawingState.activeLayerIndex = i;
                    },
                    child: Container(
                      decoration: BoxDecoration(
                        border: drawingState.activeLayerIndex == i
                            ? Border.all(
                                color: Colors.black,
                                width: 2,
                              )
                            : null,
                      ),
                      child: RawImage(
                        image: image,
                        width: drawingState.canvasWidth.toDouble(),
                        height: drawingState.canvasHeight.toDouble(),
                      ),
                    ),
                  ),
                IconButton.filled(
                  padding: const EdgeInsets.all(16),
                  onPressed: () {
                    drawingState.addLayer();
                  },
                  icon: const Icon(Icons.add),
                  style: IconButton.styleFrom(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8),
                    ),
                  ),
                ),
                OutlinedButton(
                  onPressed: () async {
                    final graphImageBytes = await generateGraphImage(drawingState.nodeGraph.rootNode);
                    if (!context.mounted) return;
                    showDialog(
                      context: context,
                      builder: (context) {
                        return AlertDialog(
                          title: const Text("Node Graph"),
                          content: Image.memory(graphImageBytes),
                          actions: [
                            TextButton(
                              onPressed: () => Navigator.of(context).pop(),
                              child: const Text("Close"),
                            ),
                          ],
                        );
                      },
                    );
                  },
                  child: const Text("Print Node Graph"),
                ),
              ],
            ),
          );
        } else {
          return const Text("Loading...");
        }
      },
    );
  }
}
