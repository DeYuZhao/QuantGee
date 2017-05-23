package data.tradeData;

import DAO.tradeDAO.TradeDAO;
import bean.HoldingStock;
import bean.Trade;
import bean.UserMoney;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import po.HoldingStocksPO;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wangxue on 2017/5/5.
 */
@Transactional
public class TradeData implements TradeDAO{

    private HibernateTemplate hibernateTemplate;
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public boolean addTradeInfo(Trade trade) {
        hibernateTemplate.save(trade);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public Iterator<Trade> getUserTradeList(String userID) {
        Iterator<Trade> iterator = (Iterator<Trade>) hibernateTemplate.find("from Trade t where t.userId =?",userID).iterator();
        return iterator;
    }

    @Override
    public HoldingStocksPO getHoldingStocks(String userID) {
        Iterator<HoldingStock> holdingStockIterator = (Iterator<HoldingStock>) hibernateTemplate.find("from HoldingStock h where h.userId =?",userID).iterator();
        UserMoney userMoney = hibernateTemplate.get(UserMoney.class, userID);
        double money = -1;
        if(userMoney != null){
            money = userMoney.getRemainMoney();
        }
        HoldingStocksPO holdingStocksPO = new HoldingStocksPO(userID, holdingStockIterator, money);
        return holdingStocksPO;
    }

    @Override
    public boolean updateHoldingStock(HoldingStock holdingStock) {
        int newNum = holdingStock.getHoldNum();
        String stockID = holdingStock.getStockId();
        String userID = holdingStock.getUserId();

        Iterator<HoldingStock> iterator =(Iterator<HoldingStock>) hibernateTemplate.find("from HoldingStock  h where h.userId = ?",userID).iterator();

        while (iterator.hasNext()){
            HoldingStock holdingStock1 = iterator.next();
            if(holdingStock1.getStockId() == stockID){
                int oriNum = holdingStock1.getHoldNum();
                if(newNum+oriNum<0){
                    return false;
                }
                holdingStock1.setHoldNum(newNum+oriNum);
                hibernateTemplate.update(holdingStock1);
                return true;
            }
        }

        hibernateTemplate.save(holdingStock);
        hibernateTemplate.flush();
        return true;
    }

    @Override
    public boolean updateUserMoney(UserMoney userMoney) {
        hibernateTemplate.saveOrUpdate(userMoney);
        return true;
    }

}
