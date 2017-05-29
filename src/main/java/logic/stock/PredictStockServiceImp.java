package logic.stock;

import DAO.stockInfoDAO.QuotaDAO;
import bean.StockPredict;
import logic.tools.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.stock.PredictStockService;
import vo.stock.LineVO;
import vo.stock.StockInputVO;
import vo.stock.StockPredictVO;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Mark.W on 2017/5/21.
 */
@Service
public class PredictStockServiceImp implements PredictStockService {

    @Autowired
    private QuotaDAO quotaDAO;

    @Override
    public StockPredictVO getStockPredictInfo(StockInputVO inputVO) {
        Iterator<StockPredict> stockPredicts = quotaDAO.getPredictData(inputVO.getStockCode(), inputVO.getStartDate(), inputVO.getEndDate());

        ArrayList<LineVO> actualValues = new ArrayList<>();
        ArrayList<LineVO> predictedValues = new ArrayList<>();

        StockPredict temp = null;
        while (stockPredicts.hasNext()) {
            temp = stockPredicts.next();

            //不是今天
            if (!temp.getDate().equals(DateHelper.getNowDate())) {
                predictedValues.add(new LineVO(temp.getDate(), temp.getPredictedPrice()));
                actualValues.add(new LineVO(temp.getDate(), temp.getActualPrice()));
            }
        }

        //此时temp为今天的数据
        StockPredictVO result = new StockPredictVO(inputVO.getStockCode(), temp.getPredictedPrice(),
                temp.getPredictIncrease(), temp.getHistoryDeviation(), actualValues, predictedValues);

        return result;
    }
}
