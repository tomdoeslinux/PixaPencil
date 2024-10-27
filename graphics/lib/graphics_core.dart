/// Support for doing something awesome.
///
/// More dartdocs go here.
library;

import 'package:graphics_core/src/algorithms/adjust_hsv.dart';
import 'package:graphics_core/src/core/color.dart';
import 'package:graphics_core/src/utils.dart';

export 'src/graphics_core_base.dart';

// TODO: Export any libraries intended for clients of this package.

void main() async {
  final bitmap = await loadBitmapFromImage("eiffel.png");
  saveBitmapToLocalDir(adjustHsv(bitmap, 0, 0, -0.5), "jaye.png");
}