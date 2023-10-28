# TOPSIS
TOPSIS is a multi-criteria decision analysis (MCDA) method. In the game this method is used to let the world creatures decide who they eat or with who they pair. In the following, TOPSIS is explained and applied to the game.

## Variables:
- Matrix: $M$
- Alternatives: $A_l$, where $l$ is the $l$'th alternative, with the total number of alternatives equal to $L$.
- Criteria: $C_m$, where $m$ is the $m$'th criterion, with the total number of criteria equal to 3 (the 3 different codes).
- Weight factors: $W_m$, the $m$ is the same as in $C_m$.

## Calculation steps
- Create matrix $M$:
  - Alternatives ($A_1 - A_L$) are the other world creatures (excluding the world creature that is going to perform the action). Let $L$ be the number of alive world creatures, subtracted by one (the creature that's going to perform the action). 
    - In case there is only one world creature, namely the creature that is going to execute the action itself (i.e. $L=0$), the creature is allowed to eat/pair with the player creature. (So the world creature is the alternative). In all other cases, the creature can only eat/pair with the other world creatures.
  - Criterion are the average code values, where the following are positive 
    - Heat resistance and
    - Light sensitivity 
  - And the following have negative impact:
    - Cold resistance
- Normalize the matrix values:
  - $M[A_l][C_m] = \frac{M[A_l][C_m]}{\sqrt{\sum_{i=1}^{L}{(M[A_i][C_m])^2}}}$
- Calculate the weighted matrix
  - Each criterion should have a weight and the sum of the weights should be 1
  - $M[A_l][C_m] = M[A_l][C_m] \cdot W_m$
  - Important: The equation $\sum_{i=1}^{3}{(W_i)} = 1$ should hold.
- For each criterion:
  - Determine the best value of the best and the worst alternatives.
    - For a positive criterion, this means that the best alternative is the highest value, and the worst alternative is the lowest value. For a negative criterion, this is the opposite.
  - Let $H_m$ denote value of the best alternative of criterion $m$.
  - Let $U_m$ denote value of the worst alternative of criterion $m$.
- Calculate for all alternatives $A_l$ the Euclidean distance to the best and worst alternative.
  - Let $D_{l}^{b}$ denote the distance from $A_l$ to the best alternative, defined by $\sqrt{\sum_{m = 1}^{3}(M[l][m] - H[m])^2}$.
  - Let $D_{l}^{w}$ denote the distance from $A_l$ to the worst alternative, defined by $\sqrt{\sum_{m = 1}^{3}(M[l][m] - U[m])^2}$.
- For all alternatives $A_l$, calculate the TOPSIS score $s_l$, defined by $s_l = \frac{D_l^w}{D_l^w + D_l^b}$.
- Find the alternative with the highest score. This is the best alternative according to the TOPSIS method.