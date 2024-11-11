import 'package:graphics/src/core/bitmap.dart';

const testAssetPath = "test/assets";

bool bitmapsAreEqual(GBitmap a, GBitmap b) {
  if (a.width != b.width || a.height != b.height) {
    return false;
  }

  for (var x = 0; x < a.width; ++x) {
    for (var y = 0; y < a.height; ++y) {
      if (a.getPixel(x, y) != b.getPixel(x, y)) {
        return false;
      }
    }
  }

  return true;
}

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

  final totalTime = times.fold(0, (sum, time) => sum + time);
  final avgTimeMillis = (totalTime / iterations.toDouble());

  print(
      'Average execution time over $iterations iterations: $avgTimeMillis ms');
}