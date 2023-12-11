package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Client {

    public static void ClientAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">首界面>功能界面>添加委托方信息\n");
            Scanner sc = new Scanner(System.in);

            System.out.print("请输入委托方ID: ");
            int clientID=sc.nextInt();
            System.out.print("请输入委托方名称: ");
            String clientName = sc.next();
            System.out.print("请输入委托方地址: ");
            String clientAddress = sc.next();
            System.out.print("请输入委托方负责人姓名: ");
            String clientContactName= sc.next();
            System.out.print("请输入委托方负责人电话: ");
            String clientContactPhone = sc.next();
            System.out.print("请输入委托方负责人邮箱: ");
            String clientContactEmail = sc.next();



            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t委托方ID: " + clientID + "\t\t委托方名称: " + clientName + "\t\t委托方地址: " + clientAddress+ "\t\t委托方负责人姓名: " + clientContactName+ "\t\t委托方负责人电话: " + clientContactPhone+ "\t\t委托方负责人邮箱: " + clientContactEmail);
            System.out.print("\n\n是否添加该委托方信息？ [Yes(1) / No(0)] ：");
            isAdd( sc,clientID,clientName,clientAddress,clientContactName,clientContactPhone,clientContactEmail);
            System.out.println("\n\n\n>首界面>功能界面>添加委托方信息\n");
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

    public static void isAdd( Scanner sc,int clientID,String clientName,String clientAddress,String clientContactName,String clientContactPhone,String clientContactEmail) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO Client (ClientID , ClientName , Address , ContactName,ContactPhone,ContactEmail  ) VALUES (?, ?, ?, ?,?,?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, clientID);
                    preparedStatement.setString(2, clientName);
                    preparedStatement.setString(3, clientAddress);
                    preparedStatement.setString(4, clientContactName);
                    preparedStatement.setString(5, clientContactPhone);
                    preparedStatement.setString(6, clientContactEmail);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("添加委托方信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void ClientQuery()throws AWTException {
        while (true) {
            System.out.println("1.按委托方ID查询");
            System.out.println("2.按委托方名称查询");
            System.out.println("3.查询全部 ");
            System.out.print("\n请输入您的选择:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int clientID0;
                int flag;
                while(true) {
                    System.out.print("输入需要查询的委托方ID：");
                    clientID0 = sc.nextInt();
                    flag =getClientID(clientID0);
                    if (flag == -1) {
                        System.out.print("\n该委托方不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }

                Connection connection = null;
                String sql = "select ClientID , ClientName , Address , ContactName,ContactPhone,ContactEmail from Client where ClientID=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, clientID0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int clientID = set.getInt("ClientID");
                        String clientName = set.getString("ClientName");
                        String clientAddress = set.getString("Address");
                        String clientContactName = set.getString("ContactName");
                        String clientContactPhone = set.getString("ContactPhone");
                        String clientContactEmail = set.getString("ContactEmail");

                        System.out.println("\t\t委托方ID: " + clientID + "\t\t委托方名称: " + clientName + "\t\t委托方地址: " + clientAddress+ "\t\t委托方负责人姓名: " + clientContactName+ "\t\t委托方负责人电话: " + clientContactPhone+ "\t\t委托方负责人邮箱: " + clientContactEmail);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String clientName0;
                String flag;
                while(true) {
                    System.out.print("输入需要查询的委托方名字：");
                    clientName0 = sc.next();
                    flag = getClientName(clientName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n该委托方不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select ClientID , ClientName , Address , ContactName,ContactPhone,ContactEmail from Client where ClientName=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, clientName0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int clientID = set.getInt("ClientID");
                        String clientName = set.getString("ClientName");
                        String clientAddress = set.getString("Address");
                        String clientContactName = set.getString("ContactName");
                        String clientContactPhone = set.getString("ContactPhone");
                        String clientContactEmail = set.getString("ContactEmail");


                        System.out.println("\t\t委托方ID: " + clientID + "\t\t委托方名称: " + clientName + "\t\t委托方地址: " + clientAddress+ "\t\t委托方负责人姓名: " + clientContactName+ "\t\t委托方负责人电话: " + clientContactPhone+ "\t\t委托方负责人邮箱: " + clientContactEmail);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if (input == 3) {
                String tablename = "Client";
                FunctionBlock.printTable(tablename);
                break;
            } else {
                System.out.print("\n输入错误!请重新输入：\n");
            }
        }
    }

    public static void deleteClient() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n请输入要删除的委托方ID：");
            int clientID = sc.nextInt();
            sc.nextLine();
            int flag = getClientID(clientID);
            if (flag == -1) {
                System.out.print("\n该委托方不存在，请重新输入\n");
            } else {
                System.out.print("\n是否删除ID为：" + clientID + " 的委托方信息？ [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>首界面>功能界面>删除委托方信息\n");
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
                String sql ="delete from Client where ClientID=?";
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
                System.out.println("删除委托方信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void updateClient() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n请输入要修改信息的委托方ID:");
            int clientID = sc.nextInt();
            int flag = getClientID(clientID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("该委托方不存在，请重新输入\n\n\n ");
            } else {
                System.out.println(">首界面>功能界面>修改委托方信息\n");
                //委托方ID（主键）不变
                System.out.print("请输入委托方名称: ");
                String clientName = sc.next();
                System.out.print("请输入委托方地址: ");
                String clientAddress = sc.next();
                System.out.print("请输入委托方负责人姓名: ");
                String clientContactName= sc.next();
                System.out.print("请输入委托方负责人电话: ");
                String clientContactPhone = sc.next();
                System.out.print("请输入委托方负责人邮箱: ");
                String clientContactEmail = sc.next();
                Extents.clearConsole();

                System.out.println(">首界面>功能界面>修改委托方信息");
                System.out.println("修改后——>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t委托方ID: " + clientID + "\t\t委托方名称: " + clientName + "\t\t委托方地址: " + clientAddress+ "\t\t委托方负责人姓名: " + clientContactName+ "\t\t委托方负责人电话: " + clientContactPhone+ "\t\t委托方负责人邮箱: " + clientContactEmail);

                System.out.print("\n\n是否修改该委托方信息？ [Yes(1) / No(0)] ：");
                isUpdate( sc,clientID,clientName,clientAddress,clientContactName,clientContactPhone,clientContactEmail);
                System.out.println("\n\n\n>首界面>功能界面>修改委托方信息\n");
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

    public static void isUpdate(Scanner sc,int clientID,String clientName,String clientAddress,String clientContactName,String clientContactPhone,String clientContactEmail) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update Client set ClientID=?,ClientName=?,Address=?,ContactName=?,ContactPhone=?,ContactEmail=? where ClientID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, clientID);
                    preparedStatement.setString(2, clientName);
                    preparedStatement.setString(3, clientAddress);
                    preparedStatement.setString(4, clientContactName);
                    preparedStatement.setString(5, clientContactPhone);
                    preparedStatement.setString(6, clientContactEmail);
                    preparedStatement.setInt(7, clientID);

                    //执行
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("修改委托方信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：\n");
        }
    }
    public static int getClientID(int clientID) {

        Connection connection =null;
        String sql="select * from Client";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while(set.next()){
                int id  =set.getInt("ClientID");
                if(clientID==id){
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

    public static String getClientName(String clientName) {

        Connection connection =null;
        String sql="select * from Client";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("ClientName");
                if(clientName.equals(name)){
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
