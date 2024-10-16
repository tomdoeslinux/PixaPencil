import 'package:graphics_core/src/core/rect.dart';
import 'package:graphics_core/src/pipeline/node.dart';
import 'package:graphics_core/src/utils.dart';
import 'package:test/test.dart';

import '../../utils.dart';

void main() {
  late SourceNode layerBackground;
  late SourceNode layerA;
  late SourceNode layerB;
  late SourceNode layerC;

  setUpAll(() async {
    layerBackground = SourceNode(
        source:
            await loadBitmapFromImage("$testAssetPath/layer_bg.png", 22, 22));
    layerA = SourceNode(
        source:
            await loadBitmapFromImage("$testAssetPath/layer_a.png", 11, 11));
    layerB = SourceNode(
        source:
            await loadBitmapFromImage("$testAssetPath/layer_b.png", 14, 14));
    layerC = SourceNode(
        source:
            await loadBitmapFromImage("$testAssetPath/layer_c.png", 22, 22));
  });

  group("Layer tests", () {
    test("layers a b added matches expected output", () async {
      final layerGraph = NodeGraph(layerBackground);

      final layerManager = LayerManager(layerGraph);
      layerManager.addLayer(layerA);
      layerManager.addLayer(layerB);

      final outputBitmap = layerGraph.process(Rect(
        x: 0,
        y: 0,
        width: 22,
        height: 22,
      ));
      final expectedBitmap =
          await loadBitmapFromImage("$testAssetPath/layers_a_b.png", 22, 22);

      expect(bitmapsAreEqual(outputBitmap, expectedBitmap), isTrue);
      expect(layerManager.layers.length, equals(3));

      expect(layerManager.layers.first.rootNode.id, equals(layerBackground.id));
      expect(layerManager.layers[1].rootNode.id, equals(layerA.id));
      expect(layerManager.layers.last.rootNode.id, equals(layerB.id));
    });

    test("insert layer c at position 1 in layers a b", () async {
      final layerGraph = NodeGraph(layerBackground);

      final layerManager = LayerManager(layerGraph);
      layerManager.addLayer(layerA);
      layerManager.addLayer(layerB);
      layerManager.addLayer(layerC, position: 1);

      final outputBitmap = layerGraph.process(Rect(
        x: 0,
        y: 0,
        width: 22,
        height: 22,
      ));
      final expectedBitmap =
          await loadBitmapFromImage("$testAssetPath/layers_c_a_b.png", 22, 22);

      expect(bitmapsAreEqual(outputBitmap, expectedBitmap), isTrue);
      expect(layerManager.layers.length, equals(4));

      expect(layerManager.layers.first.rootNode.id, equals(layerBackground.id));
      expect(layerManager.layers[1].rootNode.id, equals(layerC.id));
      expect(layerManager.layers[2].rootNode.id, equals(layerA.id));
      expect(layerManager.layers.last.rootNode.id, equals(layerB.id));
    });

    test("insert layer c at position 2 in layers a b", () async {
      final layerGraph = NodeGraph(layerBackground);

      final layerManager = LayerManager(layerGraph);
      layerManager.addLayer(layerA);
      layerManager.addLayer(layerB);
      layerManager.addLayer(layerC, position: 2);

      final outputBitmap = layerGraph.process(Rect(
        x: 0,
        y: 0,
        width: 22,
        height: 22,
      ));
      final expectedBitmap =
          await loadBitmapFromImage("$testAssetPath/layers_a_c_b.png", 22, 22);

      expect(bitmapsAreEqual(outputBitmap, expectedBitmap), isTrue);
      expect(layerManager.layers.length, equals(4));

      expect(layerManager.layers.first.rootNode.id, equals(layerBackground.id));
      expect(layerManager.layers[1].rootNode.id, equals(layerA.id));
      expect(layerManager.layers[2].rootNode.id, equals(layerC.id));
      expect(layerManager.layers.last.rootNode.id, equals(layerB.id));
    });
  });
}
