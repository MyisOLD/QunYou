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
            System.out.println(">�׽���>���ܽ���>����о�����Ϣ\n");
            Scanner sc = new Scanner(System.in);
            System.out.print("�������о���ID: ");
            int labId=sc.nextInt();
            System.out.print("�������о�������: ");
            String labName = sc.next();
            System.out.print("�������о����о�����: ");
            String researchDirection = sc.next();
            System.out.print("����������ID: ");
            int directorID = sc.nextInt();
            System.out.print("����������ID: ");
            int secretaryID =sc.nextInt();
            System.out.print("�����볡��ID: ");
            int officeLocationID =sc.nextInt();


            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t�о���ID: " + labId + "\t\t�о�������: " + labName + "\t\t�о�����: " + researchDirection+ "\t\t����ID: " + directorID
                    +"\t\t����ID: " +secretaryID+"\t\t����ID: " +officeLocationID);
            System.out.print("\n\n�Ƿ���Ӹ��о�����Ϣ�� [Yes(1) / No(0)] ��");
            isAdd( sc,labId,labName,researchDirection,directorID,secretaryID, officeLocationID);
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

    public static void isAdd( Scanner sc,int labID,String labName,String researchDirection,int directorID,int secretaryID, int  officeLocationID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
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
                System.out.println("�����Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void labQuery()throws AWTException {

        while(true){
        System.out.println("1.���о���ID��ѯ");
        System.out.println("2.���о������Ʋ�ѯ");
        System.out.println("3.��ѯȫ�� ");
        System.out.print("\n����������ѡ��:");

        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        if (input == 1) {
            int labID;
            int flag;
            while(true) {
                System.out.print("������Ҫ��ѯ���о���ID��");
                labID = sc.nextInt();
                flag = getLabID(labID);
                if (flag == -1) {
                    System.out.print("\n���о��������ڣ�����������\n");
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

                        System.out.println("\t\t�о���ID: " + labId + "\t\t�о�������: " + labName + "\t\t�о�����: " + researchDirection + "\t\t����ID: " + directorID
                                + "\t\t����ID: " + secretaryID + "\t\t����ID: " + officeLocationID);
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
                System.out.print("������Ҫ��ѯ���о������֣�");
                labName = sc.next();
                flag = getLabName(labName);
                if (flag.equals("-1")) {
                    System.out.print("\n���о��������ڣ�����������\n");
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

                    System.out.println("\t\t�о���ID: " + labId + "\t\t�о�������: " + labName0 + "\t\t�о�����: " + researchDirection + "\t\t����ID: " + directorID
                            + "\t\t����ID: " + secretaryID + "\t\t����ID: " + officeLocationID);
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
            System.out.print("\n�����������������: \n");
        }
        }
    }

    public static void deleteLab() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n������Ҫɾ�����о���ID��");
            int labID = sc.nextInt();
            sc.nextLine();
            int flag = getLabID(labID);
            if (flag == -1) {
                System.out.print("\n���о��������ڣ�����������\n");
            } else {
                System.out.print("\n�Ƿ�ɾ���о���IDΪ��" + labID + " ���о�����Ϣ�� [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>�׽���>���ܽ���>ɾ���о�����Ϣ\n");
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
                System.out.println("ɾ���о�����Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }


    public static void updateLab() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n������Ҫ�޸���Ϣ���о���ID:");
            int labID = sc.nextInt();
            int flag = getLabID(labID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("���о��������ڣ�����������\n\n\n ");
            } else {
                System.out.println(">�׽���>���ܽ���>�޸��о�����Ϣ\n");
                //�о���ID������������
                System.out.print("�������о�������: ");
                String labName = sc.next();
                System.out.print("�������о����о�����: ");
                String researchDirection = sc.next();
                System.out.print("����������ID: ");
                int directorID = sc.nextInt();
                System.out.print("����������ID: ");
                int secretaryID =sc.nextInt();
                System.out.print("�����볡��ID: ");
                int officeLocationID =sc.nextInt();
                Extents.clearConsole();

                System.out.println(">�׽���>���ܽ���>�޸��о�����Ϣ");
                System.out.println("\n\n------------------------------------------------------------------");
                System.out.println("�޸ĺ󡪡�>\n");
                System.out.println("\t\t�о���ID: " + labID + "\t\t�о�������: " + labName + "\t\t�о�����: " + researchDirection + "\t\t����ID: " + directorID
                        + "\t\t����ID: " + secretaryID + "\t\t����ID: " + officeLocationID);
                System.out.println("\n\n\n>�׽���>���ܽ���>�޸��о�����Ϣ\n");

                System.out.print("\n\n�Ƿ��޸ĸ��о�����Ϣ�� [Yes(1) / No(0)] ��");
                isUpdate(sc,labID,labName,researchDirection,directorID,secretaryID, officeLocationID);
                System.out.println("\n\n\n>�׽���>���ܽ���>�޸��о�����Ϣ\n");
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

    public static void isUpdate( Scanner sc,int labID,String labName,String researchDirection,int directorID,int secretaryID, int  officeLocationID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
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
                System.out.println("�޸��о�����Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
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
