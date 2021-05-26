package com.brt.oa;//package com.example.sm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
//import java.util.List;

public class Test extends TimerTask {
    //    public static Double[][] readData(String dataUrl) {
//        jxl.Workbook readwb = null;
//        Double[][] arr = null;
//        try {
//            // 构建Workbook对象, 只读Workbook对象 直接从本地文件创建Workbook
//            readwb = Workbook.getWorkbook(new FileInputStream(new File(dataUrl)));
//            // Sheet的下标是从0开始 获取第一张Sheet表
//            Sheet readsheet = readwb.getSheet(0);
//            // 获取Sheet表中所包含的总列数
//            int rsColumns = readsheet.getColumns();
//            // 获取Sheet表中所包含的总行数
//            int rsRows = readsheet.getRows();
//            // 获取指定单元格的对象引用
//            arr = new Double[rsRows][rsColumns];
//            for (int i = 0; i < rsRows; i++) {
//                for (int j = 0; j < rsColumns; j++) {
//                    Cell cell = readsheet.getCell(j, i);
//                    arr[i][j] = Double.parseDouble(cell.getContents());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            readwb.close();
//        }
//        return arr;
//    }
//
    public static String doPost(String pathUrl, String data) {
        OutputStreamWriter out = null;
        BufferedReader br = null;
        String result = "";
        try {
            URL url = new URL(pathUrl);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //请求方式
            conn.setRequestMethod("POST");
            //conn.setRequestMethod("GET");

            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            //DoOutput设置是否向httpUrlConnection输出，DoInput设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            conn.setDoOutput(true);
            conn.setDoInput(true);
            /**
             * 下面的三句代码，就是调用第三方http接口
             */
            //获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            //发送请求参数即数据
            out.write(data);
            //flush输出流的缓冲
            out.flush();
            /**
             * 下面的代码相当于，获取调用第三方http接口后返回的结果
             */
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                result += str;
            }
//            System.out.println(result);
            //关闭流
            is.close();
            //断开连接，disconnect是在底层tcp socket链接空闲时才切断，如果正在被其他线程使用就不切断。
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void xxx() {

//        String x_url = "E:/GBRT/data/train_x.xls";
//        String y_url = "E:/GBRT/data/train_y.xls";
        String URL = "http://localhost:8777/api/gbrt/mpredict";
//
//        //获取数据
//        int pre_time = 1;//设置预测步长
//        Double[][] train_x =  x;
//        Double[][] y = y ;
//        Double[] train_y = new Double[y.length];
//        for (int i = 0; i < y.length; i++) {
//            train_y[i] = y[i][0];
//        }
//
//

        //获取预测结果
//        String result = doPost(URL, data);
//        List<Double> preList = JSON.parseArray(result, Double.class);
//        Double[] pre = new Double[preList.size()];
//        for (int i = 0; i < preList.size(); i++) {
//            pre[i] = preList.get(i);
//            System.out.println(pre[i]);
//        }
        Connection con=null;

        Statement stmt=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//加载数据库驱动
            String url = "jdbc:mysql://121.40.165.95:3306/fcloud";//数据库连接子协议
            Connection conn = DriverManager.getConnection(url, "wangxin", "123456");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select collectTime, dox from iot_devhisdata where collectTime > '2021-04-10 08:29:55' and devId = '11202008268' ");
            List<Double> list = new ArrayList<Double>();
            List list1 = new ArrayList();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            while (rs.next()) {//不断指向下一条记录
                list.add(rs.getDouble("dox"));


                list1.add(simpleDateFormat.format(rs.getTimestamp("collectTime")));
            }

            Double[] y = list.toArray(new Double[list.size()]);

            List list2 = new ArrayList();
            ResultSet resultSet = stmt.executeQuery("select collectTime from iot_qxdevice_hisdata");
            while (resultSet.next()) {
                list2.add(resultSet.getTimestamp("collectTime"));
            }

            List list3 = new ArrayList();

            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    if (list1.get(i) == simpleDateFormat.format(list2.get(j))) {
                        list3.add(list2.get(j));
                    }
                }
            }

            int b = 0;
            int c = y.length;
            Double[][] x = new Double[c][6];

            for (int i = 0 ; i < list1.size();i++) {
                ResultSet rs1 = stmt.executeQuery("select dqsd,dqwd,dqyl,fs,fx,tyfs from iot_qxdevice_hisdata where collectTime > '2021-04-10 00:00:00' and devId = '64720111581' and substring(collectTime,1,16) = '"+list1.get(i)+"'" );
                while (rs1.next()) {

                    x[b][0] = rs1.getDouble("dqsd");
                    x[b][1] = rs1.getDouble("dqwd");
                    x[b][2] = rs1.getDouble("dqyl");
                    x[b][3] = rs1.getDouble("fs");
                    x[b][4] = rs1.getDouble("fx");
                    x[b][5] = rs1.getDouble("tyfs");
                    b++;
                    if (b == c) {
                        break;
                    }
                }

            }


            int pre_time =40;//设置预测步长

            //将数据转换为json格式
            JSONObject object = new JSONObject();
            object.put("train_x", x);
            object.put("train_y", y);
            object.put("pre_time", pre_time);
            String data = JSON.toJSONString(object);

            //获取预测结果
            String result = doPost(URL, data);
            List<Double> preList = JSON.parseArray(result, Double.class);
            Double[] pre = new Double[preList.size()];
            for (int i = 0; i < preList.size(); i++) {
                pre[i] = preList.get(i);
                System.out.println(pre[i]);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("找不到指定的驱动程序类！");
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

    @Override
    public void run() {
        xxx();
    }
}
