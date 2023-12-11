package classsystem;

import java.awt.*;
import java.util.Scanner;

public class interfaceclass {
    public static void menu() throws AWTException {
        while (true) {
            System.out.println(">�׽���\n");
            System.out.println("\t*****************************************************************");
            System.out.println("\t                       ��ӭ�������й���ϵͳ!                      ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         1.��ʾ��ر��                         ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         2.���������Ϣ                          ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         3.����������Ϣ                          ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t   ��                    p:�˳�����ϵͳ                           ");
            System.out.println("\t******************************************************************");
            Scanner sc = new Scanner(System.in);
            System.out.print("\n����������ѡ��:");
            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    Extents.clearConsole();
                    showtables();
                    break;
                case "2":
                    Extents.clearConsole();
                    mangeTable();
                    break;
                case "3":
                    Extents.clearConsole();
                    queryTable();
                    break;
                case "p":
                    System.out.println("�˳��ɹ�!");
                    System.exit(0);
                default:
                    Extents.clearConsole();
                    System.out.println("����������\n\n\n");
                    break;
            }
        }
    }

    public static void showtables() throws AWTException {
        String tablename=null;
        while (true) {
            System.out.println(">�׽���>��ر���Ϣ\n");
            System.out.println("\t****************************************************************");
            System.out.println("\t                         ���й�����Ϣһ��                         ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         1.�о��ұ�                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         2.�칫���ر�                            ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         3.���α�                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         4.�����                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         5.������Ա��                            ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         6.������Ŀ��                            ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         7.ί�з���                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         8.��������                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         9.������ⷽ��                          ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         10.�ӿ����                             ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         11.�μ���Ŀ�Ŀ�����Ա��                  ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         12.���гɹ���                           ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         13.ר�����                             ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t   ��     q:�����ϼ��˵�                  p:�˳�����ϵͳ           ");
            System.out.println("\t******************************************************************");
            Scanner sc = new Scanner(System.in);
            System.out.print("\n����������ѡ��:");
            String choose = sc.nextLine();
            tablename=null;
            switch (choose) {
                case "1":
                    tablename="researchlab";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "2":
                    tablename="officelocation";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "3":
                    tablename="Director";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "4":
                    tablename="Secretary";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "5":
                    tablename="Researcher";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "6":
                    tablename="ResearchProject";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "7":
                    tablename="Client";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "8":
                    tablename="Collaborator";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "9":
                    tablename="QualityMonitor";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "10":
                    tablename=" Subtask";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "11":
                    tablename="ResearcherProjectRelation";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "12":
                    tablename="ResearchAchievement";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "13":
                    tablename="Patent ";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "q":
                    Extents.clearConsole();
                    menu();
                    return;
                case "p":
                    System.out.println("�˳��ɹ�!");
                    System.exit(0);
                default:
                    Extents.clearConsole();
                    System.out.println("����������\n\n\n");
                    break;
            }
        }
    }

    public static void queryTable() throws AWTException {
        while (true) {
            System.out.println(">�׽���>����������Ϣ\n");
            System.out.println("\t*****************************************************************");
            System.out.println("\t                         �ɲ�ѯ���һ��                           ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         1.�о���                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         2.������Ŀ                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         3.���гɹ�                             ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         4.������Ա                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         5.�칫����                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         6.ί�з���                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         7.��������                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         8.������ⷽ��                          ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         9.���α�                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         10.�����                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t   ��     q:�����ϼ��˵�                  p:�˳�����ϵͳ          ");
            System.out.println("\t******************************************************************");
            Scanner sc = new Scanner(System.in);
            System.out.print("\n����������ѡ��:");
            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    Extents.clearConsole();
                    ResearchLab.labQuery();
                    break;
                case "2":
                    Extents.clearConsole();
                    ResearchProject.projectQuery();
                    break;
                case "3":
                    Extents.clearConsole();
                    ResearchAchievement.achievementQuery();
                    break;
                case "4":
                    Extents.clearConsole();
                    Researcher.researchQuery();
                    break;
                case "5":
                    Extents.clearConsole();
                    OfficeLocation.OfficeLocationQuery();
                    break;
                case "6":
                    Extents.clearConsole();
                    Client.ClientQuery();
                    break;
                case "7":
                    Extents.clearConsole();
                    Collaborator.CollaboratorQuery();
                    break;
                case "8":
                    Extents.clearConsole();
                    QualityMonitor.QualityMonitorQuery();
                    break;
                case "9":
                    Extents.clearConsole();
                    Director.DirectorQuery();
                    break;
                case "10":
                    Extents.clearConsole();
                    Secretary.SecretaryQuery();
                    break;
                case "q":
                    Extents.clearConsole();
                    menu();
                    return;
                case "p":
                    System.out.println("�˳��ɹ�!");
                    System.exit(0);
                default:
                    Extents.clearConsole();
                    System.out.println("����������\n\n\n");
                    break;
            }
        }
    }
    public static void mangeTable() throws AWTException {
        String waitTableName=null;

        while (true) {
            System.out.println(">�׽���>������ر��\n");
            System.out.println("\t*****************************************************************");
            System.out.println("\t                         �ɹ�����һ��                           ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         1.�о���                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         2.������Ŀ                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         3.���гɹ�                             ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         4.������Ա                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         5.�칫����                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         6.ί�з���                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         7.��������                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         8.������ⷽ��                          ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         9.���α�                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         10.�����                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t   ��     q:�����ϼ��˵�                  p:�˳�����ϵͳ          ");
            System.out.println("\t******************************************************************");
            Scanner sc = new Scanner(System.in);
            System.out.print("\n����������ѡ��:");
            String choose = sc.nextLine();
            waitTableName=null;
            switch (choose) {
                case "1":
                    waitTableName="researchlab";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "2":
                    waitTableName="researchproject";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "3":
                    waitTableName="researchachievement";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "4":
                    waitTableName="researcher";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "5":
                    waitTableName="OfficeLocation";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;

                case "6":
                    waitTableName="Client";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "7":
                    waitTableName="Collaborator";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "8":
                    waitTableName="QualityMonitor";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "9":
                    waitTableName="Director";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "10":
                    waitTableName="Secretary";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "q":
                    Extents.clearConsole();
                    menu();
                    return;
                case "p":
                    System.out.println("�˳��ɹ�!");
                    System.exit(0);
                default:
                    Extents.clearConsole();
                    System.out.println("����������\n\n\n");
                    break;
            }
        }
    }
    public static void FunctionChoose(String waitTableName) throws AWTException {
        while (true) {
            System.out.println(">�׽���>������ر��>����ѡ��\n");
            System.out.println("\t*****************************************************************");
            System.out.println("\t                         ��ִ�й���                            ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         1.���                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         2.�༭                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         3.ɾ��                             ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t   ��     q:�����ϼ��˵�                  p:�˳�����ϵͳ          ");
            System.out.println("\t******************************************************************");
            Scanner sc = new Scanner(System.in);
            System.out.print("\n����������ѡ��:");
            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    Extents.clearConsole();
                    Extents.goToTableAdd(waitTableName);
                    break;
                case "2":
                    Extents.clearConsole();
                    Extents.goToTableEdit(waitTableName);
                    break;
                case "3":
                    Extents.clearConsole();
                    Extents.goToTableDelete(waitTableName);
                    break;
                case "q":
                    Extents.clearConsole();
                    mangeTable();
                    return;
                case "p":
                    System.out.println("�˳��ɹ�!");
                    System.exit(0);
                default:
                    Extents.clearConsole();
                    System.out.println("����������\n\n\n");
                    break;
            }
        }
    }


}
