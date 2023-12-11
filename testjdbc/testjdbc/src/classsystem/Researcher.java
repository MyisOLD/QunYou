package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Researcher {
    public static void researcherAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">首界面>功能界面>添加研究员信息\n");
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入研究员ID: ");
            int erID=sc.nextInt();
            System.out.print("请输入工号: ");
            int erEmployeeID=sc.nextInt();
            System.out.print("请输入研究员姓名: ");
            String erName =sc.next();
            System.out.print("请输入研究员性别: ");
            String erGender =sc.next();
            System.out.print("请输入研究员职称: ");
            String erTitle =sc.next();
            System.out.print("请输入研究员年龄: ");
            int erAge=sc.nextInt();
            System.out.print("请输入研究方向: ");
            String erResearchDirection =sc.next();
            System.out.print("请输入研究员所在研究所ID: ");
            int erLabID =sc.nextInt();


            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t研究员ID: " + erID + "\t\t工号: " + erEmployeeID + "\t\t研究员姓名: " + erName+ "\t\t研究员性别: " + erGender
                    +"\t\t研究员职称: " +erTitle+"\t\t研究员年龄: " +erAge+"\t\t研究方向: " +erResearchDirection+"\t\t研究员所在研究所ID: " +erLabID);
            System.out.print("\n\n是否添加该研究项目信息？ [Yes(1) / No(0)] ：");
            isAdd( sc,erID,erEmployeeID,erName,erGender,erTitle,erAge,erResearchDirection,erLabID);
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

    public static void isAdd( Scanner sc,int erID,int erEmployeeID,String erName,String erGender,String erTitle,int erAge,String erResearchDirection,int erLabID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO Researcher (ResearcherID, EmployeeID, ResearcherName, Gender, Title, Age, ResearchDirection, LabID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, erID);
                    preparedStatement.setInt(2, erEmployeeID);
                    preparedStatement.setString(3, erName);
                    preparedStatement.setString(4, erGender);
                    preparedStatement.setString(5, erTitle);
                    preparedStatement.setInt(6, erAge);
                    preparedStatement.setString(7, erResearchDirection);
                    preparedStatement.setInt(8, erLabID);


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

    public static void researchQuery()throws AWTException {
        while (true) {
            System.out.println("1.按研究人员ID查询");
            System.out.println("2.按研究人员姓名查询");
            System.out.println("3.查询全部 ");
            System.out.print("\n请输入您的选择:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int ResearchID0;
                int flag;
                while(true) {
                    System.out.print("输入需要查询的研究人员ID：");
                    ResearchID0 = sc.nextInt();
                    flag = getResearchID(ResearchID0);
                    if (flag == -1) {
                        System.out.print("\n该研究人员不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select ResearcherID, EmployeeID, ResearcherName, Gender, Title, Age, ResearchDirection, LabID from Researcher where ResearcherID=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, ResearchID0);

                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int erID= set.getInt("ResearcherID");
                        int erEmployeeID = set.getInt("EmployeeID");
                        String erName = set.getString("ResearcherName");
                        String erGender = set.getString("Gender");
                        String erTitle = set.getString("Title");
                        int erAge = set.getInt("Age");
                        String erResearchDirection = set.getString("ResearchDirection");
                        int erLabID = set.getInt("LabID");

                        System.out.println("\t\t研究员ID: " + erID + "\t\t工号: " + erEmployeeID + "\t\t研究员姓名: " + erName+ "\t\t研究员性别: " + erGender
                                +"\t\t研究员职称: " +erTitle+"\t\t研究员年龄: " +erAge+"\t\t研究方向: " +erResearchDirection+"\t\t研究员所在研究所ID: " +erLabID);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String researcherName;
                String flag;
                while(true) {
                    System.out.print("输入需要查询的研究人员姓名：");
                    researcherName = sc.next();
                    flag = getResearchName(researcherName);
                    if (flag.equals("-1")) {
                        System.out.print("\n该研究人员不存在，请重新输入\n");
                    }
                    else {
                        break;
                    }
                }

                Connection connection = null;
                String sql = "select ResearcherID, EmployeeID, ResearcherName, Gender, Title, Age, ResearchDirection, LabID from Researcher where ResearcherName=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setString(1, researcherName);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int erID= set.getInt("ResearcherID");
                        int erEmployeeID = set.getInt("EmployeeID");
                        String erName = set.getString("ResearcherName");
                        String erGender = set.getString("Gender");
                        String erTitle = set.getString("Title");
                        int erAge = set.getInt("Age");
                        String erResearchDirection = set.getString("ResearchDirection");
                        int erLabID = set.getInt("LabID");

                        System.out.println("\t\t研究员ID: " + erID + "\t\t工号: " + erEmployeeID + "\t\t研究员姓名: " + erName+ "\t\t研究员性别: " + erGender
                                +"\t\t研究员职称: " +erTitle+"\t\t研究员年龄: " +erAge+"\t\t研究方向: " +erResearchDirection+"\t\t研究员所在研究所ID: " +erLabID);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if(input==3) {
                String tablename = "Researcher";
                FunctionBlock.printTable(tablename);
                break;
            }
            else{
                System.out.print("\n输入错误，请重新输入 \n");
            }
        }
    }

    public static void deleteResearcher() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n请输入要删除的研究人员ID：");
            int researcherID = sc.nextInt();
            sc.nextLine();
            int flag = getResearchID(researcherID);
            if (flag == -1) {
                System.out.print("\n该研究人员不存在，请重新输入\n");
            } else {
                System.out.print("\n是否删除研究人员ID为：" + researcherID + " 的研究人员信息？ [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>首界面>功能界面>删除研究人员信息\n");
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
                String sql ="delete from Researcher where ResearcherID=?";
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
                System.out.println("删除研究人员信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：");
        }
    }

    public static void updateResearcher() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n请输入要修改信息的研究人员ID:");
            int researcherID = sc.nextInt();
            int flag = getResearchID(researcherID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("该研究人员不存在，请重新输入\n\n\n ");
            } else {
                System.out.println(">首界面>功能界面>修改研究人员信息\n");
                //研究成就ID（主键）不变
                System.out.print("请输入工号: ");
                int erEmployeeID=sc.nextInt();
                System.out.print("请输入研究员姓名: ");
                String erName =sc.next();
                System.out.print("请输入研究员性别: ");
                String erGender =sc.next();
                System.out.print("请输入研究员职称: ");
                String erTitle =sc.next();
                System.out.print("请输入研究员年龄: ");
                int erAge=sc.nextInt();
                System.out.print("请输入研究方向: ");
                String erResearchDirection =sc.next();
                System.out.print("请输入研究员所在研究所ID: ");
                int erLabID =sc.nextInt();
                Extents.clearConsole();

                System.out.println(">首界面>功能界面>修改研究人员信息");
                System.out.println("修改后——>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t研究员ID: " + researcherID + "\t\t工号: " + erEmployeeID + "\t\t研究员姓名: " + erName+ "\t\t研究员性别: " + erGender
                        +"\t\t研究员职称: " +erTitle+"\t\t研究员年龄: " +erAge+"\t\t研究方向: " +erResearchDirection+"\t\t研究员所在研究所ID: " +erLabID);
                System.out.print("\n\n是否添加该研究项目信息？ [Yes(1) / No(0)] ：");

                isUpdate(sc,researcherID,erEmployeeID,erName,erGender,erTitle,erAge,erResearchDirection,erLabID);
                System.out.println("\n\n\n>首界面>功能界面>修改研究人员信息\n");
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

    public static void isUpdate(Scanner sc,int erID,int erEmployeeID,String erName,String erGender,String erTitle,int erAge,String erResearchDirection,int erLabID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("取消成功！");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update Researcher set ResearcherID=?,EmployeeID=?,ResearcherName=?,Gender=?,Title=?,Age=?,ResearchDirection=?, LabID=? where ResearcherID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, erID);
                    preparedStatement.setInt(2, erEmployeeID);
                    preparedStatement.setString(3, erName);
                    preparedStatement.setString(4, erGender);
                    preparedStatement.setString(5, erTitle);
                    preparedStatement.setInt(6, erAge);
                    preparedStatement.setString(7, erResearchDirection);
                    preparedStatement.setInt(8, erLabID);
                    preparedStatement.setInt(9, erID);
                    //执行
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("修改研究人员信息成功!\n\n");
                break;
            }
            System.out.print("\n输入错误!请重新输入：\n");
        }
    }



    public static int getResearchID(int resID) {

        Connection connection =null;
        String sql="select * from Researcher";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                int id  =set.getInt("ResearcherID");
                if(resID==id){
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

    public static String getResearchName(String resName) {

        Connection connection =null;
        String sql="select * from Researcher";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("ResearcherName");
                if(resName.equals(name)){
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
