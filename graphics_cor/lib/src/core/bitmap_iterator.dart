import 'bitmap.dart';
import 'color.dart';

// todo update
class BitmapIterator {
  final Bitmap _bitmap;

  BitmapIterator(this._bitmap);

  int _x = 0;
  int _y = 0;

  int get x => _x;
  int get y => _y;

  int get currentPixelIndex => (_y * _bitmap.width + _x) * _bitmap.config.numChannels;

  int get currentPixel => _bitmap.getPixel(x, y);

  void put(Color color) {
    _bitmap.setPixel(x, y, color);
  }

  Color getPixel(int x, int y) => _bitmap.getPixel(x, y);

  void reset() {
    _x = 0;
    _y = 0;
  }

  bool moveNext() {
    // Move the iterator through the bitmap.
    // - Start by moving rightwards along the x-axis.
    // - Once the iterator reaches the final x position in the row,
    // - ...move downwards to the next row on the y-axis and reset the x position to 0.
    // - This continues until the entire bitmap is processed...

    if (_x < _bitmap.width - 1) {
      ++_x;
    } else if (_y < _bitmap.height - 1) {
      _x = 0;
      ++_y;
    } else {
      return false;
    }

    return true;
  }

  bool moveHorizontal([int steps = 1]) {
    final newX = _x + steps;

    if (newX < _bitmap.width) {
      _x = newX;
    } else if (_y < _bitmap.height - 1) {
      _x = 0;
      ++_y;
    } else {
      return false;
    }

    return true;
  }

  bool moveVertical([int steps = 1]) {
    final newY = _y + steps;

    if (newY < _bitmap.height) {
      _y = newY;
    } else if (_x < _bitmap.width - 1) {
      _y = 0;
      ++_x;
    } else {
      return false;
    }

    return true;
  }

  void moveTo(int x, int y) {
    _x = x;
    _y = y;
  }
}