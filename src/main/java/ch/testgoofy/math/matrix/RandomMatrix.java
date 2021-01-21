//MIT License
//
//    Copyright (c) 2021 testgoofy
//
//    Permission is hereby granted, free of charge, to any person obtaining a copy
//    of this software and associated documentation files (the "Software"), to deal
//    in the Software without restriction, including without limitation the rights
//    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//    copies of the Software, and to permit persons to whom the Software is
//    furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in all
//    copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//    SOFTWARE.

package ch.testgoofy.math.matrix;

/**
 * Creates a matrix with random values
 * @see Matrix
 * @author  testgoofy
 * @version 1.2.0
 * @since 1.2.0
 */
public class RandomMatrix extends Matrix{

  /**
   * Generates a matrix with random data
   * @param rows  number of rows of the matrix
   * @param columns number of columns of the matrix
   */
  public RandomMatrix(int rows, int columns){
    this(rows, columns, 0, 1.0);
  }

  /**
   * Generates a matrix with random data the values will be between the min value and the min + 1 value
   * @param rows  number of rows of the matrix
   * @param columns number of columns of the matrix
   * @param min minimum value
   */
  public RandomMatrix(int rows, int columns, double min){
    this(rows, columns, min, min+1.0);
  }

  /**
   * Generates a matrix with random data the values will be between the min value and the max value
   * @param rows  number of rows of the matrix
   * @param columns number of columns of the matrix
   * @param min minimum value
   * @param max maximum value
   */
  public RandomMatrix(int rows, int columns, double min, double max){
    super(rows, columns);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        data[i][j] = (Math.random() * (max - min)) + min;
      }
    }
  }
}
