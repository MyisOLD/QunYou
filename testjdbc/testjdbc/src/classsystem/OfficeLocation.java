package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OfficeLocation {

    public static void OfficeLocationAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">首界面>功能界面>添加办公场地信息\n");
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入办公场地ID: ");
            int locationID=sc.nextInt();
            System.out.print("请输入办公场地名称: ");
            String locationName = sc.next();
            System.out.print("请输入办公面积: ");
            int locationArea = sc.nextInt();
            System.out.print("请输入办公场地地址: ");
            String locationAddress = sc.next();


            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t办公场地ID: " + locationID + "\t\t办公场地名称: " + locationName + "\t\t办公面积: " + locationArea+ "\t\t办公场地地址: " + locationAddress);
            System.out.print("\n\n是否添加该办公场地信息？ [Yes(1) / No(0)] ：");
            isAdd( sc,locationID,locationName,locationArea,locationAddress);
            System.out.println("\n\n\n>首界面>功能界面>添加办公场地信息\n");
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

    public static void isAdd( Scanner sc,int locationID,String locationName,int locationArea,String locationAddress) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO OfficeLocation (LocationID, LocationName, OfficeArea, Address) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, locationID);
                    preparedStatement.setString(2, locationName);
                    preparedStatement.setInt(3, locationArea);
                    preparedStatement.setString(4, locationAddress);

                    //执行
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("添加办公场地信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void OfficeLocationQuery()throws AWTException {
        while (true) {
            System.out.println("1.按办公场地ID查询");
            System.out.println("2.按办公场地名称查询");
            System.out.println("3.查询全部 ");
            System.out.print("\n请输入您的选择:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int locationID0;
                int flag;
                while(true) {
                    System.out.print("输入需要查询的办公场地ID：");
                    locationID0 = sc.nextInt();
                    flag = getLocationID(locationID0);
                    if (flag == -1) {
                        System.out.print("\n该办公场地不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }

                Connection connection = null;
                String sql = "select LocationID, LocationName, OfficeArea, Address from OfficeLocation where LocationID=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, locationID0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int locationID = set.getInt("LocationID");
                        String locationName = set.getString("LocationName");
                        int locationArea = set.getInt("OfficeArea");
                        String locationAddress = set.getString("Address");

                        System.out.println("\t\t办公场地ID: " + locationID + "\t\t办公场地名称: " + locationName + "\t\t办公面积: " + locationArea+ "\t\t办公场地地址: " + locationAddress);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String locationName0;
                String flag;
                while(true) {
                    System.out.print("输入需要查询的办公场地名字：");
                    locationName0 = sc.next();
                    flag = getLocationName(locationName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n该办公场地不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select LocationID, LocationName, OfficeArea, Address from OfficeLocation where LocationName=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, locationName0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int locationID = set.getInt("LocationID");
                        String locationName = set.getString("LocationName");
                        int locationArea = set.getInt("OfficeArea");
                        String locationAddress = set.getString("Address");

                        System.out.println("\t\t办公场地ID: " + locationID + "\t\t办公场地名称: " + locationName + "\t\t办公面积: " + locationArea+ "\t\t办公场地地址: " + locationAddress);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if (input == 3) {
                String tablename = "OfficeLocation";
                FunctionBlock.printTable(tablename);
                break;
            } else {
                System.out.print("\n输入错误!请重新输入：\n");
            }
        }
    }

    public static void deleteOfficeLocation() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n请输入要删除的办公场地ID：");
            int locationID = sc.nextInt();
            sc.nextLine();
            int flag = getLocationID(locationID);
            if (flag == -1) {
                System.out.print("\n该办公场地不存在，请重新输入\n");
            } else {
                System.out.print("\n是否删除ID为：" + locationID + " 的办公场地信息？ [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>首界面>功能界面>删除办公场地信息\n");
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
                String sql ="delete from OfficeLocation where LocationID=?";
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
                System.out.println("删除办公场地信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void updateLocation() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n请输入要修改信息的办公场地ID:");
            int locationID = sc.nextInt();
            int flag = getLocationID(locationID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("该办公场地不存在，请重新输入\n\n\n ");
            } else {
                System.out.println(">首界面>功能界面>修改办公场地信息\n");
                //办公场地ID（主键）不变
                System.out.print("请输入办公场地名称: ");
                String locationName = sc.next();
                System.out.print("请输入办公面积: ");
                int locationArea = sc.nextInt();
                System.out.print("请输入办公场地地址: ");
                String locationAddress = sc.next();
                Extents.clearConsole();

                System.out.println(">首界面>功能界面>修改办公场地信息");
                System.out.println("修改后——>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t办公场地ID: " + locationID + "\t\t办公场地名称: " + locationName + "\t\t办公面积: " + locationArea+ "\t\t办公场地地址: " + locationAddress);

                System.out.print("\n\n是否添加该办公场地信息？ [Yes(1) / No(0)] ：");
                isUpdate( sc,locationID,locationName,locationArea,locationAddress);
                System.out.println("\n\n\n>首界面>功能界面>修改办公场地信息\n");
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

    public static void isUpdate(Scanner sc,int locationID,String locationName,int locationArea,String locationAddress) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update OfficeLocation set LocationID=?,LocationName=?,OfficeArea=?,Address=? where LocationID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, locationID);
                    preparedStatement.setString(2, locationName);
                    preparedStatement.setInt(3, locationArea);
                    preparedStatement.setString(4, locationAddress);
                    preparedStatement.setInt(5, locationID);

                    //执行
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("修改办公场地信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：\n");
        }
    }
    public static int getLocationID(int locationID) {

        Connection connection =null;
        String sql="select * from OfficeLocation";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while(set.next()){
                int id  =set.getInt("LocationID");
                if(locationID==id){
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

    public static String getLocationName(String locationName) {

        Connection connection =null;
        String sql="select * from OfficeLocation";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("LocationName");
                if(locationName.equals(name)){
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
