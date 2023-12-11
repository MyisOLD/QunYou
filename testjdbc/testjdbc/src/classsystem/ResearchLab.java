package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ResearchLab {

    public static void LabAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">首界面>功能界面>添加研究室信息\n");
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入研究室ID: ");
            int labId=sc.nextInt();
            System.out.print("请输入研究室名称: ");
            String labName = sc.next();
            System.out.print("请输入研究室研究方向: ");
            String researchDirection = sc.next();
            System.out.print("请输入主任ID: ");
            int directorID = sc.nextInt();
            System.out.print("请输入秘书ID: ");
            int secretaryID =sc.nextInt();
            System.out.print("请输入场地ID: ");
            int officeLocationID =sc.nextInt();


            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t研究室ID: " + labId + "\t\t研究室名称: " + labName + "\t\t研究方向: " + researchDirection+ "\t\t主任ID: " + directorID
                    +"\t\t秘书ID: " +secretaryID+"\t\t场地ID: " +officeLocationID);
            System.out.print("\n\n是否添加该研究室信息？ [Yes(1) / No(0)] ：");
            isAdd( sc,labId,labName,researchDirection,directorID,secretaryID, officeLocationID);
            System.out.println("\n\n\n>首界面>功能界面>添加研究室信息\n");
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

    public static void isAdd( Scanner sc,int labID,String labName,String researchDirection,int directorID,int secretaryID, int  officeLocationID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="INSERT INTO ResearchLab (LabID, LabName, ResearchDirection, DirectorID, SecretaryID, OfficeLocationID) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, labID);
                    preparedStatement.setString(2, labName);
                    preparedStatement.setString(3, researchDirection);
                    preparedStatement.setInt(4, directorID);
                    preparedStatement.setInt(5, secretaryID);
                    preparedStatement.setInt(6, officeLocationID);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("添加信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void labQuery()throws AWTException {

        while(true){
        System.out.println("1.按研究所ID查询");
        System.out.println("2.按研究所名称查询");
        System.out.println("3.查询全部 ");
        System.out.print("\n请输入您的选择:");

        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        if (input == 1) {
            int labID;
            int flag;
            while(true) {
                System.out.print("输入需要查询的研究所ID：");
                labID = sc.nextInt();
                flag = getLabID(labID);
                if (flag == -1) {
                    System.out.print("\n该研究所不存在，请重新输入\n");
                }
                else {
                    break;
                }
            }
                Connection connection = null;
                String sql = "select LabID, LabName, ResearchDirection, DirectorID, SecretaryID, OfficeLocationID from ResearchLab where LabID=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, labID);

                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int labId = set.getInt("LabID");
                        String labName = set.getString("LabName");
                        String researchDirection = set.getString("ResearchDirection");
                        int directorID = set.getInt("DirectorID");
                        int secretaryID = set.getInt("SecretaryID");
                        int officeLocationID = set.getInt("OfficeLocationID");

                        System.out.println("\t\t研究室ID: " + labId + "\t\t研究室名称: " + labName + "\t\t研究方向: " + researchDirection + "\t\t主任ID: " + directorID
                                + "\t\t秘书ID: " + secretaryID + "\t\t场地ID: " + officeLocationID);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                    break;

        } else if (input == 2) {
            String labName;
            String flag;
            while(true) {
                System.out.print("输入需要查询的研究所名字：");
                labName = sc.next();
                flag = getLabName(labName);
                if (flag.equals("-1")) {
                    System.out.print("\n该研究所不存在，请重新输入\n");
                }
                else {
                    break;
                }
            }
            Connection connection = null;
            String sql = "select LabID, LabName, ResearchDirection, DirectorID, SecretaryID, OfficeLocationID from ResearchLab where LabName=?";
            PreparedStatement preparedStatement = null;
            ResultSet set = null;
            try {
                connection = JDBC.getConnection();
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, labName);
                set = preparedStatement.executeQuery();

                while (set.next()) {
                    int labId = set.getInt("LabID");
                    String labName0 = set.getString("LabName");
                    String researchDirection = set.getString("ResearchDirection");
                    int directorID = set.getInt("DirectorID");
                    int secretaryID = set.getInt("SecretaryID");
                    int officeLocationID = set.getInt("OfficeLocationID");

                    System.out.println("\t\t研究室ID: " + labId + "\t\t研究室名称: " + labName0 + "\t\t研究方向: " + researchDirection + "\t\t主任ID: " + directorID
                            + "\t\t秘书ID: " + secretaryID + "\t\t场地ID: " + officeLocationID);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                JDBC.close(set, preparedStatement, connection);
            }
            break;
        } else if (input == 3) {
            String tablename = "researchlab";
            FunctionBlock.printTable(tablename);
            break;
        } else {
            System.out.print("\n输入错误，请重新输入: \n");
        }
        }
    }

    public static void deleteLab() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n请输入要删除的研究所ID：");
            int labID = sc.nextInt();
            sc.nextLine();
            int flag = getLabID(labID);
            if (flag == -1) {
                System.out.print("\n该研究所不存在，请重新输入\n");
            } else {
                System.out.print("\n是否删除研究所ID为：" + labID + " 的研究所信息？ [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>首界面>功能界面>删除研究所信息\n");
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
                String sql ="delete from ResearchLab  where LabID=?";

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
                System.out.println("删除研究所信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }


    public static void updateLab() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n请输入要修改信息的研究所ID:");
            int labID = sc.nextInt();
            int flag = getLabID(labID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("该研究所不存在，请重新输入\n\n\n ");
            } else {
                System.out.println(">首界面>功能界面>修改研究室信息\n");
                //研究所ID（主键）不变
                System.out.print("请输入研究室名称: ");
                String labName = sc.next();
                System.out.print("请输入研究室研究方向: ");
                String researchDirection = sc.next();
                System.out.print("请输入主任ID: ");
                int directorID = sc.nextInt();
                System.out.print("请输入秘书ID: ");
                int secretaryID =sc.nextInt();
                System.out.print("请输入场地ID: ");
                int officeLocationID =sc.nextInt();
                Extents.clearConsole();

                System.out.println(">首界面>功能界面>修改研究所信息");
                System.out.println("\n\n------------------------------------------------------------------");
                System.out.println("修改后——>\n");
                System.out.println("\t\t研究室ID: " + labID + "\t\t研究室名称: " + labName + "\t\t研究方向: " + researchDirection + "\t\t主任ID: " + directorID
                        + "\t\t秘书ID: " + secretaryID + "\t\t场地ID: " + officeLocationID);
                System.out.println("\n\n\n>首界面>功能界面>修改研究所信息\n");

                System.out.print("\n\n是否修改该研究所信息？ [Yes(1) / No(0)] ：");
                isUpdate(sc,labID,labName,researchDirection,directorID,secretaryID, officeLocationID);
                System.out.println("\n\n\n>首界面>功能界面>修改研究室信息\n");
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

    public static void isUpdate( Scanner sc,int labID,String labName,String researchDirection,int directorID,int secretaryID, int  officeLocationID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update ResearchLab set LabID=? ,LabName=?,ResearchDirection=?,DirectorID=?,SecretaryID=?,OfficeLocationID=? where LabID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, labID);
                    preparedStatement.setString(2, labName);
                    preparedStatement.setString(3, researchDirection);
                    preparedStatement.setInt(4, directorID);
                    preparedStatement.setInt(5, secretaryID);
                    preparedStatement.setInt(6, officeLocationID);
                    preparedStatement.setInt(7, labID);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("修改研究所信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static int getLabID(int labID) {

        Connection connection =null;
        String sql="select * from ResearchLab";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                int id  =set.getInt("LabID");
                if(labID==id){
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

    public static String getLabName(String labName) {

        Connection connection =null;
        String sql="select * from ResearchLab";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("LabName");
                if(labName.equals(name)){
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
