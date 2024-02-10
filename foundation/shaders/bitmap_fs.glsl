precision highp float;

varying vec2 v_text_coord;
uniform sampler2D texture_sampler;

void main() {
    gl_FragColor = texture2D(texture_sampler, v_text_coord);
}
