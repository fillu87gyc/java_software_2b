import static java.lang.StrictMath.sqrt;

/*
回帰クラスを食品のカロリーに注目したクラス
203302 石山智也
*/

public class FoodRegression extends Regression {
    public FoodRegression(double[] variables, double[] labels) {
        super(variables, labels);
        compMean();
        variableData = 0;
    }

    @Override
    public void compMean() {
        double x = 0, y = 0;
        for (int i = 0; i < samples; i++) {
            x += variables[i];
            y += labels[i];
        }
        xmean = x / samples;
        ymean = y / samples;
    }

    @Override
    public void doRegression() {
        double Sxx = 0, Syy = 0, Sxy = 0;
        for (int i = 0; i < samples; i++) {
            Sxx += (variables[i] - xmean) * (variables[i] - xmean);
            Syy += (labels[i] - ymean) * (labels[i] - ymean);
            Sxy += (variables[i] - xmean) * (labels[i] - ymean);
        }
        this.a = Sxy / Sxx;
        this.b = ymean - this.a * xmean;
        this.predicted = a * variableData + b;
        return;
    }

    @Override
    public double computeR2() {
        double predictedMean = 0;
        for (int i = 0; i < samples; i++) {
            predictedMean += this.a * variables[i] + this.b;
        }
        predictedMean /= samples;
        double numerator = 0, denominator = 0, sigmaY = 0, vY = 0;
        for (int i = 0; i < samples; i++) {
            numerator += (labels[i] - ymean) * ((this.a * variables[i] - this.b) - predictedMean);
            sigmaY += (labels[i] - ymean) * (labels[i] - ymean);
            vY += ((this.a * variables[i] + this.b) - predictedMean) *
                    ((this.a * variables[i] + this.b) - predictedMean);
        }
        numerator = numerator * numerator;
        denominator = sigmaY * vY;
        return numerator / denominator;
    }

    @Override
    public double calcPredictedValue(double data) {
        variableData = data;
        doRegression();
        return predicted;
    }
}
