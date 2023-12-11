package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QualityMonitor {
    public static void QualityMonitorAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">首界面>功能界面>添加质量检测方信息\n");
            Scanner sc = new Scanner(System.in);

            System.out.print("请输入质量检测方ID: ");
            int QualityMonitorID=sc.nextInt();
            System.out.print("请输入质量检测方名称: ");
            String QualityMonitorName = sc.next();
            System.out.print("请输入质量检测方地址: ");
            String QualityMonitorAddress = sc.next();
            System.out.print("请输入质量检测方负责人姓名: ");
            String QualityMonitorContactName= sc.next();
            System.out.print("请输入质量检测方负责人电话: ");
            String QualityMonitorContactPhone = sc.next();
            System.out.print("请输入质量检测方负责人邮箱: ");
            String QualityMonitorContactEmail = sc.next();



            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t质量检测方ID: " + QualityMonitorID + "\t\t质量检测方名称: " + QualityMonitorName + "\t\t质量检测方地址: " + QualityMonitorAddress+ "\t\t质量检测方负责人姓名: " + QualityMonitorContactName+ "\t\t质量检测方负责人电话: " + QualityMonitorContactPhone+ "\t\t质量检测方负责人邮箱: " + QualityMonitorContactEmail);
            System.out.print("\n\n是否添加该质量检测方信息？ [Yes(1) / No(0)] ：");
            isAdd( sc,QualityMonitorID,QualityMonitorName,QualityMonitorAddress,QualityMonitorContactName,QualityMonitorContactPhone,QualityMonitorContactEmail);
            System.out.println("\n\n\n>首界面>功能界面>添加质量检测方信息\n");
            System.out.println("\t                继续添加                   请输入1               ");
            System.out.println();
            System.out.println("\t                返回上级                   请输入0               ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.print("\n请输入您的选择：");
            while (true) {
                int choose = sc.nextInt();
                if (choose == 1) {
                    break;
                } else if (choose == 0) {
                    Extents.clearConsole();
                    return;
                } else {
                    System.out.print("请重新输入：  ");
                }
            }
        }
    }

    public static void isAdd( Scanner sc,int QualityMonitorID,String QualityMonitorName,String QualityMonitorAddress,String QualityMonitorContactName,String QualityMonitorContactPhone,String QualityMonitorContactEmail) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO QualityMonitor (MonitorID , MonitorName  , Address , ContactName,ContactPhone,ContactEmail  ) VALUES (?, ?, ?, ?,?,?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, QualityMonitorID);
                    preparedStatement.setString(2, QualityMonitorName);
                    preparedStatement.setString(3, QualityMonitorAddress);
                    preparedStatement.setString(4, QualityMonitorContactName);
                    preparedStatement.setString(5, QualityMonitorContactPhone);
                    preparedStatement.setString(6, QualityMonitorContactEmail);

                    //执行
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("添加质量检测方信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void QualityMonitorQuery()throws AWTException {
        while (true) {
            System.out.println("1.按质量检测方ID查询");
            System.out.println("2.按质量检测方名称查询");
            System.out.println("3.查询全部 ");
            System.out.print("\n请输入您的选择:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int QualityMonitorID0;
                int flag;
                while(true) {
                    System.out.print("输入需要查询的质量检测方ID：");
                    QualityMonitorID0 = sc.nextInt();
                    flag =getQualityMonitorID(QualityMonitorID0);
                    if (flag == -1) {
                        System.out.print("\n该质量检测方不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }

                Connection connection = null;
                String sql = "select MonitorID  , MonitorName  , Address , ContactName,ContactPhone,ContactEmail from QualityMonitor where MonitorID =?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, QualityMonitorID0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int QualityMonitorID = set.getInt("MonitorID ");
                        String QualityMonitorName = set.getString("MonitorName ");
                        String QualityMonitorAddress = set.getString("Address");
                        String QualityMonitorContactName = set.getString("ContactName");
                        String QualityMonitorContactPhone = set.getString("ContactPhone");
                        String QualityMonitorContactEmail = set.getString("ContactEmail");

                        System.out.println("\t\t质量检测方ID: " + QualityMonitorID + "\t\t质量检测方名称: " + QualityMonitorName + "\t\t质量检测方地址: " + QualityMonitorAddress+ "\t\t质量检测方负责人姓名: " + QualityMonitorContactName+ "\t\t质量检测方负责人电话: " + QualityMonitorContactPhone+ "\t\t质量检测方负责人邮箱: " + QualityMonitorContactEmail);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String QualityMonitorName0;
                String flag;
                while(true) {
                    System.out.print("输入需要查询的质量检测方名字：");
                    QualityMonitorName0 = sc.next();
                    flag = getQualityMonitorName(QualityMonitorName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n该质量检测方不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select MonitorID  , MonitorName  , Address , ContactName,ContactPhone,ContactEmail from QualityMonitor where MonitorName =?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, QualityMonitorName0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int QualityMonitorID = set.getInt("MonitorID ");
                        String QualityMonitorName = set.getString("MonitorName ");
                        String QualityMonitorAddress = set.getString("Address");
                        String QualityMonitorContactName = set.getString("ContactName");
                        String QualityMonitorContactPhone = set.getString("ContactPhone");
                        String QualityMonitorContactEmail = set.getString("ContactEmail");


                        System.out.println("\t\t质量检测方ID: " + QualityMonitorID + "\t\t质量检测方名称: " + QualityMonitorName + "\t\t质量检测方地址: " + QualityMonitorAddress+ "\t\t质量检测方负责人姓名: " + QualityMonitorContactName+ "\t\t质量检测方负责人电话: " + QualityMonitorContactPhone+ "\t\t质量检测方负责人邮箱: " + QualityMonitorContactEmail);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if (input == 3) {
                String tablename = "QualityMonitor";
                FunctionBlock.printTable(tablename);
                break;
            } else {
                System.out.print("\n输入错误!请重新输入：\n");
            }
        }
    }

    public static void deleteQualityMonitor() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n请输入要删除的质量检测方ID：");
            int QualityMonitorID = sc.nextInt();
            sc.nextLine();
            int flag = getQualityMonitorID(QualityMonitorID);
            if (flag == -1) {
                System.out.print("\n该质量检测方不存在，请重新输入\n");
            } else {
                System.out.print("\n是否删除ID为：" + QualityMonitorID + " 的质量检测方信息？ [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>首界面>功能界面>删除质量检测方信息\n");
                System.out.println("\t                继续删除                   请输入1                ");
                System.out.println();
                System.out.println("\t                返回上级                   请输入0                ");
                System.out.println("\t ----------------------------------------------------------------");
                System.out.print("\n请输入您的选择: ");
                while (true) {
                    int choose = sc.nextInt();
                    if (choose == 1) {
                        break;
                    } else if (choose == 0) {
                        Extents.clearConsole();
                        return;
                    } else {
                        System.out.print("请重新输入: ");
                    }
                }
            }
        }
    }

    public static void isDelete( Scanner sc, int flag) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="delete from QualityMonitor where MonitorID =?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1,flag);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("删除质量检测方信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void updateQualityMonitor() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n请输入要修改信息的质量检测方ID:");
            int QualityMonitorID = sc.nextInt();
            int flag = getQualityMonitorID(QualityMonitorID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("该质量检测方不存在，请重新输入\n\n\n ");
            } else {
                System.out.println(">首界面>功能界面>修改质量检测方信息\n");
                //质量检测方ID（主键）不变
                System.out.print("请输入质量检测方名称: ");
                String QualityMonitorName = sc.next();
                System.out.print("请输入质量检测方地址: ");
                String QualityMonitorAddress = sc.next();
                System.out.print("请输入质量检测方负责人姓名: ");
                String QualityMonitorContactName= sc.next();
                System.out.print("请输入质量检测方负责人电话: ");
                String QualityMonitorContactPhone = sc.next();
                System.out.print("请输入质量检测方负责人邮箱: ");
                String QualityMonitorContactEmail = sc.next();
                Extents.clearConsole();

                System.out.println(">首界面>功能界面>修改质量检测方信息");
                System.out.println("修改后——>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t质量检测方ID: " + QualityMonitorID + "\t\t质量检测方名称: " + QualityMonitorName + "\t\t质量检测方地址: " + QualityMonitorAddress+ "\t\t质量检测方负责人姓名: " + QualityMonitorContactName+ "\t\t质量检测方负责人电话: " + QualityMonitorContactPhone+ "\t\t质量检测方负责人邮箱: " + QualityMonitorContactEmail);

                System.out.print("\n\n是否修改该质量检测方信息？ [Yes(1) / No(0)] ：");
                isUpdate( sc,QualityMonitorID,QualityMonitorName,QualityMonitorAddress,QualityMonitorContactName,QualityMonitorContactPhone,QualityMonitorContactEmail);
                System.out.println("\n\n\n>首界面>功能界面>修改质量检测方信息\n");
                System.out.println("\t                继续修改                   请输入1              ");
                System.out.println();
                System.out.println("\t                返回上级                   请输入0              ");
                System.out.println("\t --------------------------------------------------------------");
                System.out.print("\n请输入您的选择:");
                while (true) {
                    int choose = sc.nextInt();
                    if (choose == 1) {
                        Extents.clearConsole();
                        break;
                    } else if (choose == 0) {
                        Extents.clearConsole();
                        return;
                    } else {
                        System.out.print("输入错误， 请重新输入: ");
                    }
                }
            }
        }
    }

    public static void isUpdate(Scanner sc,int QualityMonitorID,String QualityMonitorName,String QualityMonitorAddress,String QualityMonitorContactName,String QualityMonitorContactPhone,String QualityMonitorContactEmail) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update QualityMonitor set MonitorID =?,MonitorName =?,Address=?,ContactName=?,ContactPhone=?,ContactEmail=? where MonitorID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, QualityMonitorID);
                    preparedStatement.setString(2, QualityMonitorName);
                    preparedStatement.setString(3, QualityMonitorAddress);
                    preparedStatement.setString(4, QualityMonitorContactName);
                    preparedStatement.setString(5, QualityMonitorContactPhone);
                    preparedStatement.setString(6, QualityMonitorContactEmail);
                    preparedStatement.setInt(7, QualityMonitorID);

                    //执行
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("修改质量检测方信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：\n");
        }
    }
    public static int getQualityMonitorID(int QualityMonitorID) {

        Connection connection =null;
        String sql="select * from QualityMonitor";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while(set.next()){
                int id  =set.getInt("MonitorID");
                if(QualityMonitorID==id){
                    return id;
                }
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close(set,preparedStatement,connection);
        }
    }

    public static String getQualityMonitorName(String QualityMonitorName) {

        Connection connection =null;
        String sql="select * from QualityMonitor";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("MonitorName");
                if(QualityMonitorName.equals(name)){
                    return name;
                }
            }
            return "-1";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close(set,preparedStatement,connection);
        }
    }




}
