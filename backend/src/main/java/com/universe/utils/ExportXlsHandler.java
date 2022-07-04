package com.universe.utils;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 导出Excel工具类
 * @author yuanjs
 * @description:
 * @date 2020-05-05 20:19
 */
public class ExportXlsHandler {

    public static String toString(Object obj) {
        StringBuilder sb = new StringBuilder("{");
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
            try {
                if (apiModelProperty != null) {
                    field.setAccessible(true);
                    if (field.get(obj) != null) {
                        if (sb.length() > 1)
                            sb.append(",");
                        sb.append(String.format("%s:%s", apiModelProperty.value(), field.get(obj).toString()));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        sb.append("}\r\n");
        return sb.toString();
    }

    public static byte[] export(PageInfo pageInfo) throws Exception {
        List list = pageInfo.getList();
        List<Field> fields = new ArrayList();
        if (list.size() > 0) {
            //创建工作薄对象
            HSSFWorkbook workbook = new HSSFWorkbook();//这里也可以设置sheet的Name
            //创建工作表对象
            HSSFSheet sheet = workbook.createSheet();

            Object obj = list.get(0);
            Class<?> cls = obj.getClass();
            while (cls != null) {
                Field[] fields1 = cls.getDeclaredFields();

                for (Field field : fields1) {
                    ApiModelProperty apiOperation = field.getAnnotation(ApiModelProperty.class);
                    if (apiOperation != null) {
                        field.setAccessible(true);
                        fields.add(field);
                    }
                }
                cls=cls.getSuperclass();
            }
            HSSFRow row = sheet.createRow(0);//设置第一行，从零开始
            for (int i = 0; i < fields.size(); i++) {
                row.createCell(i).setCellValue(fields.get(i).getAnnotation(ApiModelProperty.class).value());
            }
            for (int k = 0; k < list.size(); k++) {
                row = sheet.createRow(k + 1);
                Object obj1 = list.get(k);
                for (int i = 0; i < fields.size(); i++) {
                    if (fields.get(i).get(obj1) != null) {
                        row.createCell(i).setCellValue(fields.get(i).get(obj1).toString());
                    }
                }
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] byt = outputStream.toByteArray();
            outputStream.close();
            workbook.close();
            return byt;
        }
        return new byte[0];

    }


}
