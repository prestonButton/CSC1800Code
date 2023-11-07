
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZE 5

int patternSize(int pattern[SIZE][SIZE]) {
    int count = 0;
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            if (pattern[i][j] != 0) {
                count++;
            }
        }
    }
    return count;
}

int isBingoPatternValid(int bingoTable[SIZE][SIZE], int pattern[SIZE][SIZE], int contains[SIZE]) {
    int size = SIZE;
    int contained[SIZE] = {0};
    int checkCount = 0;

    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            if (bingoTable[i][j] == contains[j]) {
                if (pattern[i][j] != 0) {
                    checkCount++;
                    contained[j] = bingoTable[i][j];
                }
            } else if (bingoTable[i][j] == 0 && pattern[i][j] != 0) {
                checkCount++;
            }
        }
    }

    if (checkCount == patternSize(pattern)) {
        if (contains[size - 1] == contained[size - 1]) {
            return 1;
        }
    }
    return 0;
}

void rotateBoard(int bingoTable[SIZE][SIZE]) {
    int rotatedBoard[SIZE][SIZE] = {0};

    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            rotatedBoard[i][j] = bingoTable[SIZE - 1 - j][i];
        }
    }

    memcpy(bingoTable, rotatedBoard, SIZE * SIZE * sizeof(int));
}

int main() {
    int finalResult[SIZE] = {0};
    int gameOver = 0;
    int hasWon = 0;

    while (!gameOver) {
        int pattern[SIZE][SIZE] = {0};
        int bingoTable[SIZE][SIZE] = {0};
        int rotated = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                scanf("%d", &pattern[i][j]);
                if (pattern[i][j] == 4) {
                    hasWon = 1;
                }
            }
        }

        int contains[SIZE] = {0};
        for (int i = 0; i < SIZE; i++) {
            scanf("%d", &contains[i]);
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                scanf("%d", &bingoTable[i][j]);
            }
        }

        if (!hasWon) {
            if (isBingoPatternValid(bingoTable, pattern, contains)) {
                printf("Bingo\n");
            } else {
                printf("Not Bingo\n");
            }
        } else {
            for (int i = 0; i < 4; i++) {
                rotateBoard(bingoTable);
                if (isBingoPatternValid(bingoTable, pattern, contains)) {
                    printf("Bingo\n");
                    rotated = 1;
                    break;
                }
            }
            if (!rotated) {
                printf("Not Bingo\n");
            }
        }

        if (feof(stdin)) {
            gameOver = 1;
        }
    }

    return 0;
}

