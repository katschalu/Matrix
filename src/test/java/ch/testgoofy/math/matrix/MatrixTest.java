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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatrixTest {

  @Test
  @DisplayName("Initialize empty matrix")
  void init_zero() {
    Matrix matrix = new Matrix(3, 8);
    assertEquals(3, matrix.getRows());
    assertEquals(8, matrix.getColumns());
    for (int i = 0; i < matrix.getRows(); i++) {
      for (int j = 0; j < matrix.getColumns(); j++) {
        assertEquals(0.0, matrix.getData()[i][j]);
      }
    }
  }

  @Test
  @DisplayName("Initialize with bad data")
  void init_badData(){
    assertThrows(IllegalArgumentException.class, () -> {
      new Matrix(new double[][]{
          {9.7,9.7,9.7,9.7},
          {9.7,9.7,9.7},
          {9.7,9.7,9.7},
          {9.7,9.7,9.7}
      });
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Matrix(new double[][]{
          {9.7,9.7,9.7},
          {9.7,9.7,9.7,9.7},
          {9.7,9.7,9.7},
          {9.7,9.7,9.7}
      });
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Matrix(new double[][]{
          {9.7,9.7,9.7},
          {9.7,9.7,9.7},
          {9.7,9.7,9.7,9.7},
          {9.7,9.7,9.7}
      });
    });
    assertThrows(IllegalArgumentException.class, () -> {
      new Matrix(new double[][]{
          {9.7,9.7,9.7},
          {9.7,9.7,9.7},
          {9.7,9.7,9.7},
          {9.7,9.7,9.7,9.7}
      });
    });
  }

  @Test
  @DisplayName("Add a scalar to a matrix (static)")
  void add_scalar_static(){
    Matrix matrix = new Matrix(new double[][]{
        {1,1,1,1},
        {1,1,1,1},
        {1,1,1,1}
    });
    double scalar = 5.3;
    Matrix result = Matrix.add(matrix, scalar);
    for (int i = 0; i < matrix.getRows(); i++) {
      for (int j = 0; j < matrix.getColumns(); j++) {
        assertEquals(6.3, result.getData()[i][j]);
      }
    }
  }

  @Test
  @DisplayName("Add a matrix to a matrix (static)")
  void add_matrix_static(){
    Matrix m1 = new Matrix(new double[][]{
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4}
    });
    Matrix m2 = new Matrix(new double[][]{
        {2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1}
    });
    Matrix result = Matrix.add(m1, m2);
    for (int i = 0; i < m1.getRows(); i++) {
      for (int j = 0; j < m1.getColumns(); j++) {
        assertEquals(5.5, result.getData()[i][j]);
      }
    }
  }

  @Test
  @DisplayName("Add bad matrix to a matrix (static)")
  void add_badMatrix_static(){
    Matrix matrix = new Matrix(new double[][]{
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4}
    });
    Matrix badMatrix = new Matrix(new double[][]{
        {2.1,2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1,2.1}
    });
    assertThrows(IllegalArgumentException.class, () -> Matrix.add(matrix, badMatrix));
    assertThrows(IllegalArgumentException.class, () -> Matrix.add(badMatrix, matrix));
  }

  @Test
  @DisplayName("Multiply a scalar to a matrix (static)")
  void multiply_scalar_static(){
    Matrix matrix = new Matrix(new double[][]{
        {1,1,1,1},
        {1,1,1,1},
        {1,1,1,1}
    });
    double scalar = 5.3;
    Matrix result = Matrix.multiply(matrix, scalar);
    for (int i = 0; i < matrix.getRows(); i++) {
      for (int j = 0; j < matrix.getColumns(); j++) {
        assertEquals(5.3, result.getData()[i][j]);
      }
    }
  }

  @Test
  @DisplayName("Multiply a matrix to a matrix (static)")
  void multiply_matrix_static(){
    Matrix m1 = new Matrix(new double[][]{
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4}
    });
    Matrix m2 = new Matrix(new double[][]{
        {2.1,2.1},
        {2.1,2.1},
        {2.1,2.1},
        {2.1,2.1}
    });
    Matrix result = Matrix.multiply(m1, m2);
    for (int i = 0; i < result.getRows(); i++) {
      for (int j = 0; j < result.getColumns(); j++) {
        assertEquals(28.56, result.getData()[i][j]);
      }
    }
  }

  @Test
  @DisplayName("Multiply bad matrix to a matrix (static)")
  void multiply_badMatrix_static(){
    Matrix matrix = new Matrix(new double[][]{
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4}
    });
    Matrix badMatrix = new Matrix(new double[][]{
        {2.1,2.1,2.1, 2.1},
        {2.1,2.1,2.1, 2.1},
        {2.1,2.1,2.1, 2.1},
        {2.1,2.1,2.1, 2.1},
        {2.1,2.1,2.1, 2.1}
    });
    assertThrows(IllegalArgumentException.class, () -> Matrix.multiply(matrix, badMatrix));
    assertThrows(IllegalArgumentException.class, () -> Matrix.multiply(badMatrix, matrix));
  }

  @Test
  @DisplayName("Multiply each component of a matrix to a matrix (static)")
  void multiplyEachComponent_matrix_static(){
    Matrix m1 = new Matrix(new double[][]{
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4}
    });
    Matrix m2 = new Matrix(new double[][]{
        {2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1}
    });
    Matrix result = Matrix.multiplyEachComponent(m1, m2);
    for (int i = 0; i < m1.getRows(); i++) {
      for (int j = 0; j < m1.getColumns(); j++) {
        assertEquals(7.14, result.getData()[i][j]);
      }
    }
  }

  @Test
  @DisplayName("Multiply each component of a bad matrix to a matrix (static)")
  void multiplyEachComponent_badMatrix_static(){
    Matrix matrix = new Matrix(new double[][]{
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4}
    });
    Matrix badMatrix = new Matrix(new double[][]{
        {2.1,2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1,2.1}
    });
    assertThrows(IllegalArgumentException.class, () -> Matrix.multiplyEachComponent(matrix, badMatrix));
    assertThrows(IllegalArgumentException.class, () -> Matrix.multiplyEachComponent(badMatrix, matrix));
  }

  @Test
  @DisplayName("Transpose (static)")
  void transpose_static(){
    Matrix matrix = new Matrix(new double[][]{
        {1,2,3,4,5},
        {6,7,8,9,10},
    });
    Matrix expectation = new Matrix(new double[][]{
        {1,6},
        {2,7},
        {3,8},
        {4,9},
        {5,10}
    });
    assertEquals(expectation, Matrix.transpose(matrix));
  }

  @Test
  @DisplayName("Add a scalar to a matrix")
  void add_scalar(){
    Matrix matrix = new Matrix(new double[][]{
        {1,1,1,1},
        {1,1,1,1},
        {1,1,1,1}
    });
    double scalar = 5.3;
    matrix.add(scalar);
    for (int i = 0; i < matrix.getRows(); i++) {
      for (int j = 0; j < matrix.getColumns(); j++) {
        assertEquals(6.3, matrix.getData()[i][j]);
      }
    }
  }

  @Test
  @DisplayName("Add a matrix to a matrix")
  void add_matrix(){
    Matrix m1 = new Matrix(new double[][]{
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4}
    });
    Matrix m2 = new Matrix(new double[][]{
        {2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1}
    });
    m1.add(m2);
    for (int i = 0; i < m1.getRows(); i++) {
      for (int j = 0; j < m1.getColumns(); j++) {
        assertEquals(5.5, m1.getData()[i][j]);
      }
    }
  }

  @Test
  @DisplayName("Add bad matrix to a matrix")
  void add_badMatrix(){
    Matrix matrix = new Matrix(new double[][]{
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4}
    });
    Matrix badMatrix = new Matrix(new double[][]{
        {2.1,2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1,2.1}
    });
    assertThrows(IllegalArgumentException.class, () -> matrix.add(badMatrix));
    assertThrows(IllegalArgumentException.class, () -> badMatrix.add(matrix));
  }

  @Test
  @DisplayName("Multiply a scalar to a matrix")
  void multiply_scalar(){
    Matrix matrix = new Matrix(new double[][]{
        {1,1,1,1},
        {1,1,1,1},
        {1,1,1,1}
    });
    double scalar = 5.3;
    matrix.multiply(scalar);
    for (int i = 0; i < matrix.getRows(); i++) {
      for (int j = 0; j < matrix.getColumns(); j++) {
        assertEquals(5.3, matrix.getData()[i][j]);
      }
    }
  }

  @Test
  @DisplayName("Multiply a matrix to a matrix")
  void multiply_matrix(){
    Matrix m1 = new Matrix(new double[][]{
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4}
    });
    Matrix m2 = new Matrix(new double[][]{
        {2.1,2.1},
        {2.1,2.1},
        {2.1,2.1},
        {2.1,2.1}
    });
    m1.multiply(m2);
    for (int i = 0; i < m1.getRows(); i++) {
      for (int j = 0; j < m1.getColumns(); j++) {
        assertEquals(28.56, m1.getData()[i][j]);
      }
    }
  }

  @Test
  @DisplayName("Multiply bad matrix to a matrix")
  void multiply_badMatrix(){
    Matrix matrix = new Matrix(new double[][]{
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4}
    });
    Matrix badMatrix = new Matrix(new double[][]{
        {2.1,2.1,2.1, 2.1},
        {2.1,2.1,2.1, 2.1},
        {2.1,2.1,2.1, 2.1},
        {2.1,2.1,2.1, 2.1},
        {2.1,2.1,2.1, 2.1}
    });
    assertThrows(IllegalArgumentException.class, () -> matrix.multiply(badMatrix));
    assertThrows(IllegalArgumentException.class, () -> badMatrix.multiply(matrix));
  }

  @Test
  @DisplayName("Multiply each component of a matrix to a matrix")
  void multiplyEachComponent_matrix(){
    Matrix m1 = new Matrix(new double[][]{
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4}
    });
    Matrix m2 = new Matrix(new double[][]{
        {2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1}
    });
    m1.multiplyEachComponent(m2);
    for (int i = 0; i < m1.getRows(); i++) {
      for (int j = 0; j < m1.getColumns(); j++) {
        assertEquals(7.14, m1.getData()[i][j]);
      }
    }
  }

  @Test
  @DisplayName("Multiply each component of a bad matrix to a matrix")
  void multiplyEachComponent_badMatrix(){
    Matrix matrix = new Matrix(new double[][]{
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4},
        {3.4,3.4,3.4,3.4}
    });
    Matrix badMatrix = new Matrix(new double[][]{
        {2.1,2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1,2.1},
        {2.1,2.1,2.1,2.1,2.1}
    });
    assertThrows(IllegalArgumentException.class, () -> matrix.multiplyEachComponent(badMatrix));
    assertThrows(IllegalArgumentException.class, () -> badMatrix.multiplyEachComponent(matrix));
  }

  @Test
  @DisplayName("Transpose")
  void transpose(){
    Matrix matrix = new Matrix(new double[][]{
        {1,2,3,4,5},
        {6,7,8,9,10},
    });
    Matrix expectation = new Matrix(new double[][]{
        {1,6},
        {2,7},
        {3,8},
        {4,9},
        {5,10}
    });
    matrix.transpose();
    assertEquals(expectation, matrix);
  }
}