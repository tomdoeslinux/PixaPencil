import '../core/color.dart';

GColor blendColors(GColor foreground, GColor background) {
  // Optimization: If the background alpha is 255 and the foreground is partially transparent, you can blend normally without considering any transparency from the background.
  if (foreground.a == 255) {
    return foreground;
  } else if (foreground.a == 0) {
    return background;
  } else if (background.a == 0) {
    return foreground;
  } else if (background.a == 255 && foreground.a == 0) {
    return background;
  } else if (background.a == 0 && foreground.a == 0) {
    return 0;
  }

  // Extract RGBA values from a color (represented as an integer, exp format (0xRRGGBBAA)
  final fgRed = foreground.r / 255.0;
  final fgGreen = foreground.g / 255.0;
  final fgBlue = foreground.b / 255.0;
  final fgAlpha = foreground.a / 255.0;

  final bgRed = background.r / 255.0;
  final bgGreen = background.g / 255.0;
  final bgBlue = background.b / 255.0;
  final bgAlpha = background.a / 255.0;

  // Transparency of foreground, basically the inverse of opacity
  // Used to determine how much of the background color/alpha should show through
  // in the final blended color
  final fgTransparency = (1 - fgAlpha);

  // The resulting alpha is a combination of the foreground alpha and the background alpha
  // adjusted by foreground transparency
  final outAlpha = fgAlpha + (bgAlpha * fgTransparency);

  // The resulting (red, green, blue) is a combination of the foreground
  // (red, green, blue) and the background (red, green, blue) adjusted by foreground transparency
  // and alpha blending (outAlpha)

  final outRed = outAlpha == 0
      ? 0
      : ((fgRed * fgAlpha) + ((bgRed * bgAlpha) * fgTransparency)) / outAlpha;
  final outGreen = outAlpha == 0
      ? 0
      : ((fgGreen * fgAlpha) + ((bgGreen * bgAlpha) * fgTransparency)) /
          outAlpha;
  final outBlue = outAlpha == 0
      ? 0
      : ((fgBlue * fgAlpha) + ((bgBlue * bgAlpha) * fgTransparency)) / outAlpha;

  final outRed_ = (outRed * 255).ceil();
  final outGreen_ = (outGreen * 255).ceil();
  final outBlue_ = (outBlue * 255).ceil();
  final outAlpha_ = (outAlpha * 255).ceil();

  return GColors.rgba(
    outRed_,
    outGreen_,
    outBlue_,
    outAlpha_,
  );
}
