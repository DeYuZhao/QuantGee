package view.ui;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import logic.tools.DateHelper;
import view.controller.SearchSharesController;
import vo.KLineVO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public class AdvCandleStickChart extends Pane {
    private SearchSharesController searchSharesController;
    // DAY, OPEN, CLOSE, HIGH, LOW, AVERAGE
//    KLineVO vo1 = new KLineVO(false,"one",25,20,32,16);
//    KLineVO vo2 = new KLineVO("two",26,30,33,22);
//    ArrayList<KLineVO> KLineData = new ArrayList<KLineVO>();
//    KLineData.add(new KLineVO("one",25,20,32,16));
//    private static double[][] data = new double[][]{
//            {1, 25, 20, 32, 16, 20},
//            {2, 26, 30, 33, 22, 25},
//            {3, 30, 38, 40, 20, 32},
//            {4, 24, 30, 34, 22, 30},
//            {5, 26, 36, 40, 24, 32},
//            {6, 28, 38, 45, 25, 34},
//            {7, 36, 30, 44, 28, 39},
//            {8, 30, 18, 36, 16, 31},
//            {9, 40, 50, 52, 36, 41},
//            {10, 30, 34, 38, 28, 36},
//            {11, 24, 12, 30, 8, 32.4},
//            {12, 28, 40, 46, 25, 31.6},
//            {13, 28, 18, 36, 14, 32.6},
//            {14, 38, 30, 40, 26, 30.6},
//            {15, 28, 33, 40, 28, 30.6},
//            {16, 25, 10, 32, 6, 30.1},
//            {17, 26, 30, 42, 18, 27.3},
//            {18, 20, 18, 30, 10, 21.9},
//            {19, 20, 10, 30, 5, 21.9},
//            {20, 26, 16, 32, 10, 17.9},
//            {21, 38, 40, 44, 32, 18.9},
//            {22, 26, 40, 41, 12, 18.9},
//            {23, 30, 18, 34, 10, 18.9},
//            {24, 12, 23, 26, 12, 18.2},
//            {25, 30, 40, 45, 16, 18.9},
//            {26, 25, 35, 38, 20, 21.4},
//            {27, 24, 12, 30, 8, 19.6},
//            {28, 23, 44, 46, 15, 22.2},
//            {29, 28, 18, 30, 12, 23},
//            {30, 28, 18, 30, 12, 23.2},
//            {31, 28, 18, 30, 12, 22}
//    };

    public AdvCandleStickChart(SearchSharesController searchSharesController) throws Exception{
        this.searchSharesController = searchSharesController;
        // x-axis:

        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis xAxis = new NumberAxis(0, 32, 1);
//        xAxis.setMinorTickCount(0);
        xAxis.setLabel("Day");

        // y-axis:
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Price");
        yAxis.setForceZeroInRange(false);

        // chart:
        final CandleStickChart bc = new CandleStickChart(xAxis, yAxis);
        bc.setTitle("Custom Candle Stick Chart");

        // add starting data
        ArrayList<KLineVO> kLineVOArrayList = searchSharesController.getKlineVoList();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        for (int i = 0; i < kLineVOArrayList.size(); i++) {
            KLineVO day = kLineVOArrayList.get(i);
            series.getData().add(
                    new XYChart.Data<String, Number>(DateHelper.getInstance().dateTransToString(day.date), day.openPrice, new CandleStickExtraValues(day.closePrice, day.maxValue, day.minValue))
            );
        }
        ObservableList<XYChart.Series<String, Number>> data = bc.getData();
        if (data == null) {
            data = FXCollections.observableArrayList(series);
            bc.setData(data);
        } else {
            bc.getData().add(series);
        }

        getChildren().add(bc);
    }

    /**
     * A candlestick chart is a style of bar-chart used primarily to describe
     * price movements of a security, derivative, or currency over time.
     *
     * The Data Y value is used for the opening price and then the close, high
     * and low values are stored in the Data's extra value property using a
     * CandleStickExtraValues object.
     */
    private class CandleStickChart extends XYChart<String, Number> {

        // -------------- CONSTRUCTORS ----------------------------------------------
        /**
         * Construct a new CandleStickChart with the given axis.
         *
         * @param xAxis The x axis to use
         * @param yAxis The y axis to use
         */
        public CandleStickChart(Axis<String> xAxis, Axis<Number> yAxis) {
            super(xAxis, yAxis);
            setAnimated(false);
            xAxis.setAnimated(false);
            yAxis.setAnimated(false);
        }

        /**
         * Construct a new CandleStickChart with the given axis and data.
         *
         * @param xAxis The x axis to use
         * @param yAxis The y axis to use
         * @param data The data to use, this is the actual list used so any
         * changes to it will be reflected in the chart
         */
        public CandleStickChart(Axis<String> xAxis, Axis<Number> yAxis, ObservableList<Series<String, Number>> data) {
            this(xAxis, yAxis);
            setData(data);
        }

        // -------------- METHODS ------------------------------------------------------------------------------------------
        /**
         * Called to update and layout the content for the plot
         */
        @Override
        protected void layoutPlotChildren() {
            // we have nothing to layout if no data is present
            if (getData() == null) {
                return;
            }
            // update candle positions
            for (int seriesIndex = 0; seriesIndex < getData().size(); seriesIndex++) {
                Series<String, Number> series = getData().get(seriesIndex);
                Iterator<Data<String, Number>> iter = getDisplayedDataIterator(series);
                Path seriesPath = null;
                if (series.getNode() instanceof Path) {
                    seriesPath = (Path) series.getNode();
                    seriesPath.getElements().clear();
                }
                while (iter.hasNext()) {
                    Data<String, Number> item = iter.next();
                    double x = getXAxis().getDisplayPosition(getCurrentDisplayedXValue(item));
                    double y = getYAxis().getDisplayPosition(getCurrentDisplayedYValue(item));
                    Node itemNode = item.getNode();
                    CandleStickExtraValues extra = (CandleStickExtraValues) item.getExtraValue();
                    if (itemNode instanceof Candle && extra != null) {
                        Candle candle = (Candle) itemNode;

                        double close = getYAxis().getDisplayPosition(extra.getClose());
                        double high = getYAxis().getDisplayPosition(extra.getHigh());
                        double low = getYAxis().getDisplayPosition(extra.getLow());
                        // calculate candle width
                        double candleWidth = -1;
                        if (getXAxis() instanceof CategoryAxis) {
                            CategoryAxis xa = (CategoryAxis) getXAxis();
//                            candleWidth = xa.getDisplayPosition(xa.getTickUnit()) * 0.90; // use 90% width between ticks
                        }
                        // update candle
                        //K线图
                        candle.update(close - y, high - y, low - y, candleWidth);
//                        candle.updateTooltip(item.getYValue().doubleValue(), extra.getClose(), extra.getHigh(), extra.getLow());

                        // position the candle
                        candle.setLayoutX(x);
                        candle.setLayoutY(y);
                    }
                    //均线图
//                    if (seriesPath != null) {
//                        if (seriesPath.getElements().isEmpty()) {
//                            seriesPath.getElements().add(new MoveTo(x, getYAxis().getDisplayPosition(extra.getAverage())));
//                        } else {
//                            seriesPath.getElements().add(new LineTo(x, getYAxis().getDisplayPosition(extra.getAverage())));
//                        }
//                    }
                }
            }
        }

        @Override
        protected void dataItemChanged(Data<String, Number> item) {
        }

        @Override
        protected void dataItemAdded(Series<String, Number> series, int itemIndex, Data<String, Number> item) {
            Node candle = createCandle(getData().indexOf(series), item, itemIndex);
            if (shouldAnimate()) {
                candle.setOpacity(0);
                getPlotChildren().add(candle);
                // fade in new candle
                FadeTransition ft = new FadeTransition(Duration.millis(500), candle);
                ft.setToValue(1);
                ft.play();
            } else {
                getPlotChildren().add(candle);
            }
            // always draw average line on top
            if (series.getNode() != null) {
                series.getNode().toFront();
            }
        }

        @Override
        protected void dataItemRemoved(Data<String, Number> item, Series<String, Number> series) {
            final Node candle = item.getNode();
            if (shouldAnimate()) {
                // fade out old candle
                FadeTransition ft = new FadeTransition(Duration.millis(500), candle);
                ft.setToValue(0);
                ft.setOnFinished(new EventHandler<ActionEvent>() {

//                    @Override
                    public void handle(ActionEvent actionEvent) {
                        getPlotChildren().remove(candle);
                    }
                });
                ft.play();
            } else {
                getPlotChildren().remove(candle);
            }
        }

        @Override
        protected void seriesAdded(Series<String, Number> series, int seriesIndex) {
            // handle any data already in series
            for (int j = 0; j < series.getData().size(); j++) {
                Data item = series.getData().get(j);
                Node candle = createCandle(seriesIndex, item, j);
                if (shouldAnimate()) {
                    candle.setOpacity(0);
                    getPlotChildren().add(candle);
                    // fade in new candle
                    FadeTransition ft = new FadeTransition(Duration.millis(500), candle);
                    ft.setToValue(1);
                    ft.play();
                } else {
                    getPlotChildren().add(candle);
                }
            }
            // create series path
            Path seriesPath = new Path();
            seriesPath.getStyleClass().setAll("candlestick-average-line", "series" + seriesIndex);
            series.setNode(seriesPath);
            getPlotChildren().add(seriesPath);
        }

        @Override
        protected void seriesRemoved(Series<String, Number> series) {
            // remove all candle nodes
            for (XYChart.Data<String, Number> d : series.getData()) {
                final Node candle = d.getNode();
                if (shouldAnimate()) {
                    // fade out old candle
                    FadeTransition ft = new FadeTransition(Duration.millis(500), candle);
                    ft.setToValue(0);
                    ft.setOnFinished(new EventHandler<ActionEvent>() {

//                        @Override
                        public void handle(ActionEvent actionEvent) {
                            getPlotChildren().remove(candle);
                        }
                    });
                    ft.play();
                } else {
                    getPlotChildren().remove(candle);
                }
            }
        }

        /**
         * Create a new Candle node to represent a single data item
         *
         * @param seriesIndex The index of the series the data item is in
         * @param item The data item to create node for
         * @param itemIndex The index of the data item in the series
         * @return New candle node to represent the give data item
         */
        private Node createCandle(int seriesIndex, final Data item, int itemIndex) {
            Node candle = item.getNode();
            // check if candle has already been created
            if (candle instanceof Candle) {
                ((Candle) candle).setSeriesAndDataStyleClasses("series" + seriesIndex, "data" + itemIndex);
            } else {
                candle = new Candle("series" + seriesIndex, "data" + itemIndex);
                item.setNode(candle);
            }
            return candle;
        }

        /**
         * This is called when the range has been invalidated and we need to
         * update it. If the axis are auto ranging then we compile a list of all
         * data that the given axis has to plot and call invalidateRange() on
         * the axis passing it that data.
         */
        @Override
        protected void updateAxisRange() {
            // For candle stick chart we need to override this method as we need to let the axis know that they need to be able
            // to cover the whole area occupied by the high to low range not just its center data value
            final Axis<String> xa = getXAxis();
            final Axis<Number> ya = getYAxis();
            List<String> xData = null;
            List<Number> yData = null;
            if (xa.isAutoRanging()) {
                xData = new ArrayList<String>();
            }
            if (ya.isAutoRanging()) {
                yData = new ArrayList<Number>();
            }
            if (xData != null || yData != null) {
                for (Series<String, Number> series : getData()) {
                    for (Data<String, Number> data : series.getData()) {
                        if (xData != null) {
                            xData.add(data.getXValue());
                        }
                        if (yData != null) {
                            CandleStickExtraValues extras = (CandleStickExtraValues) data.getExtraValue();
                            if (extras != null) {
                                yData.add(extras.getHigh());
                                yData.add(extras.getLow());
                            } else {
                                yData.add(data.getYValue());
                            }
                        }
                    }
                }
                if (xData != null) {
                    xa.invalidateRange(xData);
                }
                if (yData != null) {
                    ya.invalidateRange(yData);
                }
            }
        }
    }

    /**
     * Data extra values for storing close, high and low.
     */
    private class CandleStickExtraValues {

        private double close;
        private double high;
        private double low;
//        private double average;

        public CandleStickExtraValues(double close, double high, double low) {
            this.close = close;
            this.high = high;
            this.low = low;
//            this.average = average;
        }

        public double getClose() {
            return close;
        }

        public double getHigh() {
            return high;
        }

        public double getLow() {
            return low;
        }

//        public double getAverage() {
//            return average;
//        }
    }

    /**
     * Candle node used for drawing a candle
     */
    private class Candle extends Group {

        private Line highLowLine = new Line();
        private Region bar = new Region();
        private String seriesStyleClass;
        private String dataStyleClass;
        private boolean openAboveClose = true;
        private Tooltip tooltip = new Tooltip();

        private Candle(String seriesStyleClass, String dataStyleClass) {
            setAutoSizeChildren(false);
            getChildren().addAll(highLowLine, bar);
            this.seriesStyleClass = seriesStyleClass;
            this.dataStyleClass = dataStyleClass;
            updateStyleClasses();
            tooltip.setGraphic(new TooltipContent());
            Tooltip.install(bar, tooltip);
        }

        public void setSeriesAndDataStyleClasses(String seriesStyleClass, String dataStyleClass) {
            this.seriesStyleClass = seriesStyleClass;
            this.dataStyleClass = dataStyleClass;
            updateStyleClasses();
        }

        public void update(double closeOffset, double highOffset, double lowOffset, double candleWidth) {
            openAboveClose = closeOffset > 0;
            updateStyleClasses();
            highLowLine.setStartY(highOffset);
            highLowLine.setEndY(lowOffset);
            if (candleWidth == -1) {
                candleWidth = bar.prefWidth(-1);
            }
            if (openAboveClose) {
                bar.resizeRelocate(-candleWidth / 2, 0, candleWidth, closeOffset);
            } else {
                bar.resizeRelocate(-candleWidth / 2, closeOffset, candleWidth, closeOffset * -1);
            }
        }

        public void updateTooltip(double open, double close, double high, double low) {
            TooltipContent tooltipContent = (TooltipContent) tooltip.getGraphic();
            tooltipContent.update(open, close, high, low);
//                    tooltip.setText("Open: "+open+"\nClose: "+close+"\nHigh: "+high+"\nLow: "+low);
        }

        private void updateStyleClasses() {
            getStyleClass().setAll("candlestick-candle", seriesStyleClass, dataStyleClass);
            highLowLine.getStyleClass().setAll("candlestick-line", seriesStyleClass, dataStyleClass,
                    openAboveClose ? "open-above-close" : "close-above-open");
            bar.getStyleClass().setAll("candlestick-bar", seriesStyleClass, dataStyleClass,
                    openAboveClose ? "open-above-close" : "close-above-open");
        }
    }

    private class TooltipContent extends GridPane {

        private Label openValue = new Label();
        private Label closeValue = new Label();
        private Label highValue = new Label();
        private Label lowValue = new Label();

        private TooltipContent() {
            Label open = new Label("OPEN:");
            Label close = new Label("CLOSE:");
            Label high = new Label("HIGH:");
            Label low = new Label("LOW:");
            open.getStyleClass().add("candlestick-tooltip-label");
            close.getStyleClass().add("candlestick-tooltip-label");
            high.getStyleClass().add("candlestick-tooltip-label");
            low.getStyleClass().add("candlestick-tooltip-label");
            setConstraints(open, 0, 0);
            setConstraints(openValue, 1, 0);
            setConstraints(close, 0, 1);
            setConstraints(closeValue, 1, 1);
            setConstraints(high, 0, 2);
            setConstraints(highValue, 1, 2);
            setConstraints(low, 0, 3);
            setConstraints(lowValue, 1, 3);
            getChildren().addAll(open, openValue, close, closeValue, high, highValue, low, lowValue);
        }

        public void update(double open, double close, double high, double low) {
            openValue.setText(Double.toString(open));
            closeValue.setText(Double.toString(close));
            highValue.setText(Double.toString(high));
            lowValue.setText(Double.toString(low));
        }
    }
}

