import 'package:app/screens/drawing/drawing_state.dart';
import 'package:app/screens/drawing/widgets/bottom_panel.dart';
import 'package:app/screens/drawing/widgets/color_picker.dart';
import 'package:app/screens/drawing/widgets/drawing_canvas.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class DrawingScreen extends StatelessWidget {
  const DrawingScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => DrawingState(),
      child: const Column(
        children: [
          ColorPicker(),
          Expanded(
            child: DrawingCanvas(),
          ),
          BottomPanel(),
        ],
      ),
    );
  }
}
