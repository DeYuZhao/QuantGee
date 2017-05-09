package service.stock;

import bean.Histogram;
import vo.*;

/**
 * Created by Mark.W on 2017/5/5.
 */
public interface MarketInfoService {
    /**
     * 获取K线图的输入
     * @param kLineForm K线图的输入（股票代码、日期）
     * @return 返回K线图的对象（包含K线图的所有数据）
     */
    public KLineVO getKLine(KLineInputVO kLineForm);

    /**
     * 获取均线图的输入
     * @param averageLineForm 均线图的输入（股票代码、日期）
     * @return 返回均线的对象（包含均线的所有数据）
     */
    public AverageLineVO getAverageLine(AverageLineForm averageLineForm);

    /**
     * 获取直方图的输入
     * @param histogramForm 直方图的输入（日期）
     * @return 返回直方图的对象（包含直方图的所有数据）
     */
    public Histogram getHistogram(HistogramForm histogramForm);

}
