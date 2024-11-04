/// Support for doing something awesome.
///
/// More dartdocs go here.
library;

import 'src/algorithms/adjust_hsl.dart';
import 'src/utils.dart';

void main() async {
  final bitmap = await loadBitmapFromImage("eiffel.png");
  saveBitmapToLocalDir(adjustHsl(bitmap, 72, 1, 0, colorize: true), "eiffel_hue_plus_72_colorize.png");
}