import 'package:graphics/src/core/rect.dart';
import 'package:graphics/src/pipeline/layer_manager.dart';
import 'package:graphics/src/pipeline/node.dart';
import 'package:graphics/src/pipeline/node_graph.dart';
import 'package:graphics/src/pipeline/source_node.dart';
import 'package:graphics/src/utils.dart';
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

  tearDown(() {
    layerBackground.invalidateCache();
    layerA.invalidateCache();
    layerB.invalidateCache();
    layerC.invalidateCache();
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
      expect(layerManager.layers[2].rootNode.id, equals(layerB.id));

      await exportGraphToPNG(layerGraph.rootNode, "layers_a_b_graph");
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

      await saveBitmapToLocalDir(outputBitmap, "output_JJJ.png");

      expect(bitmapsAreEqual(outputBitmap, expectedBitmap), isTrue);
      expect(layerManager.layers.length, equals(4));

      expect(layerManager.layers.first.rootNode.id, equals(layerBackground.id));
      expect(layerManager.layers[1].rootNode.id, equals(layerC.id));
      expect(layerManager.layers[2].rootNode.id, equals(layerA.id));
      expect(layerManager.layers[3].rootNode.id, equals(layerB.id));
    });

    test("insert layer c at position 2 in layers a b", () async {
      final layerGraph = NodeGraph(layerBackground);

      await exportGraphToPNG(layerBackground, "layer_bg");

      final layerManager = LayerManager(layerGraph);
      layerManager.addLayer(layerA);
      await exportGraphToPNG(layerGraph.rootNode, "layer_a");

      layerManager.addLayer(layerB);
      await exportGraphToPNG(layerGraph.rootNode, "layer_ab");

      layerManager.addLayer(layerC, position: 2);
      await exportGraphToPNG(layerGraph.rootNode, "layer_acb");

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
      expect(layerManager.layers[3].rootNode.id, equals(layerB.id));
    });

    test("remove layer b in layers a b", () async {
      final layerGraph = NodeGraph(layerBackground);

      final layerManager = LayerManager(layerGraph);
      layerManager.addLayer(layerA);
      layerManager.addLayer(layerB);

      layerManager.removeLayer(2);

      final outputBitmap = layerGraph.process(Rect(
        x: 0,
        y: 0,
        width: 22,
        height: 22,
      ));
      final expectedBitmap =
          await loadBitmapFromImage("$testAssetPath/layers_a.png", 22, 22);

      expect(bitmapsAreEqual(outputBitmap, expectedBitmap), isTrue);
      expect(layerManager.layers.length, equals(2));
      expect(layerManager.layers.first.rootNode.id, equals(layerBackground.id));
      expect(layerManager.layers[1].rootNode.id, equals(layerA.id));
    });

    test("remove layer a in layers a b", () async {
      final layerGraph = NodeGraph(layerBackground);

      final layerManager = LayerManager(layerGraph);
      layerManager.addLayer(layerA);
      layerManager.addLayer(layerB);

      layerManager.removeLayer(1);

      final outputBitmap = layerGraph.process(Rect(
        x: 0,
        y: 0,
        width: 22,
        height: 22,
      ));
      final expectedBitmap =
          await loadBitmapFromImage("$testAssetPath/layers_b.png", 22, 22);

      expect(bitmapsAreEqual(outputBitmap, expectedBitmap), isTrue);
      expect(layerManager.layers.length, equals(2));
      expect(layerManager.layers.first.rootNode.id, equals(layerBackground.id));
      expect(layerManager.layers[1].rootNode.id, equals(layerB.id));
    });

    test("remove layer b in layers a b c", () async {
      final layerGraph = NodeGraph(layerBackground);

      final layerManager = LayerManager(layerGraph);
      layerManager.addLayer(layerA);
      layerManager.addLayer(layerB);
      layerManager.addLayer(layerC);

      layerManager.removeLayer(2);

      final outputBitmap = layerGraph.process(Rect(
        x: 0,
        y: 0,
        width: 22,
        height: 22,
      ));
      final expectedBitmap =
          await loadBitmapFromImage("$testAssetPath/layers_a_c.png", 22, 22);

      expect(bitmapsAreEqual(outputBitmap, expectedBitmap), isTrue);
      expect(layerManager.layers.length, equals(3));
      expect(layerManager.layers.first.rootNode.id, equals(layerBackground.id));
      expect(layerManager.layers[1].rootNode.id, equals(layerA.id));
      expect(layerManager.layers[2].rootNode.id, equals(layerC.id));
    });

    test("Cache is used and hits are counted", () {
      final layerGraph = NodeGraph(layerBackground);
      final layerManager = LayerManager(layerGraph);
      layerManager.addLayer(layerA);
      layerManager.addLayer(layerB);

      final output1 =
          layerGraph.process(Rect(x: 0, y: 0, width: 22, height: 22));
      expect(layerGraph.rootNode.cacheHits, equals(0));

      final output2 =
          layerGraph.process(Rect(x: 0, y: 0, width: 22, height: 22));
      expect(layerGraph.rootNode.cacheHits, equals(1)); // cache was used

      expect(bitmapsAreEqual(output1, output2), isTrue);
    });

    test("layer benchmark - add and process single layer", () async {
      final layerBg = SourceNode(
          source: await loadBitmapFromImage(
              "$testAssetPath/layer_benchmark_bg.png"));
      final layerA = SourceNode(
          source: await loadBitmapFromImage(
              "$testAssetPath/layer_benchmark_a.png"));

      benchmark(() {
        final layerGraph = NodeGraph(layerBg);
        final layerManager = LayerManager(layerGraph);

        layerManager.addLayer(layerA);
        layerGraph.process(Rect(
          x: 0,
          y: 0,
          width: layerBg.source.width,
          height: layerBg.source.height,
        ));
      }, iterations: 200);

      expect(true, isTrue);
    });
  });
}
