package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ResearchProject {
    public static void ProjectAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">首界面>功能界面>添加研究项目信息\n");
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入研究项目ID: ");
            int proId=sc.nextInt();
            System.out.print("请输入项目负责人ID: ");
            int proLeaderId=sc.nextInt();
            System.out.print("请输入研究项目名称: ");
            String proName = sc.next();
            System.out.print("请输入研究项目内容: ");
            String proContent = sc.next();
            System.out.print("请输入项目总资金: ");
            int proFunding = sc.nextInt();
            System.out.print("请输入项目开始日期（例：2003-11-14）: ");
            String proStartDate =sc.next();
            System.out.print("请输入合作方ID: ");
            int proCollaboratorID =sc.nextInt();
            System.out.print("请输入质量检测方ID: ");
            int proQualityMonitorID =sc.nextInt();
            System.out.print("请输入结束日期（例：2003-11-14）: ");
            String proEndDate =sc.next();
            System.out.print("请输入委托方ID: ");
            int proClientID =sc.nextInt();

            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t研究项目ID: " + proId + "\t\t研究负责人ID: " + proLeaderId + "\t\t研究项目名称: " + proName+ "\t\t研究项目内容: " + proContent
                    +"\t\t项目总资金: " +proFunding+"\t\t项目开始日期: " +proStartDate+"\t\t合作方ID: " +proCollaboratorID+"\t\t质量检测方ID: " + proQualityMonitorID +"\t\t项目结束日期: " +proEndDate+"\t\t委托方ID: " +proClientID);
            System.out.print("\n\n是否添加该研究项目信息？ [Yes(1) / No(0)] ：");
            isAdd( sc,proId,proLeaderId,proName,proContent,proFunding,proStartDate,proCollaboratorID,proEndDate,proClientID,proQualityMonitorID);
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

    public static void isAdd( Scanner sc,int proId,int proLeaderId,String proName,String proContent,int proFunding,String proStartDate,int proCollaboratorID,String proEndDate,int proClientID,int proQualityMonitorID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO ResearchProject (ProjectID, ProjectLeaderID, ProjectName, ResearchContent, TotalFunding, StartDate,CollaboratorID,QualityMonitorID,EndDate,ClientID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, proId);
                    preparedStatement.setInt(2, proLeaderId);
                    preparedStatement.setString(3, proName);
                    preparedStatement.setString(4, proContent);
                    preparedStatement.setInt(5, proFunding);
                    preparedStatement.setString(6, proStartDate);
                    preparedStatement.setInt(7, proCollaboratorID);
                    preparedStatement.setInt(8,proQualityMonitorID);
                    preparedStatement.setString(9, proEndDate);
                    preparedStatement.setInt(10,proClientID);


                    //执行
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

    public static void projectQuery()throws AWTException {
        while (true) {
            System.out.println("1.按研究项目ID查询");
            System.out.println("2.按研究项目名称查询");
            System.out.println("3.查询全部 ");
            System.out.print("\n请输入您的选择:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int projectID;
                int flag;
                while(true) {
                    System.out.print("输入需要查询的研究项目ID：");
                    projectID = sc.nextInt();
                    flag = getProID(projectID);
                    if (flag == -1) {
                        System.out.print("\n该研究项目不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select ProjectID, ProjectLeaderID, ProjectName, ResearchContent, TotalFunding, StartDate, CollaboratorID, QualityMonitorID, EndDate, ClientID from ResearchProject where ProjectID=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, projectID);

                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int proId = set.getInt("ProjectID");
                        int proLeaderId = set.getInt("ProjectLeaderID");
                        String proName = set.getString("ProjectName");
                        String proContent = set.getString("ResearchContent");
                        int proFunding = set.getInt("TotalFunding");
                        String proStartDate = set.getString("StartDate");
                        int proCollaboratorID = set.getInt("CollaboratorID");
                        int proQualityMonitorID = set.getInt("QualityMonitorID");
                        String proEndDate = set.getString("EndDate");
                        int proClientID = set.getInt("ClientID");

                        System.out.println("\t\t研究项目ID: " + proId + "\t\t研究负责人ID: " + proLeaderId + "\t\t研究项目名称: " + proName + "\t\t研究项目内容: " + proContent
                                + "\t\t项目总资金: " + proFunding + "\t\t项目开始日期: " + proStartDate + "\t\t合作方ID: " + proCollaboratorID + "\t\t质量检测方ID: " + proQualityMonitorID + "\t\t项目结束日期: " + proEndDate + "\t\t委托方ID: " + proClientID);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String proName0;
                String flag;
                while(true) {
                    System.out.print("输入需要查询的研究项目名字：");
                    proName0 = sc.next();
                    flag = getProName(proName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n该研究项目不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }

                Connection connection = null;
                String sql = "select ProjectID, ProjectLeaderID, ProjectName, ResearchContent, TotalFunding, StartDate, CollaboratorID, QualityMonitorID, EndDate, ClientID from ResearchProject where ProjectName=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setString(1, proName0);
                    //执行
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int proId = set.getInt("ProjectID");
                        int proLeaderId = set.getInt("ProjectLeaderID");
                        String proName = set.getString("ProjectName");
                        String proContent = set.getString("ResearchContent");
                        int proFunding = set.getInt("TotalFunding");
                        String proStartDate = set.getString("StartDate");
                        int proCollaboratorID = set.getInt("CollaboratorID");
                        int proQualityMonitorID = set.getInt("QualityMonitorID");
                        String proEndDate = set.getString("EndDate");
                        int proClientID = set.getInt("ClientID");

                        System.out.println("\t\t研究项目ID: " + proId + "\t\t研究负责人ID: " + proLeaderId + "\t\t研究项目名称: " + proName + "\t\t研究项目内容: " + proContent
                                + "\t\t项目总资金: " + proFunding + "\t\t项目开始日期: " + proStartDate + "\t\t合作方ID: " + proCollaboratorID + "\t\t质量检测方ID: " + proQualityMonitorID + "\t\t项目结束日期: " + proEndDate + "\t\t委托方ID: " + proClientID);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if(input==3) {
                String tablename = "ResearchProject";
                FunctionBlock.printTable(tablename);
                break;
            }
            else{
                System.out.print("\n输入错误，请重新输入: \n");
            }
        }
    }

    public static void deleteProject() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n请输入要删除的研究项目ID：");
            int proID = sc.nextInt();
            sc.nextLine();
            int flag = getProID(proID);
            if (flag == -1) {
                System.out.print("\n该研究所不存在，请重新输入\n");
            } else {
                System.out.print("\n是否删除研究项目ID为：" + proID + " 的研究项目信息？ [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>首界面>功能界面>删除研究项目信息\n");
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
                String sql ="delete from ResearchProject  where ProjectID=?";

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
                System.out.println("删除研究项目信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void updateProject() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n请输入要修改信息的研究项目ID:");
            int proID = sc.nextInt();
            int flag = getProID(proID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("该研究项目不存在，请重新输入\n\n\n ");
            } else {
                System.out.println(">首界面>功能界面>修改研究项目信息\n");
                //研究项目ID（主键）不变
                System.out.print("请输入项目负责人ID: ");
                int proLeaderId=sc.nextInt();
                System.out.print("请输入研究项目名称: ");
                String proName = sc.next();
                System.out.print("请输入研究项目内容: ");
                String proContent = sc.next();
                System.out.print("请输入项目总资金: ");
                int proFunding = sc.nextInt();
                System.out.print("请输入项目开始日期（例：2003-11-14）: ");
                String proStartDate =sc.next();
                System.out.print("请输入合作方ID: ");
                int proCollaboratorID =sc.nextInt();
                System.out.print("请输入质量检测方ID: ");
                int proQualityMonitorID =sc.nextInt();
                System.out.print("请输入结束日期（例：2003-11-14）: ");
                String proEndDate =sc.next();
                System.out.print("请输入委托方ID: ");
                int proClientID =sc.nextInt();
                Extents.clearConsole();

                System.out.println(">首界面>功能界面>修改研究项目信息");
                System.out.println("修改后——>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t研究项目ID: " + proID + "\t\t研究负责人ID: " + proLeaderId + "\t\t研究项目名称: " + proName+ "\t\t研究项目内容: " + proContent
                        +"\t\t项目总资金: " +proFunding+"\t\t项目开始日期: " +proStartDate+"\t\t合作方ID: " +proCollaboratorID+"\t\t质量检测方ID: " + proQualityMonitorID +"\t\t项目结束日期: " +proEndDate+"\t\t委托方ID: " +proClientID);
                System.out.print("\n\n是否添加该研究项目信息？ [Yes(1) / No(0)] ：");

                isUpdate(sc,proID,proLeaderId,proName,proContent,proFunding,proStartDate,proCollaboratorID,proEndDate,proClientID,proQualityMonitorID);
                System.out.println("\n\n\n>首界面>功能界面>修改研究项目信息\n");
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

    public static void isUpdate( Scanner sc,int proId,int proLeaderId,String proName,String proContent,int proFunding,String proStartDate,int proCollaboratorID,String proEndDate,int proClientID,int proQualityMonitorID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update ResearchProject set ProjectID=?,ProjectLeaderID=?,ProjectName=?,ResearchContent=?,TotalFunding=?,StartDate=?,CollaboratorID=?,QualityMonitorID=?,EndDate=?,ClientID=? where ProjectID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, proId);
                    preparedStatement.setInt(2, proLeaderId);
                    preparedStatement.setString(3, proName);
                    preparedStatement.setString(4, proContent);
                    preparedStatement.setInt(5, proFunding);
                    preparedStatement.setString(6, proStartDate);
                    preparedStatement.setInt(7, proCollaboratorID);
                    preparedStatement.setInt(8,proQualityMonitorID);
                    preparedStatement.setString(9, proEndDate);
                    preparedStatement.setInt(10,proClientID);
                    preparedStatement.setInt(11, proId);
                    //执行
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("修改研究项目信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：\n");
        }
    }



    public static int getProID(int proID) {
        Connection connection =null;
        String sql="select * from ResearchProject";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                int id  =set.getInt("ProjectID");
                if(proID==id){
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

    public static String getProName(String proName) {
        Connection connection =null;
        String sql="select * from ResearchProject";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("ProjectName");
                if(proName.equals(name)){
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
