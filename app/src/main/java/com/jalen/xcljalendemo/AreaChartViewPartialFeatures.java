/**
 * Copyright 2014  XCL-Charts
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @Project XCL-Charts
 * @Description Android图表基类库
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @Copyright Copyright (c) 2014 XCL-Charts (www.xclcharts.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 1.5
 */
package com.jalen.xcljalendemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import org.xclcharts.chart.AreaChart;
import org.xclcharts.chart.AreaData;
import org.xclcharts.chart.CustomLineData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.event.click.PointPosition;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.info.AnchorDataPoint;
import org.xclcharts.view.ChartView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @ClassName AreaChart02View
 * @Description 平滑区域图部分使用特性
 */

public class AreaChartViewPartialFeatures extends ChartView {

    private AreaChart chart = new AreaChart();

    /**
     * 分类文字集合
     */
    private LinkedList<String> mLabels = new LinkedList<>();

    /**
     * 数据集合
     */
    private LinkedList<AreaData> mDataset = new LinkedList<>();

    /**
     * 标尺线集合
     */
    private List<CustomLineData> mCustomLineDataset = new LinkedList<>();

    /**
     * 批注线集合
     */
    List<AnchorDataPoint> mAnchorSet = new ArrayList<>();

    public AreaChartViewPartialFeatures(Context context) {
        super(context);
        initView();
    }

    public AreaChartViewPartialFeatures(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AreaChartViewPartialFeatures(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    // [+] Override
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("dragon", "AreaChartView onSizeChanged width = " + w);
        Log.i("dragon", "AreaChartView onSizeChanged height = " + h);
        //图所占范围大小
        chart.setChartRange(w, h);
    }

    @Override
    public void render(Canvas canvas) {
        try {
            chart.render(canvas);
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            triggerClick(event.getX(), event.getY());
        }
        return true;
    }
    // [-] Override

    private void initView() {
        chartLabels();
        chartDataSet();
        chartAnchor();
        charCustomLine();
        chartRender();
        //綁定手势滑动事件
        //this.bindTouch(this, chart);
    }

    //X轴的标签
    private void chartLabels() {
        mLabels.add("");
        mLabels.add("1月");
        mLabels.add("");
        mLabels.add("2月");
      /*   mLabels.add("3月");
       mLabels.add("4月");
        mLabels.add("5月");
        mLabels.add("6月");
        mLabels.add("7月");
        mLabels.add("8月");
        mLabels.add("9月");
        mLabels.add("10月");
        mLabels.add("11月");
        mLabels.add("12月");*/
    }

    //标签对应的数据集 Y轴对应数据
    private void chartDataSet() {
        //将标签与对应的数据集分别绑定
        List<Double> dataSeries = new LinkedList<>();
        dataSeries.add((double) 50);
        dataSeries.add((double) 22);
        dataSeries.add((double) 30);
        dataSeries.add((double) 35);
      /*   dataSeries.add((double) 15);
        dataSeries.add((double) 15);
        dataSeries.add((double) 10);
        dataSeries.add((double) 15);
        dataSeries.add((double) 0);
        dataSeries.add((double) 15);
        dataSeries.add((double) 35);
        dataSeries.add((double) 15);*/

        //图例,数据集,轮廓线颜色,区域颜色
        AreaData areaData = new AreaData("key", dataSeries, Color.YELLOW, Color.BLUE);

        //标签字体
        areaData.getDotLabelPaint().setColor(Color.RED);
        areaData.getDotLabelPaint().setTextAlign(Paint.Align.CENTER);
        areaData.getDotLabelPaint().setTextSize(24);

        //设置标签样式
        areaData.setLabelVisible(true);
        areaData.getLabelOptions().setLabelBoxStyle(XEnum.LabelBoxStyle.CAPRECT);
        //areaData.getLabelOptions().hideBorder();
        areaData.getLabelOptions().showBorder();
        areaData.getLabelOptions().setMargin(30);
        areaData.getLabelOptions().setOffsetY(10.0f);
        // areaData.getLabelOptions().setOffsetX(60.0f);
        areaData.getLabelOptions().getBox().getBackgroundPaint().setColor(Color.BLACK);

        //曲线上的点
        areaData.setDotStyle(XEnum.DotStyle.RING);
        areaData.getDotPaint().setColor(Color.BLACK);//这个颜色是指定曲线上点外面的颜色 Color.rgb(65, 160, 255)
        areaData.getPlotLine().getPlotDot().setRingInnerColor(Color.WHITE);//这个颜色是指定曲线上点里面的颜色

        //区域图填充颜色
        areaData.setApplayGradient(true);
        areaData.setGradientDirection(XEnum.Direction.VERTICAL);
        areaData.setAreaBeginColor(Color.BLACK);
        areaData.setAreaEndColor(Color.RED);
        //areaData.setGradientMode(Shader.TileMode.MIRROR);

        mDataset.add(areaData);
    }

    //批注  白色竖线
    private void chartAnchor() {
        for (int i = 0; i < 3; i++) {
            AnchorDataPoint anchor = new AnchorDataPoint(0, i, XEnum.AnchorStyle.TOBOTTOM);
            anchor.setBgColor(Color.YELLOW);
            anchor.setLineWidth(15);
            anchor.setLineStyle(XEnum.LineStyle.DASH);
            mAnchorSet.add(anchor);
        }
    }

    //中间的定制线
    private void charCustomLine() {
        CustomLineData line = new CustomLineData("标识线", 18d, Color.RED, 7);
        line.setCustomLineCap(XEnum.DotStyle.CROSS);
        line.setLabelHorizontalPostion(Paint.Align.CENTER);
        line.setLabelOffset(15);
        line.getLineLabelPaint().setColor(Color.RED);
        mCustomLineDataset.add(line);
    }

    private void chartRender() {
        try {
            //设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....
            int[] ltrb = new int[4];
            ltrb[0] = DensityUtil.dip2px(getContext(), 30); //left
            ltrb[1] = DensityUtil.dip2px(getContext(), 80); //top
            ltrb[2] = DensityUtil.dip2px(getContext(), 40); //right
            ltrb[3] = DensityUtil.dip2px(getContext(), 60); //bottom

            chart.setPadding(ltrb[0], ltrb[2], ltrb[0], ltrb[2]);

            //分类轴
            chart.setCategories(mLabels);

            //数据轴
            chart.setDataSource(mDataset);

            //批注
            chart.setAnchorDataPoint(mAnchorSet);

            //标示线
            chart.setCustomLines(mCustomLineDataset);

            //画贝塞尔曲线
            chart.setCrurveLineStyle(XEnum.CrurveLineStyle.BEZIERCURVE);

            //仅横向平移
            // chart.setPlotPanMode(XEnum.PanMode.HORIZONTAL);

            //隐藏背景网格
            chart.getPlotGrid().hideHorizontalLines();
            chart.getPlotGrid().hideVerticalLines();

            //显示背景网格
           /* chart.getPlotGrid().showHorizontalLines();
            chart.getPlotGrid().showVerticalLines();*/

            chart.getPlotArea().setBackgroundColor(true, Color.GREEN);

            //标题
           /* chart.setTitle("平滑区域图");
            chart.addSubtitle("(XCL-Charts Demo)");*/

            //X轴标题
            //chart.getAxisTitle().setLowerTitle("(年份)");

            //显示图例
            // chart.getPlotLegend().show();

            //透明度
            chart.setAreaAlpha(180);

            //分类轴
            //chart.getCategoryAxis().getAxisPaint().setColor(Color.BLACK);
            // chart.getCategoryAxis().getAxisPaint().setTextSize(50);

            //隐藏X轴线
            chart.getCategoryAxis().hideAxisLine();
            //隐藏X轴的刻度尺
            chart.getCategoryAxis().hideTickMarks();

            //chart.getCategoryAxis().showAxisLine();
            //chart.getCategoryAxis().showTickMarks();

            //分类轴字体
            chart.getCategoryAxis().getTickLabelPaint().setColor(Color.BLUE);
            chart.getCategoryAxis().getTickLabelPaint().setFakeBoldText(true);
            chart.getCategoryAxis().setTickLabelMargin(50);
            chart.getCategoryAxis().getTickLabelPaint().setTextSize(35);

            //把数据轴线和刻度线给隐藏起来
//            chart.getDataAxis().hideAxisLine();
//            chart.getDataAxis().hideTickMarks();

            //隐藏数据轴的数值 轴线 刻度线
            chart.getDataAxis().hide();

          /*  chart.getDataAxis().showAxisLine();
            chart.getDataAxis().showTickMarks();*/

            //数据轴最大值
            chart.getDataAxis().setAxisMax(50);

            //数据轴刻度间隔
            chart.getDataAxis().setAxisSteps(22);

            //隐藏上面的比例尺
            chart.getPlotLegend().hide();

            //chart.getPlotLegend().show();

            //激活点击监听
            chart.ActiveListenItemClick();

            //为了让触发更灵敏，可以扩大20px的点击监听范围
            chart.extPointClickRange(20);

            chart.disableHighPrecision();

            //定义数据轴标签显示格式
           /* chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack() {
                @Override
                public String textFormatter(String value) {
                    Double tmp = Double.parseDouble(value);
                    DecimalFormat df = new DecimalFormat("#0");
                    String label = df.format(tmp).toString();
                    return label;
                }
            });*/

            //设定交叉点标签显示格式
            chart.setItemLabelFormatter(new IFormatterDoubleCallBack() {
                @Override
                public String doubleFormatter(Double value) {
                   /* DecimalFormat df = new DecimalFormat("#0");
                    String label = df.format(value).toString();*/
                    String label = String.valueOf(value.intValue());
                    return label;
                }
            });

            //扩大显示宽度
            //chart.getPlotArea().extWidth(100f);

            chart.disablePanMode();
        } catch (Exception e) {

        }
    }

    //触发监听
    private void triggerClick(float x, float y) {
        PointPosition record = chart.getPositionRecord(x, y);
        if (null == record) {
            return;
        }


        String s = mLabels.get(record.getDataChildID());//标签轴的值
        //Toast.makeText(this.getContext(),s,Toast.LENGTH_SHORT).show();

        AreaData lData = mDataset.get(record.getDataID());
        Double lValue = lData.getLinePoint().get(record.getDataChildID());//数据轴的值

        Toast.makeText(this.getContext(), s + "------" + lValue, Toast.LENGTH_SHORT).show();

        // Toast.makeText(this.getContext(),label,Toast.LENGTH_SHORT).show();


        // Double lValue = lData.getLinePoint().get(record.getDataChildID());

        /*Toast.makeText(this.getContext(),
                record.getPointInfo() +
                        " Key:" + lData.getLineKey() +
                        " Label:" + lData.getLabel() +
                        " Current Value:" + Double.toString(lValue),
                Toast.LENGTH_SHORT).show();*/
    }
}

