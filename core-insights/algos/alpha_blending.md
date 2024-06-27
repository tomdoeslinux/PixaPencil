# Alpha Blending

## 1. Overview
### Purpose
Alpha blending is a process used in computer graphics to enable the use of composite (multi-layered) images. It is important for adding a visual, transparency effect when using overlapping layers.
### Scope
This feature will handle the blending of pixel colors across multiple layers based on the foreground and background RGBA values of a pixel, similar to how Aseprite does it.

As illustrated in the image below, we initially drew the blue line on top of the pixel canvas, which created a transparency effect allowing the underlying pixel grid to remain (partially) visible, much like looking through a tinted glass window.

When the green drawing is overlaid, it creates a layered appearance, similar to placing a translucent sheet of paper over another.

![Pasted image 20240627022437](https://github.com/tomdoeslinux/PixaPencil/assets/129924903/cf44fc21-8127-449e-97ca-b344656a8dd0)

## 2. Algorithm
### Description
When computers were first introduced, the challenge was how to draw pixels on a screen. However, just drawing pixels wasn't enough; we also needed to create solutions that involved complex visual elements, such as layering. Layering allows for multiple elements to be stacked and combined visually, which is essential for modern graphics.

Colors are represented by RGB values, but they also include an alpha (A) component. The alpha value represents the opacity of a color, indicating how translucent a particular pixel is. 

Incorporating the alpha component is important for rendering effects such as blending and transparency, which are fundamental to layering. By accurately handling both color and transparency, we can ensure that the UI reflects the full range of visual effects, providing users with a richer and more dynamic experience. This requires calculations and optimizations to display layered graphics in a realistic manner.

For example, a window can have a blue tint but still allow light to pass through. The resultant color and transparency of the window are not only determined by the individual colors and opacities of each layer but also by how these layers interact with each other. 

Another example is, if you have a blue layer with 50% opacity over a red layer with 100% opacity, the resulting color will be a blend of blue and red, and the transparency will be affected by the opacity of both layers.

![Pasted image 20240627234103](https://github.com/tomdoeslinux/PixaPencil/assets/129924903/0929e07f-336d-4e61-bd20-691f860b37fd)

---

To address this issue and similar ones in computer graphics, we utilize the concept of alpha compositing. Alpha compositing allows us to blend colors and transparencies in a way that creates realistic images. To better understand alpha compositing, it helps to visualize each pixel as being composed of multiple subpixels. For example, a pixel with an opacity of 70 percent means that 70 percent of the subpixels are visible while the remaining 30 percent are transparent.

When blending two pixels together, the first step is to calculate the transparency (or opacity) of both the foreground and background pixels. Imagine placing a pixel composed of 70 percent subpixels on top of another pixel with 30 percent subpixels. The resulting opacity of the new pixel will be at least 70 percent, as all the subpixels from the foreground will show through. Additionally, the remaining 30 percent of the foreground that is transparent must be considered, taking into account the chances that these transparent areas will reveal the background subpixels.

The chance that a particular subpixel in the transparent part of the foreground will show a subpixel from the background is determined by the opacity value of the background pixel. Since the pixels are randomly distributed, this chance remains consistent across the pixel.

This same concept applies to colors. To blend colors using alpha compositing, we need to calculate the new transparency and the new hue (color). Each color is separated into three components: red (R), green (G), and blue (B).

For each color channel, both the foreground and background colors are divided into subpixels. Darker colors have lower values for each channel, while lighter colors have higher values. To accurately calculate the new color value for each channel, we must consider both the hues and the translucency of each layer and blend them together.

For instance, if the background has a red hue with low opacity, its effect on the new blended color will be minimal. However, if the background red hue has high opacity, it will significantly influence the new color and opacity effect. The blending process involves combining the hues and transparency values to create a seamless and realistic image.

Equations:

$\Huge α_{out}​=α_{f}​+α_{b}​(1−α_{f}​)$

$\Huge C_{out}​=\frac{C_{f}\alpha_{f}​+C_{b}​\alpha_{b}​(1-\alpha_{f})}{\alpha_{out}}$

Where:
- $C_{out}$ is the output color.
- $C_{f}$​ is the foreground color.
- $C_{b}$​ is the background color.
- $\alpha_{f}$ is the alpha (opacity) of the foreground.
- $\alpha_{b}$ is the alpha (opacity) of the background.
- $\alpha_{out}$ is the output alpha.

### Inputs and Outputs

- **Inputs:** RGBA values of the foreground and background layers
- **Outputs:** The RGBA color after blending

### Implementation (Dart)
```Dart
@pragma("vm:prefer-inline")  
int blendColors(int foreground, int background) {  
  // Extract RGBA values from a color (represented as an integer, exp format (0xRRGGBBAA)  
  final double fgRed = ((foreground >> 24) & 0xFF) / 255.0;  
  final double fgGreen = ((foreground >> 16) & 0xFF) / 255.0;  
  final double fgBlue = ((foreground >> 8) & 0xFF) / 255.0;  
  final double fgAlpha = (foreground & 0xFF) / 255.0;  
  
  final double bgRed = ((background >> 24) & 0xFF) / 255.0;  
  final double bgGreen = ((background >> 16) & 0xFF) / 255.0;  
  final double bgBlue = ((background >> 8) & 0xFF) / 255.0;  
  final double bgAlpha = (background & 0xFF) / 255.0;  
  
  // Transparency of foreground, basically the inverse of opacity  
  // Used to determine how much of the background color/alpha should show through  // in the final blended color  final double fgTransparency = (1 - fgAlpha);  
  
  // The resulting alpha is a combination of the foreground alpha and the background alpha  
  // adjusted by foreground transparency  final double outAlpha = fgAlpha + (bgAlpha * fgTransparency);  
  
  // The resulting (red, green, blue) is a combination of the foreground  
  // (red, green, blue) and the background (red, green, blue) adjusted by foreground transparency  // and alpha blending (outAlpha)  
  final double outRed = ((fgRed * fgAlpha) + ((bgRed * bgAlpha) * fgTransparency)) / outAlpha;  
  final double outGreen = ((fgGreen * fgAlpha) + ((bgGreen * bgAlpha) * fgTransparency)) / outAlpha;  
  final double outBlue = ((fgBlue * fgAlpha) + ((bgBlue * bgAlpha) * fgTransparency)) / outAlpha;  
  
  final int outRed_ = (outRed * 255).ceil();  
  final int outGreen_ = (outGreen * 255).ceil();  
  final int outBlue_ = (outBlue * 255).ceil();  
  final int outAlpha_ = (outAlpha * 255).ceil();  
  
  // Combine back to single color  
  final int outColor = (outRed_ << 24) | (outGreen_ << 16) | (outBlue_ << 8) | outAlpha_;  
  
  return outColor;  
}
  
class Color {  
  final int r;  
  final int g;  
  final int b;  
  final int a;  
  
  const Color(this.r, this.g, this.b, this.a);  
  
  const Color.fromInt(int input)  
      : r = (input >> 24) & 0xFF,  
        g = (input >> 16) & 0xFF,  
        b = (input >> 8) & 0xFF,  
        a = input & 0xFF;  
  
  @pragma("vm:prefer-inline")  
  int toInt() {  
    return (r << 24) | (g << 16) | (b << 8) | a;  
  }  
  
  @override  
  String toString() {  
    return "($r, $g, $b, $a)";  
  }  
}  
  
void main() {  
  const colorBackground = Color(128, 129, 129, 255);  
  const colorForeground = Color(223, 113, 38, 50);  
  
  final result = blendColors(colorForeground.toInt(), colorBackground.toInt());  
  
  print(Color.fromInt(result));  
}
```

## 3. Diagrams

These diagrams are useful for helping you visualize how alpha blending works.

![image (2)](https://github.com/tomdoeslinux/PixaPencil/assets/129924903/802271c5-3199-4272-b093-39b6653ff037)

![image (1)](https://github.com/tomdoeslinux/PixaPencil/assets/129924903/a7f05e6b-1c6d-44a3-ab77-b76429ae9064)
