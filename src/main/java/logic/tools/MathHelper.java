package logic.tools;


import java.text.DecimalFormat;

/**
 * 数学统计量计算的类
 * Created by Mark.W on 2017/3/6.
 */
public class MathHelper {

    /**
     * 计算方差
     * @param data 数据
     * @return 方差
     */
    public static double variance(double[] data) {
        double average = average(data);
        double sum = 0;

        for(int i=0; i<data.length; ++i) {
            sum += Math.pow(data[i]-average, 2);
        }
        return sum/data.length;
    }

    /**
     * 计算协方差
     * @param data1 数据
     * @param data2 数据
     * @return 方差
     */
    public static double covariance(double[] data1, double[] data2) {
        double average1 = average(data1);
        double average2 = average(data2);
        double cov;

        if(data1.length <= data2.length) {
            for(int i=0; i<data1.length; ++i) {
                data1[i] = data1[i] * data2[i];
            }
            cov = average(data1) - average1 * average2;
        } else {
            for(int i=0; i<data2.length; ++i) {
                data2[i] = data1[i] * data2[i];
            }
            cov = average(data2) - average1 * average2;
        }

        return cov;
    }


    public static double average(double[] data) {
        double sum = 0;
        for(int i=0; i<data.length; ++i) {
            sum += data[i];
        }
        return sum/data.length;
    }

    /**
     * 数字格式化
     * @param d 数字
     * @param num 精确到几位小数 默认两位小数
     * @return double
     */
    public static double formatData(double d, int num) {
        DecimalFormat df;

        if(num == 3) {
            df = new DecimalFormat("#.000");
        } else if(num == 4) {
            df = new DecimalFormat("#.0000");
        } else {
            df = new DecimalFormat("#.00");
        }

        return Double.parseDouble(df.format(d));
    }
}
