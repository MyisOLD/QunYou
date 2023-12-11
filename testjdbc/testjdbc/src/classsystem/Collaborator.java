package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Collaborator {
    public static void CollaboratorAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">首界面>功能界面>添加合作方信息\n");
            Scanner sc = new Scanner(System.in);

            System.out.print("请输入合作方ID: ");
            int CollaboratorID=sc.nextInt();
            System.out.print("请输入合作方名称: ");
            String CollaboratorName = sc.next();
            System.out.print("请输入合作方地址: ");
            String CollaboratorAddress = sc.next();
            System.out.print("请输入合作方负责人姓名: ");
            String CollaboratorContactName= sc.next();
            System.out.print("请输入合作方负责人电话: ");
            String CollaboratorContactPhone = sc.next();
            System.out.print("请输入合作方负责人邮箱: ");
            String CollaboratorContactEmail = sc.next();



            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t合作方ID: " + CollaboratorID + "\t\t合作方名称: " + CollaboratorName + "\t\t合作方地址: " + CollaboratorAddress+ "\t\t合作方负责人姓名: " + CollaboratorContactName+ "\t\t合作方负责人电话: " + CollaboratorContactPhone+ "\t\t合作方负责人邮箱: " + CollaboratorContactEmail);
            System.out.print("\n\n是否添加该合作方信息？ [Yes(1) / No(0)] ：");
            isAdd( sc,CollaboratorID,CollaboratorName,CollaboratorAddress,CollaboratorContactName,CollaboratorContactPhone,CollaboratorContactEmail);
            System.out.println("\n\n\n>首界面>功能界面>添加合作方信息\n");
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

    public static void isAdd( Scanner sc,int CollaboratorID,String CollaboratorName,String CollaboratorAddress,String CollaboratorContactName,String CollaboratorContactPhone,String CollaboratorContactEmail) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO Collaborator (CollaboratorID , CollaboratorName , Address , ContactName,ContactPhone,ContactEmail  ) VALUES (?, ?, ?, ?,?,?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, CollaboratorID);
                    preparedStatement.setString(2, CollaboratorName);
                    preparedStatement.setString(3, CollaboratorAddress);
                    preparedStatement.setString(4, CollaboratorContactName);
                    preparedStatement.setString(5, CollaboratorContactPhone);
                    preparedStatement.setString(6, CollaboratorContactEmail);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("添加合作方信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void CollaboratorQuery()throws AWTException {
        while (true) {
            System.out.println("1.按合作方ID查询");
            System.out.println("2.按合作方名称查询");
            System.out.println("3.查询全部 ");
            System.out.print("\n请输入您的选择:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int CollaboratorID0;
                int flag;
                while(true) {
                    System.out.print("输入需要查询的合作方ID：");
                    CollaboratorID0 = sc.nextInt();
                    flag =getCollaboratorID(CollaboratorID0);
                    if (flag == -1) {
                        System.out.print("\n该合作方不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }

                Connection connection = null;
                String sql = "select CollaboratorID , CollaboratorName , Address , ContactName,ContactPhone,ContactEmail from Collaborator where CollaboratorID=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, CollaboratorID0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int CollaboratorID = set.getInt("CollaboratorID");
                        String CollaboratorName = set.getString("CollaboratorName");
                        String CollaboratorAddress = set.getString("Address");
                        String CollaboratorContactName = set.getString("ContactName");
                        String CollaboratorContactPhone = set.getString("ContactPhone");
                        String CollaboratorContactEmail = set.getString("ContactEmail");

                        System.out.println("\t\t合作方ID: " + CollaboratorID + "\t\t合作方名称: " + CollaboratorName + "\t\t合作方地址: " + CollaboratorAddress+ "\t\t合作方负责人姓名: " + CollaboratorContactName+ "\t\t合作方负责人电话: " + CollaboratorContactPhone+ "\t\t合作方负责人邮箱: " + CollaboratorContactEmail);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String CollaboratorName0;
                String flag;
                while(true) {
                    System.out.print("输入需要查询的合作方名字：");
                    CollaboratorName0 = sc.next();
                    flag = getCollaboratorName(CollaboratorName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n该合作方不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select CollaboratorID , CollaboratorName , Address , ContactName,ContactPhone,ContactEmail from Collaborator where CollaboratorName=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, CollaboratorName0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int CollaboratorID = set.getInt("CollaboratorID");
                        String CollaboratorName = set.getString("CollaboratorName");
                        String CollaboratorAddress = set.getString("Address");
                        String CollaboratorContactName = set.getString("ContactName");
                        String CollaboratorContactPhone = set.getString("ContactPhone");
                        String CollaboratorContactEmail = set.getString("ContactEmail");


                        System.out.println("\t\t合作方ID: " + CollaboratorID + "\t\t合作方名称: " + CollaboratorName + "\t\t合作方地址: " + CollaboratorAddress+ "\t\t合作方负责人姓名: " + CollaboratorContactName+ "\t\t合作方负责人电话: " + CollaboratorContactPhone+ "\t\t合作方负责人邮箱: " + CollaboratorContactEmail);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if (input == 3) {
                String tablename = "Collaborator";
                FunctionBlock.printTable(tablename);
                break;
            } else {
                System.out.print("\n输入错误!请重新输入：\n");
            }
        }
    }

    public static void deleteCollaborator() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n请输入要删除的合作方ID：");
            int CollaboratorID = sc.nextInt();
            sc.nextLine();
            int flag = getCollaboratorID(CollaboratorID);
            if (flag == -1) {
                System.out.print("\n该合作方不存在，请重新输入\n");
            } else {
                System.out.print("\n是否删除ID为：" + CollaboratorID + " 的合作方信息？ [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>首界面>功能界面>删除合作方信息\n");
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
                String sql ="delete from Collaborator where CollaboratorID=?";
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
                System.out.println("删除合作方信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void updateCollaborator() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n请输入要修改信息的合作方ID:");
            int CollaboratorID = sc.nextInt();
            int flag = getCollaboratorID(CollaboratorID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("该合作方不存在，请重新输入\n\n\n ");
            } else {
                System.out.println(">首界面>功能界面>修改合作方信息\n");
                //合作方ID（主键）不变
                System.out.print("请输入合作方名称: ");
                String CollaboratorName = sc.next();
                System.out.print("请输入合作方地址: ");
                String CollaboratorAddress = sc.next();
                System.out.print("请输入合作方负责人姓名: ");
                String CollaboratorContactName= sc.next();
                System.out.print("请输入合作方负责人电话: ");
                String CollaboratorContactPhone = sc.next();
                System.out.print("请输入合作方负责人邮箱: ");
                String CollaboratorContactEmail = sc.next();
                Extents.clearConsole();

                System.out.println(">首界面>功能界面>修改合作方信息");
                System.out.println("修改后——>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t合作方ID: " + CollaboratorID + "\t\t合作方名称: " + CollaboratorName + "\t\t合作方地址: " + CollaboratorAddress+ "\t\t合作方负责人姓名: " + CollaboratorContactName+ "\t\t合作方负责人电话: " + CollaboratorContactPhone+ "\t\t合作方负责人邮箱: " + CollaboratorContactEmail);

                System.out.print("\n\n是否修改该合作方信息？ [Yes(1) / No(0)] ：");
                isUpdate( sc,CollaboratorID,CollaboratorName,CollaboratorAddress,CollaboratorContactName,CollaboratorContactPhone,CollaboratorContactEmail);
                System.out.println("\n\n\n>首界面>功能界面>修改合作方信息\n");
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

    public static void isUpdate(Scanner sc,int CollaboratorID,String CollaboratorName,String CollaboratorAddress,String CollaboratorContactName,String CollaboratorContactPhone,String CollaboratorContactEmail) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update Collaborator set CollaboratorID=?,CollaboratorName=?,Address=?,ContactName=?,ContactPhone=?,ContactEmail=? where CollaboratorID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, CollaboratorID);
                    preparedStatement.setString(2, CollaboratorName);
                    preparedStatement.setString(3, CollaboratorAddress);
                    preparedStatement.setString(4, CollaboratorContactName);
                    preparedStatement.setString(5, CollaboratorContactPhone);
                    preparedStatement.setString(6, CollaboratorContactEmail);
                    preparedStatement.setInt(7, CollaboratorID);

                    //执行
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("修改合作方信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：\n");
        }
    }
    public static int getCollaboratorID(int CollaboratorID) {

        Connection connection =null;
        String sql="select * from Collaborator";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while(set.next()){
                int id  =set.getInt("CollaboratorID");
                if(CollaboratorID==id){
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

    public static String getCollaboratorName(String CollaboratorName) {

        Connection connection =null;
        String sql="select * from Collaborator";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("CollaboratorName");
                if(CollaboratorName.equals(name)){
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
