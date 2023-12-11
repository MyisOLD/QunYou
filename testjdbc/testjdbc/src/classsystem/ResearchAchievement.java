package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ResearchAchievement {
    public static void AchievementAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">首界面>功能界面>添加研究成果信息\n");
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入成果ID: ");
            int achID=sc.nextInt();
            System.out.print("请输入成果名称: ");
            String achName = sc.next();
            System.out.print("请输入成果完成日期（例：2003-11-14）: ");
            String achFinDate =sc.next();
            System.out.print("请输入杰出贡献人ID: ");
            int achKeyID = sc.nextInt();
            System.out.print("请输入项目成果排名: ");
            int achLevel = sc.nextInt();
            System.out.print("请输入成果类型: ");
            String achType = sc.next();
            System.out.print("请输入项目ID: ");
            int achProID =sc.nextInt();


            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t研究成果ID: " + achID + "\t\t研究成果名称: " + achName + "\t\t研究成果完成日期: " + achFinDate+ "\t\t杰出贡献人ID: " + achKeyID
                    +"\t\t项目成果排名: " +achLevel+"\t\t项目成果类型: " +achType+"\t\t项目ID: " +achProID);
            System.out.print("\n\n是否添加该研究项目信息？ [Yes(1) / No(0)] ：");
            isAdd( sc,achID,achName,achFinDate,achKeyID,achLevel,achType,achProID);
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

    public static void isAdd( Scanner sc,int achID,String achName,String achFinDate,int achKeyID,int achLevel,String achType,int achProID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO ResearchAchievement (AchievementID, AchievementName, AchievementDate, KeyResearcherID, Level, AchievementType,ProjectID) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, achID);
                    preparedStatement.setString(2, achName);
                    preparedStatement.setString(3, achFinDate);
                    preparedStatement.setInt(4, achKeyID);
                    preparedStatement.setInt(5, achLevel);
                    preparedStatement.setString(6, achType);
                    preparedStatement.setInt(7, achProID);


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

    public static void achievementQuery()throws AWTException {
        while (true) {
            System.out.println("1.按研究成果ID查询");
            System.out.println("2.按研究成果名称查询");
            System.out.println("3.查询全部 ");
            System.out.print("\n请输入您的选择:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
                if (input == 1) {
                    int achID;
                    int flag;
                    while(true) {
                        System.out.print("输入需要查询的研究成果ID：");
                        achID = sc.nextInt();
                        flag = getAchID(achID);
                        if (flag == -1) {
                            System.out.print("\n该研究成果不存在，请重新输入\n");
                        }
                        else {
                            break;
                        }
                    }

                    Connection connection = null;
                    String sql = "select AchievementID, AchievementName, AchievementDate, KeyResearcherID, Level, AchievementType, ProjectID from ResearchAchievement where AchievementID=?";
                    PreparedStatement preparedStatement = null;
                    ResultSet set = null;
                    try {
                        connection = JDBC.getConnection();
                        preparedStatement = connection.prepareStatement(sql);

                        preparedStatement.setInt(1, achID);

                        set = preparedStatement.executeQuery();

                        while (set.next()) {
                            int achievementID = set.getInt("AchievementID");
                            String achName = set.getString("AchievementName");
                            String achFinDate = set.getString("AchievementDate");
                            int achKeyID = set.getInt("KeyResearcherID");
                            int achLevel = set.getInt("Level");
                            String achType = set.getString("AchievementType");
                            int achProID = set.getInt("ProjectID");

                            System.out.println("\t\t研究成果ID: " + achievementID + "\t\t研究成果名称: " + achName + "\t\t研究成果完成日期: " + achFinDate + "\t\t杰出贡献人ID: " + achKeyID
                                    + "\t\t项目成果排名: " + achLevel + "\t\t项目成果类型: " + achType + "\t\t项目ID: " + achProID);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        JDBC.close(set, preparedStatement, connection);
                    }
                    break;

                } else if (input == 2) {
                    String achName0;
                    String flag;
                    while(true) {
                        System.out.print("输入需要查询的研究成果名字：");
                        achName0 = sc.next();
                        flag = getAchName(achName0);
                        if (flag.equals("-1")) {
                            System.out.print("\n该研究成果不存在，请重新输入\n");
                        }
                        else {
                            break;
                        }
                    }
                    Connection connection = null;
                    String sql = "select AchievementID, AchievementName, AchievementDate, KeyResearcherID, Level, AchievementType, ProjectID from ResearchAchievement where AchievementName=?";
                    PreparedStatement preparedStatement = null;
                    ResultSet set = null;
                    try {
                        connection = JDBC.getConnection();
                        preparedStatement = connection.prepareStatement(sql);

                        preparedStatement.setString(1, achName0);
                        set = preparedStatement.executeQuery();

                        while (set.next()) {
                            int achievementID = set.getInt("AchievementID");
                            String achName = set.getString("AchievementName");
                            String achFinDate = set.getString("AchievementDate");
                            int achKeyID = set.getInt("KeyResearcherID");
                            int achLevel = set.getInt("Level");
                            String achType = set.getString("AchievementType");
                            int achProID = set.getInt("ProjectID");

                            System.out.println("\t\t研究成果ID: " + achievementID + "\t\t研究成果名称: " + achName + "\t\t研究成果完成日期: " + achFinDate + "\t\t杰出贡献人ID: " + achKeyID
                                    + "\t\t项目成果排名: " + achLevel + "\t\t项目成果类型: " + achType + "\t\t项目ID: " + achProID);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        JDBC.close(set, preparedStatement, connection);
                    }
                    break;
                } else if (input == 3) {
                    String tablename = "ResearchAchievement";
                    FunctionBlock.printTable(tablename);
                    break;
                } else {
                    System.out.print("\n输入错误!请重新输入：\n");
                }
        }
    }

    public static void deleteAchievement() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n请输入要删除的研究成果ID：");
            int achID = sc.nextInt();
            sc.nextLine();
            int flag = getAchID(achID);
            if (flag == -1) {
                System.out.print("\n该研究成果不存在，请重新输入\n");
            } else {
                System.out.print("\n是否删除研究成果ID为：" + achID + " 的研究成果信息？ [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>首界面>功能界面>删除研究成果信息\n");
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
                String sql ="delete from ResearchAchievement where AchievementID=?";
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
                System.out.println("删除研究成果信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void updateAchievement() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n请输入要修改信息的研究成就ID:");
            int achID = sc.nextInt();
            int flag = getAchID(achID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("该研究成就不存在，请重新输入\n\n\n ");
            } else {
                System.out.println(">首界面>功能界面>修改研究成就信息\n");
                //研究成就ID（主键）不变
                System.out.print("请输入成果名称: ");
                String achName = sc.next();
                System.out.print("请输入成果完成日期（例：2003-11-14）: ");
                String achFinDate =sc.next();
                System.out.print("请输入杰出贡献人ID: ");
                int achKeyID = sc.nextInt();
                System.out.print("请输入项目成果排名: ");
                int achLevel = sc.nextInt();
                System.out.print("请输入成果类型: ");
                String achType = sc.next();
                System.out.print("请输入项目ID: ");
                int achProID =sc.nextInt();
                Extents.clearConsole();

                System.out.println(">首界面>功能界面>修改研究成就信息");
                System.out.println("修改后——>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t研究成果ID: " + achID + "\t\t研究成果名称: " + achName + "\t\t研究成果完成日期: " + achFinDate+ "\t\t杰出贡献人ID: " + achKeyID
                        +"\t\t项目成果排名: " +achLevel+"\t\t项目成果类型: " +achType+"\t\t项目ID: " +achProID);
                System.out.print("\n\n是否添加该研究项目信息？ [Yes(1) / No(0)] ：");

                isUpdate(sc,achID,achName,achFinDate,achKeyID,achLevel,achType,achProID);
                System.out.println("\n\n\n>首界面>功能界面>修改研究成就信息\n");
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

    public static void isUpdate( Scanner sc,int achID,String achName,String achFinDate,int achKeyID,int achLevel,String achType,int achProID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update ResearchAchievement set AchievementID=?,AchievementName=?,AchievementDate=?,KeyResearcherID=?,Level=?,AchievementType=?,ProjectID=? where AchievementID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, achID);
                    preparedStatement.setString(2, achName);
                    preparedStatement.setString(3, achFinDate);
                    preparedStatement.setInt(4, achKeyID);
                    preparedStatement.setInt(5, achLevel);
                    preparedStatement.setString(6, achType);
                    preparedStatement.setInt(7, achProID);
                    preparedStatement.setInt(8, achID);
                    //执行
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("修改研究项目成就成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：\n");
        }
    }
    public static int getAchID(int achID) {

        Connection connection =null;
        String sql="select * from ResearchAchievement";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while(set.next()){
                int id  =set.getInt("AchievementID");
                if(achID==id){
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

    public static String getAchName(String achName) {

        Connection connection =null;
        String sql="select * from ResearchAchievement";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("AchievementName");
                if(achName.equals(name)){
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
