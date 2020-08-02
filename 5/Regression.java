/*
抽象回帰を管理するクラス
203302 石山智也
 */

public abstract class Regression {
    protected double a, b, R2, xmean, ymean;
    protected int samples;
    protected double[] variables;//説明変数（計算対象説明変数のみ）
    protected double[] labels;//目的変数（本課題ではカロリー）
    protected double predicted;//目的変数の予測値(サンプル数個)
    protected double variableData;

    public Regression(double[] variables, double[] labels) {
        this.variables = variables;
        this.labels = labels;
        this.samples = labels.length;

        a = b = R2 = xmean = ymean = predicted = 0;
    }

    public abstract void compMean();

    public abstract void doRegression();

    public abstract double computeR2();

    public abstract double calcPredictedValue(double data);

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
}
