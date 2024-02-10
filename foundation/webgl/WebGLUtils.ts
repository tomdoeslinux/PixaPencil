import {Asset} from "expo-asset";
import {ExpoWebGLRenderingContext} from "expo-gl";

export class WebGLUtils {
  private static createShader(
    gl: ExpoWebGLRenderingContext,
    type: GLenum,
    source: string
  ) {
    const shader = gl.createShader(type)
    gl.shaderSource(shader, source)
    gl.compileShader(shader)

    const success = gl.getShaderParameter(shader, gl.COMPILE_STATUS)

    if (success) {
      return shader
    }

    console.log(gl.getShaderInfoLog(shader))
    gl.deleteShader(shader)
  }

  private static async loadGLSLAssets(
    fetch_vertexShader: string,
    fetch_fragmentShader: string
  ): Promise<[string, string]> {
    const vertexShaderAsset = await Asset.loadAsync(fetch_vertexShader);
    const fragmentShaderAsset = await Asset.loadAsync(fetch_fragmentShader);

    const [vertexResponse, fragmentResponse] = await Promise.all([
      fetch(vertexShaderAsset[0].localUri!),
      fetch(fragmentShaderAsset[0].localUri!),
    ])

    const vertexShaderSrc = await vertexResponse.text();
    const fragmentShaderSrc = await fragmentResponse.text();

    return [vertexShaderSrc, fragmentShaderSrc]
  }

  static async createProgramFromScripts(
    gl: ExpoWebGLRenderingContext,
    fetch_vertexShader: string,
    fetch_fragmentShader: string
  ) {
    const [vertexShaderSrc, fragmentShaderSrc] = await WebGLUtils.loadGLSLAssets(
      fetch_vertexShader,
      fetch_fragmentShader
    )

    const vertexShader = WebGLUtils.createShader(gl, gl.VERTEX_SHADER, vertexShaderSrc)
    const fragmentShader = WebGLUtils.createShader(gl, gl.FRAGMENT_SHADER, fragmentShaderSrc)

    const program = gl.createProgram()
    gl.attachShader(program, vertexShader)
    gl.attachShader(program, fragmentShader)
    gl.linkProgram(program)

    const success = gl.getProgramParameter(program, gl.LINK_STATUS)

    if (success) {
      return program
    }

    console.log(gl.getProgramInfoLog(program))
    gl.deleteProgram(program)
  }

  static normalize(cx: number, cy: number) {
    const mid = 16 / 2
    const x = (cx - mid) / mid + 1 / 16
    const y = (mid - cy) / mid - 1 / 16
    return [x, y, 0]
  };

  static prepareViewport(gl: ExpoWebGLRenderingContext): void {
    gl.viewport(0, 0, gl.drawingBufferWidth, gl.drawingBufferHeight);

    gl.clearColor(0, 1, 1, 1);
    gl.clear(gl.COLOR_BUFFER_BIT);
  }
}
