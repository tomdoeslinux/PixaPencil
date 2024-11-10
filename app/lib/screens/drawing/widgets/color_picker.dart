import 'package:flutter/material.dart';
import 'package:graphics/src/core/color.dart' as g;

class ColorPicker extends StatelessWidget {
  final ValueChanged<g.Color> onColorSelected;

  static const dummyColors = [
    Colors.red,
    Colors.green,
    Colors.blue,
    Colors.yellow,
    Colors.purple,
    Colors.orange,
    Colors.pink,
    Colors.cyan,
    Colors.lime,
    Colors.indigo,
    Colors.teal,
    Colors.brown,
    Colors.grey,
  ];

  const ColorPicker({
    super.key,
    required this.onColorSelected,
  });

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 60,
      child: ListView.builder(
        scrollDirection: Axis.horizontal,
        itemCount: dummyColors.length,
        itemBuilder: (context, index) {
          final color = dummyColors[index];

          return GestureDetector(
            onTap: () => onColorSelected(
              g.Colors.rgb(color.red, color.green, color.blue),
            ),
            child: Container(width: 60, color: color),
          );
        },
      ),
    );
  }
}
