import 'package:app/screens/drawing/widgets/layers_panel.dart';
import 'package:app/screens/drawing/widgets/tools_panel.dart';
import 'package:flutter/material.dart';

class BottomPanel extends StatelessWidget {
  const BottomPanel({super.key});

  @override
  Widget build(BuildContext context) {
    return const DefaultTabController(
      length: 2,
      child: Column(
        children: [
          TabBar(
            tabs: [Tab(text: 'Tools'), Tab(text: 'Layers')],
          ),
          SizedBox(
            height: 100,
            child: TabBarView(children: [
              ToolsPanel(),
              LayersPanel(),
            ]),
          )
        ],
      ),
    );
  }
}
