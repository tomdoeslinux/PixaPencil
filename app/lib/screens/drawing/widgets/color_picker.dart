import 'package:app/models/color_extensions.dart';
import 'package:flutter/material.dart';
import 'package:graphics/graphics.dart';
import 'package:provider/provider.dart';

import '../drawing_state.dart';

class ColorPicker extends StatelessWidget {
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

  const ColorPicker({super.key});

  @override
  Widget build(BuildContext context) {
    final drawingState = Provider.of<DrawingState>(context);

    print(
        "${drawingState.selectedColor.r} ${drawingState.selectedColor.r} ${drawingState.selectedColor.b}");

    return Padding(
      padding: const EdgeInsets.all(16),
      child: Row(
        children: [
          Expanded(
            child: SizedBox(
              height: 60,
              child: ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: dummyColors.length,
                itemBuilder: (context, index) {
                  final color = dummyColors[index];

                  return GestureDetector(
                    onTap: () {
                      drawingState.selectedColor = color.toGColor();
                    },
                    child: Container(width: 60, color: color),
                  );
                },
              ),
            ),
          ),
          const SizedBox(width: 10),
          Container(
            height: 60,
            width: 60,
            color: drawingState.selectedColor.toFlutterColor(),
          ),
        ],
      ),
    );
  }
}
