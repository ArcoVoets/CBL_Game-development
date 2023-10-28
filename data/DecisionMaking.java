package data;

/**
 * DecisionMaking class.
 */
public class DecisionMaking {

    public static boolean chooseToEat(Creature creature) {
        if (creature.getStatsContainer().getEnergy().getValue() < 10) {
            return true;
        }
        return false;
    }

    static Creature chooseCreature(Creature creature,
        Creature[] otherAliveWorldCreatures, double[][] decisionMatrix,
        double[] criterionWeights, boolean[] hasPositiveImpact) {

        double[][] normalizedDecisionMatrix = normalizeMatrix(decisionMatrix);

        double[][] weightedDecisionMatrix = getWeightedMatrix(
            normalizedDecisionMatrix, criterionWeights);

        ExtremeAlternatives extremeAlternatives = getExtremeAlternatives(
            weightedDecisionMatrix, hasPositiveImpact);

        EuclideanDistances euclideanDistances = getEuclideanDistances(
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

    private static double[][] getWeightedMatrix(double[][] matrix,
        double[] weights) {
        double[][] weightedMatrix = new double[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                weightedMatrix[row][col] = matrix[row][col]
                    * weights[col];
            }
        }
        return weightedMatrix;
    }

    private static ExtremeAlternatives getExtremeAlternatives(
        double[][] matrix, boolean[] hasPositiveImpact) {
        double[] bestAlternatives = new double[matrix[0].length];
        double[] worstAlternatives = new double[matrix[0].length];
        for (int col = 0; col < matrix[0].length; col++) {
            double best = matrix[0][col];
            double worst = matrix[0][col];
            for (int row = 1; row < matrix.length; row++) {
                if (hasPositiveImpact[col] && matrix[row][col] > best
                    || !hasPositiveImpact[col] && matrix[row][col] < best) {
                    best = matrix[row][col];
                }
                if (hasPositiveImpact[col] && matrix[row][col] < worst
                    || !hasPositiveImpact[col] && matrix[row][col] > worst) {
                    worst = matrix[row][col];
                }
            }
            bestAlternatives[col] = best;
            worstAlternatives[col] = worst;
        }
        return new ExtremeAlternatives(bestAlternatives, worstAlternatives);
    }

    private static EuclideanDistances getEuclideanDistances(double[][] matrix,
        ExtremeAlternatives extremeAlternatives) {
        double[] euclideanDistancesToBestAlternative = new double[matrix.length];
        double[] euclideanDistancesToWorstAlternative = new double[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            double sumOfSquaredDistancesToBestAlternative = 0.0;
            double sumOfSquaredDistancesToWorstAlternative = 0.0;
            for (int col = 0; col < matrix[0].length; col++) {
                sumOfSquaredDistancesToBestAlternative += Math
                    .pow(matrix[row][col]
                        - extremeAlternatives.bestAlternatives[col], 2.0);
                sumOfSquaredDistancesToWorstAlternative += Math
                    .pow(matrix[row][col]
                        - extremeAlternatives.worstAlternatives[col], 2.0);
            }
            euclideanDistancesToBestAlternative[row] = Math
                .sqrt(sumOfSquaredDistancesToBestAlternative);
            euclideanDistancesToWorstAlternative[row] = Math
                .sqrt(sumOfSquaredDistancesToWorstAlternative);
        }
        return new EuclideanDistances(euclideanDistancesToBestAlternative,
            euclideanDistancesToWorstAlternative);
    }

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

    public static Creature[] getOtherAliveWorldCreatures(
        Creature[] worldCreatures,
        Creature creature) {
        int numAliveWorldCreatures = 0;
        for (Creature worldCreature : worldCreatures) {
            if (!worldCreature.isDead() && worldCreature != creature) {
                numAliveWorldCreatures++;
            }
        }

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
