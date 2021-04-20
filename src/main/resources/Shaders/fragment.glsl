#version 330 core
out vec4 FragColor;

in vec3 outColor;
in vec2 Texcoords;

uniform sampler2D texture1;

void main()
{
    //FragColor = vec4(1.0, 1.0, 0.0, 1.0);
    FragColor = vec4(outColor, 1.0);
}