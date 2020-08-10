package com.xq.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @DATE : 2020/3/19
 * @USER : xq2580z
 * @DESCRIPTION : excel表格导出
 **/
public class ExcelExport {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            users.add(new User(i, "小明" + i, "武汉" + i, i % 2, new Date()));
        }
        String path = "D:/users.xls";
        export(users, path);
    }

    /**
     * 导出
     *
     * @param users
     *            数据
     * @param path
     *            保存路径
     */
    public static void export(List<User> users, String path) {
        // 1,创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 2,在工作簿里面创建sheet
        // workbook.createSheet()//只是创建名字为默认的sheetX
        HSSFSheet sheet = workbook.createSheet("用户数据");
        // 3,sheet的相关设置
        // sheet.setColumnHidden(columnIndex, hidden);设置某一列是否隐藏
        // sheet.setColumnWidth(1, 20*256);//设置某一列的宽度
        sheet.setDefaultColumnWidth(25);// 设置默认列度
//		sheet.setDefaultRowHeight((short) (20 * 20));// 设置默认行高

        // 合并
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 4);
        sheet.addMergedRegion(region);
        CellRangeAddress region2 = new CellRangeAddress(1, 1, 0, 4);
        sheet.addMergedRegion(region2);

        int row = 0;
        // 在sheet里面创建行
        HSSFRow row1 = sheet.createRow(row);
        // 在这一行里面创建一个单元格
        HSSFCell row1_cell1 = row1.createCell(0);
        // 向row1_cell1里面添加数据
        row1_cell1.setCellValue("用户数据");
        //创建标题样式
        HSSFCellStyle titleStyle=createTitleStyle(workbook);
        row1_cell1.setCellStyle(titleStyle);

        // 第二行
        row++;
        HSSFRow row2 = sheet.createRow(row);
        HSSFCell row2_cell1 = row2.createCell(0);
        //创建小标题样式
        HSSFCellStyle subTitleStyle=createSubTitleStyle(workbook);
        row2_cell1.setCellValue("总条数:" + users.size() + "  导出时间:" + new Date().toLocaleString());
        row2_cell1.setCellStyle(subTitleStyle);
        // 第三行
        String[] titles = { "用户ID", "用户名", "用户地址", "性别", "入职时间" };
        row++;
        HSSFRow row3 = sheet.createRow(row);
        //创建表头样式
        HSSFCellStyle tableHeaderStyle=createTableTitleStyle(workbook);
        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = row3.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(tableHeaderStyle);
        }
        //创建基础样式
        HSSFCellStyle baseStyle=createBaseStyle(workbook);
        //第四行到最后
        for (int i = 0; i < users.size(); i++) {
            User user=users.get(i);
            row++;
            HSSFRow rowx = sheet.createRow(row);

            HSSFCell cell1 = rowx.createCell(0);
            cell1.setCellValue(user.getId());
            cell1.setCellStyle(baseStyle);

            HSSFCell cell2 = rowx.createCell(1);
            cell2.setCellValue(user.getName());
            cell2.setCellStyle(baseStyle);

            HSSFCell cell3 = rowx.createCell(2);
            cell3.setCellValue(user.getAddress());
            cell3.setCellStyle(baseStyle);

            HSSFCell cell4 = rowx.createCell(3);
            cell4.setCellValue(user.getSex()==1?"男":"女");
            cell4.setCellStyle(baseStyle);

            HSSFCell cell5 = rowx.createCell(4);
            cell5.setCellValue(user.getBirth().toLocaleString());
            cell5.setCellStyle(baseStyle);
        }
        // 导出保存到D盘
        try {
            workbook.write(new File(path));
            System.out.println("导出成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建基础样式  
     * 水平和垂直居中
     */
    public static HSSFCellStyle  createBaseStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        //设置水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /**
     * 创建数据表格的头的样式 
     */
    public static HSSFCellStyle createTableTitleStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = createBaseStyle(workbook);

        //设置字体
        HSSFFont font=workbook.createFont();
        font.setBold(true);//是否加粗
        font.setItalic(true);//是否斜体
        font.setFontHeightInPoints((short)25); //设置字体大小
        font.setColor(HSSFColor.HSSFColorPredefined.DARK_YELLOW.getIndex());//设置颜色
        font.setFontName("华文行楷");//设置字体
        style.setFont(font);

        return style;
    }


    /**
     * 创建小标题样式
     */
    public static HSSFCellStyle createSubTitleStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = createBaseStyle(workbook);
        //设置字体
        HSSFFont font=workbook.createFont();
        font.setBold(true);//是否加粗
        font.setFontHeightInPoints((short)18); //设置字体大小
        font.setColor(HSSFColor.HSSFColorPredefined.SKY_BLUE.getIndex());//设置颜色
        font.setFontName("黑体");//设置字体
        style.setFont(font);
        return style;
    }



    /**
     * 创建标题样式
     */
    public static HSSFCellStyle createTitleStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = createBaseStyle(workbook);
        //设置字体
        HSSFFont font=workbook.createFont();
        font.setBold(true);//是否加粗
        font.setFontHeightInPoints((short)35); //设置字体大小
        font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());//设置颜色
        font.setFontName("华文彩云");//设置字体
        style.setFont(font);
        return style;
    }


}
