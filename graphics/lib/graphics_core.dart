/// Support for doing something awesome.
///
/// More dartdocs go here.
library;

import 'src/algorithms/adjust_hsl.dart';
import 'src/utils.dart';
export 'src/graphics_core_base.dart';

void main() async {
  final bitmap = await loadBitmapFromImage("eiffel.png");
  saveBitmapToLocalDir(adjustHsl(bitmap, -0.92, 0, 0), "eiffel_hue_minus_92.png");
}