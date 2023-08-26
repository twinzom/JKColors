# JKColors

JKColors is a project that uses the K-Means methodology to reduce the number of colors in an image. Users can define how many colors the output image will use.

## How to Run

You can run the project using the following command:

```shell
java Run <IMAGE_PATH> <NUM_OF_COLOR>
```
Replace `<IMAGE_PATH>` with the path to the image you wish to process and `<NUM_OF_COLOR>` with the number of colors you want in the output image.

For example:

```shell
java Run /path/to/image.png 256
```

This will generate a new image with the filename `image.png.256colors.png`.

## How it Works

This project performs the K-Means algorithm on the distinct colors, using Advanced Euclidean Distance to calculate the distances.

## Sample Images

**Input image**

<img src="https://github.com/twinzom/JKColors/blob/master/input.jpg?raw=true" width="200" >

**16 Colors**

<img src="https://github.com/twinzom/JKColors/blob/master/input.jpg.16colors.png?raw=true" width="200" >

**24 Colors**

<img src="https://github.com/twinzom/JKColors/blob/master/input.jpg.24colors.png?raw=true" width="200" >

**36 Colors**

<img src="https://github.com/twinzom/JKColors/blob/master/input.jpg.36colors.png?raw=true" width="200" >

**48 image**

<img src="https://github.com/twinzom/JKColors/blob/master/input.jpg.48colors.png?raw=true" width="200" >

**256 image**

<img src="https://github.com/twinzom/JKColors/blob/master/input.jpg.256colors.png?raw=true" width="200" >

## MIT License

Copyright (c) 2023 Twinzom

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
