package logic.strategy;

import po.StockPO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Mark.W on 2017/3/29.
 * 动量策略
 */
public class MomentumDriveStrategy implements Strategy {

    @Override
    public ArrayList<StockYield> initHoldingStocks(StockPool stockPool) {
        ArrayList<StockYield> stockYields = new ArrayList<>();

        for(int i=0; i<stockPool.getStockInfos().size(); ++i) {
            StockPO before = stockPool.getStockInfos().get(i).getBeforeStockPO();
            StockPO yesterday = stockPool.getStockInfos().get(i).getYesterdayStock();

            if(yesterday == null || before == null) {
                continue;
            }

            //计算收益，昨天的收盘价- returnPeriod天前的收盘价)/ returnPeriod天前的收盘价
            double yield = (yesterday.getADJ()-before.getADJ())/before.getADJ();

            stockYields.add(new StockYield(yesterday.getStockCode(), yield));
        }
        return stockYields;
    }

    @Override
    public ArrayList<StockYield> rebalanceHoldingStocks(StockPool stockPool, Date beforeDate, Date today) {
        ArrayList<StockYield> stockYields = new ArrayList<>();
        for(int i=0; i<stockPool.getStockInfos().size(); ++i) {
            StockPO before = stockPool.getStockInfos().get(i).getStockByDate(beforeDate);
            StockPO yesterday = stockPool.getStockInfos().get(i).getStockByDate(today);

            if(yesterday == null || before == null) {
                continue;
            }
            //计算收益，昨天的收盘价- returnPeriod天前的收盘价)/ returnPeriod天前的收盘价
            double yield = (yesterday.getADJ()-before.getADJ())/before.getADJ();

            stockYields.add(new StockYield(yesterday.getStockCode(), yield));
        }
        return stockYields;
    }
}
