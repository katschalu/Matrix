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
 * Represents a mathematical matrix
 * @author  testgoofy
 * @version 1.1.1
 * @since 1.0.0
 */
public class Matrix {
  protected double[][] data;
  protected int rows;
  protected int columns;

  public double[][] getData() {
    return data;
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }

  private static Matrix calculate(Matrix matrix, Operation operation, double scalar){
    Matrix result = new Matrix(matrix);
    for (int i = 0; i < matrix.rows; i++) {
      for (int j = 0; j < matrix.columns; j++) {
        result.data[i][j] = calculate(matrix.data[i][j], operation, scalar);
      }
    }
    return result;
  }

  private static Matrix calculate(Matrix m1, Operation operation, Matrix m2) throws IllegalArgumentException{
    if (m1.rows != m2.rows || m1.columns != m2.columns){
      throw new IllegalArgumentException("Matrix 'm1' and Matrix 'm2' don't have the correct dimensions");
    }
    Matrix result = new Matrix(m1);
    for (int i = 0; i < m1.rows; i++) {
      for (int j = 0; j < m1.columns; j++) {
        result.data[i][j] = calculate(m1.data[i][j], operation, m2.data[i][j]);
      }
    }
    return result;
  }

  private static double calculate(double s1, Operation operation, double s2){
    return switch (operation){
      case ADDITION -> s1 + s2;
      case SUBTRACTION -> s1 - s2;
      case MULTIPLICATION -> s1 * s2;
      default -> throw new IllegalArgumentException("Operation '" + operation.name() + "' is not known");
    };
  }

  /**
   * Adds a scalar to a matrix
   * @param matrix  the matrix to witch the scalar should be added
   * @param scalar  the scalar to be added
   * @return  the sum as a matrix
   */
  public static Matrix add(Matrix matrix, double scalar){
    return calculate(matrix, Operation.ADDITION, scalar);
  }

  /**
   * Adds to matrices
   * @param m1  The first Matrix to be added
   * @param m2  The second Matrix to be added
   * @return  The sum as a matrix
   * @throws IllegalArgumentException Matrix 'm1' and Matrix 'm2' must have the same dimensions
   */
  public static Matrix add(Matrix m1, Matrix m2) throws IllegalArgumentException{
    return calculate(m1, Operation.ADDITION, m2);
  }

  /**
   * Multiplies a scalar to a matrix
   * @param matrix  the matrix to witch the scalar should be multiplied
   * @param scalar  the scalar to be multiplied
   * @return  the product as a matrix
   */
  public static Matrix multiply(Matrix matrix, double scalar){
    return calculate(matrix, Operation.MULTIPLICATION, scalar);
  }

  /**
   * Multiplies to matrices
   * @see <a href="https://en.wikipedia.org/wiki/Matrix_multiplication" hreflang="en" title="Wikipedia">Matrix multiplication</a>
   * @param m1  The first Matrix to be multiplied
   * @param m2  The second Matrix to be multiplied
   * @return  The product as a matrix
   * @throws IllegalArgumentException Matrix 'm1' and Matrix 'm2' must have the correct dimensions. See <a href="https://en.wikipedia.org/wiki/Matrix_multiplication" hreflang="en" title="Wikipedia">Matrix multiplication</a> for more information
   */
  public static Matrix multiply(Matrix m1, Matrix m2) throws IllegalArgumentException{
    if (m1.columns != m2.rows){
      throw new IllegalArgumentException("Matrix 'm1' and Matrix 'm2' don't have the correct dimensions");
    }
    Matrix product = new Matrix(m1.rows, m2.columns);
    for (int i = 0; i < m1.rows; i++) {
      for (int j = 0; j < m2.columns; j++) {
        double sum = 0;
        for (int k = 0; k < m1.columns; k++) {
          sum += m1.data[i][k] * m2.data[k][j];
        }
        product.data[i][j] = sum;
      }
    }
    return product;
  }

  /**
   * Multiplies each component from Matrix 'm1' with the component on the same spot from Matrix 'm2'
   * @param m1  The first Matrix to be multiplied
   * @param m2  The second Matrix to be multiplied
   * @return  The product as a matrix
   * @throws IllegalArgumentException Matrix 'm1' and Matrix 'm2' must have the same dimensions
   */
  public static Matrix multiplyEachComponent(Matrix m1, Matrix m2) throws IllegalArgumentException{
    return calculate(m1, Operation.MULTIPLICATION, m2);
  }

  /**
   * Subtracts a scalar from a matrix
   * @param matrix  the matrix to witch the scalar should be subtracted
   * @param scalar  the scalar to be subtracted
   * @return  the difference as a matrix
   */
  public static Matrix subtract(Matrix matrix, double scalar){
    return calculate(matrix, Operation.SUBTRACTION, scalar);
  }

  /**
   * Subtracts to matrices
   * @param m1  The first Matrix to be subtracted from
   * @param m2  The second Matrix to be subtracted
   * @return  The difference as a matrix
   * @throws IllegalArgumentException Matrix 'm1' and Matrix 'm2' must have the same dimensions
   */
  public static Matrix subtract(Matrix m1, Matrix m2) throws IllegalArgumentException{
    return calculate(m1, Operation.SUBTRACTION, m2);
  }

  /**
   * Transpose a Matrix
   * @see <a href="https://en.wikipedia.org/wiki/Transpose" hreflang="en" title="Wikipedia">Transpose</a>
   * @param matrix The matrix to be transposed
   * @return  The transposed Matrix
   */
  public static Matrix transpose(Matrix matrix){
    Matrix result = new Matrix(matrix.columns, matrix.rows);
    for (int i = 0; i < result.rows; i++) {
      for (int j = 0; j < result.columns; j++) {
        result.data[i][j] = matrix.data[j][i];
      }
    }
    return result;
  }

  /**
   * Adds a scalar to the matrix
   * @param scalar  The scalar to be added
   */
  public void add(double scalar){
    data = add(new Matrix(data), scalar).data;
  }

  /**
   * Adds a matrix to the matrix
   * @param matrix  The matrix to be added
   */
  public void add(Matrix matrix){
    data = add(new Matrix(data), matrix).data;
  }

  /**
   * Compares the argument to the receiver, and answers true if they represent the <em>same</em> object using a class
   * specific comparison. The implementation in Object answers true only if the argument is the exact same object as the
   * receiver (==).
   */
  @Override
  public final boolean equals(Object o) {
    if (o instanceof Matrix){
      Matrix that = (Matrix) o;
      if (rows == that.rows && columns == that.columns){
        boolean sameData = true;
        for (int i = 0; i < rows; i++) {
          for (int j = 0; j < columns; j++) {
            if (data[i][j] != that.data[i][j]){
              sameData = false;
              break;
            }
          }
          return sameData;
        }
      }
    }
    return false;
  }

  /**
   * Multiply a scalar to the matrix
   * @param scalar  The scalar to be multiplied
   */
  public void multiply(double scalar){
    data = multiply(new Matrix(data), scalar).data;
  }

  /**
   * Multiply a matrix to the matrix
   * @param matrix  The matrix to be multiplied
   */
  public void multiply(Matrix matrix){
    Matrix result = multiply(new Matrix(this.data), matrix);
    data = result.data;
    rows = result.rows;
    columns = result.columns;
  }

  /**
   * Multiplies each component from Matrix 'matrix' with the component on the same spot from this Matrix
   * @param matrix  The matrix to be multiplied
   */
  public void multiplyEachComponent(Matrix matrix){
    data = multiplyEachComponent(new Matrix(data), matrix).data;
  }

  /**
   * Subtracts a scalar from the matrix
   * @param scalar  The scalar to be subtracted
   */
  public void subtract(double scalar){
    data = subtract(new Matrix(data), scalar).data;
  }

  /**
   * Subtracts a matrix from the matrix
   * @param matrix  The matrix to be subtracted
   */
  public void subtract(Matrix matrix){
    data = subtract(new Matrix(data), matrix).data;
  }

  /**
   * Transposes the Matrix
   */
  public void transpose(){
    Matrix result = transpose(new Matrix(data));
    data = result.data;
    rows = result.rows;
    columns = result.columns;
  }

  public Matrix(Matrix matrix){
    data = matrix.data;
    rows = matrix.rows;
    columns = matrix.columns;
  }

  public Matrix(double[][] data) throws IllegalArgumentException{
    rows = data.length;
    columns = data[0].length;
    this.data = data;
    for (int i = 0; i < rows; i++) {
      if (columns != data[i].length){
        throw new IllegalArgumentException("Bad data");
      }
    }
  }

  public Matrix(int rows, int columns){
    this.rows = rows;
    this.columns = columns;
    data = new double[rows][columns];
  }

  protected Matrix(){}
}