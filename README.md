# Hyperskill.org - Numeric Matrix Processor

My solution for the hyperskill.org problem: Numeric Matrix Processor

https://hyperskill.org/projects/87

Solution splitted into branches for each stage

## Stage 1

Two matrix addition

input
```
4 5        # first matrix dimensions
1 2 3 4 5  # first matrix row by row
3 2 3 2 1
8 0 9 9 1
1 3 4 5 6
4 5        # second matrix dimensions
1 1 4 4 5  # second matrix row by row
4 4 5 7 8
1 2 3 9 8
1 0 0 0 1
```
output
```
5 2
2 6
```

## Stage 2

Multiplication matrix by a scalar

input
```
3 3   # matrix dimensions
1 2 3 # matrix row by row
4 5 6
7 8 9
3     # scalar
```
output
```
22 55
33 77
```

## Stage 3

Multiply the matrix with another matrix

```
1. Add matrices
2. Multiply matrix by a constant
3. Multiply matrices
0. Exit
Your choice: > 3
Enter size of first matrix: > 3 3
Enter first matrix:
> 1 7 7
> 6 6 4
> 4 2 1
Enter size of second matrix: > 3 3
Enter second matrix:
> 3 2 4
> 5 5 9
> 8 0 10
The result is:
94 37 137
80 42 118
30 18 44

1. Add matrices
2. Multiply matrix by a constant
3. Multiply matrices
0. Exit
Your choice: > 0
```

## Stage 4

Transposition of a matrix

```
1. Add matrices
2. Multiply matrix by a constant
3. Multiply matrices
4. Transpose matrix
0. Exit
Your choice: > 4

1. Main diagonal
2. Side diagonal
3. Vertical line
4. Horizontal line
Your choice: > 1
Enter matrix size: > 3 3
Enter matrix:
> 1 7 7
> 6 6 4
> 4 2 1
The result is:
1 6 4
7 6 2
7 4 1

1. Add matrices
2. Multiply matrix by a constant
3. Multiply matrices
4. Transpose matrix
0. Exit
Your choice: > 0
```

## Stage 5

Finding the determinant of a matrix

```
1. Add matrices
2. Multiply matrix by a constant
3. Multiply matrices
4. Transpose matrix
5. Calculate a determinant
0. Exit
Your choice: > 5
Enter matrix size: > 3 3
Enter matrix:
> 1 7 7
> 6 6 4
> 4 2 1
The result is:
-16

1. Add matrices
2. Multiply matrix by a constant
3. Multiply matrices
4. Transpose matrix
5. Calculate a determinant
0. Exit
Your choice: > 5
Enter matrix size: > 5 5
Enter matrix:
> 1 2 3 4 5
> 4 5 6 4 3
> 0 0 0 1 5
> 1 3 9 8 7
> 5 8 4 7 11
The result is:
191

1. Add matrices
2. Multiply matrix by a constant
3. Multiply matrices
4. Transpose matrix
5. Calculate a determinant
0. Exit
Your choice: > 0
```

## Stage 6

Finding the inverse of a matrix

```
1. Add matrices
2. Multiply matrix by a constant
3. Multiply matrices
4. Transpose matrix
5. Calculate a determinant
6. Inverse matrix
0. Exit
Your choice: > 6
Enter matrix size: > 3 3
Enter matrix:
> 2 -1 0
> 0 1 2
> 1 1 0
The result is:
 0.33   0  0.33
-0.33   0  0.66
 0.16 0.5 -0.33

1. Add matrices
2. Multiply matrix by a constant
3. Multiply matrices
4. Transpose matrix
5. Calculate a determinant
6. Inverse matrix
0. Exit
Your choice: > 0
```
