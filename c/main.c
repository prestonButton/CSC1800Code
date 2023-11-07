
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define GRID_SIZE 5
#define PATTERN_SIZE (GRID_SIZE * GRID_SIZE)
#define CARD_SIZE (GRID_SIZE * GRID_SIZE)
#define MAX_NUMBERS 75
#define MAX_LINE_LENGTH 226

void rotate(char *grid[GRID_SIZE][GRID_SIZE])
{
    char *temporary[GRID_SIZE][GRID_SIZE];
    int i, j;

    for (i = 0; i < GRID_SIZE; i++)
    {
        for (j = 0; j < GRID_SIZE; j++)
        {
            temporary[i][j] = grid[i][j];
        }
    }

    for (i = 0; i < GRID_SIZE; i++)
    {
        for (j = 0; j < GRID_SIZE; j++)
        {
            grid[i][j] = temporary[j][GRID_SIZE - 1 - i];
        }
    }
}

int verify(char *pattern[GRID_SIZE][GRID_SIZE], char *card[GRID_SIZE][GRID_SIZE], int count, char *calledNumbers[MAX_NUMBERS])
{
    int valid = 1;
    int contains = 0;
    int last = 0;

    for (int i = 0; i < GRID_SIZE; i++)
    {
        for (int j = 0; j < GRID_SIZE; j++)
        {
            if (atoi(&pattern[i][j]) != 0)
            {
                if (atoi(&card[i][j]) != 0)
                {
                    contains = 0;
                    for (int k = 0; k < count; k++)
                    {
                        if (atoi(&card[i][j]) == atoi(calledNumbers[k]))
                        {
                            contains = 1;
                            if (atoi(calledNumbers[k]) == atoi(calledNumbers[count - 1]))
                            {
                                last = 1;
                            }
                        }
                    }
                }
                if (!contains)
                {
                    valid = 0;
                }
            }
        }
    }

    if (!last)
    {
        valid = 0;
    }

    return valid;
}

int main()
{
    while (!feof(stdin))
    {
        char *pattern[GRID_SIZE][GRID_SIZE];
        char *card[GRID_SIZE][GRID_SIZE];
        char numStr[MAX_LINE_LENGTH];
        char *calledNumbers[MAX_NUMBERS];
        char *oneStrNum;
        int count = 0;
        int type = 0;

        for (int i = 0; i < GRID_SIZE; i++)
        {
            for (int j = 0; j < GRID_SIZE; j++)
            {
                if (fscanf(stdin, "%s", &pattern[i][j]) == EOF)
                    return 0;
                if (atoi(&pattern[i][j]) == 1)
                {
                    type = 1;
                }
                if (atoi(&pattern[i][j]) == 4)
                {
                    type = 4;
                }
            }
        }

        if (fgets(numStr, MAX_LINE_LENGTH, stdin) == NULL)
            return 0;
        if (fgets(numStr, MAX_LINE_LENGTH, stdin) == NULL)
            return 0;
        if (fgets(numStr, MAX_LINE_LENGTH, stdin) == NULL)
            return 0;

        numStr[strlen(numStr)] = 0;

        oneStrNum = strtok(numStr, " ");

        while (oneStrNum != NULL)
        {
            calledNumbers[count] = oneStrNum;
            count++;
            oneStrNum = strtok(NULL, " ");
        }

        for (int i = 0; i < GRID_SIZE; i++)
        {
            for (int j = 0; j < GRID_SIZE; j++)
            {
                if (fscanf(stdin, "%s", &card[i][j]) == EOF)
                    return 0;
            }
        }

        if (type == 1)
        {
            if (verify(pattern, card, count, calledNumbers))
            {
                printf("VALID BINGO\n");
            }
            else
            {
                printf("NO BINGO\n");
            }
        }
        else if (type == 4)
        {
            int verifiedCrazy = 0;
            int verifiedCounter = 0;

            while (verifiedCounter < 4 && verifiedCrazy == 0)
            {
                if (verify(pattern, card, count, calledNumbers))
                {
                    verifiedCrazy = 1;
                }
                else
                {
                    rotate(pattern);
                }
                verifiedCounter++;
            }

            if (verifiedCrazy == 1)
            {
                printf("VALID BINGO\n");
            }
            else
            {
                printf("NO BINGO\n");
            }
        }
        else
        {
            printf("NO BINGO\n");
        }
    }

    return 0;
}
