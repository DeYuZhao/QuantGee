package service.strategy;

import vo.strategy.CollectStrategyForm;

/**
 * Created by Mark.W on 2017/5/11.
 * 收藏策略的接口
 */
public interface CollectStrategyService {
    /**
     * 获取所有收藏的策略
     * @param userID userid
     * @return  ArrayList<Strategy>
     */
    public ArrayList<Strategy> getCollectedStrategy(String userID);

    /**
     * 收藏策略
     * @param collectStrategyForm userid和策略id
     * @return boolean
     */
    public boolean collectStrategy(CollectStrategyForm collectStrategyForm);

    /**
     * 删除收藏的策略
     * @param collectStrategyForm userid和策略id
     * @return boolean
     */
    public boolean deleteCollectedStrategy(CollectStrategyForm collectStrategyForm);

}
