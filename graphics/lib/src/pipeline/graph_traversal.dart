import 'dart:collection';

import 'node.dart';

void traverseGraphBFS(Node rootNode, void Function(Node) onNodeVisit) {
  final queue = Queue.of([rootNode]);

  while (queue.isNotEmpty) {
    final currentNode = queue.removeFirst();

    onNodeVisit(currentNode);

    if (currentNode.inputNode != null) {
      queue.add(currentNode.inputNode!);
    }

    if (currentNode.auxNode != null) {
      queue.add(currentNode.auxNode!);
    }
  }
}