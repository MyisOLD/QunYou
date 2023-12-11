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
            System.out.println(">�׽���>���ܽ���>����о��ɹ���Ϣ\n");
            Scanner sc = new Scanner(System.in);
            System.out.print("������ɹ�ID: ");
            int achID=sc.nextInt();
            System.out.print("������ɹ�����: ");
            String achName = sc.next();
            System.out.print("������ɹ�������ڣ�����2003-11-14��: ");
            String achFinDate =sc.next();
            System.out.print("������ܳ�������ID: ");
            int achKeyID = sc.nextInt();
            System.out.print("��������Ŀ�ɹ�����: ");
            int achLevel = sc.nextInt();
            System.out.print("������ɹ�����: ");
            String achType = sc.next();
            System.out.print("��������ĿID: ");
            int achProID =sc.nextInt();


            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t�о��ɹ�ID: " + achID + "\t\t�о��ɹ�����: " + achName + "\t\t�о��ɹ��������: " + achFinDate+ "\t\t�ܳ�������ID: " + achKeyID
                    +"\t\t��Ŀ�ɹ�����: " +achLevel+"\t\t��Ŀ�ɹ�����: " +achType+"\t\t��ĿID: " +achProID);
            System.out.print("\n\n�Ƿ���Ӹ��о���Ŀ��Ϣ�� [Yes(1) / No(0)] ��");
            isAdd( sc,achID,achName,achFinDate,achKeyID,achLevel,achType,achProID);
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

    public static void isAdd( Scanner sc,int achID,String achName,String achFinDate,int achKeyID,int achLevel,String achType,int achProID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
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
                System.out.println("�����Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void achievementQuery()throws AWTException {
        while (true) {
            System.out.println("1.���о��ɹ�ID��ѯ");
            System.out.println("2.���о��ɹ����Ʋ�ѯ");
            System.out.println("3.��ѯȫ�� ");
            System.out.print("\n����������ѡ��:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
                if (input == 1) {
                    int achID;
                    int flag;
                    while(true) {
                        System.out.print("������Ҫ��ѯ���о��ɹ�ID��");
                        achID = sc.nextInt();
                        flag = getAchID(achID);
                        if (flag == -1) {
                            System.out.print("\n���о��ɹ������ڣ�����������\n");
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

                            System.out.println("\t\t�о��ɹ�ID: " + achievementID + "\t\t�о��ɹ�����: " + achName + "\t\t�о��ɹ��������: " + achFinDate + "\t\t�ܳ�������ID: " + achKeyID
                                    + "\t\t��Ŀ�ɹ�����: " + achLevel + "\t\t��Ŀ�ɹ�����: " + achType + "\t\t��ĿID: " + achProID);
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
                        System.out.print("������Ҫ��ѯ���о��ɹ����֣�");
                        achName0 = sc.next();
                        flag = getAchName(achName0);
                        if (flag.equals("-1")) {
                            System.out.print("\n���о��ɹ������ڣ�����������\n");
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

                            System.out.println("\t\t�о��ɹ�ID: " + achievementID + "\t\t�о��ɹ�����: " + achName + "\t\t�о��ɹ��������: " + achFinDate + "\t\t�ܳ�������ID: " + achKeyID
                                    + "\t\t��Ŀ�ɹ�����: " + achLevel + "\t\t��Ŀ�ɹ�����: " + achType + "\t\t��ĿID: " + achProID);
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
                    System.out.print("\n�������!���������룺\n");
                }
        }
    }

    public static void deleteAchievement() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n������Ҫɾ�����о��ɹ�ID��");
            int achID = sc.nextInt();
            sc.nextLine();
            int flag = getAchID(achID);
            if (flag == -1) {
                System.out.print("\n���о��ɹ������ڣ�����������\n");
            } else {
                System.out.print("\n�Ƿ�ɾ���о��ɹ�IDΪ��" + achID + " ���о��ɹ���Ϣ�� [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>�׽���>���ܽ���>ɾ���о��ɹ���Ϣ\n");
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
                System.out.println("ɾ���о��ɹ���Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void updateAchievement() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n������Ҫ�޸���Ϣ���о��ɾ�ID:");
            int achID = sc.nextInt();
            int flag = getAchID(achID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("���о��ɾͲ����ڣ�����������\n\n\n ");
            } else {
                System.out.println(">�׽���>���ܽ���>�޸��о��ɾ���Ϣ\n");
                //�о��ɾ�ID������������
                System.out.print("������ɹ�����: ");
                String achName = sc.next();
                System.out.print("������ɹ�������ڣ�����2003-11-14��: ");
                String achFinDate =sc.next();
                System.out.print("������ܳ�������ID: ");
                int achKeyID = sc.nextInt();
                System.out.print("��������Ŀ�ɹ�����: ");
                int achLevel = sc.nextInt();
                System.out.print("������ɹ�����: ");
                String achType = sc.next();
                System.out.print("��������ĿID: ");
                int achProID =sc.nextInt();
                Extents.clearConsole();

                System.out.println(">�׽���>���ܽ���>�޸��о��ɾ���Ϣ");
                System.out.println("�޸ĺ󡪡�>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t�о��ɹ�ID: " + achID + "\t\t�о��ɹ�����: " + achName + "\t\t�о��ɹ��������: " + achFinDate+ "\t\t�ܳ�������ID: " + achKeyID
                        +"\t\t��Ŀ�ɹ�����: " +achLevel+"\t\t��Ŀ�ɹ�����: " +achType+"\t\t��ĿID: " +achProID);
                System.out.print("\n\n�Ƿ���Ӹ��о���Ŀ��Ϣ�� [Yes(1) / No(0)] ��");

                isUpdate(sc,achID,achName,achFinDate,achKeyID,achLevel,achType,achProID);
                System.out.println("\n\n\n>�׽���>���ܽ���>�޸��о��ɾ���Ϣ\n");
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

    public static void isUpdate( Scanner sc,int achID,String achName,String achFinDate,int achKeyID,int achLevel,String achType,int achProID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
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
                    //ִ��
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("�޸��о���Ŀ�ɾͳɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺\n");
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
