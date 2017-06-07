package logic.strategy.backTesting;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Mark.W on 2017/6/7.
 * 双均线策略
 */
@Service("2")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DoubleMaStrategy implements IStrategy {

    @Override
    public ArrayList<String> getRebalancedStockCodes(StockPool stockPool,ArrayList<LogicHoldingStock> holdingStocks, int holdingStockNum, String beforeDate, ArrayList<String> dates) {


        return null;
    }
}
