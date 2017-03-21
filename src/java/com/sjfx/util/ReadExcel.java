/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjfx.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.K;
import static javax.swing.UIManager.put;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.sjfx.model.DataModel;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Date;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

public class ReadExcel {

    public List<DataModel> readExcel(String excelName) throws IOException {
        System.out.println(excelName);

        String str = excelName.substring(excelName.indexOf("."), excelName.length());
        System.out.println(str);
        if (str.equals(".xlsx")&&checkExcel(excelName)) {
            return getExcelData(excelName);
        } else if (str.equals(".xls")) {
            return readXls(excelName);
        }
        return null;

    }

    //没用到
    public List<DataModel> importDB() {
        DataModel bean = new DataModel();
        return (List<DataModel>) bean;
    }

    //没用到,才怪，这个是读取excel2007以下版本的
    public List<DataModel> readXlsx(String excelName) {
        InputStream input;
        DataModel data = new DataModel();
        List<DataModel> list = new ArrayList<>();
        String temp = null;
        int flag = -1;//用于标记对应哪一列
        try {
            input = new FileInputStream(excelName);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(input);
            if (checkExcel(excelName)) {
                Sheet sheet = xssfWorkbook.getSheetAt(0);     //获得第一个表单  
                Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器  
                if (rows.hasNext()) {
                    Row row = rows.next();
                    while (rows.hasNext()) {//获取表格的全部内容
                        row = rows.next();  //获得行数据  
                        Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器  
//                        if (cells.hasNext()) {
//                            Cell cell = cells.next();//这样是为了跳过第一列
//                        }
                        while (cells.hasNext()) {

                            Cell cell = cells.next();
                            switch (cell.getCellType()) {   //根据cell中的类型来输出数据  
                                case HSSFCell.CELL_TYPE_NUMERIC:
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        temp = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                                    }else{
                                        temp = cell.getNumericCellValue() + "";
                                    }
                                    
                                    flag++;
                                    break;
                                case HSSFCell.CELL_TYPE_STRING:
                                    temp = cell.getStringCellValue();
                                    flag++;
                                    break;
                                case HSSFCell.CELL_TYPE_BLANK:
                                    break;
                                default:
                                    break;
                            }
                            if(temp.equals("")||temp==null) {
                            } else {
                                switch (flag) {
                                    case 0:
                                        System.out.println(temp);
                                        data.setArea(temp);
                                        break;
                                    case 1:
                                        data.setOutDepartment(temp);
                                        break;
                                    case 2:
                                        data.setInpatientNumber(temp);
                                        break;
                                    case 3:
										data.setName(temp);
                                        break;
                                    case 4:
                                        data.setSex(temp);
                                        break;
                                    case 5:
                                        data.setAge(Integer.parseInt(temp));
                                        break;
                                    case 6:
                                        data.setOutDiagnosis(temp);
                                        break;
                                    case 7:
                                        data.setOutDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(temp));
                                        break;
                                    default:
                                        break;
                                }
                            }

                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ReadExcel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

        list.add(data);
        return list;
    }

    //没用到
    public List<DataModel> readXls(String excelName) throws IOException {
        return null;

    }

    public boolean checkExcel(String fileName) {//检查excel表格中表头是否对应着这些数据
        String excelHeader[] = new String[]{"区域", "出院科室", "住院号", "姓名", "性别", "年龄", "出院诊断", "出院日期"};
        boolean excelHeaderCheck[] = new boolean[]{false, false, false, false, false, false, false, false, false, false};
        List<String> list = new ArrayList<String>();
        int i = 0;
        boolean isE2007 = false;    //判断是否是excel2007格式  
        if (fileName.endsWith("xlsx")) {
            isE2007 = true;
        }
        try {
            InputStream input = new FileInputStream(fileName);  //建立输入流  
            Workbook wb = null;
            //根据文件格式(2003或者2007)来初始化  
            if (isE2007) {
                wb = new XSSFWorkbook(input);
            } else {
                wb = new HSSFWorkbook(input);
            }
            Sheet sheet = wb.getSheetAt(0);     //获得第一个表单  
            Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器  
            if (rows.hasNext()) {
                Row row = rows.next();  //获得行数据  
//                System.out.println("Row #" + row.getRowNum());  //获得行号从0开始  
                Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器  
                while (cells.hasNext()) {

                    Cell cell = cells.next();
                    i++;
                    if ((!(cell.getStringCellValue().equals(""))) && (cell.getStringCellValue() != null)) {
                        list.add(cell.getStringCellValue());
                    }

                }
                System.out.println(list.size());//看看总共有多少列

                for (int j = 0; j < list.size(); j++) {//检测excel表头跟数据定义是否相同
                    for (int n = 0; n < excelHeader.length; n++) {
                        if (list.get(j).equals(excelHeader[n])) {
                            excelHeaderCheck[n] = true;
                        }
                    }
                }
                for (int j = 0; j < excelHeader.length; j++) {
                    if (excelHeaderCheck[j] != true) {
                        System.out.println("不全符合");
                        return false;
                    } else {
                        if (j == excelHeader.length - 1) {
                            System.out.println("全符合");
                            return true;
                        }
                    }
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public List<DataModel> getExcelData(String fileName){
        boolean isE2007 = false;    //判断是否是excel2007格式  
        String temp=null;
        
        List<DataModel> list = new ArrayList<>();
        if (fileName.endsWith("xlsx")) {
            isE2007 = true;
        }
        try{
            FileInputStream fis=new FileInputStream(fileName);
            Workbook workbook = null;
            if (isE2007) {
                workbook = new XSSFWorkbook(fis);
            } else {
                workbook = new HSSFWorkbook(fis);
            }
//            HSSFWorkbook workbook=new HSSFWorkbook(fs);
            Sheet sheet=workbook.getSheetAt(0);
            Row row=null;
            for(int i=0;i<sheet.getLastRowNum();i++){
                DataModel data = new DataModel();
                row=sheet.getRow(i);
                Cell cell0=row.getCell(0);
                Cell cell1=row.getCell(1);
                Cell cell2=row.getCell(2);
                Cell cell3=row.getCell(3);
                Cell cell4=row.getCell(4);
                Cell cell5=row.getCell(5);
                Cell cell6=row.getCell(6);
                Cell cell7=row.getCell(7);
//                if(cell1==null||cell1.getStringCellValue().equals("")){
//                    continue;
//                }
                data.setArea(cell0.getStringCellValue());
                data.setOutDepartment(cell1.getStringCellValue());
                data.setInpatientNumber((int)cell2.getNumericCellValue()+"");
                data.setName(cell3.getStringCellValue());
                data.setSex(cell4.getStringCellValue());
                data.setAge(Integer.parseInt(cell5.getStringCellValue()));
                data.setOutDiagnosis(cell6.getStringCellValue());
				Date date= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(cell7.getStringCellValue());
				data.setOutDate(date);
                list.add(data);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

}
