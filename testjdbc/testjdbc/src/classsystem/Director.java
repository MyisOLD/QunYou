package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Director {
    public static void DirectorAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">首界面>功能界面>添加主任信息\n");
            Scanner sc = new Scanner(System.in);

            System.out.print("请输入主任ID: ");
            int DirectorID=sc.nextInt();
            System.out.print("请输入主任名称: ");
            String DirectorName = sc.next();
            System.out.print("请输入主任上任日期(例：2003-1-1): ");
            String DirectorDate = sc.next();
            System.out.print("请输入任期: ");
            int DirectorTerm=sc.nextInt();
            System.out.print("请输入实验室ID: ");
            int DirectorLabID=sc.nextInt();




            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t主任ID: " + DirectorID + "\t\t主任名称: " + DirectorName + "\t\t主任上任日期: " + DirectorDate+ "\t\t主任任期: " + DirectorTerm+ "\t\t实验室ID: " + DirectorLabID);
            System.out.print("\n\n是否添加该主任信息？ [Yes(1) / No(0)] ：");
            isAdd( sc,DirectorID,DirectorName,DirectorDate,DirectorTerm,DirectorLabID);
            System.out.println("\n\n\n>首界面>功能界面>添加主任信息\n");
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

    public static void isAdd( Scanner sc,int DirectorID,String DirectorName,String DirectorDate,int DirectorTerm,int DirectorLabID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO Director (DirectorID , DirectorName , StartDate, Term,LabID  ) VALUES (?, ?, ?, ?,?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, DirectorID);
                    preparedStatement.setString(2, DirectorName);
                    preparedStatement.setString(3, DirectorDate);
                    preparedStatement.setInt(4, DirectorTerm);
                    preparedStatement.setInt(5, DirectorLabID);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("添加主任信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void DirectorQuery()throws AWTException {
        while (true) {
            System.out.println("1.按主任ID查询");
            System.out.println("2.按主任名称查询");
            System.out.println("3.查询全部 ");
            System.out.print("\n请输入您的选择:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int DirectorID0;
                int flag;
                while(true) {
                    System.out.print("输入需要查询的主任ID：");
                    DirectorID0 = sc.nextInt();
                    flag =getDirectorID(DirectorID0);
                    if (flag == -1) {
                        System.out.print("\n该主任不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select DirectorID, DirectorName, StartDate, Term,LabID  from Director where DirectorID=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, DirectorID0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int DirectorID = set.getInt("DirectorID");
                        String DirectorName = set.getString("DirectorName");
                        String DirectorDate = set.getString("StartDate");
                        int DirectorTerm = set.getInt("Term");
                        int DirectorLabID = set.getInt("LabID");


                        System.out.println("\t\t主任ID: " + DirectorID + "\t\t主任名称: " + DirectorName + "\t\t主任上任日期: " + DirectorDate+ "\t\t主任任期: " + DirectorTerm+ "\t\t实验室ID: " + DirectorLabID);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String DirectorName0;
                String flag;
                while(true) {
                    System.out.print("输入需要查询的主任名字：");
                    DirectorName0 = sc.next();
                    flag = getDirectorName(DirectorName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n该主任不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select DirectorID, DirectorName, StartDate, Term,LabID  from Director where DirectorName=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, DirectorName0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int DirectorID = set.getInt("DirectorID");
                        String DirectorName = set.getString("DirectorName");
                        String DirectorDate = set.getString("StartDate");
                        int DirectorTerm = set.getInt("Term");
                        int DirectorLabID = set.getInt("LabID");


                        System.out.println("\t\t主任ID: " + DirectorID + "\t\t主任名称: " + DirectorName + "\t\t主任上任日期: " + DirectorDate+ "\t\t主任任期: " + DirectorTerm+ "\t\t实验室ID: " + DirectorLabID);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if (input == 3) {
                String tablename = "Director";
                FunctionBlock.printTable(tablename);
                break;
            } else {
                System.out.print("\n输入错误!请重新输入：\n");
            }
        }
    }

    public static void deleteDirector() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n请输入要删除的主任ID：");
            int DirectorID = sc.nextInt();
            sc.nextLine();
            int flag = getDirectorID(DirectorID);
            if (flag == -1) {
                System.out.print("\n该主任不存在，请重新输入\n");
            } else {
                System.out.print("\n是否删除ID为：" + DirectorID + " 的主任信息？ [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>首界面>功能界面>删除主任信息\n");
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
                String sql ="delete from Director where DirectorID=?";
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
                System.out.println("删除主任信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void updateDirector() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n请输入要修改信息的主任ID:");
            int DirectorID = sc.nextInt();
            int flag = getDirectorID(DirectorID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("该主任不存在，请重新输入\n\n\n ");
            } else {
                System.out.println(">首界面>功能界面>修改主任信息\n");
                //主任ID（主键）不变
                System.out.print("请输入主任名称: ");
                String DirectorName = sc.next();
                System.out.print("请输入主任上任日期(例：2003-1-1): ");
                String DirectorDate = sc.next();
                System.out.print("请输入任期: ");
                int DirectorTerm=sc.nextInt();
                System.out.print("请输入实验室ID: ");
                int DirectorLabID=sc.nextInt();
                Extents.clearConsole();

                System.out.println(">首界面>功能界面>修改主任信息");
                System.out.println("修改后——>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t主任ID: " + DirectorID + "\t\t主任名称: " + DirectorName + "\t\t主任上任日期: " + DirectorDate+ "\t\t主任任期: " + DirectorTerm+ "\t\t实验室ID: " + DirectorLabID);

                System.out.print("\n\n是否修改该主任信息？ [Yes(1) / No(0)] ：");
                isUpdate( sc,DirectorID,DirectorName,DirectorDate,DirectorTerm,DirectorLabID);
                System.out.println("\n\n\n>首界面>功能界面>修改主任信息\n");
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

    public static void isUpdate( Scanner sc,int DirectorID,String DirectorName,String DirectorDate,int DirectorTerm,int DirectorLabID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update Director set DirectorID=?,DirectorName=?,StartDate =?,Term =?,LabID =? where DirectorID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, DirectorID);
                    preparedStatement.setString(2, DirectorName);
                    preparedStatement.setString(3, DirectorDate);
                    preparedStatement.setInt(4, DirectorTerm);
                    preparedStatement.setInt(5, DirectorLabID);
                    preparedStatement.setInt(6, DirectorID);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("修改主任信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：\n");
        }
    }
    public static int getDirectorID(int DirectorID) {

        Connection connection =null;
        String sql="select * from Director";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while(set.next()){
                int id  =set.getInt("DirectorID");
                if(DirectorID==id){
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

    public static String getDirectorName(String DirectorName) {

        Connection connection =null;
        String sql="select * from Director";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("DirectorName");
                if(DirectorName.equals(name)){
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
