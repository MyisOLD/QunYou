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
            System.out.println(">�׽���>���ܽ���>����о�Ա��Ϣ\n");
            Scanner sc = new Scanner(System.in);
            System.out.print("�������о�ԱID: ");
            int erID=sc.nextInt();
            System.out.print("�����빤��: ");
            int erEmployeeID=sc.nextInt();
            System.out.print("�������о�Ա����: ");
            String erName =sc.next();
            System.out.print("�������о�Ա�Ա�: ");
            String erGender =sc.next();
            System.out.print("�������о�Աְ��: ");
            String erTitle =sc.next();
            System.out.print("�������о�Ա����: ");
            int erAge=sc.nextInt();
            System.out.print("�������о�����: ");
            String erResearchDirection =sc.next();
            System.out.print("�������о�Ա�����о���ID: ");
            int erLabID =sc.nextInt();


            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t�о�ԱID: " + erID + "\t\t����: " + erEmployeeID + "\t\t�о�Ա����: " + erName+ "\t\t�о�Ա�Ա�: " + erGender
                    +"\t\t�о�Աְ��: " +erTitle+"\t\t�о�Ա����: " +erAge+"\t\t�о�����: " +erResearchDirection+"\t\t�о�Ա�����о���ID: " +erLabID);
            System.out.print("\n\n�Ƿ���Ӹ��о���Ŀ��Ϣ�� [Yes(1) / No(0)] ��");
            isAdd( sc,erID,erEmployeeID,erName,erGender,erTitle,erAge,erResearchDirection,erLabID);
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

    public static void isAdd( Scanner sc,int erID,int erEmployeeID,String erName,String erGender,String erTitle,int erAge,String erResearchDirection,int erLabID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
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

    public static void researchQuery()throws AWTException {
        while (true) {
            System.out.println("1.���о���ԱID��ѯ");
            System.out.println("2.���о���Ա������ѯ");
            System.out.println("3.��ѯȫ�� ");
            System.out.print("\n����������ѡ��:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int ResearchID0;
                int flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ���о���ԱID��");
                    ResearchID0 = sc.nextInt();
                    flag = getResearchID(ResearchID0);
                    if (flag == -1) {
                        System.out.print("\n���о���Ա�����ڣ�����������\n");
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

                        System.out.println("\t\t�о�ԱID: " + erID + "\t\t����: " + erEmployeeID + "\t\t�о�Ա����: " + erName+ "\t\t�о�Ա�Ա�: " + erGender
                                +"\t\t�о�Աְ��: " +erTitle+"\t\t�о�Ա����: " +erAge+"\t\t�о�����: " +erResearchDirection+"\t\t�о�Ա�����о���ID: " +erLabID);
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
                    System.out.print("������Ҫ��ѯ���о���Ա������");
                    researcherName = sc.next();
                    flag = getResearchName(researcherName);
                    if (flag.equals("-1")) {
                        System.out.print("\n���о���Ա�����ڣ�����������\n");
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

                        System.out.println("\t\t�о�ԱID: " + erID + "\t\t����: " + erEmployeeID + "\t\t�о�Ա����: " + erName+ "\t\t�о�Ա�Ա�: " + erGender
                                +"\t\t�о�Աְ��: " +erTitle+"\t\t�о�Ա����: " +erAge+"\t\t�о�����: " +erResearchDirection+"\t\t�о�Ա�����о���ID: " +erLabID);
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
                System.out.print("\n����������������� \n");
            }
        }
    }

    public static void deleteResearcher() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n������Ҫɾ�����о���ԱID��");
            int researcherID = sc.nextInt();
            sc.nextLine();
            int flag = getResearchID(researcherID);
            if (flag == -1) {
                System.out.print("\n���о���Ա�����ڣ�����������\n");
            } else {
                System.out.print("\n�Ƿ�ɾ���о���ԱIDΪ��" + researcherID + " ���о���Ա��Ϣ�� [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>�׽���>���ܽ���>ɾ���о���Ա��Ϣ\n");
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
                System.out.println("ɾ���о���Ա��Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void updateResearcher() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n������Ҫ�޸���Ϣ���о���ԱID:");
            int researcherID = sc.nextInt();
            int flag = getResearchID(researcherID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("���о���Ա�����ڣ�����������\n\n\n ");
            } else {
                System.out.println(">�׽���>���ܽ���>�޸��о���Ա��Ϣ\n");
                //�о��ɾ�ID������������
                System.out.print("�����빤��: ");
                int erEmployeeID=sc.nextInt();
                System.out.print("�������о�Ա����: ");
                String erName =sc.next();
                System.out.print("�������о�Ա�Ա�: ");
                String erGender =sc.next();
                System.out.print("�������о�Աְ��: ");
                String erTitle =sc.next();
                System.out.print("�������о�Ա����: ");
                int erAge=sc.nextInt();
                System.out.print("�������о�����: ");
                String erResearchDirection =sc.next();
                System.out.print("�������о�Ա�����о���ID: ");
                int erLabID =sc.nextInt();
                Extents.clearConsole();

                System.out.println(">�׽���>���ܽ���>�޸��о���Ա��Ϣ");
                System.out.println("�޸ĺ󡪡�>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t�о�ԱID: " + researcherID + "\t\t����: " + erEmployeeID + "\t\t�о�Ա����: " + erName+ "\t\t�о�Ա�Ա�: " + erGender
                        +"\t\t�о�Աְ��: " +erTitle+"\t\t�о�Ա����: " +erAge+"\t\t�о�����: " +erResearchDirection+"\t\t�о�Ա�����о���ID: " +erLabID);
                System.out.print("\n\n�Ƿ���Ӹ��о���Ŀ��Ϣ�� [Yes(1) / No(0)] ��");

                isUpdate(sc,researcherID,erEmployeeID,erName,erGender,erTitle,erAge,erResearchDirection,erLabID);
                System.out.println("\n\n\n>�׽���>���ܽ���>�޸��о���Ա��Ϣ\n");
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

    public static void isUpdate(Scanner sc,int erID,int erEmployeeID,String erName,String erGender,String erTitle,int erAge,String erResearchDirection,int erLabID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
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
                    //ִ��
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("�޸��о���Ա��Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺\n");
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
