attribute vec2 position;

varying vec2 v_text_coord;

uniform vec2 u_resolution;


void main() {
    vec2 zeroToOne = position / u_resolution;
    vec2 zeroToTwo = zeroToOne * 2.0;
    vec2 clipSpace = (zeroToTwo - 1.0) * vec2(1, -1);

    gl_Position = vec4(clipSpace, 0.0, 1.0);
    v_text_coord = clipSpace;
}
