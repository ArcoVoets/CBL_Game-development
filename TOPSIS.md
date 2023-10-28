# TOPSIS
TOPSIS is a multi-criteria decision analysis (MCDA) method. In the game this method is used to let the world creatures decide who they eat or with who they pair. In the following, TOPSIS is explained and applied to the game.

## Variables:
- Matrix: $X$
- Alternatives: $A_l$, where $l$ is the $l$'th alternative, with the total number of alternatives equal to $L$.
- Criteria: $C_m$, where $m$ is the $m$'th criterion, with the total number of criteria equal to M.
- Weight factors: $W_m$, the $m$ is the same as in $C_m$.

## Calculation steps
- Create matrix $X$ with $L$ rows (the alternatives) and $M$ columns (the criteria):
  - Alternatives ($A_1 - A_L$) are the other world creatures (excluding the world creature that is going to perform the action). Let $L$ be the number of alive world creatures, subtracted by one (the creature that's going to perform the action). 
    - In case there is only one world creature, namely the creature that is going to execute the action itself (i.e. $L=0$), the creature is allowed to eat/pair with the player creature. (So the world creature is the alternative). In all other cases, the creature can only eat/pair with the other world creatures.
  - Criteria ($C_1-C_M) are the average code values, where $M$ is the number of criteria (In the game, this is 3, for all 3 of the codes)
    - The following have positive impact (i.e. more is better): 
      - Heat resistance and
      - Light sensitivity 
    - And the following have negative impact (i.e. less is better):
      - Cold resistance
- Normalize the matrix values:
  - For all $l$ in $[1,L]$ and for all $m$ in $[1,M]$:
    - $X[A_l][C_m] = \frac{X[A_l][C_m]}{\sqrt{\sum_{i=1}^{L}{(X[A_i][C_m])^2}}}$
- Calculate the weighted matrix:
  - For all $l$ in $[1,L]$ and for all $m$ in $[1,M]$:
    - Each criterion should have a weight and the sum of the weights should be 1
    - $X[A_l][C_m] = X[A_l][C_m] \cdot W_m$
    - Important: The equation $\sum_{i=1}^{3}{(W_i)} = 1$ should hold.
- Determine the best and the worst alternatives:
  - For all $C_m$ with $m$ in $[1,M]$:
    - Determine the best value of the best and the worst alternatives.
      - For a positive criterion, this means that the best alternative is the highest value, and the worst alternative is the lowest value. For a negative criterion, this is the opposite.
  - Let $z_m^+$ denote the value of the best alternative of criterion $m$.
  - Let $z_m^-$ denote the value of the worst alternative of criterion $m$.
- Calculate for all alternatives $A_l$ the Euclidean distance to the best and worst alternative.
  - Let $D_{l}^{+}$ denote the distance from $A_l$ to the best alternative, defined by $\sqrt{\sum_{m = 1}^{3}(X[l][m] - z_m^+)^2}$.
  - Let $D_{l}^{-}$ denote the distance from $A_l$ to the worst alternative, defined by $\sqrt{\sum_{m = 1}^{3}(X[l][m] - z_m^-)^2}$.
- For all alternatives $A_l$, calculate the TOPSIS score $C_l$, defined by $s_l = \frac{D_l^-}{D_l^- + D_l^+}$.
- Find the alternative with the highest score. This is the best alternative according to the TOPSIS method.

## References
- https://www.atlantis-press.com/article/25877.pdf
- https://robertsoczewica.medium.com/what-is-topsis-b05c50b3cd05
- https://en.wikipedia.org/wiki/TOPSIS