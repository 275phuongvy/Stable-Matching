# Stable Marriage Matching (Backtracking)

Author: Vy Phuong Tran

The program is to employ a backtracking design which involves making a series of decisions/choices and making a forward move after each such decision and then eventually returning to each previous point where a decision was previously made to cancel the decision/choice in making a different decision/choice to move forward all over again in a different direction in either finding all solutions or finding just one solution, whichever is the case.

PROBLEM:

We have a set of N men and N women. And, we have two input matrices of their respective preferences. We want to find out all possible matchings of these people (eight men and as many women) subject to just one constraint. The only one constraint is that: if man-A and women-B are to be matched as a potential married couple, then it is necessary that (a) there is no married woman-W such that this woman-W prefers this man-A to her own husband and man-A also prefers this woman-W to his proposed wife woman-B and (b) there is no married man-M such that this man-M prefers this woman-B to his own wife and woman-B also prefers this man-M to her proposed husband man-A.

INPUTS:  

Men's Preference Matrix (womanmr[m,r] represents a woman who is the preference #r of man #m). 

Women's Preference Matrix (manwr[w,r] represents a man who is the preference #r of woman #w. 

OUTPUTS:

The optimal solution of men and women is printed out

DEVIATIONS: The difference between the first choice and the optimal choice of each man/woman

