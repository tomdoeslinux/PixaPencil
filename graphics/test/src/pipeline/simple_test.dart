import 'package:graphics/src/core/color.dart';
import 'package:graphics/src/core/rect.dart';
import 'package:graphics/src/pipeline/ellipse_node.dart';
import 'package:graphics/src/pipeline/node_graph.dart';
import 'package:graphics/src/pipeline/source_node.dart';
import 'package:graphics/src/utils.dart';

void main() async {
  final nodeGraph = NodeGraph(
    EllipseNode(
      color: GColors.blue,
      from: (x: 50, y: 50),
      to: (x: 450, y: 250),
      inputNode: SourceNode(
        source: await loadBitmapFromImage("sunflowerfield.jpg"),
      ),
    ),
  );

  await saveBitmapToLocalDir(
    nodeGraph.process(GRect(x: 0, y: 0, width: 539, height: 360)),
    "output.png",
  );
}
