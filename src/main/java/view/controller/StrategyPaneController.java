package view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import logic.calculation.StrategyCalculation;
import logicService.StrategyCalculationService;
import view.graph.Graph;
import vo.*;

import javax.xml.crypto.dom.DOMCryptoContext;


/**
 * 展示策略的三个图
 * Created by wangxue on 2017/3/31.
 */
public class StrategyPaneController {

    StrategyCalculationService strategyCalculationService;

    Graph graph ;

    @FXML
    private AnchorPane graphA;
    @FXML
    private AnchorPane graphB_0;
    @FXML
    private AnchorPane graphB_1;
    @FXML
    private AnchorPane graphC;

    @FXML
    private Label year;
    @FXML
    private Label base;

    @FXML
    private Label sharp;
    @FXML
    private Label alpha;
    @FXML
    private Label beta;
//    @FXML
//    private Label volatility;
//    @FXML
//    private Label message;
    @FXML
    private Label max;
//    @FXML
//    private Label turnover;

    @FXML
    private Label positive;
    @FXML
    private Label negative;
    @FXML
    private Label win;

    public StrategyPaneController(){

        strategyCalculationService = new StrategyCalculation();
        graph = new Graph();

    }

    public void init(BackTestingResultVO backTestingResultVO ,
                     AbnormalReturnGraphVO abnormalReturnGraphVO ){

        CumulativeYieldGraphVO cumulativeYieldGraphVO = backTestingResultVO.cumulativeYieldGraphVO;
        YieldHistogramGraphVO yieldHistogramGraphVO = backTestingResultVO.yieldHistogramGraphVO;

        //a
        year.setText(Double.toString(cumulativeYieldGraphVO.annualRevenue));
        base.setText(Double.toString(cumulativeYieldGraphVO.baseAnnualRevenue));
        sharp.setText(Double.toString(cumulativeYieldGraphVO.sharpeRatio));
        alpha.setText(Double.toString(cumulativeYieldGraphVO.alpha));
        beta.setText(Double.toString(cumulativeYieldGraphVO.beta));
        max .setText(Double.toString(cumulativeYieldGraphVO.maxDrawdown));

        //图

        //b

        //c
        positive.setText(Double.toString(yieldHistogramGraphVO.positiveEarningNum));
        negative.setText(Double.toString(yieldHistogramGraphVO.negativeEarningNum));
        win.setText(Double.toString(yieldHistogramGraphVO.winRate));

        //图

    }

}
