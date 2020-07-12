package chapter2;

import java.util.List;

/** 分類器のテストを行うメインプログラム(1)　1個のテストデータを分類する */

public class TestClassify1 {

    public static void main(String[] args) {
        // 学習データとテストデータの準備
        // ファイル内の末尾のデータをテストデータとし、その他のデータを学習データとする
        LearningDataSet dataSet = LearningDataSet.readFromFile("person_data.txt");
        List<LabeledVector> lvList = dataSet.labeledVectorList;
        List<LabeledVector> trainingDataList = lvList.subList(0, lvList.size() - 1);
        LabeledVector testData = lvList.get(lvList.size() - 1);

        // 分類器の初期化
        Classifier classifier = new NaiveBayesClassifier();
        // Classifier classifier = new SVMClassifier();
        // Classifier classifier = new NeuralClassifier();

        // 学習データを用いた学習
        classifier.train(trainingDataList, dataSet.maxLabel, dataSet.maxFeature);

        // テストデータへの適用
        int c = classifier.classify(testData.featureVector);
        System.out.println("名前=" + testData.name + "\t正解ラベル=" + testData.label + "\t分類結果=" + c);
    }
}
