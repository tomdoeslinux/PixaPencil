import 'package:graphics_core/src/core/rect.dart';
import 'package:graphics_core/src/pipeline/layer_manager.dart';
import 'package:graphics_core/src/pipeline/node.dart';
import 'package:graphics_core/src/pipeline/node_graph.dart';
import 'package:graphics_core/src/pipeline/source_node.dart';
import 'package:graphics_core/src/utils.dart';
import 'package:test/test.dart';

import '../../utils.dart';

void benchmark(void Function() action, {required int iterations}) {
  final times = <int>[];
  final stopwatch = Stopwatch();

  for (var i = 0; i < iterations; ++i) {
    stopwatch.reset();
    stopwatch.start();

    action();

    stopwatch.stop();
    times.add(stopwatch.elapsedMilliseconds);
  }

  print(times);

  final totalTime = times.fold(0, (sum, time) => sum + time);
  final avgTimeMillis = (totalTime / iterations.toDouble());

  print(
      'Average execution time over $iterations iterations: $avgTimeMillis ms');
}

void main() {
  group("benchmark", () {
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
