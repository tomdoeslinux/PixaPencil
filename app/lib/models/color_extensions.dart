import 'package:graphics/graphics.dart';
import 'package:flutter/material.dart';

extension GColorExtensions on GColor {
  Color toFlutterColor() {
    return Color.fromARGB(a, r, g, b);
  }
}

extension ColorExtensions on Color {
  GColor toGColor() {
    return GColors.rgba(red, green, blue, alpha);
  }
}
