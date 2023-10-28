package data;

/**
 * DecisionMaking class.
 */
public class DecisionMaking {

    /**
     * Chooses whether to eat or not to eat (pair).
     * 
     * @param creature The creature for which to choose the action to be
     *            executed
     * @return True if the creature should eat, false if it should not eat
     *         (pair)
     */
    public static boolean chooseToEat(Creature creature) {
        if (creature.getStatsContainer().getEnergy().getValue() < 10) {
            return true;
        }
        return false;
    }

    /**
     * Choose creature to perform action with.
     * 
     * @param creature The creature which is going to execute the action
     * @param otherAliveWorldCreatures The other alive world creatures (i.e. all
     *            the alive world creatures except the creature which is going
     *            to execute the action)
     * @param decisionMatrix The decision matrix, where each row represents a
     *            alternative (i.e. creature) and each column represents a
     *            criterion
     * @param criterionWeights The weights of the criteria
     * @param hasPositiveImpact The impact of the criteria (true if the criteria
     *            has positive impact, false if it has negative impact)
     * @return The creature to perform action with
     */
    static Creature chooseCreature(Creature creature,
        Creature[] otherAliveWorldCreatures, double[][] decisionMatrix,
        double[] criterionWeights, boolean[] hasPositiveImpact) {

        double[][] normalizedDecisionMatrix = normalizeMatrix(decisionMatrix);

        double[][] weightedDecisionMatrix = calculateWeightedMatrix(
            normalizedDecisionMatrix, criterionWeights);

        ExtremeAlternatives extremeAlternatives = calculateExtremeAlternatives(
            weightedDecisionMatrix, hasPositiveImpact);

        EuclideanDistances euclideanDistances = calculateEuclideanDistances(
            weightedDecisionMatrix, extremeAlternatives);

        double[] topsisRanking = calculateTopsisRanking(weightedDecisionMatrix,
            euclideanDistances);

        // select best option
        double bestAlternative = topsisRanking[0];
        int index = 0;
        for (int alternative = 1; alternative < otherAliveWorldCreatures.length; alternative++) {
            if (topsisRanking[alternative] > bestAlternative) {
                bestAlternative = topsisRanking[alternative];
                index = alternative;
            }
        }
        return otherAliveWorldCreatures[index];

    }

    /**
     * Choose creature to eat.
     * 
     * @param creature The creature which is going to eat
     * @param worldCreatures The world creatures
     * @return The creature to eat
     */
    public static Creature creatureToEat(Creature creature,
        Creature[] worldCreatures) {

        Creature[] otherAliveWorldCreatures = getOtherAliveWorldCreatures(
            worldCreatures, creature);
        int numCriteria = 2;
        double[][] decisionMatrix = new double[otherAliveWorldCreatures.length][numCriteria];
        int j = 0;
        for (Creature worldCreature : otherAliveWorldCreatures) {
            decisionMatrix[j][0] = worldCreature
                .getCodesContainer().calculateCodesScore();
            decisionMatrix[j][1] = worldCreature
                .getStatsContainer().getEnergy().getValue();
            j++;
        }
        boolean[] hasPositiveImpact = new boolean[] {
            false, true };
        double[] criterionWeights = new double[] {
            0.5, 0.5 };

        Creature creatureToEat = chooseCreature(creature,
            otherAliveWorldCreatures, decisionMatrix, criterionWeights,
            hasPositiveImpact);

        return creatureToEat;
    }

    /**
     * Choose creature to pair.
     * 
     * @param creature The creature which is going to pair
     * @param worldCreatures The world creatures
     * @return The creature to pair with
     */
    public static Creature creatureToPair(Creature creature,
        Creature[] worldCreatures) {

        int numCriteria = 3;
        Creature[] otherAliveWorldCreatures = getOtherAliveWorldCreatures(
            worldCreatures, creature);
        double[][] decisionMatrix = new double[otherAliveWorldCreatures.length][numCriteria];
        int j = 0;
        for (Creature worldCreature : otherAliveWorldCreatures) {
            decisionMatrix[j][0] = worldCreature
                .getCodesContainer().averageColdResistance;
            decisionMatrix[j][1] = worldCreature
                .getCodesContainer().averageHeatResistance;
            decisionMatrix[j][2] = worldCreature
                .getCodesContainer().averageLightSensitivity;
            j++;
        }
        boolean[] hasPositiveImpact = new boolean[] {
            false, true, true };
        double[] criterionWeights = {
            0.25, 0.25, 0.25 };

        Creature creatureToPair = chooseCreature(creature,
            otherAliveWorldCreatures, decisionMatrix, criterionWeights,
            hasPositiveImpact);
        return creatureToPair;
    }

    /**
     * Normalizes the decision matrix.
     * 
     * @param decisionMatrix The decision matrix
     * @return The normalized decision matrix
     */
    private static double[][] normalizeMatrix(double[][] decisionMatrix) {
        double[][] normalizedMatrix = new double[decisionMatrix.length][decisionMatrix[0].length];
        for (int column = 0; column < decisionMatrix[0].length; column++) {
            double sumOfColumn = 0.0;
            for (int row = 0; row < decisionMatrix.length; row++) {
                sumOfColumn += Math.pow(decisionMatrix[row][column], 2.0);
            }
            for (int row = 0; row < decisionMatrix.length; row++) {
                normalizedMatrix[row][column] = decisionMatrix[row][column]
                    / Math.sqrt(sumOfColumn);
            }
        }
        return normalizedMatrix;
    }

    /**
     * Calculates the weighted matrix, based on the criteria weights.
     * 
     * @param matrix The matrix to be weighted
     * @param weights The weights of the criteria
     * @return The weighted matrix
     */
    private static double[][] calculateWeightedMatrix(double[][] matrix,
        double[] weights) {
        double[][] weightedMatrix = new double[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                weightedMatrix[row][column] = matrix[row][column]
                    * weights[column];
            }
        }
        return weightedMatrix;
    }

    /**
     * Calculates the best impact of a criterion.
     * 
     * @param row The row of the item in the matrix to compare best with
     * @param column The column of the item in the matrix to compare best with
     * @param best The best value of the criterion so far
     * @param matrix The matrix from which to calculate the best impact
     * @param hasPositiveImpact The impact of the criteria (true if the criteria
     *            has positive impact, false if it has negative impact)
     * @return The best impact of the criterion, that is matrix[row][column] if
     *         matrix[row][column] is better than best, otherwise best
     */
    private static double calculateBestImpact(int row, int column, double best,
        double[][] matrix, boolean[] hasPositiveImpact) {
        if (hasPositiveImpact[column] && matrix[row][column] > best
            || !hasPositiveImpact[column] && matrix[row][column] < best) {
            best = matrix[row][column];
        }
        return best;
    }

    /**
     * Calculates the worst impact of a criterion.
     * 
     * @param row The row of the item in the matrix to compare worst with
     * @param column The column of the item in the matrix to compare worst with
     * @param worst The worst value of the criterion so far
     * @param matrix The matrix from which to calculate the worst impact
     * @param hasPositiveImpact The impact of the criteria (true if the criteria
     *            has positive impact, false if it has negative impact)
     * @return The worst impact of the criterion, that is matrix[row][column] if
     *         matrix[row][column] is worse than worst, otherwise worst
     */
    private static double calculateWorstImpact(int row, int column,
        double worst,
        double[][] matrix, boolean[] hasPositiveImpact) {
        if (hasPositiveImpact[column] && matrix[row][column] < worst
            || !hasPositiveImpact[column] && matrix[row][column] > worst) {
            worst = matrix[row][column];
        }
        return worst;
    }

    /**
     * Gets the best and worst alternatives.
     * 
     * @param matrix The matrix from which to calculate the best and worst
     * @param hasPositiveImpact The impact of the criteria (true if the criteria
     *            has positive impact, false if it has negative impact)
     * @return The best and worst alternatives, as an ExtremeAlternatives object
     */
    private static ExtremeAlternatives calculateExtremeAlternatives(
        double[][] matrix, boolean[] hasPositiveImpact) {
        double[] bestAlternatives = new double[matrix[0].length];
        double[] worstAlternatives = new double[matrix[0].length];
        for (int column = 0; column < matrix[0].length; column++) {
            double best = matrix[0][column];
            double worst = matrix[0][column];
            for (int row = 1; row < matrix.length; row++) {
                best = calculateBestImpact(row, column, best, matrix,
                    hasPositiveImpact);
                worst = calculateWorstImpact(row, column, worst, matrix,
                    hasPositiveImpact);
            }
            bestAlternatives[column] = best;
            worstAlternatives[column] = worst;
        }
        return new ExtremeAlternatives(bestAlternatives, worstAlternatives);
    }

    /**
     * Calculates the euclidean distances to the best and worst alternatives.
     * 
     * @param matrix The matrix from which to calculate the euclidean distances
     * @param extremeAlternatives The best and worst alternatives, as an
     *            ExtremeAlternatives object
     * @return The euclidean distances to the best and worst alternatives, as an
     *         EuclideanDistances object
     */
    private static EuclideanDistances calculateEuclideanDistances(
        double[][] matrix,
        ExtremeAlternatives extremeAlternatives) {
        double[] euclideanDistancesToBestAlternative = new double[matrix.length];
        double[] euclideanDistancesToWorstAlternative = new double[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            double sumOfSquaredDistancesToBestAlternative = 0.0;
            double sumOfSquaredDistancesToWorstAlternative = 0.0;
            for (int column = 0; column < matrix[0].length; column++) {
                sumOfSquaredDistancesToBestAlternative += Math
                    .pow(matrix[row][column]
                        - extremeAlternatives.bestAlternatives[column], 2.0);
                sumOfSquaredDistancesToWorstAlternative += Math
                    .pow(matrix[row][column]
                        - extremeAlternatives.worstAlternatives[column], 2.0);
            }
            euclideanDistancesToBestAlternative[row] = Math
                .sqrt(sumOfSquaredDistancesToBestAlternative);
            euclideanDistancesToWorstAlternative[row] = Math
                .sqrt(sumOfSquaredDistancesToWorstAlternative);
        }
        return new EuclideanDistances(euclideanDistancesToBestAlternative,
            euclideanDistancesToWorstAlternative);
    }

    /**
     * Calculates the TOPSIS ranking based on the euclidean distances to the
     * best and worst alternatives.
     * 
     * @param matrix The matrix from which to calculate the TOPSIS ranking
     * @param euclideanDistances The euclidean distances to the best and worst,
     *            as an EuclideanDistances object
     * @return The TOPSIS ranking
     */
    private static double[] calculateTopsisRanking(double[][] matrix,
        EuclideanDistances euclideanDistances) {
        double[] topsisRanking = new double[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            topsisRanking[row] = euclideanDistances.euclideanDistancesToWorstAlternative[row]
                / (euclideanDistances.euclideanDistancesToWorstAlternative[row]
                    + euclideanDistances.euclideanDistancesToBestAlternative[row]);
        }
        return topsisRanking;
    }

    /**
     * Gets the number of other alive world creatures.
     * 
     * @param worldCreatures The world creatures
     * @param creature The creature for which to get the number of other alive
     *            world creatures
     * @return The number of other alive world creatures
     */
    public static int getNumOtherAliveWorldCreatures(Creature[] worldCreatures,
        Creature creature) {
        int numAliveWorldCreatures = 0;
        for (Creature worldCreature : worldCreatures) {
            if (!worldCreature.isDead() && worldCreature != creature) {
                numAliveWorldCreatures++;
            }
        }
        return numAliveWorldCreatures;
    }

    /**
     * Gets the other alive world creatures.
     * 
     * @param worldCreatures The world creatures
     * @param creature The creature for which to get the other alive world
     *            creatures
     * @return The other alive world creatures
     */
    public static Creature[] getOtherAliveWorldCreatures(
        Creature[] worldCreatures,
        Creature creature) {
        int numAliveWorldCreatures = getNumOtherAliveWorldCreatures(
            worldCreatures, creature);

        if (numAliveWorldCreatures == 0) {
            return new Creature[] {
                creature.getWorld().getPlayerCreature() }; // player creature
        }

        Creature[] aliveWorldCreatures = new Creature[numAliveWorldCreatures];
        int i = 0;
        for (Creature worldCreature : worldCreatures) {
            if (!worldCreature.isDead && worldCreature != creature) {
                aliveWorldCreatures[i] = worldCreature;
                i++;
            }
        }

        return aliveWorldCreatures;
    }

    private static class ExtremeAlternatives {
        double[] bestAlternatives;
        double[] worstAlternatives;

        ExtremeAlternatives(double[] bestAlternatives,
            double[] worstAlternatives) {
            this.bestAlternatives = bestAlternatives;
            this.worstAlternatives = worstAlternatives;
        }
    }

    private static class EuclideanDistances {
        double[] euclideanDistancesToBestAlternative;
        double[] euclideanDistancesToWorstAlternative;

        EuclideanDistances(double[] euclideanDistancesToBestAlternative,
            double[] euclideanDistancesToWorstAlternative) {
            this.euclideanDistancesToBestAlternative = euclideanDistancesToBestAlternative;
            this.euclideanDistancesToWorstAlternative = euclideanDistancesToWorstAlternative;
        }
    }
}
