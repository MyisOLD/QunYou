package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Secretary{
    public static void SecretaryAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">首界面>功能界面>添加秘书信息\n");
            Scanner sc = new Scanner(System.in);

            System.out.print("请输入秘书ID: ");
            int SecretaryID=sc.nextInt();
            System.out.print("请输入员工ID: ");
            int SecretaryEmployeeID=sc.nextInt();
            System.out.print("请输入秘书名称: ");
            String SecretaryName = sc.next();
            System.out.print("请输入秘书性别: ");
            String SecretaryGender = sc.next();
            System.out.print("请输入秘书年龄: ");
            int SecretaryAge=sc.nextInt();
            System.out.print("请输入秘书上任日期(例：2003-1-1): ");
            String SecretaryDate = sc.next();
            System.out.print("请输入秘书职责: ");
            String SecretaryResponsibilities = sc.next();

            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t秘书ID: " + SecretaryID + "\t\t员工ID: " + SecretaryEmployeeID + "\t\t秘书名称: " + SecretaryName+ "\t\t秘书性别: " + SecretaryGender+ "\t\t秘书年龄: " + SecretaryAge+ "\t\t秘书上任日期: " + SecretaryDate+ "\t\t秘书职责: " + SecretaryResponsibilities);
            System.out.print("\n\n是否添加该秘书信息？ [Yes(1) / No(0)] ：");
            isAdd( sc,SecretaryID,SecretaryEmployeeID,SecretaryName,SecretaryGender,SecretaryAge,SecretaryDate,SecretaryResponsibilities);
            System.out.println("\n\n\n>首界面>功能界面>添加秘书信息\n");
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

    public static void isAdd( Scanner sc,int SecretaryID,int SecretaryEmployeeID,String SecretaryName,String SecretaryGender,int SecretaryAge,String SecretaryDate,String SecretaryResponsibilities) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO Secretary (SecretaryID , EmployeeID, SecretaryName , Gender, Age, HireDate, Responsibilities) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, SecretaryID);
                    preparedStatement.setInt(2, SecretaryEmployeeID);
                    preparedStatement.setString(3, SecretaryName);
                    preparedStatement.setString(4, SecretaryGender);
                    preparedStatement.setInt(5, SecretaryAge);
                    preparedStatement.setString(6, SecretaryDate);
                    preparedStatement.setString(7, SecretaryResponsibilities);


                    //执行
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("添加秘书信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void SecretaryQuery()throws AWTException {
        while (true) {
            System.out.println("1.按秘书ID查询");
            System.out.println("2.按秘书名称查询");
            System.out.println("3.查询全部 ");
            System.out.print("\n请输入您的选择:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int SecretaryID0;
                int flag;
                while(true) {
                    System.out.print("输入需要查询的秘书ID：");
                    SecretaryID0 = sc.nextInt();
                    flag =getSecretaryID(SecretaryID0);
                    if (flag == -1) {
                        System.out.print("\n该秘书不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select SecretaryID, EmployeeID, SecretaryName, Gender, Age, HireDate, Responsibilities from Secretary where SecretaryID=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, SecretaryID0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int SecretaryID = set.getInt("SecretaryID");
                        int SecretaryEmployeeID= set.getInt("EmployeeID");
                        String SecretaryName = set.getString("SecretaryName");
                        String SecretaryGender = set.getString("Gender");
                        int SecretaryAge = set.getInt("Age");
                        String SecretaryDate = set.getString("HireDate");
                        String SecretaryResponsibilities = set.getString("Responsibilities");


                        System.out.println("\t\t秘书ID: " + SecretaryID + "\t\t员工ID: " + SecretaryEmployeeID + "\t\t秘书名称: " + SecretaryName+ "\t\t秘书性别: " + SecretaryGender+ "\t\t秘书年龄: " + SecretaryAge+ "\t\t秘书上任日期: " + SecretaryDate+ "\t\t秘书职责: " + SecretaryResponsibilities);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String SecretaryName0;
                String flag;
                while(true) {
                    System.out.print("输入需要查询的秘书名字：");
                    SecretaryName0 = sc.next();
                    flag = getSecretaryName(SecretaryName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n该秘书不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select SecretaryID, EmployeeID, SecretaryName, Gender, Age, HireDate, Responsibilities from Secretary where SecretaryName=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, SecretaryName0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int SecretaryID = set.getInt("SecretaryID");
                        int SecretaryEmployeeID= set.getInt("EmployeeID");
                        String SecretaryName = set.getString("SecretaryName");
                        String SecretaryGender = set.getString("Gender");
                        int SecretaryAge = set.getInt("Age");
                        String SecretaryDate = set.getString("HireDate");
                        String SecretaryResponsibilities = set.getString("Responsibilities");


                        System.out.println("\t\t秘书ID: " + SecretaryID + "\t\t员工ID: " + SecretaryEmployeeID + "\t\t秘书名称: " + SecretaryName+ "\t\t秘书性别: " + SecretaryGender+ "\t\t秘书年龄: " + SecretaryAge+ "\t\t秘书上任日期: " + SecretaryDate+ "\t\t秘书职责: " + SecretaryResponsibilities);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if (input == 3) {
                String tablename = "Secretary";
                FunctionBlock.printTable(tablename);
                break;
            } else {
                System.out.print("\n输入错误!请重新输入：\n");
            }
        }
    }

    public static void deleteSecretary() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n请输入要删除的秘书ID：");
            int SecretaryID = sc.nextInt();
            sc.nextLine();
            int flag = getSecretaryID(SecretaryID);
            if (flag == -1) {
                System.out.print("\n该秘书不存在，请重新输入\n");
            } else {
                System.out.print("\n是否删除ID为：" + SecretaryID + " 的秘书信息？ [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>首界面>功能界面>删除秘书信息\n");
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
                String sql ="delete from Secretary where SecretaryID=?";
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
                System.out.println("删除秘书信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void updateSecretary() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n请输入要修改信息的秘书ID:");
            int SecretaryID = sc.nextInt();
            int flag = getSecretaryID(SecretaryID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("该秘书不存在，请重新输入\n\n\n ");
            } else {
                System.out.println(">首界面>功能界面>修改秘书信息\n");
                //秘书ID（主键）不变
                System.out.print("请输入员工ID: ");
                int SecretaryEmployeeID=sc.nextInt();
                System.out.print("请输入秘书名称: ");
                String SecretaryName = sc.next();
                System.out.print("请输入秘书性别: ");
                String SecretaryGender = sc.next();
                System.out.print("请输入秘书年龄: ");
                int SecretaryAge=sc.nextInt();
                System.out.print("请输入秘书上任日期(例：2003-1-1): ");
                String SecretaryDate = sc.next();
                System.out.print("请输入秘书职责: ");
                String SecretaryResponsibilities = sc.next();
                Extents.clearConsole();

                System.out.println(">首界面>功能界面>修改秘书信息");
                System.out.println("修改后——>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t秘书ID: " + SecretaryID + "\t\t员工ID: " + SecretaryEmployeeID + "\t\t秘书名称: " + SecretaryName+ "\t\t秘书性别: " + SecretaryGender+ "\t\t秘书年龄: " + SecretaryAge+ "\t\t秘书上任日期: " + SecretaryDate+ "\t\t秘书职责: " + SecretaryResponsibilities);

                System.out.print("\n\n是否修改该秘书信息？ [Yes(1) / No(0)] ：");
                isUpdate( sc,SecretaryID,SecretaryEmployeeID,SecretaryName,SecretaryGender,SecretaryAge,SecretaryDate,SecretaryResponsibilities);
                System.out.println("\n\n\n>首界面>功能界面>修改秘书信息\n");
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

    public static void isUpdate( Scanner sc,int SecretaryID,int SecretaryEmployeeID,String SecretaryName,String SecretaryGender,int SecretaryAge,String SecretaryDate,String SecretaryResponsibilities) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update Secretary set SecretaryID=?,EmployeeID=?,SecretaryName =?,Gender =?,Age =?, HireDate=?,Responsibilities=?where SecretaryID=?";

                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, SecretaryID);
                    preparedStatement.setInt(2, SecretaryEmployeeID);
                    preparedStatement.setString(3, SecretaryName);
                    preparedStatement.setString(4, SecretaryGender);
                    preparedStatement.setInt(5, SecretaryAge);
                    preparedStatement.setString(6, SecretaryDate);
                    preparedStatement.setString(7, SecretaryResponsibilities);
                    preparedStatement.setInt(8, SecretaryID);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("修改秘书信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：\n");
        }
    }
    public static int getSecretaryID(int SecretaryID) {

        Connection connection =null;
        String sql="select * from Secretary";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while(set.next()){
                int id  =set.getInt("SecretaryID");
                if(SecretaryID==id){
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

    public static String getSecretaryName(String SecretaryName) {

        Connection connection =null;
        String sql="select * from Secretary";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("SecretaryName");
                if(SecretaryName.equals(name)){
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
