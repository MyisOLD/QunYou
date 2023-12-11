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
            System.out.println(">�׽���>���ܽ���>����о���Ŀ��Ϣ\n");
            Scanner sc = new Scanner(System.in);
            System.out.print("�������о���ĿID: ");
            int proId=sc.nextInt();
            System.out.print("��������Ŀ������ID: ");
            int proLeaderId=sc.nextInt();
            System.out.print("�������о���Ŀ����: ");
            String proName = sc.next();
            System.out.print("�������о���Ŀ����: ");
            String proContent = sc.next();
            System.out.print("��������Ŀ���ʽ�: ");
            int proFunding = sc.nextInt();
            System.out.print("��������Ŀ��ʼ���ڣ�����2003-11-14��: ");
            String proStartDate =sc.next();
            System.out.print("�����������ID: ");
            int proCollaboratorID =sc.nextInt();
            System.out.print("������������ⷽID: ");
            int proQualityMonitorID =sc.nextInt();
            System.out.print("������������ڣ�����2003-11-14��: ");
            String proEndDate =sc.next();
            System.out.print("������ί�з�ID: ");
            int proClientID =sc.nextInt();

            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t�о���ĿID: " + proId + "\t\t�о�������ID: " + proLeaderId + "\t\t�о���Ŀ����: " + proName+ "\t\t�о���Ŀ����: " + proContent
                    +"\t\t��Ŀ���ʽ�: " +proFunding+"\t\t��Ŀ��ʼ����: " +proStartDate+"\t\t������ID: " +proCollaboratorID+"\t\t������ⷽID: " + proQualityMonitorID +"\t\t��Ŀ��������: " +proEndDate+"\t\tί�з�ID: " +proClientID);
            System.out.print("\n\n�Ƿ���Ӹ��о���Ŀ��Ϣ�� [Yes(1) / No(0)] ��");
            isAdd( sc,proId,proLeaderId,proName,proContent,proFunding,proStartDate,proCollaboratorID,proEndDate,proClientID,proQualityMonitorID);
            System.out.println("\n\n\n>�׽���>���ܽ���>����о�����Ϣ\n");
            System.out.println("\t                �������                   ������1               ");
            System.out.println();
            System.out.println("\t                �����ϼ�                   ������0               ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.print("\n����������ѡ��");
            while (true) {
                int choose = sc.nextInt();
                if (choose == 1) {
                    break;
                } else if (choose == 0) {
                    Extents.clearConsole();
                    return;
                } else {
                    System.out.print("���������룺  ");
                }
            }
        }
    }

    public static void isAdd( Scanner sc,int proId,int proLeaderId,String proName,String proContent,int proFunding,String proStartDate,int proCollaboratorID,String proEndDate,int proClientID,int proQualityMonitorID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
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


                    //ִ��
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("�����Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void projectQuery()throws AWTException {
        while (true) {
            System.out.println("1.���о���ĿID��ѯ");
            System.out.println("2.���о���Ŀ���Ʋ�ѯ");
            System.out.println("3.��ѯȫ�� ");
            System.out.print("\n����������ѡ��:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int projectID;
                int flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ���о���ĿID��");
                    projectID = sc.nextInt();
                    flag = getProID(projectID);
                    if (flag == -1) {
                        System.out.print("\n���о���Ŀ�����ڣ�����������\n");
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

                        System.out.println("\t\t�о���ĿID: " + proId + "\t\t�о�������ID: " + proLeaderId + "\t\t�о���Ŀ����: " + proName + "\t\t�о���Ŀ����: " + proContent
                                + "\t\t��Ŀ���ʽ�: " + proFunding + "\t\t��Ŀ��ʼ����: " + proStartDate + "\t\t������ID: " + proCollaboratorID + "\t\t������ⷽID: " + proQualityMonitorID + "\t\t��Ŀ��������: " + proEndDate + "\t\tί�з�ID: " + proClientID);
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
                    System.out.print("������Ҫ��ѯ���о���Ŀ���֣�");
                    proName0 = sc.next();
                    flag = getProName(proName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n���о���Ŀ�����ڣ�����������\n");
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
                    //ִ��
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

                        System.out.println("\t\t�о���ĿID: " + proId + "\t\t�о�������ID: " + proLeaderId + "\t\t�о���Ŀ����: " + proName + "\t\t�о���Ŀ����: " + proContent
                                + "\t\t��Ŀ���ʽ�: " + proFunding + "\t\t��Ŀ��ʼ����: " + proStartDate + "\t\t������ID: " + proCollaboratorID + "\t\t������ⷽID: " + proQualityMonitorID + "\t\t��Ŀ��������: " + proEndDate + "\t\tί�з�ID: " + proClientID);
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
                System.out.print("\n�����������������: \n");
            }
        }
    }

    public static void deleteProject() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n������Ҫɾ�����о���ĿID��");
            int proID = sc.nextInt();
            sc.nextLine();
            int flag = getProID(proID);
            if (flag == -1) {
                System.out.print("\n���о��������ڣ�����������\n");
            } else {
                System.out.print("\n�Ƿ�ɾ���о���ĿIDΪ��" + proID + " ���о���Ŀ��Ϣ�� [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>�׽���>���ܽ���>ɾ���о���Ŀ��Ϣ\n");
                System.out.println("\t                ����ɾ��                   ������1                ");
                System.out.println();
                System.out.println("\t                �����ϼ�                   ������0                ");
                System.out.println("\t ----------------------------------------------------------------");
                System.out.print("\n����������ѡ��: ");
                while (true) {
                    int choose = sc.nextInt();
                    if (choose == 1) {
                        break;
                    } else if (choose == 0) {
                        Extents.clearConsole();
                        return;
                    } else {
                        System.out.print("����������: ");
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
                System.out.println("ȡ���ɹ���");
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
                System.out.println("ɾ���о���Ŀ��Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void updateProject() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n������Ҫ�޸���Ϣ���о���ĿID:");
            int proID = sc.nextInt();
            int flag = getProID(proID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("���о���Ŀ�����ڣ�����������\n\n\n ");
            } else {
                System.out.println(">�׽���>���ܽ���>�޸��о���Ŀ��Ϣ\n");
                //�о���ĿID������������
                System.out.print("��������Ŀ������ID: ");
                int proLeaderId=sc.nextInt();
                System.out.print("�������о���Ŀ����: ");
                String proName = sc.next();
                System.out.print("�������о���Ŀ����: ");
                String proContent = sc.next();
                System.out.print("��������Ŀ���ʽ�: ");
                int proFunding = sc.nextInt();
                System.out.print("��������Ŀ��ʼ���ڣ�����2003-11-14��: ");
                String proStartDate =sc.next();
                System.out.print("�����������ID: ");
                int proCollaboratorID =sc.nextInt();
                System.out.print("������������ⷽID: ");
                int proQualityMonitorID =sc.nextInt();
                System.out.print("������������ڣ�����2003-11-14��: ");
                String proEndDate =sc.next();
                System.out.print("������ί�з�ID: ");
                int proClientID =sc.nextInt();
                Extents.clearConsole();

                System.out.println(">�׽���>���ܽ���>�޸��о���Ŀ��Ϣ");
                System.out.println("�޸ĺ󡪡�>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t�о���ĿID: " + proID + "\t\t�о�������ID: " + proLeaderId + "\t\t�о���Ŀ����: " + proName+ "\t\t�о���Ŀ����: " + proContent
                        +"\t\t��Ŀ���ʽ�: " +proFunding+"\t\t��Ŀ��ʼ����: " +proStartDate+"\t\t������ID: " +proCollaboratorID+"\t\t������ⷽID: " + proQualityMonitorID +"\t\t��Ŀ��������: " +proEndDate+"\t\tί�з�ID: " +proClientID);
                System.out.print("\n\n�Ƿ���Ӹ��о���Ŀ��Ϣ�� [Yes(1) / No(0)] ��");

                isUpdate(sc,proID,proLeaderId,proName,proContent,proFunding,proStartDate,proCollaboratorID,proEndDate,proClientID,proQualityMonitorID);
                System.out.println("\n\n\n>�׽���>���ܽ���>�޸��о���Ŀ��Ϣ\n");
                System.out.println("\t                �����޸�                   ������1              ");
                System.out.println();
                System.out.println("\t                �����ϼ�                   ������0              ");
                System.out.println("\t --------------------------------------------------------------");
                System.out.print("\n����������ѡ��:");
                while (true) {
                    int choose = sc.nextInt();
                    if (choose == 1) {
                        Extents.clearConsole();
                        break;
                    } else if (choose == 0) {
                        Extents.clearConsole();
                        return;
                    } else {
                        System.out.print("������� ����������: ");
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
                System.out.println("ȡ���ɹ���");
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
                    //ִ��
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("�޸��о���Ŀ��Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺\n");
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
